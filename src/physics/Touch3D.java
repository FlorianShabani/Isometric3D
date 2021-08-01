package physics;

import entities3D.Entity3D;

public class Touch3D {
	public static boolean touching(Entity3D e, Entity3D b) {

		if (((e.getX() >= b.getX() && e.getX() <= b.getX() + b.getWidth())
				|| e.getX() + e.getWidth() >= b.getX() && e.getX() + e.getWidth() <= b.getX() + b.getWidth())
				&& ((e.getY() >= b.getY() && e.getY() <= b.getY() + b.getHeight())
						|| e.getY() + e.getHeight() >= b.getY() && e.getY() + e.getHeight() <= b.getY() + b.getHeight())
				&& ((e.getZ() >= b.getZ() && e.getZ() <= b.getZ() + b.getDepth())
						|| e.getZ() + e.getDepth() >= b.getZ() && e.getZ() + e.getDepth() <= b.getZ() + b.getDepth()))
		{
			return true;
		}

		return false;
	}
}
