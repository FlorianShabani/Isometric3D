package entities3D;

import java.awt.Color;
import java.awt.Graphics;

// 2 Vanishing Points 3D;

public class Entity3D {

	private double x, y, z;
	private int width, height, depth;

	double[][] points;

	private int r, gr, b;

	private int dx, dy, dz;

	double[][] dP;
	int[] xs1;
	int[] xs2;
	int[] xs3;
	int[] xs4;
	int[] xs5;
	int[] xs6;

	int[] ys1;
	int[] ys2;
	int[] ys3;
	int[] ys4;
	int[] ys5;
	int[] ys6;

	public Entity3D(int ix, int iy, int iz, int width, int height, int depth, int x, int y, int d, int r, int gr,
			int b) {
		this.x = ix;
		this.y = iy;
		this.z = iz;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.r = r;
		this.gr = gr;
		this.b = b;
		dx = x;
		dy = y;
		dz = d;

		points = new double[][] { { ix, iy, iz }, { ix + width, iy, iz }, { ix + width, iy + height, iz },
				{ ix, iy + height, iz }, { ix, iy, iz + depth }, { ix + width, iy, iz + depth },
				{ ix + width, iy + height, iz + depth }, { ix, iy + height, iz + depth } };

		dP = points.clone();
		updateGZ(x, y, d);
		updateGX(x, y, d);
	}

	public Entity3D(int ix, int iy, int iz, int width, int height, int depth, int r, int gr, int b) {
		this.x = ix;
		this.y = iy;
		this.z = iz;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.r = r;
		this.gr = gr;
		this.b = b;

		points = new double[][] { { ix, iy, iz }, { ix + width, iy, iz }, { ix + width, iy + height, iz },
				{ ix, iy + height, iz }, { ix, iy, iz + depth }, { ix + width, iy, iz + depth },
				{ ix + width, iy + height, iz + depth }, { ix, iy + height, iz + depth } };

		dP = points.clone();
	}

	public void draw(Graphics g) {
		// Graphics2D g2d = (Graphics2D) g;

		// g2d.setStroke(new BasicStroke(0.7F));)

		g.setColor(new Color(r, gr, b));
		if (dP[7][2] >= 0) {
			g.fillPolygon(xs6, ys6, 4);
		}

		g.setColor(new Color(r - 20, gr - 20, b));
		if (points[5][0] > 300) {
			if (dP[7][2] >= 0) {
				g.fillPolygon(xs3, ys3, 4);
			}
		} else {
			if (dP[5][2] >= 0) {
				g.fillPolygon(xs2, ys2, 4);
			}
		}

		if (points[2][1] > 300) {
			g.setColor(new Color(r + 20, gr + 20, b + 20));
			if (dP[5][2] >= 0) {
				g.fillPolygon(xs5, ys5, 4);
			}
		} else {
			g.setColor(new Color(r - 20, gr - 20, b - 20));
			if (dP[6][2] >= 0) {
				g.fillPolygon(xs4, ys4, 4);
			}
		}

		g.setColor(new Color(r, gr, b));
		if (dP[0][2] >= 0) {
			g.fillPolygon(xs1, ys1, 4);
		}
	}

	public void updateGX(int x, int y, int d) {
		for (int i = 0; i < points.length; i++) {
			// Z POINT
			int z = d;
			double[] p = dP[i];
			double px = p[0];
			double py = p[1];
			double pz = p[2];

			py -= y;
			px -= x;

			double aX = Math.atan2(px, z + pz);

			px -= (Math.tan(aX) * (z + pz)) - (Math.tan(aX) * (z));

			double aY = Math.atan2(py, z + pz);

			py -= (Math.tan(aY) * (z + pz)) - (Math.tan(aY) * (z));

			px += x;
			py += y;

			dP[i] = new double[] { px, py, pz };
		}
		xs1 = new int[] { (int) dP[0][0], (int) dP[1][0], (int) dP[2][0], (int) dP[3][0] };
		ys1 = new int[] { (int) dP[0][1], (int) dP[1][1], (int) dP[2][1], (int) dP[3][1] };
		xs2 = new int[] { (int) dP[1][0], (int) dP[2][0], (int) dP[6][0], (int) dP[5][0] };
		ys2 = new int[] { (int) dP[1][1], (int) dP[2][1], (int) dP[6][1], (int) dP[5][1] };
		xs3 = new int[] { (int) dP[0][0], (int) dP[3][0], (int) dP[7][0], (int) dP[4][0] };
		ys3 = new int[] { (int) dP[0][1], (int) dP[3][1], (int) dP[7][1], (int) dP[4][1] };
		xs4 = new int[] { (int) dP[3][0], (int) dP[2][0], (int) dP[6][0], (int) dP[7][0] };
		ys4 = new int[] { (int) dP[3][1], (int) dP[2][1], (int) dP[6][1], (int) dP[7][1] };
		xs5 = new int[] { (int) dP[0][0], (int) dP[1][0], (int) dP[5][0], (int) dP[4][0] };
		ys5 = new int[] { (int) dP[0][1], (int) dP[1][1], (int) dP[5][1], (int) dP[4][1] };
		xs6 = new int[] { (int) dP[4][0], (int) dP[5][0], (int) dP[6][0], (int) dP[7][0] };
		ys6 = new int[] { (int) dP[4][1], (int) dP[5][1], (int) dP[6][1], (int) dP[7][1] };
	}

	public void updateGZ(int x, int y, int d) {
		for (int i = 0; i < points.length; i++) {
			int dx = d * 15;
			double[] p = dP[i];
			double px = p[0];
			double py = p[1];
			double pz = p[2];

			px -= x;

			if (px <= 0) {
				dx *= -1;
			}

			py -= y;

			double aY = Math.atan2(py, dx + px);

			py -= Math.tan(aY) * (dx + px) - Math.tan(aY) * (dx);

			py += y;
			px += x;

			dP[i] = new double[] { px, py, pz };
		}
		xs1 = new int[] { (int) dP[0][0], (int) dP[1][0], (int) dP[2][0], (int) dP[3][0] };

		ys1 = new int[] { (int) dP[0][1], (int) dP[1][1], (int) dP[2][1], (int) dP[3][1] };

		xs2 = new int[] { (int) dP[1][0], (int) dP[2][0], (int) dP[6][0], (int) dP[5][0] };

		ys2 = new int[] { (int) dP[1][1], (int) dP[2][1], (int) dP[6][1], (int) dP[5][1] };

		xs3 = new int[] { (int) dP[0][0], (int) dP[3][0], (int) dP[7][0], (int) dP[4][0] };

		ys3 = new int[] { (int) dP[0][1], (int) dP[3][1], (int) dP[7][1], (int) dP[4][1] };

		xs4 = new int[] { (int) dP[3][0], (int) dP[2][0], (int) dP[6][0], (int) dP[7][0] };

		ys4 = new int[] { (int) dP[3][1], (int) dP[2][1], (int) dP[6][1], (int) dP[7][1] };

		xs5 = new int[] { (int) dP[0][0], (int) dP[1][0], (int) dP[5][0], (int) dP[4][0] };

		ys5 = new int[] { (int) dP[0][1], (int) dP[1][1], (int) dP[5][1], (int) dP[4][1] };

		xs6 = new int[] { (int) dP[4][0], (int) dP[5][0], (int) dP[6][0], (int) dP[7][0] };

		ys6 = new int[] { (int) dP[4][1], (int) dP[5][1], (int) dP[6][1], (int) dP[7][1] };
	}

	public void update(int x, int y, int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;

		points = new double[][] { { this.x, this.y, this.z }, { this.x + width, this.y, this.z },
				{ this.x + width, this.y + height, this.z }, { this.x, this.y + height, this.z },
				{ this.x, this.y, this.z + depth }, { this.x + width, this.y, this.z + depth },
				{ this.x + width, this.y + height, this.z + depth }, { this.x, this.y + height, this.z + depth } };

		dP = points.clone();
		updateGZ(dx, dy, dz);
		updateGX(dx, dy, dz);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
