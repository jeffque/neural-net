package br.com.jeffque.neural;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jeffque.neural.sample.TrainingSample;
import br.com.jeffque.neural.sample.TrainingSamplePool;

public class PerceptronAnd {
	
	private static TrainingSamplePool pool;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		pool = new TrainingSamplePool();
		TrainingSample sampleTT = new TrainingSample(new double[] {1.0, 1.0}, 1.0);
		TrainingSample sampleTF = new TrainingSample(new double[] {1.0, 0.0}, 0.0);
		TrainingSample sampleFF = new TrainingSample(new double[] {0.0, 0.0}, 0.0);
		TrainingSample sampleFT = new TrainingSample(new double[] {0.0, 1.0}, 0.0);
		
		pool.addSample(sampleTT);
		pool.addSample(sampleFF);
		pool.addSample(sampleTF);
		pool.addValidation(sampleFT);
	}

	@Test
	public void testTraining() {
		fail("Not yet implemented");
	}

}
