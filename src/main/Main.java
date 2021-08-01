package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import entities3D.Block3D;
import entities3D.Entity3D;
import setup.Manager;
import setup.Window;

public class Main implements Manager {

	long time = System.nanoTime();
	int count = 0;

	public static int Width = 1200, Height = 500;

	public static int ix = Width / 2, iy = 250, iz = 550;

	List<Entity3D> blocks = new ArrayList<Entity3D>();
	Entity3D ent = new Entity3D(0, Height , 0, 600, 100, 10000, ix, iy, iz, 230, 100, 20);
	Entity3D ent2 = new Entity3D(0, Height - 500, 0, 600, 100, 10000, ix, iy, iz, 230, 100, 20);

	public Main() {

		blocks.add(ent);
		blocks.add(ent2);
		for (int i = 10; i >= 0; i--) {
			blocks.add(new Block3D(-99, Height - 100, i * 1000, ix, iy, iz));
			blocks.add(new Block3D(600, Height - 100, i * 1000, ix, iy, iz));
		}
	}

	@Override
	public void tick() {
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Width, Height);
		for (Entity3D e : blocks) {
			e.draw(g);
		}
	}

	@Override
	public void keyPressed(int k) {
		for (Entity3D e : blocks) {
			e.updateGZ(ix, iy, iz);
			e.updateGX(ix, iy, iz);
		}
		switch (k) {
		case 87:
			for (Entity3D e : blocks) {
				e.update(0, 0, 10);
			}
			break;
		case 83:
			for (Entity3D e : blocks) {
				e.update(0, 0, -10);
			}
			break;
		case 65:
			for (Entity3D e : blocks) {
				e.update(-10, 0, 0);
			}
			break;
		case 68:
			for (Entity3D e : blocks) {
				e.update(10, 0, 0);
			}
			break;
		case 16:
			for (Entity3D e : blocks) {
				e.update(0, 10, 0);
			}
			break;
		case 32:
			for (Entity3D e : blocks) {
				e.update(0, -10, 0);
			}
			break;
		}
	}

	@Override
	public void keyReleased(int k) {

	}

	public static void main(String[] args) {
		Window wind = new Window(Width, Height, 60, 60, "Cool3D", new Main());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(int e) {
		// TODO Auto-generated method stub

	}

}
