package br.com.jeffque.neural.sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TrainingSamplePool implements Iterable<TrainingSample> {
	private boolean useTrainingPool = true;
	private boolean useValidationPool = false;
	
	public boolean isUseTrainingPool() {
		return useTrainingPool;
	}

	public void setUseTrainingPool(boolean useTrainingPool) {
		this.useTrainingPool = useTrainingPool;
	}

	public boolean isUseValidationPool() {
		return useValidationPool;
	}

	public void setUseValidationPool(boolean useValidationPool) {
		this.useValidationPool = useValidationPool;
	}

	private List<TrainingSample> trainingPool = new ArrayList<>();
	private List<TrainingSample> validationPool = new ArrayList<>();
	
	public void addValidation(TrainingSample validation) {
		validationPool.add(validation);
	}
	
	public void addValidations(Collection<TrainingSample> validations) {
		validationPool.addAll(validations);
	}
	
	public void addValidations(TrainingSample[] validations) {
		for (TrainingSample validation: validations) {
			validationPool.add(validation);
		}
	}
	
	public void addSample(TrainingSample sample) {
		trainingPool.add(sample);
	}
	
	public void addSamples(Collection<TrainingSample> samples) {
		trainingPool.addAll(samples);
	}
	
	public void addSamples(TrainingSample[] samples) {
		for (TrainingSample sample: samples) {
			trainingPool.add(sample);
		}
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
	
	/**
	 * 
	 * @return If all samples are the same size, and that the size is greater than 0
	 */
	public boolean isValid() {
		if (trainingPool.size() == 0) {
			return false;
		}
		
		int size = trainingPool.get(0).getSize();
		if (size == 0) {
			return false;
		}
		
		for (int i = trainingPool.size() - 1; i >= 1; i--) {
			if (trainingPool.get(i).getSize() != size) {
				return false;
			}
		}
		
		for (int i = validationPool.size() - 1; i >= 0; i--) {
			if (validationPool.get(i).getSize() != size) {
				return false;
			}
		}
		
		return true;
	}
}
