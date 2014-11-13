package kassio.soccer_wizard;

public class ParametersForApplication extends Parameters {

	private double confidenceInterval;

	public ParametersForApplication(int numTeams, int numTeamsTotal,
			int numRodadas, int numRodadasTotal, double confidenceInterval) {
		setNumTeams(numTeams);
		setNumTeamsTotal(numTeamsTotal);
		setNumRodadas(numRodadas);
		setNumRodadasTotal(numRodadasTotal);
		setConfidenceInterval(confidenceInterval);
	}

	public double getConfidenceInterval() {
		return confidenceInterval;
	}

	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

}
