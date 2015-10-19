package br.com.jeffque.neural.sample;

public class TrainingSample extends Sample {
	private double expected;
	
	public TrainingSample(double[] values, double expected) {
		super(values);
		
		this.expected = expected;
	}
	
	public double getExpected() {
		return expected;
	}
	
	public double error(double calculated) {
		return expected - calculated;
	}

}
