package kassio.parameters;

public class ParametersForTraining extends Parameters {

	private double learningRate;
	private int maxRotinas;
	private double thresholdError;
	private int hiddenNeurons;
	private Structure structure;
	private Modifiers modifiers;

	public ParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas,
			int numRodadasTotal, int hiddenNeurons) {
		this.learningRate = learningRate;
		this.numTeams = numTeams;
		this.maxRotinas = maxRotinas;
		this.thresholdError = thresholdError;
		this.numTeamsTotal = numTeamsTotal;
		this.numRodadas = numRodadas;
		this.numRodadasTotal = numRodadasTotal;
		this.hiddenNeurons = hiddenNeurons;
		this.structure = new Structure();
		this.modifiers = new Modifiers();
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

	public Structure getStructure() {
		return structure;
	}

	public Modifiers getModifiers() {
		return modifiers;
	}

}
