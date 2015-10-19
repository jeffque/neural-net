package br.com.jeffque.neural.sample;

import java.util.Iterator;
import java.util.List;

public class TrainingSamplePool implements Iterable<TrainingSample> {
	private List<TrainingSample> pool;
	
	public TrainingSample getSample(int n) {
		return pool.get(n);
	}
	
	public int getSize() {
		return pool.size();
	}

	@Override
	public Iterator<TrainingSample> iterator() {
		return pool.iterator();
	}
}
