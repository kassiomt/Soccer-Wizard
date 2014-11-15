package Initializer;

import java.util.Map;

import jxl.Cell;
import jxl.Sheet;

public abstract class Initializer {
	protected double[][] inputMatrix;
	protected double[] hiddenNeurons;
	protected double[][] outputMatrix;

	protected double[][] targetMatrix;
	
	private int[] inputColumns;
	private int[] outputColumns;
	
	protected int[] gamesRandom;
	
	public abstract int[] getGamesRandom();

	protected int[] useThisColumnsOfExcelTable() {
		// int[] colunasSelecionaveis = {16, 17, 19, 21, 23, 25}; //Full Inicial
		setInputColumns(new int[] { 16, 17 }); // teste
		// int[] colunasSelecionaveis = {16, 17}; //Encurtado
		return getInputColumns();
	}

	// private int enemyDataColumn() {
	// int enemyColumn = 1; //Brasileiro
	// int enemyColumn = 4; //Italiano
	// return enemyColumn;
	// }

	protected int[] outputDataColumns() {
		setOutputColumns(new int[] { 9, 10, 11 });
		return getOutputColumns();
	}
	
	protected void setInputMatrix(int startOfData, int numberOfData, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		inputMatrix = new double[inputColumns.length * 2 + 1][numberOfData];

		for (int i = 0; i < numberOfData; i++) {
			inputMatrix[0][i] = 1;
			setTeamDataOnInputMatrix(i, startOfData, gamesRandom, inputColumns, mapaDeTimes);
			setEnemyTeamDataOnInputMatrix(i, startOfData, gamesRandom, inputColumns, mapaDeTimes);
		}
	}

	private void setTeamDataOnInputMatrix(int i, int startOfData, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		for (int k = 0; k < inputColumns.length; k++) {
			Cell cell = mapaDeTimes.get("timeRandom").getCell(inputColumns[k], gamesRandom[i + startOfData]);
			getInputMatrix()[k + 1][i] = Double.parseDouble(cell.getContents().replace(',', '.'));
		}
	}

	private void setEnemyTeamDataOnInputMatrix(int i, int startOfData, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		int offset = inputColumns.length;
		for (int k = offset; k < offset * 2; k++) {
			Cell enemyCell = mapaDeTimes.get("adversarioRandom").getCell(inputColumns[k - offset], gamesRandom[i + startOfData]);
			getInputMatrix()[k + 1][i] = (-1) * Double.parseDouble(enemyCell.getContents().replace(',', '.'));
		}
	}

	protected void setTargetMatrix(int startOfData, int numberOfData, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		targetMatrix = new double[outputColumns.length * 2][numberOfData];

		for (int i = 0; i < numberOfData; i++) {
			setTeamDataOnTargetMatrix(i, startOfData, gamesRandom, outputColumns, mapaDeTimes);
			setEnemyTeamDataOnTargetMatrix(i, startOfData, gamesRandom, outputColumns, mapaDeTimes);
		}
	}

	private void setTeamDataOnTargetMatrix(int i, int startOfData, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		for (int k = 0; k < outputColumns.length; k++) {
			Cell cell = mapaDeTimes.get("timeRandom").getCell(outputColumns[k], gamesRandom[i + startOfData]);
			getTargetMatrix()[k][i] = Double.parseDouble(cell.getContents());
		}
	}

	private void setEnemyTeamDataOnTargetMatrix(int i, int startOfData, int[] gamesRandom, int[] outputColumns,
			Map<String, Sheet> mapaDeTimes) {
		int offset = outputColumns.length;
		for (int k = offset; k < offset * 2; k++) {
			Cell enemyCell = mapaDeTimes.get("adversarioRandom").getCell(outputColumns[k - offset], gamesRandom[i + startOfData]);
			getTargetMatrix()[k][i] = Double.parseDouble(enemyCell.getContents().replace(',', '.'));
		}
	}

	public double[] getHiddenNeurons() {
		return hiddenNeurons;
	}

	public void setHiddenNeurons(double[] hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}

	public double[][] getOutputMatrix() {
		return outputMatrix;
	}

	public void setOutputMatrix(double[][] outputMatrix) {
		this.outputMatrix = outputMatrix;
	}

	public int[] getInputColumns() {
		return inputColumns;
	}

	public void setInputColumns(int[] inputColumns) {
		this.inputColumns = inputColumns;
	}

	public int[] getOutputColumns() {
		return outputColumns;
	}

	public void setOutputColumns(int[] outputColumns) {
		this.outputColumns = outputColumns;
	}

	public double[][] getInputMatrix() {
		return inputMatrix;
	}

	public double[][] getTargetMatrix() {
		return targetMatrix;
	}

}
