package kassio.parameters;

public class ParametersForApplication extends Parameters {

	private double probabilityThreshold;
	private double stdThreshold;
	private Structure structure;
	private Modifiers modifiers;

	public ParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double probabilityThreshold,
			double stdThreshold) {
		this.numTeams = numTeams;
		this.numTeamsTotal = numTeamsTotal;
		this.numRodadas = numRodadas;
		this.numRodadasTotal = numRodadasTotal;
		this.probabilityThreshold = probabilityThreshold;
		this.stdThreshold = stdThreshold;
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
		public double getProbabilityThreshold() {
			return probabilityThreshold;
		}

		public double getStdThreshold() {
			return stdThreshold;
		}
	}

	public Structure getStructure() {
		return structure;
	}

	public Modifiers getModifiers() {
		return modifiers;
	}
}
