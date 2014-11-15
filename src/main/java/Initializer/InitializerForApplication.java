package Initializer;

import java.util.Map;

import Parameters.ParametersForApplication;
import jxl.Sheet;

public class InitializerForApplication extends Initializer {
	
	public InitializerForApplication(ParametersForApplication.Structure structure, Map<String, Sheet> mapaDeTimes, int[] gamesRandom){

		int[] inputColumns = useThisColumnsOfExcelTable();
		int[] outputColumns = outputDataColumns();
		
		int numberOfApplicationData = structure.getNumTeams()*structure.getNumRodadas();
		int startOfApplicationData = gamesRandom.length-numberOfApplicationData;
		
		setInputMatrix(startOfApplicationData, numberOfApplicationData, gamesRandom, inputColumns, mapaDeTimes);
		
		setTargetMatrix(startOfApplicationData, numberOfApplicationData, gamesRandom, outputColumns, mapaDeTimes);
	}

	public int[] getGamesRandom() {
		return gamesRandom;
	}

	public void setGamesRandom(int[] gamesRandom) {
		this.gamesRandom = gamesRandom;
	}
}
