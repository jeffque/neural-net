package br.com.jeffque.neural;

import br.com.jeffque.neural.sample.TrainingSample;
import br.com.jeffque.neural.sample.TrainingSamplePool;
import br.com.jeffque.neural.utils.Context;
import br.com.jeffque.neural.utils.Function;
import br.com.jeffque.neural.utils.NeuralUtils;
import br.com.jeffque.neural.utils.TrainingFactorYielder;

public class Perceptron {
	private double[] weights;
	private double bias;
	private Function activator;

	public Perceptron(int inputs, Function activator, Context context) {
		this(NeuralUtils.randomizedWeigths(inputs, context), NeuralUtils.random(context), activator);
	}
	
	public Perceptron(double[] weights, double bias, Function activator) {
		this.weights = weights;
		this.bias = bias;
		this.activator = activator;
	}
	
	public int getInputSize() {
		return weights.length;
	}
	
	public double judgeInput(double[] input) {
		return activator.y(NeuralUtils.dotProduct(input, weights) - bias);
	}
	
	private boolean trainingCore(TrainingFactorYielder trainingFactor, TrainingSamplePool pool, double acceptance, int age) {
		boolean existsError = false;
		for (TrainingSample sample: pool) {
			double[] input = sample.getValues();
			double y = judgeInput(input);
			double error = sample.error(y);
			
			if (Math.abs(error) > acceptance) {
				existsError = true;
				double aleph = trainingFactor.yieldAleph(age);
				
				for (int i = weights.length - 1; i >= 0; i--) {
					weights[i] = weights[i] + aleph *(error) * input[i];
				}
			}
		}
		
		return existsError;
	}
	
	public int training(TrainingFactorYielder trainingFactor, TrainingSamplePool pool, double acceptance, int ageLimit) {
		int age = 0;
		
		while (trainingCore(trainingFactor, pool, acceptance, age) && age < ageLimit) {
			age++;
		}
		
		return age + 1 /* The last run isn't accounted */;
	}
	
	public int training(TrainingFactorYielder trainingFactor, TrainingSamplePool pool, double acceptance) {
		int age = 0;
		
		while (trainingCore(trainingFactor, pool, acceptance, age)) {
			age++;
		}
		
		return age + 1 /* The last run isn't accounted */;
	}

	/**
	 * 
	 * @param pool
	 * @param acceptance
	 * @return How many samples from the sample are out of acceptance
	 */
	public int validation(TrainingSamplePool pool, double acceptance) {
		int nErrors = 0;
		for (TrainingSample sample: pool) {
			double[] input = sample.getValues();
			double y = judgeInput(input);
			double error = sample.error(y);
			
			if (Math.abs(error) > acceptance) {
				nErrors++;
			}
		}
		return nErrors;
	}

	public boolean isPoolValid(TrainingSamplePool pool) {
		return pool.isValid() && pool.getSample(0).getSize() == getInputSize();
	}
	
}
