package com.af.model;

public class AfPoint {
	
	public double x;
	public double y;
	
	public AfPoint() {
		super();
	}

	public AfPoint(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object o) {
		AfPoint point = (AfPoint)o;
		if(this.x == point.x && this.y == point.y){
			return true;   
		}
        return false;   
	}

	@Override
	public int hashCode() {
		return (int)(x*y)^8;
	}
	
	
}
