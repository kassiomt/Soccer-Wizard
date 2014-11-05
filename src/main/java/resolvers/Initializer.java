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

	public Initializer(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, int hiddenNeurons, Map<String, Sheet> mapaDeTimes){

		int[] colunasSelecionaveis = useThisColumnsOfExcelTable();
		int enemyDataColumn = enemyDataColumn(); 
		int[] resultsDataColumns = resultsDataColumns();
		
		setInputMatrixWithEnemyTeamDataAndRange(timeInicial, timeFinal, rodadaInicial, rodadaFinal, mapaDeTimes, colunasSelecionaveis, enemyDataColumn);

		setHiddenNeurons(new double[hiddenNeurons]);
		int numRodadas = 1 + (rodadaFinal - rodadaInicial);
		int numTimes = 1 + (timeFinal - timeInicial);
		setOutputMatrix(new double[resultsDataColumns.length][numRodadas*numTimes]);

		setTargetMatrixWithEnemyTeamDataAndRange(timeInicial, timeFinal, rodadaInicial, rodadaFinal, mapaDeTimes, resultsDataColumns);
	}

	
	public Initializer(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, Map<String, Sheet> mapaDeTimes){

		int[] colunasSelecionaveis = useThisColumnsOfExcelTable();
		int enemyDataColumn = enemyDataColumn();
		int[] resultsDataColumns = resultsDataColumns();
		
//		setInputMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, colunasSelecionaveis, enemyDataColumn);
//		setTargetMatrixWithEnemyTeamData(numTimes, numRodadas, mapaDeTimes, resultsDataColumns, enemyDataColumn);
		
		setInputMatrixWithEnemyTeamDataAndRange(timeInicial, timeFinal, rodadaInicial, rodadaFinal, mapaDeTimes, colunasSelecionaveis, enemyDataColumn);		
		setTargetMatrixWithEnemyTeamDataAndRange(timeInicial, timeFinal, rodadaInicial, rodadaFinal, mapaDeTimes, resultsDataColumns);
	}

	private void setInputMatrixWithEnemyTeamDataAndRange(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, Map<String, Sheet> mapaDeTimes,	int[] colunasSelecionaveis, int enemyDataColumn) {
		int numRodadas = 1 + (rodadaFinal - rodadaInicial);
		int numTimes = 1 + (timeFinal - timeInicial);
		inputMatrix = new double[colunasSelecionaveis.length*2+1][numRodadas*numTimes];
		for (int j = timeInicial; j <= timeFinal; j++) {
			for (int i = rodadaInicial; i <= rodadaFinal; i++) {
				inputMatrix[0][(numRodadas*(j-timeInicial)) + (i-rodadaInicial)] = 1;
				for (int k = 0; k < colunasSelecionaveis.length; k++) {
					Cell dados = mapaDeTimes.get("time" + j).getCell(colunasSelecionaveis[k], i+1);
					inputMatrix[k+1][(numRodadas*(j-timeInicial)) + (i-rodadaInicial)] = Double.parseDouble(dados.getContents().replace(',', '.'));
				}
				Cell adversario = mapaDeTimes.get("time" + j).getCell(enemyDataColumn, i+1);
				int offset = colunasSelecionaveis.length;
				for (int k = offset; k < offset*2; k++) {
					Cell dadosAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(colunasSelecionaveis[k-offset], i+1);
					inputMatrix[k+1][(numRodadas*(j-timeInicial)) + (i-rodadaInicial)] = (-1) * Double.parseDouble(dadosAdversario.getContents().replace(',', '.'));
				}
			}
		}
//		inputMatrix = new double[colunasSelecionaveis.length*2+1][numRodadas*numTimes];
//		for (int j = (18-numTimes); j < 18; j++) {
//			for (int i = (31-numRodadas); i < 31; i++) {
//				inputMatrix[0][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = 1;
//				for (int k = 0; k < colunasSelecionaveis.length; k++) {
//					Cell dados = mapaDeTimes.get("time" + (j+1)).getCell(colunasSelecionaveis[k], i);
//					inputMatrix[k+1][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = Double.parseDouble(dados.getContents().replace(',', '.'));
//				}
//				Cell adversario = mapaDeTimes.get("time" + (j+1)).getCell(enemyDataColumn, i);
//				int offset = colunasSelecionaveis.length;
//				for (int k = offset; k < offset*2; k++) {
//					Cell dadosAdversario = mapaDeTimes.get("time" + adversario.getContents()).getCell(colunasSelecionaveis[k-offset], i);
//					inputMatrix[k+1][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = (-1) * Double.parseDouble(dadosAdversario.getContents().replace(',', '.'));
//				}
//			}
//		}
	}
	
	private void setTargetMatrixWithEnemyTeamDataAndRange(int timeInicial, int timeFinal, int rodadaInicial, int rodadaFinal, Map<String, Sheet> mapaDeTimes, int[] resultsDataColumns) {
		int numRodadas = 1 + (rodadaFinal - rodadaInicial);
		int numTimes = 1 + (timeFinal - timeInicial);
		targetMatrix = new double[resultsDataColumns.length][numRodadas*numTimes];
		for (int j = timeInicial; j <= timeFinal; j++) {
			for (int i = rodadaInicial; i <= rodadaFinal; i++) {
				for (int k = 0; k < resultsDataColumns.length; k++) {
				Cell dados = mapaDeTimes.get("time" + j).getCell(resultsDataColumns[k], i+1);
				targetMatrix[k][(numRodadas*(j-timeInicial)) + (i-rodadaInicial)] = Double.parseDouble(dados.getContents()); 
				}
			}
		}
//		targetMatrix = new double[resultsDataColumns.length][numRodadas*numTimes];	
//		for (int j = (18-numTimes); j < 18; j++) {
//			for (int i = (31-numRodadas); i < 31; i++) {
//				for (int k = 0; k < resultsDataColumns.length; k++) {
//				Cell dados = mapaDeTimes.get("time" + (j+1)).getCell(resultsDataColumns[k], i);
//				targetMatrix[k][(numRodadas*(j-(18-numTimes))) + i - (31-numRodadas)] = Double.parseDouble(dados.getContents()); 
//				}
//			}
//		}
	}

	private int[] useThisColumnsOfExcelTable() {
//		int[] colunasSelecionaveis = {16, 17, 19, 21, 23, 25}; //Full Inicial
		int[] colunasSelecionaveis = {16, 17, 19, 21, 23, 25}; //teste
//		int[] colunasSelecionaveis = {16}; //Encurtado
		return colunasSelecionaveis;
	}
	
	private int enemyDataColumn() {
//		int enemyColumn = 1; //Brasileiro
		int enemyColumn = 4; //Italiano
		return enemyColumn;
	}
	
	int[] resultsDataColumns(){
		int[] resultColumn = {9, 10, 11}; //Brasileiro novo
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