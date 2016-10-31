package com.af.model;

public class AfCircle {
	
	public AfPoint point;
	public double r;
	
	public AfCircle() {
		super();
	}

	public AfCircle(AfPoint point, double r) {
		super();
		this.point = point;
		this.r = r;
	}

	@Override
	public String toString() {
		return "(" + point.x + "," + point.y + "),r="+r;
	}

}
