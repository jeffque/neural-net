package br.com.jeffque.neural.utils;

public class Context {
	private double min;
	private double max;

	public Context(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
}
