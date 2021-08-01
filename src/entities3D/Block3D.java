package entities3D;

public class Block3D extends Entity3D{

	private static int r = 235, gr = 110, b = 20;
	
	private static int width = 100, height = 100, depth = 1000;
	
	public Block3D(int ix, int iy, int iz, int x, int y, int d) {
		super(ix, iy, iz, width, height, depth, x, y, d, r, gr, b);
	}
}
