package UserInterface.Elements.Graph;

import org.eclipse.swt.graphics.Color;

/**
 * ADT for Pairs of colors to plot on the chart
 */
public class LineNodeColorPair {
	private final Color lineColor;
	private final Color nodeColor;
	
	/**
	 * Creates a line color and node color pairing
	 * @param lineColor color of the line
	 * @param nodeColor color of the node
	 */
	public LineNodeColorPair(Color lineColor, Color nodeColor){
		this.lineColor = lineColor;
		this.nodeColor = nodeColor;
	}
	
	/**
	 * @return the color of the line
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * @return the color of the node
	 */
	public Color getNodeColor() {
		return nodeColor;
	}

	/**
	 * Tests line-node color pair equalities
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LineNodeColorPair){
			LineNodeColorPair p = (LineNodeColorPair) obj;
			if(this.getLineColor().equals(p.getLineColor()) && this.getNodeColor().equals(p.getNodeColor()))
				return true;
		}
		return false;
	}
}
