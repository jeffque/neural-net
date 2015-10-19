package br.com.jeffque.neural;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.jeffque.neural.utils.Function;
import br.com.jeffque.neural.utils.activator.HardLimiter;
import br.com.jeffque.neural.utils.activator.SymmetricHardLimiter;

public class NonDifferentiableTest {

	public void verifyDifferentaibility(Function f) {
		Exception ex = null;
		try {
			f.dy(0);
		} catch(UnsupportedOperationException e) {
			ex = e;
		}
		
		assertNotNull(ex);
	}
	
	@Test
	public void hardLimiter() {
		verifyDifferentaibility(new HardLimiter());
	}
	
	@Test
	public void symmetricHardLimiter() {
		verifyDifferentaibility(new SymmetricHardLimiter());
	}

}
