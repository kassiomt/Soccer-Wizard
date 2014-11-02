package kassio.soccer_wizard;

public class TrainingParameters {
	
	private double learningRate;
	private double minError;
	private int maxRotinas;
	private int hiddenNeurons;
	private int numRodadaInicialForApplication;
	private int numTimeInicialForApplication;
	private int numRodadaFinalForApplication;
	private int numTimeFinalForApplication;
	private double confidenceInterval;
	
	public double getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	public double getMinError() {
		return minError;
	}
	public void setMinError(double minError) {
		this.minError = minError;
	}
	public int getMaxRotinas() {
		return maxRotinas;
	}
	public void setMaxRotinas(int maxRotinas) {
		this.maxRotinas = maxRotinas;
	}
	public int getHiddenNeurons() {
		return hiddenNeurons;
	}
	public void setHiddenNeurons(int hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}
	public int getNumTimeFinalForApplication() {
		return numTimeFinalForApplication;
	}
	public void setNumTimeFinalForApplication(int numTimesForApplication) {
		this.numTimeFinalForApplication = numTimesForApplication;
	}
	public int getNumRodadaFinalForApplication() {
		return numRodadaFinalForApplication;
	}
	public void setNumRodadaFinalForApplication(int numRodadasForApplication) {
		this.numRodadaFinalForApplication = numRodadasForApplication;
	}
	public double getConfidenceInterval() {
		return confidenceInterval;
	}
	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}
	public int getNumRodadaInicialForApplication() {
		return numRodadaInicialForApplication;
	}
	public void setNumRodadaInicialForApplication(
			int numRodadaInicialForApplication) {
		this.numRodadaInicialForApplication = numRodadaInicialForApplication;
	}
	public int getNumTimeInicialForApplication() {
		return numTimeInicialForApplication;
	}
	public void setNumTimeInicialForApplication(int numTimeInicialForApplication) {
		this.numTimeInicialForApplication = numTimeInicialForApplication;
	}	
}
