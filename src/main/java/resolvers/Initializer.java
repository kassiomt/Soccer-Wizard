package resolvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;

public class Initializer {

	private double[][] inputMatrix;
	private double[] hiddenNeurons;
	private double[][] outputMatrix;
	
	private double[][] targetMatrix;
	
	private int[] gamesRandom;

	private int[] useThisColumnsOfExcelTable() {
//		int[] colunasSelecionaveis = {16, 17, 19, 21, 23, 25}; //Full Inicial
		int[] inputColumns = {16, 17, 19, 21, 23, 25}; //teste
//		int[] colunasSelecionaveis = {16}; //Encurtado
		return inputColumns;
	}
	
//	private int enemyDataColumn() {
//		int enemyColumn = 1; //Brasileiro
//		int enemyColumn = 4; //Italiano
//		return enemyColumn;
//	}
	
	int[] outputDataColumns(){
		int[] outputColumns = {9, 10, 11}; //Brasileiro novo
		return outputColumns;
	}

	public Initializer() {

		inputMatrix = new double [3][4];
		inputMatrix[0][0] = 1;		inputMatrix[1][0] = 0;		inputMatrix[2][0] = 0;
		inputMatrix[0][1] = 1;		inputMatrix[1][1] = 0;		inputMatrix[2][1] = 1;
		inputMatrix[0][2] = 1;		inputMatrix[1][2] = 1;		inputMatrix[2][2] = 0;
		inputMatrix[0][3] = 1;		inputMatrix[1][3] = 1;		inputMatrix[2][3] = 1;

		setHiddenNeurons(new double[5]);
		setOutputMatrix(new double[1][4]);

		targetMatrix = new double[1][4];
		targetMatrix[0][0] = -1;
		targetMatrix[0][1] = 1;
		targetMatrix[0][2] = 1;
		targetMatrix[0][3] = -1;
	}

	public Initializer(int trainingTeams, int totalTeams, int trainingRodadas, int totalRodadas, int hiddenNeurons, Map<String, Sheet> mapaDeTimes){

		int[] inputColumns = useThisColumnsOfExcelTable();
		int[] outputColumns = outputDataColumns();
		
		setGamesRandom(createRandomizedRows(totalRodadas, totalTeams));
		int numberOfTrainingData = trainingTeams*trainingRodadas;

		setInputMatrixWithRandomData(numberOfTrainingData, getGamesRandom(), inputColumns, mapaDeTimes);
		
		setHiddenNeurons(new double[hiddenNeurons]);
		
		setOutputMatrix(new double[outputColumns.length][numberOfTrainingData]);

		setTargetMatrixWithRandomData(numberOfTrainingData, getGamesRandom(), outputColumns, mapaDeTimes);
	}
	
	private int[] createRandomizedRows(int totalRodadas, int totalTeams) {
		int[] gamesRandom = new int[totalRodadas*totalTeams];
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= gamesRandom.length; i++)
		    list.add(i);
		
		Collections.shuffle(list);
		for (int i = 0; i < gamesRandom.length; i++)
			gamesRandom[i] = list.get(i);
		
		return gamesRandom;
	}

	private void setInputMatrixWithRandomData(int numberOfTrainingData, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		inputMatrix = new double[inputColumns.length*2+1][numberOfTrainingData];
		
		for (int i = 0; i < numberOfTrainingData; i++) {			
			inputMatrix[0][i] = 1;
			setTeamDataOnInputMatrix(i, gamesRandom, inputColumns, mapaDeTimes);
			setEnemyTeamDataOnInputMatrix(i, gamesRandom, inputColumns, mapaDeTimes);
		}
	}
	
	private void setTeamDataOnInputMatrix(int i, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		for (int k = 0; k < inputColumns.length; k++) {
			Cell cell = mapaDeTimes.get("timeRandom").getCell(inputColumns[k], gamesRandom[i]);
			inputMatrix[k+1][i] = Double.parseDouble(cell.getContents().replace(',', '.'));
		}
	}
	
	private void setEnemyTeamDataOnInputMatrix(int i, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		int offset = inputColumns.length;
		for (int k = offset; k < offset*2; k++) {
			Cell enemyCell = mapaDeTimes.get("adversarioRandom").getCell(inputColumns[k-offset], gamesRandom[i]);
			inputMatrix[k+1][i] = (-1) * Double.parseDouble(enemyCell.getContents().replace(',', '.'));
		}
	}

	private void setTargetMatrixWithRandomData(int numberOfTrainingData, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		targetMatrix = new double[outputColumns.length*2][numberOfTrainingData];
		
		for (int i = 0; i < numberOfTrainingData; i++) {
			setTeamDataOnTargetMatrix(i, gamesRandom, outputColumns, mapaDeTimes);
			setEnemyTeamDataOnTargetMatrix(i, gamesRandom, outputColumns, mapaDeTimes);
		}
		
	}

	private void setTeamDataOnTargetMatrix(int i, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		for (int k = 0; k < outputColumns.length; k++) {
			Cell cell = mapaDeTimes.get("timeRandom").getCell(outputColumns[k], gamesRandom[i]);
			targetMatrix[k][i] = Double.parseDouble(cell.getContents());
		}
	}
	
	private void setEnemyTeamDataOnTargetMatrix(int i, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		int offset = outputColumns.length;
		for (int k = offset; k < offset*2; k++) {
			Cell enemyCell = mapaDeTimes.get("adversarioRandom").getCell(outputColumns[k-offset], gamesRandom[i]);
			targetMatrix[k][i] = Double.parseDouble(enemyCell.getContents().replace(',', '.'));
		}
	}
	
	
	public Initializer(int applicationTeams, int totalTeams, int applicationRodadas, int totalRodadas, Map<String, Sheet> mapaDeTimes, int[] gamesRandom){

		int[] inputColumns = useThisColumnsOfExcelTable();
		int[] outputColumns = outputDataColumns();
		
		int numberOfApplicationData = applicationTeams*applicationRodadas;
		int startOfApplicationData = gamesRandom.length-numberOfApplicationData;
		
		setApplicationInputMatrixWithRandomData(startOfApplicationData, numberOfApplicationData, gamesRandom, inputColumns, mapaDeTimes);
		
		setApplicationTargetMatrixWithRandomData(startOfApplicationData, numberOfApplicationData, gamesRandom, outputColumns, mapaDeTimes);
	}

	private void setApplicationInputMatrixWithRandomData(int startOfApplicationData, int numberOfApplicationData, int[] gamesRandom, int[] inputColumns, Map<String, Sheet> mapaDeTimes) {
		inputMatrix = new double[inputColumns.length*2+1][numberOfApplicationData];
		
		for (int i = startOfApplicationData; i < gamesRandom.length; i++) {			
			inputMatrix[0][i] = 1;
			setTeamDataOnInputMatrix(i, gamesRandom, inputColumns, mapaDeTimes);
			setEnemyTeamDataOnInputMatrix(i, gamesRandom, inputColumns, mapaDeTimes);
		}
	}
	
	private void setApplicationTargetMatrixWithRandomData(int startOfApplicationData, int numberOfApplicationData, int[] gamesRandom, int[] outputColumns, Map<String, Sheet> mapaDeTimes) {
		targetMatrix = new double[outputColumns.length*2][numberOfApplicationData];
		
		for (int i = startOfApplicationData; i < gamesRandom.length; i++) {
			setTeamDataOnTargetMatrix(i, gamesRandom, outputColumns, mapaDeTimes);
			setEnemyTeamDataOnTargetMatrix(i, gamesRandom, outputColumns, mapaDeTimes);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private void setInputMatrixWithEnemyTeamDataAndRange(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, Map<String, Sheet> mapaDeTimes,	int[] colunasSelecionaveis, int enemyDataColumn) {
//		int numRodadas = 1 + (rodadaFinal - rodadaInicial);
//		int numTimes = 1 + (timeFinal - timeInicial);
//		int loopControl = 0;
//		inputMatrix = new double[colunasSelecionaveis.length*2+1][numRodadas*numTimes];
//		for (int j = timeInicial; j <= timeFinal; j++) {
//			for (int i = rodadaInicial; i <= rodadaFinal; i++) {
//				inputMatrix[0][(numRodadas*(j-timeInicial)) + loopControl] = 1;
//				for (int k = 0; k < colunasSelecionaveis.length; k++) {
//					Cell dados = mapaDeTimes.get("time" + j).getCell(colunasSelecionaveis[k], i+1);
//					inputMatrix[k+1][(numRodadas*(j-timeInicial)) + loopControl] = Double.parseDouble(dados.getContents().replace(',', '.'));
//				}
//				Cell adversario = mapaDeTimes.get("time" + j).getCell(enemyDataColumn, i+1);
//				int offset = colunasSelecionaveis.length;
//				for (int k = offset; k < offset*2; k++) {
//					Cell dadosAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(colunasSelecionaveis[k-offset], i+1);
//					inputMatrix[k+1][(numRodadas*(j-timeInicial)) + loopControl] = (-1) * Double.parseDouble(dadosAdversario.getContents().replace(',', '.'));
//				}
//				loopControl++;
//			}
//		}
//	}
//	
//	private void setTargetMatrixWithEnemyTeamDataAndRange(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, Map<String, Sheet> mapaDeTimes, int[] resultsDataColumns) {
//		int numRodadas = 1 + (rodadaFinal - rodadaInicial);
//		int numTimes = 1 + (timeFinal - timeInicial);
//		targetMatrix = new double[resultsDataColumns.length][numRodadas*numTimes];
//		for (int j = timeInicial; j <= timeFinal; j++) {
//			for (int i = rodadaInicial; i <= rodadaFinal; i++) {
//				for (int k = 0; k < resultsDataColumns.length; k++) {
//				Cell dados = mapaDeTimes.get("time" + j).getCell(resultsDataColumns[k], i+1);
//				targetMatrix[k][(numRodadas*(j-timeInicial)) + (i-rodadaInicial)] = Double.parseDouble(dados.getContents()); 
//				}
//			}
//		}
//	}

	public double[][] getInputMatrix() {
		return inputMatrix;
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

	public double[][] getTargetMatrix() {
		return targetMatrix;
	}

	public int[] getGamesRandom() {
		return gamesRandom;
	}

	public void setGamesRandom(int[] gamesRandom) {
		this.gamesRandom = gamesRandom;
	}

}