package br.com.jeffque.neural.activator;

public class SymmetricHardLimiter extends NonDifferentiableFunction {

	@Override
	public double y(double x) {
		if (x > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
