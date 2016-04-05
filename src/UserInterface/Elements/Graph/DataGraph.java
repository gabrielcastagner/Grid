package UserInterface.Elements.Graph;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import UserInterface.Elements.ColorPalette;

import org.swtchart.*;
import org.swtchart.ISeries.SeriesType;

import PowerModels.Graph.Month;

/**
 * Adds a chart to a composite, plotting versus month Note it will take up the
 * entire composite
 */
public class DataGraph {

	private static Chart chart;
	private static int nextLineColorPtr, nextNodeColorPtr, seriesID;
	private static Color[] plotPalette = ColorPalette.plotPalette;

	// Temporary and arbitrary series to initially plot so user sees a nice plot
	private static final double[] ySeries = { 0.0, 0.38, 0.71, 0.92, 0.6, 0.92, 0.71, 0.38, 0.0, 0.38, 0.71, 0.92,
			1.0 };
	// Centers Nodes for each month
	private static final double[] xSeries = { 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 12.5 };

	/**
	 * create the chart.
	 * 
	 * @param parent
	 *            The parent composite
	 * @param bgColor
	 *            The plot areas color
	 * @return The created chart
	 */
	static public Chart createChart(Composite parent, Color bgColor) {

		String[] xAxisLable = new String[13];
		int ind = 0;
		for (Month m : Month.values()) {
			xAxisLable[ind++] = m.getMonthName();
		}

		// create a chart
		chart = new Chart(parent, SWT.NONE);

		chart.setBackground(bgColor);
		chart.setBackgroundInPlotArea(bgColor);

		// set titles
		chart.getTitle().setText("Output Data");
		chart.getTitle().setForeground(ColorPalette.CUSTOM_BLACK);
		chart.getTitle().setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		// X Axis
		chart.getAxisSet().getXAxis(0).getTitle().setText("Month");
		chart.getAxisSet().getXAxis(0).getTitle().setForeground(ColorPalette.CUSTOM_BLACK);
		chart.getAxisSet().getXAxis(0).getTitle().setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		IAxisTick xTick = chart.getAxisSet().getXAxis(0).getTick();
		xTick.setForeground(ColorPalette.CUSTOM_BLACK);
		xTick.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		// Y axis
		chart.getAxisSet().getYAxis(0).getTitle().setText("Power (watts)");
		chart.getAxisSet().getYAxis(0).getTitle().setForeground(ColorPalette.CUSTOM_BLACK);
		chart.getAxisSet().getYAxis(0).getTitle().setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		IAxisTick yTick = chart.getAxisSet().getYAxis(0).getTick();
		yTick.setForeground(ColorPalette.CUSTOM_BLACK);
		yTick.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));

		// Legend
		chart.getLegend().setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		// set category
		chart.getAxisSet().getXAxis(0).enableCategory(true);
		chart.getAxisSet().getXAxis(0).setCategorySeries(xAxisLable);

		refreshPlot();
		// create scatter series to normalize the plot area before the user can
		// see it, it is arbitrary what is displayed
		addSeries(ySeries);
		refreshPlot();

		return chart;
	}

	/**
	 * Add a series to the chart, note truncated values past 13 inputs
	 * 
	 * @param yValues
	 */
	public static void addSeries(double[] yValues) {
		double[] yPlotable = new double[13];
		for (int i = 0; i < yPlotable.length; i++)
			yPlotable[i] = yValues[i];

		// Get a line color
		LineNodeColorPair p = getNewSeriesColors();
		// create scatter series
		ILineSeries plottedSeries = (ILineSeries) chart.getSeriesSet().createSeries(SeriesType.LINE,
				"Plot:" + Integer.toString(seriesID++));
		plottedSeries.setSymbolColor(p.getNodeColor());
		plottedSeries.setLineColor(p.getLineColor());
		plottedSeries.setLineStyle(LineStyle.SOLID);
		plottedSeries.setLineWidth(1);
		plottedSeries.setXSeries(xSeries);
		plottedSeries.setYSeries(yPlotable);
		// adjust the axis range
		chart.getAxisSet().adjustRange();
	}

	/**
	 * Adds yValues to the Graph
	 * 
	 * @param yValues
	 *            - Double Array of size 13 to be plotted.
	 * @param ID
	 *            - UNIQUE string representation of the plotted data!
	 */
	public static void addSeries(double[] yValues, String ID) {
		double[] yPlotable = new double[13];
		for (int i = 0; i < yPlotable.length; i++)
			yPlotable[i] = yValues[i];

		// Get a line color
		LineNodeColorPair p = getNewSeriesColors();
		// create scatter series
		ILineSeries plottedSeries = (ILineSeries) chart.getSeriesSet().createSeries(SeriesType.LINE, ID);
		plottedSeries.setSymbolColor(p.getNodeColor());
		plottedSeries.setLineColor(p.getLineColor());
		plottedSeries.setLineStyle(LineStyle.SOLID);
		plottedSeries.setLineWidth(1);
		plottedSeries.setXSeries(xSeries);
		plottedSeries.setYSeries(yPlotable);
		// adjust the axis range
		chart.getAxisSet().adjustRange();
	}

	/**
	 * @return Node and Line color pairing from ColorPalette.plotPalette
	 */
	public static LineNodeColorPair getNewSeriesColors() {
		LineNodeColorPair p = new LineNodeColorPair(plotPalette[nextLineColorPtr++], plotPalette[nextNodeColorPtr]);
		// Check pointers to the next colors to be used
		if (nextLineColorPtr % plotPalette.length == 0) {
			nextLineColorPtr = 0;
			nextNodeColorPtr += 1;
			if (nextNodeColorPtr % plotPalette.length == 0)
				nextNodeColorPtr = 0;
		}

		return p;
	}

	/**
	 * Needs To be called when the data is analyzed such that new data may be
	 * shown
	 */
	public static void refreshPlot() {
		nextLineColorPtr = 0;
		nextNodeColorPtr = 0;
		seriesID = 0;
		
		for (ISeries s : chart.getSeriesSet().getSeries()) {
			chart.getSeriesSet().deleteSeries(s.getId());
		}
	}

}
