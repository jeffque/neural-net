package br.com.jeffque.neural.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainingSamplePool implements Iterable<TrainingSample> {
	private boolean useTrainingPool = true;
	
	private List<TrainingSample> trainingPool = new ArrayList<>();
	
	public void addSample(TrainingSample sample) {
		trainingPool.add(sample);
	}
	
	public TrainingSample getSample(int idx) {
		int n = idx;
		if (useTrainingPool) {
			try {
				return trainingPool.get(n);
			} catch (IndexOutOfBoundsException e) {
				n -= trainingPool.size();
			}
		}
		throw new IndexOutOfBoundsException("Index " + idx + " is out of the range for: " + (useTrainingPool? " training":""));
	}
	
	public int getSize() {
		int size = 0;
		if (useTrainingPool) {
			size += trainingPool.size();
		}
		return size;
	}

	@Override
	public Iterator<TrainingSample> iterator() {
		List<TrainingSample> usedSample = new ArrayList<>();
		if (useTrainingPool) {
			usedSample.addAll(trainingPool);
		}
		return usedSample.iterator();
	}
}
