package entities;

import java.awt.image.BufferedImage;

public abstract class CustomBlock extends Entity3D{

	private static double[][] points = new double[0][0];
	private static int[][] areas = new int[0][0];
	
	public CustomBlock(BufferedImage[] images, double x, double y,
			double z, double width, double height, double depth, double aX,
			double aY) {
		super(points, areas, x, y, z, width, height, depth, aX, aY);
	}

	
	
	
	@Override
	public void rotate(double aX1, double aY1) {
		
	}
	@Override
	public void checkFaces() {}
	
	@Override
	public void updateG() {}
	
	@Override
	public void render() {}
	
	
}
