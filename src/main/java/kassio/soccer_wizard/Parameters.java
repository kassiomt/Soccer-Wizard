package kassio.soccer_wizard;

public abstract class Parameters {

	private int numRodadas;
	private int numRodadasTotal;
	private int numTeams;
	private int numTeamsTotal;

	public int getNumRodadas() {
		return numRodadas;
	}

	public void setNumRodadas(int numRodadas) {
		this.numRodadas = numRodadas;
	}

	public int getNumRodadasTotal() {
		return numRodadasTotal;
	}

	public void setNumRodadasTotal(int numRodadasTotal) {
		this.numRodadasTotal = numRodadasTotal;
	}

	public int getNumTeams() {
		return numTeams;
	}

	public void setNumTeams(int numTeams) {
		this.numTeams = numTeams;
	}

	public int getNumTeamsTotal() {
		return numTeamsTotal;
	}

	public void setNumTeamsTotal(int numTeamsTotal) {
		this.numTeamsTotal = numTeamsTotal;
	}

}
