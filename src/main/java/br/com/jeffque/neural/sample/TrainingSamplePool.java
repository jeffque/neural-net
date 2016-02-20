package br.com.jeffque.neural.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainingSamplePool implements Iterable<TrainingSample> {
	private boolean useTrainingPool = true;
	private boolean useValidationPool = false;
	
	private List<TrainingSample> trainingPool = new ArrayList<>();
	private List<TrainingSample> validationPool = new ArrayList<>();
	
	public void addValidation(TrainingSample validation) {
		validationPool.add(validation);
	}
	
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
		if (useValidationPool) {
			try {
				return validationPool.get(n);
			} catch (IndexOutOfBoundsException e) {
			}
		}
		throw new IndexOutOfBoundsException("Index " + idx + " is out of the range for: " + (useTrainingPool? " training":"") + (useValidationPool? " validation": ""));
	}
	
	public int getSize() {
		int size = 0;
		if (useTrainingPool) {
			size += trainingPool.size();
		}
		if (useValidationPool) {
			size += validationPool.size();
		}
		return size;
	}

	@Override
	public Iterator<TrainingSample> iterator() {
		List<TrainingSample> usedSample = new ArrayList<>();
		if (useTrainingPool) {
			usedSample.addAll(trainingPool);
		}
		if (useValidationPool) {
			usedSample.addAll(validationPool);
		}
		return usedSample.iterator();
	}
}
