package br.com.jeffque.neural.utils.activator;

import br.com.jeffque.neural.utils.Function;

public abstract class NonDifferentiableFunction implements Function {

	@Override
	public double dy(double x) {
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + " is not differentiable at " + x);
	}

}
