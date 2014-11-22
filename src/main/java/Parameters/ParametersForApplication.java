package Parameters;

public class ParametersForApplication extends Parameters {

	private double confidenceInterval;
	private Structure structure;
	private Modifiers modifiers;

	public ParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double confidenceInterval) {
		this.numTeams = numTeams;
		this.numTeamsTotal = numTeamsTotal;
		this.numRodadas = numRodadas;
		this.numRodadasTotal = numRodadasTotal;
		this.confidenceInterval = confidenceInterval;
		this.structure = new Structure();
		this.modifiers = new Modifiers();
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

	public Structure getStructure() {
		return structure;
	}

	public Modifiers getModifiers() {
		return modifiers;
	}
}
