package br.com.jeffque.neural;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jeffque.neural.activator.HardLimiter;
import br.com.jeffque.neural.activator.SymmetricHardLimiter;
import br.com.jeffque.neural.sample.TrainingSample;
import br.com.jeffque.neural.sample.TrainingSamplePool;
import br.com.jeffque.neural.utils.TrainingFactorYielder;

public class PerceptronAnd {
	
	private static TrainingSamplePool poolSymmetricHardLimit;
	private static TrainingSamplePool poolHardLimit;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		poolSymmetricHardLimit = new TrainingSamplePool();
		TrainingSample sampleSTT = new TrainingSample(new double[] { 1.0,  1.0},  1.0);
		TrainingSample sampleSTF = new TrainingSample(new double[] { 1.0, -1.0}, -1.0);
		TrainingSample sampleSFF = new TrainingSample(new double[] {-1.0, -1.0}, -1.0);
		TrainingSample sampleSFT = new TrainingSample(new double[] {-1.0,  1.0}, -1.0);
		
		poolSymmetricHardLimit.addSample(sampleSTT);
		poolSymmetricHardLimit.addSample(sampleSFF);
		poolSymmetricHardLimit.addSample(sampleSTF);
		poolSymmetricHardLimit.addValidation(sampleSFT);
		
		poolHardLimit = new TrainingSamplePool();
		TrainingSample sampleTT = new TrainingSample(new double[] {1.0, 1.0}, 1.0);
		TrainingSample sampleTF = new TrainingSample(new double[] {1.0, 0.0}, 0.0);
		TrainingSample sampleFF = new TrainingSample(new double[] {0.0, 0.0}, 0.0);
		TrainingSample sampleFT = new TrainingSample(new double[] {0.0, 1.0}, 0.0);
		
		poolHardLimit.addSample(sampleTT);
		poolHardLimit.addSample(sampleFF);
		poolHardLimit.addSample(sampleTF);
		poolHardLimit.addValidation(sampleFT);
	}
	
	private void testTraining(Perceptron perceptronAnd, TrainingSamplePool pool) {
		pool.setUseTrainingPool(true);
		pool.setUseValidationPool(false);
		@SuppressWarnings("unused")
		int trainingAge = perceptronAnd.training(new TrainingFactorYielder() {
			
			@Override
			public double yieldAleph(int trainingAge) {
				return 0.1;
			}
		}, pool, 0.01);
		
		pool.setUseValidationPool(true);
		assertThat(perceptronAnd.validation(pool, 0.01), equalTo(0));
	}
	
	@Test
	public void testTrainingSymetricHardLimit() {
		Perceptron perceptronAnd = new Perceptron(new double[] {2.0, 0.2}, 0.25, new SymmetricHardLimiter());
		testTraining(perceptronAnd, poolSymmetricHardLimit);
	}
	
	@Test
	public void testTrainingHardLimit() {
		Perceptron perceptronAnd = new Perceptron(new double[] {2.0, 0.2}, 0.25, new HardLimiter());
		testTraining(perceptronAnd, poolHardLimit);
	}

}
