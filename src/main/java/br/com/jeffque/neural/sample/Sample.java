package br.com.jeffque.neural.sample;

public class Sample {
	private int size;
	private double[] values;
	
	public Sample(double[] values) {
		this.size = values.length;
		this.values = values;
	}
	
	public int getSize() {
		return size;
	}
	
	public double[] getValues() {
		return values;
	}
}
