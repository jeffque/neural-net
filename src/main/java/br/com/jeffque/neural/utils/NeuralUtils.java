package br.com.jeffque.neural.utils;

import java.util.Random;

public class NeuralUtils {
	private NeuralUtils() {}
	private static Random random = new Random();
	
	public static void setRandom(Random random) {
		NeuralUtils.random = random;
		
		if (random == null) {
			throw new NullPointerException("argument cannot be null");
		}
	}
	
	public static double[] randomizedWeigths(int n, double rMin, double rMax) {
		double[] r = new double[n];
		
		for (int i = n - 1; i >= 0; i--) {
			r[i] = random.nextDouble() * (rMax - rMin) + rMin;
		}
		
		return r;
	}
	
	public static double random(double rMin, double rMax) {
		return random.nextDouble() * (rMax - rMin) + rMin;
	}

	public static double dotProduct(double[] a, double[] b) {
		double v = a[0]*b[0];
		
		for (int i = a.length - 1; i >= 1; i--) {
			v += a[i]*b[i];
		}
		
		return v;
	}

	public static double[] randomizedWeigths(int inputs, Context context) {
		return randomizedWeigths(inputs, context.getMin(), context.getMax());
	}

	public static double random(Context context) {
		return random(context.getMin(), context.getMax());
	}
}
