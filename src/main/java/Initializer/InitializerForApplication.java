package initializer;

import java.util.Map;

import parameters.ParametersForApplication;
import jxl.Sheet;

public class InitializerForApplication extends Initializer {

	public InitializerForApplication(ParametersForApplication.Structure structure, Map<String, Sheet> mapaDeTimes, int[] gamesRandom) {

		setInputColumns(useThisColumnsOfExcelTable());
		setOutputColumns(outputDataColumns());

		int numberOfApplicationData = structure.getNumTeams() * structure.getNumRodadas();
		int startOfApplicationData = gamesRandom.length - numberOfApplicationData;

		setInputMatrix(startOfApplicationData, numberOfApplicationData, gamesRandom, getInputColumns(), mapaDeTimes);

		setTargetMatrix(startOfApplicationData, numberOfApplicationData, gamesRandom, getOutputColumns(), mapaDeTimes);
	}

	public int[] getGamesRandom() {
		return gamesRandom;
	}

	public void setGamesRandom(int[] gamesRandom) {
		this.gamesRandom = gamesRandom;
	}
}
