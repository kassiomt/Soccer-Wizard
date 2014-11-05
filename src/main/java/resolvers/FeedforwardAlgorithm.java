package resolvers;

import calculators.FeedforwardCalculator;

public class FeedforwardAlgorithm {

	private double[][] lastLayerOutMatrix;
	double[][] winFailureMatrix;
	double[][] drawFailureMatrix;
	double[][] loseFailureMatrix;
	private double winSuccessRatio;
	private double drawSuccessRatio;
	private double loseSuccessRatio;
	private double anyoneSuccessRatio;
	private double bothSuccessRatio;
	private int numberOfWinPrediction;
	private int numberOfDrawPrediction;
	private int numberOfLosePrediction;
	private int numberOfWinResults;
	private int numberOfDrawResults;
	private int numberOfLoseResults;
	private int numberOfSuccessWinPredictions = 0;
	private int numberOfSuccessDrawPredictions = 0;
	private int numberOfSuccessLosePredictions = 0;
	
	
	
	public FeedforwardAlgorithm(Initializer initializedData, BackPropagationAlgorithm bias, double confidenceInterval) {

		System.out.println("Começando aplicação");
		double[][] target = initializedData.getTargetMatrix();
		setLastLayerOutMatrix(new double[initializedData.resultsDataColumns().length][initializedData.getInputMatrix()[0].length]);
		
		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
			// Feedforward
			// STEP 3
			double[] firstLayer = FeedforwardCalculator.receivesInputSignal(initializedData.getInputMatrix(), l);

			// STEP 4
			double[] hiddenLayerIn = FeedforwardCalculator.weightedInputSignal(firstLayer, bias.getHiddenBias());
			double[] hiddenLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(hiddenLayerIn);
			hiddenLayerOut[0] = 1;

			// STEP 5
			double[] lastLayerIn = FeedforwardCalculator.weightedInputSignal(hiddenLayerOut, bias.getOutputBias());
			double[] lastLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(lastLayerIn);

			getLastLayerOutMatrix()[0][l] = lastLayerOut[0];
			getLastLayerOutMatrix()[1][l] = lastLayerOut[1];
			getLastLayerOutMatrix()[2][l] = lastLayerOut[2];
			
		}
		
		errorCalculation(initializedData, confidenceInterval, target);

		System.out.println("Aplicação terminada");
	}

	private void errorCalculation(Initializer initializedData, double confidenceInterval, double[][] target) {
		winFailureMatrix = new double[target.length][target[0].length];
		drawFailureMatrix = new double[target.length][target[0].length];
		loseFailureMatrix = new double[target.length][target[0].length];
		
		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
			thirdErrorCalculation(target, l);
		}
				
		setWinSuccessRatio((double) numberOfSuccessWinPredictions/ (double) numberOfWinResults);
		setDrawSuccessRatio((double) numberOfSuccessDrawPredictions/ (double) numberOfDrawResults);
		setLoseSuccessRatio((double) numberOfSuccessLosePredictions/ (double) numberOfLoseResults);
		setOverallSuccessRatio((double) (numberOfSuccessWinPredictions + numberOfSuccessDrawPredictions + numberOfSuccessLosePredictions)
				/ (double) initializedData.getInputMatrix()[0].length);
	}

		private void thirdErrorCalculation(double[][] target, int l) {
		boolean isWin = false;
		boolean isDraw = false;
		boolean isLose = false;
		
		isWin = isWinBiggerThanOthers(l, isWin);
		isDraw = isDrawBiggerThanOthers(l, isDraw);
		isLose = isLoseBiggerThanOthers(l, isLose);
			
		checkWinResult(target, l, isWin);
		checkDrawResult(target, l, isDraw);
		checkLoseResult(target, l, isLose);
	}

	private boolean isLoseBiggerThanOthers(int l, boolean isLose) {
		if (getLastLayerOutMatrix()[2][l] > getLastLayerOutMatrix()[0][l] && getLastLayerOutMatrix()[2][l] > getLastLayerOutMatrix()[1][l])
			isLose = predictionIsLose(isLose);
		return isLose;
	}

	private boolean isDrawBiggerThanOthers(int l, boolean isDraw) {
		if (getLastLayerOutMatrix()[1][l] > getLastLayerOutMatrix()[0][l] && getLastLayerOutMatrix()[1][l] > getLastLayerOutMatrix()[2][l])
			isDraw = predictionIsDraw(isDraw);
		return isDraw;
	}

	private boolean isWinBiggerThanOthers(int l, boolean isWin) {
		if (getLastLayerOutMatrix()[0][l] > getLastLayerOutMatrix()[1][l] && getLastLayerOutMatrix()[0][l] > getLastLayerOutMatrix()[2][l])
			isWin = predictionIsWin(isWin);
		return isWin;
	}

	private boolean predictionIsWin(boolean isWin) {
		numberOfWinPrediction++;
		return isWin = true;
	}
	private boolean predictionIsDraw(boolean isDraw) {
		numberOfDrawPrediction++;
		return isDraw = true;
	}
	private boolean predictionIsLose(boolean isLose) {
		numberOfLosePrediction++;
		return isLose = true;
	}

	private void checkWinResult(double[][] target, int l, boolean isWin) {
		if (target[0][l] == 1){
			numberOfWinResults++;
			if (isWin)	numberOfSuccessWinPredictions++;
			if (!isWin) recordWinFailures(target, l);
			
		}
	}
	
	private void checkDrawResult(double[][] target, int l, boolean isDraw) {
		if (target[1][l] == 1) {
			numberOfDrawResults++;
			if (isDraw)	numberOfSuccessDrawPredictions++;
			if (!isDraw) recordDrawFailures(target, l);
		}
	}

	private void checkLoseResult(double[][] target, int l, boolean isLose) {
		if (target[2][l] == 1) {
			numberOfLoseResults++;
			if (isLose)	numberOfSuccessLosePredictions++;
			if (!isLose) recordLoseFailures(target, l);
		}
	}
	
	private void recordWinFailures(double[][] target, int l) {
		winFailureMatrix[0][l] = getLastLayerOutMatrix()[0][l];
		winFailureMatrix[1][l] = getLastLayerOutMatrix()[1][l];
		winFailureMatrix[2][l] = getLastLayerOutMatrix()[2][l];
	}
	
	private void recordDrawFailures(double[][] target, int l) {
		drawFailureMatrix[0][l] = getLastLayerOutMatrix()[0][l];
		drawFailureMatrix[1][l] = getLastLayerOutMatrix()[1][l];
		drawFailureMatrix[2][l] = getLastLayerOutMatrix()[2][l];
	}
	
	private void recordLoseFailures(double[][] target, int l) {
		loseFailureMatrix[0][l] = getLastLayerOutMatrix()[0][l];
		loseFailureMatrix[1][l] = getLastLayerOutMatrix()[1][l];
		loseFailureMatrix[2][l] = getLastLayerOutMatrix()[2][l];
	}

	public FeedforwardAlgorithm(BackPropagationAlgorithm bias) {

		System.out.println("Começando aplicação XOR");
		setLastLayerOutMatrix(new double[1][4]);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				// STEP 1
				double[] firstLayer = new double[3];
				firstLayer[0] = 1; 
				firstLayer[1] = i;
				firstLayer[2] = j;

				// STEP 2
				double[] hiddenLayerIn = FeedforwardCalculator.weightedInputSignal(firstLayer, bias.getHiddenBias());
				double[] hiddenLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(hiddenLayerIn);
				hiddenLayerOut[0] = 1;

				// STEP 3
				double[] lastLayerIn = FeedforwardCalculator.weightedInputSignal(hiddenLayerOut, bias.getOutputBias());
				double[] lastLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(lastLayerIn);

				getLastLayerOutMatrix()[0][i * 2 + j] = lastLayerOut[0];
				System.out.println(getLastLayerOutMatrix()[0][i * 2 + j]);
			}
		}
		System.out.println("Aplicação terminada");

	}

	public double[][] getLastLayerOutMatrix() {
		return lastLayerOutMatrix;
	}

	public void setLastLayerOutMatrix(double[][] lastLayerOutMatrix) {
		this.lastLayerOutMatrix = lastLayerOutMatrix;
	}

	public double getWinSuccessRatio() {
		return winSuccessRatio;
	}

	public void setWinSuccessRatio(double winSuccessRatio) {
		this.winSuccessRatio = winSuccessRatio;
	}

	public double getDrawSuccessRatio() {
		return drawSuccessRatio;
	}

	public void setDrawSuccessRatio(double drawSuccessRatio) {
		this.drawSuccessRatio = drawSuccessRatio;
	}
	
	public double getLoseSuccessRatio() {
		return loseSuccessRatio;
	}
	
	public void setLoseSuccessRatio(double loseSuccessRatio) {
		this.loseSuccessRatio = loseSuccessRatio;
	}
	
	public double getAnyoneSuccessRatio() {
		return anyoneSuccessRatio;
	}

	public void setAnyoneSuccessRatio(double anyoneSuccessRatio) {
		this.anyoneSuccessRatio = anyoneSuccessRatio;
	}

	public double getBothSuccessRatio() {
		return bothSuccessRatio;
	}

	public void setOverallSuccessRatio(double bothSuccessRatio) {
		this.bothSuccessRatio = bothSuccessRatio;
	}
}