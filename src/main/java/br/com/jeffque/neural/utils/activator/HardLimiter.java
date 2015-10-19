package br.com.jeffque.neural.utils.activator;

public class HardLimiter extends NonDifferentiableFunction {

	@Override
	public double y(double x) {
		if (x >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
