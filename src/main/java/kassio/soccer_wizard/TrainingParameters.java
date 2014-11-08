package kassio.soccer_wizard;

public class TrainingParameters {
	
	private double learningRate;
	private int maxRotinas;
	private double thresholdError;
	private int numRodadasForTraining;
	private int numRodadasTotalForTraining;
	private int numTeamsForTraining;
	private int numTeamsTotalForTraining;
	private int hiddenNeurons;
	
	private int numRodadasForApplication;
	private int numRodadaTotalForApplication;
	private int numTeamsForApplication;
	private int numTeamsTotalForApplication;
	private double confidenceInterval;
	
	public double getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	public double getThresholdError() {
		return thresholdError;
	}
	public void setThresholdError(double thresholdError) {
		this.thresholdError = thresholdError;
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
	public int getNumTeamsTotalForApplication() {
		return numTeamsTotalForApplication;
	}
	public void setNumTeamsTotalForApplication(int numTeamsTotalForApplication) {
		this.numTeamsTotalForApplication = numTeamsTotalForApplication;
	}
	public int getNumRodadaTotalForApplication() {
		return numRodadaTotalForApplication;
	}
	public void setNumRodadaTotalForApplication(int numRodadaTotalForApplication) {
		this.numRodadaTotalForApplication = numRodadaTotalForApplication;
	}
	public double getConfidenceInterval() {
		return confidenceInterval;
	}
	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}
	public int getNumRodadasForApplication() {
		return numRodadasForApplication;
	}
	public void setNumRodadasForApplication(int numRodadasForApplication) {
		this.numRodadasForApplication = numRodadasForApplication;
	}
	public int getNumTeamsForApplication() {
		return numTeamsForApplication;
	}
	public void setNumTeamsForApplication(int numTeamsForApplication) {
		this.numTeamsForApplication = numTeamsForApplication;
	}
	public int getNumRodadasForTraining() {
		return numRodadasForTraining;
	}
	public void setNumRodadasForTraining(int numRodadasForTraining) {
		this.numRodadasForTraining = numRodadasForTraining;
	}
	public int getNumRodadasTotalForTraining() {
		return numRodadasTotalForTraining;
	}
	public void setNumRodadasTotalForTraining(int numRodadasTotalForTraining) {
		this.numRodadasTotalForTraining = numRodadasTotalForTraining;
	}
	public int getNumTeamsForTraining() {
		return numTeamsForTraining;
	}
	public void setNumTeamsForTraining(int numTeamsForTraining) {
		this.numTeamsForTraining = numTeamsForTraining;
	}
	public int getNumTeamsTotalForTraining() {
		return numTeamsTotalForTraining;
	}
	public void setNumTeamsTotalForTraining(int numTeamsTotalForTraining) {
		this.numTeamsTotalForTraining = numTeamsTotalForTraining;
	}	
}
