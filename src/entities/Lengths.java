package entities;

public enum Lengths {
	BlockWidth(40),
	BlockHeight(-40),
	BlockDepth(40);
	
	double n;
	Lengths(double n) {
		this.n = n;
	}
	
}
