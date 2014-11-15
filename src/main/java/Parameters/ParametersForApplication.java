package Parameters;


public class ParametersForApplication extends Parameters {

	private double confidenceInterval;
	private Structure structure;
	private Modifiers modifiers;

	public ParametersForApplication(int numTeams, int numTeamsTotal,
			int numRodadas, int numRodadasTotal, double confidenceInterval) {
		setNumTeams(numTeams);
		setNumTeamsTotal(numTeamsTotal);
		setNumRodadas(numRodadas);
		setNumRodadasTotal(numRodadasTotal);
		setConfidenceInterval(confidenceInterval);
	}

	public class Structure {

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
		public double getConfidenceInterval() {
			return confidenceInterval;
		}

	}
	
	public double getConfidenceInterval() {
		return confidenceInterval;
	}

	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

	public Structure getStructure() {
		return structure;
	}
	
	public Modifiers getModifiers() {
		return modifiers;
	}
}
