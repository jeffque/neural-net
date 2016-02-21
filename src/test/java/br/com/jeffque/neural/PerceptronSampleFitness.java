package br.com.jeffque.neural;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jeffque.neural.activator.HardLimiter;
import br.com.jeffque.neural.sample.TrainingSample;
import br.com.jeffque.neural.sample.TrainingSamplePool;

public class PerceptronSampleFitness {
	
	private static TrainingSamplePool poolConstantSize2;
	private static TrainingSamplePool poolConstantSize3;
	private static TrainingSamplePool poolWTFSize;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		poolConstantSize2 = new TrainingSamplePool();
		TrainingSample sampleSTT = new TrainingSample(new double[] { 1.0,  1.0},  1.0);
		TrainingSample sampleSTF = new TrainingSample(new double[] { 1.0, -1.0}, -1.0);
		TrainingSample sampleSFF = new TrainingSample(new double[] {-1.0, -1.0}, -1.0);
		TrainingSample sampleSFT = new TrainingSample(new double[] {-1.0,  1.0}, -1.0);
		
		poolConstantSize2.addSample(sampleSTT);
		poolConstantSize2.addSample(sampleSFF);
		poolConstantSize2.addSample(sampleSTF);
		poolConstantSize2.addValidation(sampleSFT);
		
		poolConstantSize3 = new TrainingSamplePool();
		TrainingSample sampleTT = new TrainingSample(new double[] {1.0, 1.0, 1.0}, 1.0);
		TrainingSample sampleTF = new TrainingSample(new double[] {1.0, 0.0, 1.0}, 0.0);
		TrainingSample sampleFF = new TrainingSample(new double[] {0.0, 0.0, 1.0}, 0.0);
		TrainingSample sampleFT = new TrainingSample(new double[] {0.0, 1.0, 1.0}, 0.0);
		
		poolConstantSize3.addSample(sampleTT);
		poolConstantSize3.addSample(sampleFF);
		poolConstantSize3.addSample(sampleTF);
		poolConstantSize3.addValidation(sampleFT);
		
		poolWTFSize = new TrainingSamplePool();
		TrainingSample sampleWTF1 = new TrainingSample(new double[] {1.0}, 1.0);
		TrainingSample sampleWTF2 = new TrainingSample(new double[] {1.0, 1.0}, 0.0);
		TrainingSample sampleWTF3 = new TrainingSample(new double[] {0.0, 0.0, 1.0}, 0.0);
		TrainingSample sampleWTF4 = new TrainingSample(new double[] {1.0, 0.0, 1.0, 1.0}, 0.0);
		
		poolWTFSize.addSample(sampleWTF1);
		poolWTFSize.addSample(sampleWTF2);
		poolWTFSize.addSample(sampleWTF3);
		poolWTFSize.addValidation(sampleWTF4);
	}
	
	@Test
	public void testSamplesWTFness() {
		assertThat(poolWTFSize.isValid(), equalTo(false));
	}
	
	@Test
	public void testSample2Perceptron2Fit() {
		Perceptron perceptronAnd = new Perceptron(new double[] {2.0, 0.2}, 0.25, new HardLimiter());
		assertThat(perceptronAnd.isPoolValid(poolConstantSize2), equalTo(true));
	}
	
	@Test
	public void testSample2Perceptron3Fit() {
		Perceptron perceptronAnd = new Perceptron(new double[] {2.0, 0.2, 0.1}, 0.25, new HardLimiter());
		assertThat(perceptronAnd.isPoolValid(poolConstantSize2), equalTo(false));
	}

}
