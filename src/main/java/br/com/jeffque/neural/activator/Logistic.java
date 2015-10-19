package br.com.jeffque.neural.activator;

import static java.lang.Math.E;
import static java.lang.Math.pow;

import br.com.jeffque.neural.utils.Function;

public class Logistic implements Function {

	double beta;
	
	public Logistic(double beta) {
		this.beta = beta;
	}
	
	@Override
	public double y(double x) {
		return 1/(1+pow(E,-beta*x));
	}

	@Override
	public double dy(double x) {
		double factor = pow(E,-beta*x);
		double den = 1 + factor;
		return beta*factor/(den*den);
	}

}
