package entities;

public enum Points {
	BlockPoints(new double[][]{ { 0, 0, 0 },
			{ Lengths.BlockWidth.n, 0, 0 }, { Lengths.BlockWidth.n, Lengths.BlockHeight.n, 0 }, { 0, Lengths.BlockHeight.n, 0 },
			{ 0, 0, Lengths.BlockDepth.n }, { Lengths.BlockWidth.n, 0, Lengths.BlockDepth.n }, { Lengths.BlockWidth.n, Lengths.BlockHeight.n, Lengths.BlockDepth.n },
			{ 0, Lengths.BlockHeight.n, Lengths.BlockDepth.n } });
	
	public double[][] points;
	
	Points(double[][] d) {
		points = d;
	}
}
