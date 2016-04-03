package UserInterface.Elements.Graph;

import org.eclipse.swt.graphics.Color;

public class LineNodeColorPair {
	private final Color lineColor;
	private final Color nodeColor;
	
	public LineNodeColorPair(Color lineColor, Color nodeColor){
		this.lineColor = lineColor;
		this.nodeColor = nodeColor;
	}
	
	public Color getLineColor() {
		return lineColor;
	}

	public Color getNodeColor() {
		return nodeColor;
	}

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
