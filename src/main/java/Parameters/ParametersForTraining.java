package Parameters;

public class ParametersForTraining extends Parameters {

	private double learningRate;
	private int maxRotinas;
	private double thresholdError;
	private int hiddenNeurons;
	private Structure structure;
	private Modifiers modifiers;

	public ParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas,
			int numRodadasTotal, int hiddenNeurons) {
		setLearningRate(learningRate);
		setNumTeams(numTeams);
		setMaxRotinas(maxRotinas);
		setThresholdError(thresholdError);
		setNumTeamsTotal(numTeamsTotal);
		setNumRodadas(numRodadas);
		setNumRodadasTotal(numRodadasTotal);
		setHiddenNeurons(hiddenNeurons);
	}

	public class Structure {
		public int getHiddenNeurons() {
			return hiddenNeurons;
		}

		public int getNumRodadas() {
			return numRodadas;
		}

		public int getNumRodadasTotal() {
			return numRodadasTotal;
		}

		public int getNumTeams() {
			return numTeams;
		}

		public int getNumTeamsTotal() {
			return numTeamsTotal;
		}
	}

	public class Modifiers {
		public double getLearningRate() {
			return learningRate;
		}

		public int getMaxRotinas() {
			return maxRotinas;
		}

		public double getThresholdError() {
			return thresholdError;
		}

	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public void setMaxRotinas(int maxRotinas) {
		this.maxRotinas = maxRotinas;
	}

	public void setThresholdError(double thresholdError) {
		this.thresholdError = thresholdError;
	}

	public void setHiddenNeurons(int hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}

	public Structure getStructure() {
		return structure;
	}

	public Modifiers getModifiers() {
		return modifiers;
	}

}
