package kassio.soccer_wizard;

public class ParametersForTraining extends Parameters {

	private double learningRate;
	private int maxRotinas;
	private double thresholdError;
	private int hiddenNeurons;

	public ParametersForTraining(double learningRate, int maxRotinas,
			double thresholdError, int numTeams, int numTeamsTotal,
			int numRodadas, int numRodadasTotal, int hiddenNeurons) {
		setLearningRate(learningRate);
		setNumTeams(numTeams);
		setMaxRotinas(maxRotinas);
		setThresholdError(thresholdError);
		setNumTeamsTotal(numTeamsTotal);
		setNumRodadas(numRodadas);
		setNumRodadasTotal(numRodadasTotal);
		setHiddenNeurons(hiddenNeurons);
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public int getMaxRotinas() {
		return maxRotinas;
	}

	public void setMaxRotinas(int maxRotinas) {
		this.maxRotinas = maxRotinas;
	}

	public double getThresholdError() {
		return thresholdError;
	}

	public void setThresholdError(double thresholdError) {
		this.thresholdError = thresholdError;
	}

	public int getHiddenNeurons() {
		return hiddenNeurons;
	}

	public void setHiddenNeurons(int hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}
}
