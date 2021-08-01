package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import calculations.Calc;
import entities.Block3D;
import setup.Manager;
import setup.Window;

public class Main implements Manager {

	public static final int Width = 500, Height = 500;

	public static final int aX = 45, aY = -45;

	int count = 0;

	Block3D bl = new Block3D(0, 0, 0, aX, aY);
	
	int sx = 100, sy = 0, sz = -350;

	ArrayList<Block3D> b = new ArrayList<Block3D>();

	private BufferedImage grid = new BufferedImage(Width, Height,
			BufferedImage.TYPE_4BYTE_ABGR);

	public Main() {
		drawGrid();
		/*for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				b.add(new Block3D(i * 80 + sx, 0, j * 80 + sz, aX, aY));
			}
		}*/
		bl.output("block");
	}

	@Override
	public void tick() {

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Width, Height);
		g.drawImage(grid, 0, 0, null);
		for (Block3D d : b) {
			d.draw(g);
		}
	}

	private void drawGrid() {
		Graphics2D g = grid.createGraphics();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		((Graphics2D) g).setRenderingHints(rh);
		g.setColor(new Color(70, 255, 0, 75));
		for (int i = 0; i < 1000; i += 40) {
			int[] c = Calc.getD(0, 0, i - 400, aX, aY);
			int[] c1 = Calc.getD(1000, 0, i - 400, aX, aY);
			g.drawLine(c[0], c[1], c1[0], c1[1]);
		}
		for (int i = 0; i < 1000; i += 40) {
			int[] c = Calc.getD(i, 0, -400, aX, aY);
			int[] c1 = Calc.getD(i, 0, 400, aX, aY);
			g.drawLine(c[0], c[1], c1[0], c1[1]);
		}
		g.dispose();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Window window = new Window(Width, Height, 60, "3D", new Main());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyPressed(int e) {

	}

	@Override
	public void keyReleased(int e) {

	}

	@Override
	public void keyTyped(int e) {

	}
}
