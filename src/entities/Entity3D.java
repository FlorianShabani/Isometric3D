package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import calculations.Calc;

public abstract class Entity3D {
	// 3D points and shapes
	protected double[][] points;
	protected int[][] areas;

	// 2D points and shapes
	protected int[][] drawP;
	protected int[][][] shapes;

	public double x, y, z;
	protected int dx, dy;
	protected double width, height, depth;
	protected double[] midP = new double[3];
	protected double velX = 0, velY = 0, velZ = 2;

	// camera angle
	protected double aX, aY;
	// buff image stuff
	protected int lx, ly, mx, my;
	// the actual rotated angle
	protected double angleX = 0, angleY = 0;

	protected int[] faces = new int[3];

	// Draw everything in a BufferedImage so whenever we call draw we just use
	// the image
	protected BufferedImage img;

	public Entity3D(double[][] points, int[][] areas, double x, double y,
			double z, double width, double height, double depth, double aX,
			double aY) {
		this.points = points;
		this.areas = areas;
		shapes = new int[areas.length][2][10];
		drawP = new int[this.points.length][3];
		this.aX = aX;
		this.aY = aY;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
		midP = new double[] { width / 2, height / 2, depth / 2 };
		checkFaces();
		updateG();
		render();
	}

	public void updateG() {
		// Convert 3D points to 2D and store in drawP

		for (int i = 0; i < points.length; i++) {
			double[] p = points[i];

			int[] pos = Calc.getD(p[0], p[1], p[2], aX, aY);

			// System.out.println(pos[0]);

			drawP[i][0] = pos[0];
			drawP[i][1] = pos[1];
		}

		// Make every point a positive one so everything is within the image
		ArrayList<Integer> joinedx = new ArrayList<Integer>();
		ArrayList<Integer> joinedy = new ArrayList<Integer>();

		for (int[] i : drawP) {
			joinedx.add(i[0]);
			joinedy.add(i[1]);
		}

		int[] xs = joinedx.stream().mapToInt(i -> i).toArray();
		int[] ys = joinedy.stream().mapToInt(i -> i).toArray();

		lx = Calc.lowest(xs);
		ly = Calc.lowest(ys);

		mx = Calc.highest(xs);
		my = Calc.highest(ys);
		// till here

		int[] c = Calc.getD(x, y, z, aX, aY);

		dx = lx + c[0];
		dy = my + c[1] - (my - ly);

		img = new BufferedImage(mx - lx, my - ly, BufferedImage.TYPE_INT_ARGB);

		// Convert the areas points(3D) to drawP(2D)
		for (int i = 0; i < areas.length; i++) {
			int[] area = areas[i];
			int[][] shape = new int[2][area.length];
			for (int j = 0; j < area.length; j++) {
				int n = area[j];
				// substract lowest x and lowest y so the lowest point is zero
				shape[0][j] = drawP[n][0] - lx;
				shape[1][j] = drawP[n][1] - ly;
			}
			shapes[i] = shape;
		}
	}

	public void render() {
		// Draw the shapes with different color(needs work) into BufferedImage
		Graphics2D g = img.createGraphics();

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		((Graphics2D) g).setRenderingHints(rh);

		int c = 50;

		for (int i = 0; i < shapes.length; i++) {
			if (faces[i / 2] == i) {
				g.setColor(new Color(c, c, c));
				g.fillPolygon(shapes[i][0], shapes[i][1], shapes[i][0].length);
			}
		}

		/*
		 * for(int[][] i : shapes) { for(int[] j : i) { for(int k : j) {
		 * System.out.print(k); } System.out.println(); } }
		 */
		g.dispose();
	}

	public void rotate(double aX1, double aY1) {
		midP = new double[] { width / 2, height / 2, depth / 2 };
		angleX += aX1;
		angleY += aY1;
		angleX = angleX % 360;
		angleY = angleY % 360;
		for (int i = 0; i < points.length; i++) {
			double[] p = points[i];
			double[] rx = Calc.rotateX(p, midP, aX1);
			p[0] = rx[0];
			p[2] = rx[1];
			double[] ry = Calc.rotateY(p, midP, aY1);
			p[1] = ry[0];
			p[2] = ry[1];
			points[i] = p;
		}
		checkFaces();
		updateG();
		render();
	}

	public abstract void checkFaces();

	public void reshape() {
	};

	public void output(String file) {
		File f = new File(System.getProperty("user.home") + "/Desktop/" + file + ".png");
		try {
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		x += velX;
		y += velY;
		z += velZ;
	}

	public void draw(Graphics g) {
		g.drawImage(img, (int) dx, (int) dy, null);
	}
}
