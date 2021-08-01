package calculations;

public class Calc {
	public static int[] getD(double x, double y, double z, double aX,
			double aY) {
		double d;
		double angle;
		if (aX != 0) {
			d = Math.sqrt((x * x) + (z * z));

			angle = Math.atan2(x, z);

			angle = Math.toDegrees(angle);

			angle -= aX;

			angle = Math.toRadians(angle);

			x = Math.cos(angle) * d;
			z = Math.sin(angle) * d;
		}
		if (aY != 0) {
			d = Math.sqrt((y * y) + (z * z));

			angle = Math.atan2(y, z);

			angle = Math.toDegrees(angle);

			angle -= aY;

			angle = Math.toRadians(angle);

			y = Math.sin(angle) * d;
			z = Math.cos(angle) * d;
		}
		return new int[] { (int) (x), (int) (y) };
	}

	public static int lowest(int[] arr) {
		int n = 1000000;
		for (int i : arr) {
			if (n > i)
				n = i;
		}
		return n;
	}

	public static int highest(int[] arr) {
		int n = 0;
		for (int i : arr) {
			if (n < i)
				n = i;
		}
		return n;
	}

	public static double checkdist(double x, double y, double z, double dx,
			double dy, double dz) {
		double d1 = ((x - dx) * (x - dx)) + ((z - dz) * (z - dz));

		double d = Math.sqrt(d1 + ((y - dy) * (y - dy)));

		return d;
	}

	public static double[] rotateX(double[] p, double[] m, double a) {
		double d = Math.sqrt(((p[0] - m[0]) * (p[0] - m[0]))
				+ ((p[2] - m[2]) * (p[2] - m[2])));
		double ax = Math.toRadians(Math.toDegrees(Math.atan2(p[2] - m[2], p[0] - m[0])) + a);
		return new double[] { ((Math.cos(ax) * d) + m[0]),
				((Math.sin(ax) * d) + m[2]) };
	}

	public static double[] rotateY(double[] p, double[] m, double a) {
		double d = Math.sqrt(((p[1] - m[1]) * (p[1] - m[1]))
				+ ((p[2] - m[2]) * (p[2] - m[2])));
		double ay = Math.toRadians(Math.toDegrees(Math.atan2(p[1] - m[1], p[2] - m[2])) + a);
		return new double[] { ((Math.sin(ay) * d) + m[1]),
				((Math.cos(ay) * d) + m[2]) };
	}
}
