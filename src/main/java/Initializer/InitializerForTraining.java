package initializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parameters.ParametersForTraining;
import jxl.Sheet;

public class InitializerForTraining extends Initializer {

	public InitializerForTraining(ParametersForTraining.Structure parameters, Map<String, Sheet> mapaDeTimes) {

		setInputColumns(useThisColumnsOfExcelTable());
		setOutputColumns(outputDataColumns());

		setGamesRandom(createRandomizedRows(parameters.getNumRodadasTotal(), parameters.getNumTeamsTotal()));
		int numberOfTrainingData = parameters.getNumTeams() * parameters.getNumRodadas();

		setInputMatrix(0, numberOfTrainingData, getGamesRandom(), getInputColumns(), mapaDeTimes);

		setHiddenNeurons(new double[parameters.getHiddenNeurons()]);

		setOutputMatrix(new double[getOutputColumns().length][numberOfTrainingData]);

		setTargetMatrix(0, numberOfTrainingData, getGamesRandom(), getOutputColumns(), mapaDeTimes);
	}

	protected int[] createRandomizedRows(int totalRodadas, int totalTeams) {
		int[] gamesRandom = new int[totalRodadas * totalTeams];

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= gamesRandom.length; i++)
			list.add(i);

		Collections.shuffle(list);
		for (int i = 0; i < gamesRandom.length; i++)
			gamesRandom[i] = list.get(i);

		return gamesRandom;
	}

	public int[] getGamesRandom() {
		return gamesRandom;
	}

	public void setGamesRandom(int[] gamesRandom) {
		this.gamesRandom = gamesRandom;
	}

}
