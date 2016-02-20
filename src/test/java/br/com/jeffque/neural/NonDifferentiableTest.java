package br.com.jeffque.neural;

import org.junit.Test;

import br.com.jeffque.neural.activator.HardLimiter;
import br.com.jeffque.neural.activator.SymmetricHardLimiter;

public class NonDifferentiableTest {

	
	@Test(expected = UnsupportedOperationException.class)
	public void hardLimiter() {
		new HardLimiter().dy(0);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void symmetricHardLimiter() {
		new SymmetricHardLimiter().dy(0);
	}

}
