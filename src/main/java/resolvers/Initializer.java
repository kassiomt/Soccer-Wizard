package resolvers;

import java.util.Map;

import jxl.Cell;
import jxl.Sheet;

public class Initializer {

	private double[][] inputMatrix;
	private double[] hiddenNeurons;
	private double[][] outputMatrix;

	private double[][] targetMatrix;


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

	public Initializer(int numTimes, int numRodadas, Map<String, Sheet> mapaDeTimes, int hiddenNeurons){

		int[] colunasSelecionaveis = useThisColumnsOfExcelTable();
		int enemyDataColumn = enemyDataColumn(); 
		int[] resultsDataColumns = resultsDataColumns();
		
		setInputMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, colunasSelecionaveis,enemyDataColumn);

		setHiddenNeurons(new double[hiddenNeurons]);
		setOutputMatrix(new double[resultsDataColumns.length][numRodadas*numTimes]);

		setTargetMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, resultsDataColumns, enemyDataColumn);
	}

	
	public Initializer(int numTimes, int numRodadas, Map<String, Sheet> mapaDeTimes){

		int[] colunasSelecionaveis = useThisColumnsOfExcelTable();
		int enemyDataColumn = enemyDataColumn();
		int[] resultsDataColumns = resultsDataColumns();
		
//		setInputMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, colunasSelecionaveis, enemyDataColumn);
//		setTargetMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, resultsDataColumns, enemyDataColumn);
		
		setInputMatrixWithEnemyTeamDataAndOffset(numTimes, numRodadas, mapaDeTimes, colunasSelecionaveis, enemyDataColumn);		
		setTargetMatrixWithEnemyTeamDataAndOffset(numTimes, numRodadas, mapaDeTimes, resultsDataColumns);
	}

	private void setInputMatrixWithEnemyTeamDataAndOffset(int numTimes,	int numRodadas, Map<String, Sheet> mapaDeTimes,
			int[] colunasSelecionaveis, int enemyDataColumn) {
			inputMatrix = new double[colunasSelecionaveis.length*2+1][numRodadas*numTimes];
			for (int j = (18-numTimes); j < 18; j++) {
				for (int i = (31-numRodadas); i < 31; i++) {
					inputMatrix[0][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = 1;
					for (int k = 0; k < colunasSelecionaveis.length; k++) {
						Cell dados = mapaDeTimes.get("time" + (j+1)).getCell(colunasSelecionaveis[k], i);
						inputMatrix[k+1][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = Double.parseDouble(dados.getContents().replace(',', '.'));
					}
					Cell adversario = mapaDeTimes.get("time" + (j+1)).getCell(enemyDataColumn, i);
					int offset = colunasSelecionaveis.length;
					for (int k = offset; k < offset*2; k++) {
						Cell dadosAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(colunasSelecionaveis[k-offset], i);
						inputMatrix[k+1][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = (-1) * Double.parseDouble(dadosAdversario.getContents().replace(',', '.'));
					}
				}
		}
	}
	
	private void setTargetMatrixWithEnemyTeamDataAndOffset(int numTimes, int numRodadas, Map<String, Sheet> mapaDeTimes, int[] resultsDataColumns) {
		targetMatrix = new double[resultsDataColumns.length][numRodadas*numTimes];	
		for (int j = (18-numTimes); j < 18; j++) {
			for (int i = (31-numRodadas); i < 31; i++) {
				for (int k = 0; k < resultsDataColumns.length; k++) {
				Cell dados = mapaDeTimes.get("time" + (j+1)).getCell(resultsDataColumns[k], i);
				targetMatrix[k][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = Double.parseDouble(dados.getContents()); 
				}
			}
		}
	}

	private int[] useThisColumnsOfExcelTable() {
//		int[] colunasSelecionaveis = {4, 5, 6, 9, 12, 15}; //Brasileiro
		int[] colunasSelecionaveis = {15}; //Italiano
		return colunasSelecionaveis;
	}
	
	private int enemyDataColumn() {
//		int enemyColumn = 1; //Brasileiro
		int enemyColumn = 4; //Italiano
		return enemyColumn;
	}
	
	int[] resultsDataColumns(){
		int[] resultColumn = {8, 9, 10}; //Italiano
		return resultColumn;
	}

	private void setInputMatrixWithEnemyTeamData(int numTimes, int numRodadas, Map<String, Sheet> mapaDeTimes, int[] colunasSelecionaveis, int enemyDataColumn) {
		inputMatrix = new double[colunasSelecionaveis.length*2+1][numRodadas*numTimes];
		for (int j = 0; j < numTimes; j++) {
			for (int i = 2; i < numRodadas+2; i++) {
				inputMatrix[0][numRodadas*j + i-2] = 1;
				
				putTeamDataOnInputMatrix(numRodadas, mapaDeTimes, colunasSelecionaveis, j, i);
				putEnemyTeamDataOnInputMatrix(numRodadas, mapaDeTimes, colunasSelecionaveis, j, i, enemyDataColumn);
			}
		}
	}

	private void putTeamDataOnInputMatrix(int numRodadas, Map<String, Sheet> mapaDeTimes, int[] colunasSelecionaveis, int j, int i) {
		for (int k = 0; k < colunasSelecionaveis.length; k++) {
			Cell dados = mapaDeTimes.get("time" + (j+1)).getCell(colunasSelecionaveis[k], i);
			inputMatrix[k+1][numRodadas*j + i-2] = Double.parseDouble(dados.getContents().replace(',', '.'));
		}
	}
	
	private void putEnemyTeamDataOnInputMatrix(int numRodadas, Map<String, Sheet> mapaDeTimes, int[] colunasSelecionaveis, int j, int i, int enemyDataColumn) {
		Cell adversario = mapaDeTimes.get("time" + (j+1)).getCell(enemyDataColumn, i);
		int offset = colunasSelecionaveis.length;
		for (int k = offset; k < offset*2; k++) {
			Cell dadosAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(colunasSelecionaveis[k-offset], i);
			inputMatrix[k+1][numRodadas*j + i-2] = (-1) * Double.parseDouble(dadosAdversario.getContents().replace(',', '.'));
		}
	}
	
	private void setTargetMatrixWithEnemyTeamData(int numTimes, int numRodadas,	Map<String, Sheet> mapaDeTimes, int[] resultsDataColumn, int enemyDataColumn) {
		targetMatrix = new double[resultsDataColumn.length][numRodadas*numTimes];
		for (int j = 0; j < numTimes; j++) {
			for (int i = 2; i < numRodadas+2; i++) {
//				putTeamDataOnTargetMatrix(numRodadas, mapaDeTimes, j, i);
//				putEnemyTeamDataOnTargetMatrix(numRodadas, mapaDeTimes, j, i, enemyDataColumn);
				putWinDrawLoseDataOnTargetMatrix(numRodadas, mapaDeTimes, j, i, resultsDataColumn);
			}
		}
	}

	private void putWinDrawLoseDataOnTargetMatrix(int numRodadas, Map<String, Sheet> mapaDeTimes, int j, int i, int[] resultsDataColumn) {
		for (int k = 0; k < 3; k++) {
			Cell resultado = mapaDeTimes.get("time" + (j+1)).getCell(resultsDataColumn[k], i);
			targetMatrix[k][numRodadas*j + i-2] = Double.parseDouble(resultado.getContents());	
		}
	}
		
//	private void putTeamDataOnTargetMatrix(int numRodadas, Map<String, Sheet> mapaDeTimes, int j, int i) {
//		Cell resultado = mapaDeTimes.get("time" + (j+1)).getCell(2, i);
//		targetMatrix[0][numRodadas*j + i-2] = Double.parseDouble(resultado.getContents());
//	}
//	
//	private void putEnemyTeamDataOnTargetMatrix(int numRodadas, Map<String, Sheet> mapaDeTimes, int j, int i, int enemyDataColumn) {
//		Cell adversario = mapaDeTimes.get("time" + (j+1)).getCell(enemyDataColumn, i);
//		Cell resultadoAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(2, i);
//		targetMatrix[1][numRodadas*j + i-2] = Double.parseDouble(resultadoAdversario.getContents());
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

}