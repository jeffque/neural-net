package br.com.jeffque.neural;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jeffque.neural.sample.TrainingSample;
import br.com.jeffque.neural.sample.TrainingSamplePool;

public class TrainingSamplePoolTest {
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
	
	@Before
	public void resetPoolModes() {
		setOnlyTrainingMode();
	}

	private void setOnlyTrainingMode() {
		pool.setUseTrainingPool(true);
		pool.setUseValidationPool(false);
	}
	
	private void setOnlyValidationMode() {
		pool.setUseTrainingPool(false);
		pool.setUseValidationPool(true);
	}
	
	private void setBothModes() {
		pool.setUseTrainingPool(true);
		pool.setUseValidationPool(true);
	}
	
	private void setNoMode() {
		pool.setUseTrainingPool(false);
		pool.setUseValidationPool(false);
	}

	@Test
	public void testModesDefault() {
		TrainingSamplePool pool = new TrainingSamplePool();
		assertThat(pool.isUseTrainingPool(), equalTo(true));
		assertThat(pool.isUseValidationPool(), equalTo(false));
	}
	
	@Test
	public void testModesOnlyTraining() {
		setOnlyTrainingMode();
		assertThat(pool.isUseTrainingPool(), equalTo(true));
		assertThat(pool.isUseValidationPool(), equalTo(false));
	}
	
	@Test
	public void testModesOnlyValidation() {
		setOnlyValidationMode();
		assertThat(pool.isUseTrainingPool(), equalTo(false));
		assertThat(pool.isUseValidationPool(), equalTo(true));
	}
	
	@Test
	public void testModesBoth() {
		setBothModes();
		assertThat(pool.isUseTrainingPool(), equalTo(true));
		assertThat(pool.isUseValidationPool(), equalTo(true));
	}
	
	@Test
	public void testModesNoMode() {
		setNoMode();
		assertThat(pool.isUseTrainingPool(), equalTo(false));
		assertThat(pool.isUseValidationPool(), equalTo(false));
	}
	
	@Test
	public void testSizeOnlyTraining() {
		setOnlyTrainingMode();
		assertThat(pool.getSize(), equalTo(3));
	}
	
	@Test
	public void testSizeOnlyValidation() {
		setOnlyValidationMode();
		assertThat(pool.getSize(), equalTo(1));
	}
	
	@Test
	public void testSizeBoth() {
		setBothModes();
		assertThat(pool.getSize(), equalTo(4));
	}
	
	@Test
	public void testSizeNoMode() {
		setNoMode();
		assertThat(pool.getSize(), equalTo(0));
	}

}
