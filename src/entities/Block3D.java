package entities;

import calculations.Calc;

public class Block3D extends Entity3D {

	// What areas will latter be filled(Specified with points
	private static int[][] areas = { { 1, 2, 6, 5 }, { 0, 4, 7, 3 },
			{ 1, 5, 4, 0 }, { 2, 6, 7, 3 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } };

	// 3D points from which the object will be constructed

	public Block3D(int x, int y, int z, double aX, double aY) {
		super(Points.BlockPoints.points, areas, x, y, z, Lengths.BlockWidth.n,
				Lengths.BlockHeight.n, Lengths.BlockDepth.n, aX, aY);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void checkFaces() {
		int[] face = new int[3];
		double[] d = new double[] {width/2, height/2, 0};
		double[] b = Calc.rotateY(d,  midP, -aY);
		d[1] = b[0];
		d[2] = b[1];
		double[] a = Calc.rotateX(d,  midP,  aX);
		d[0] = a[0];
		d[2] = a[1];
		
		
		for (int i = 0; i < areas.length; i += 2) {
			double d1 = Calc.checkdist(points[areas[i][0]][0],
					points[areas[i][0]][1], points[areas[i][0]][2], d[0], d[1],
					d[2]);
			double d2 = Calc.checkdist(points[areas[i + 1][0]][0],
					points[areas[i + 1][0]][1], points[areas[i + 1][0]][2],
					d[0], d[1], d[2]);
			if (d1 < d2)
				face[i / 2] = i;
			else
				face[i / 2] = i + 1;
		}
		faces = face;
	}

	public void reshape(double x, double y, double z) {
		points = Points.BlockPoints.points;

		width += x;
		height += y;
		depth += z;
		this.x -= x/2;
		this.y -= y/2;
		this.z -= z/2;

		double[] k = { width, height, depth };

		for (int i = 0; i < points.length; i++) {
			double[] p = points[i];
			for (int c = 0; c < 3; c++) {
				if ((int) p[c] != 0)
					p[c] = k[c];
			}
		}
		rotate(angleX, angleY);
	}
}
