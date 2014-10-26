package kassio.soccer_wizard;

public class TrainingParameters {
	
	private double learningRate;
	private double minError;
	private int maxRotinas;
	private int numRodadas;
	private int numTimes;
	private int hiddenNeurons;
	
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
	public int getNumRodadas() {
		return numRodadas;
	}
	public void setNumRodadas(int numRodadas) {
		this.numRodadas = numRodadas;
	}
	public int getNumTimes() {
		return numTimes;
	}
	public void setNumTimes(int numTimes) {
		this.numTimes = numTimes;
	}
	public int getHiddenNeurons() {
		return hiddenNeurons;
	}
	public void setHiddenNeurons(int hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}	
}
