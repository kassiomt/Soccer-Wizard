package resolvers;

public class ApplicationErrorAnalysis {
	
	private double winSuccessRatio;
	private double drawSuccessRatio;
	private double loseSuccessRatio;
	private double overallSuccessRatio;
	private double[][] winFailureMatrix;
	private double[][] drawFailureMatrix;
	private double[][] loseFailureMatrix;
	private int numberOfWinPrediction;
	private int numberOfDrawPrediction;
	private int numberOfLosePrediction;
	private int numberOfWinResults;
	private int numberOfDrawResults;
	private int numberOfLoseResults;
	private int numberOfSuccessWinPredictions = 0;
	private int numberOfSuccessDrawPredictions = 0;
	private int numberOfSuccessLosePredictions = 0;

	public ApplicationErrorAnalysis(Initializer initializedData, double confidenceInterval, double[][] lastLayerOutMatrix, double[][] target) {
		setWinFailureMatrix(new double[target.length][target[0].length]);
		setDrawFailureMatrix(new double[target.length][target[0].length]);
		setLoseFailureMatrix(new double[target.length][target[0].length]);
		
		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
			thirdErrorCalculation(lastLayerOutMatrix, target, l);
		}

		setWinSuccessRatio((double) getNumberOfSuccessWinPredictions()/ (double) getNumberOfWinPrediction());
		setDrawSuccessRatio((double) getNumberOfSuccessDrawPredictions()/ (double) getNumberOfDrawPrediction());
		setLoseSuccessRatio((double) getNumberOfSuccessLosePredictions()/ (double) getNumberOfLosePrediction());
		setOverallSuccessRatio((double) (getNumberOfSuccessWinPredictions() + getNumberOfSuccessDrawPredictions() + getNumberOfSuccessLosePredictions())
				/ (double) (getNumberOfWinPrediction() + getNumberOfDrawPrediction() + getNumberOfLosePrediction()));
	}
	
//	public static void errorCalculation(Initializer initializedData, double confidenceInterval, double[][] target) {
//		setWinFailureMatrix(new double[target.length][target[0].length]);
//		setDrawFailureMatrix(new double[target.length][target[0].length]);
//		setLoseFailureMatrix(new double[target.length][target[0].length]);
//		
//		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
//			thirdErrorCalculation(target, l);
//		}
//				
//		FeedforwardAlgorithm.setWinSuccessRatio((double) numberOfSuccessWinPredictions/ (double) numberOfWinPrediction);
//		FeedforwardAlgorithm.setDrawSuccessRatio((double) numberOfSuccessDrawPredictions/ (double) numberOfDrawPrediction);
//		FeedforwardAlgorithm.setLoseSuccessRatio((double) numberOfSuccessLosePredictions/ (double) numberOfLosePrediction);
//		FeedforwardAlgorithm.setOverallSuccessRatio((double) (numberOfSuccessWinPredictions + numberOfSuccessDrawPredictions + numberOfSuccessLosePredictions)
//				/ (double) (numberOfWinPrediction + numberOfDrawPrediction + numberOfLosePrediction));
//	}

		private void thirdErrorCalculation(double[][] lastLayerOut, double[][] target, int l) {
		
		boolean predictionIsWin = false;	boolean predictionIsDraw = false;	boolean predictionIsLose = false;
		
		predictionIsWin = isWinBiggerThanOthers(lastLayerOut, l, predictionIsWin);
		predictionIsDraw = isDrawBiggerThanOthers(lastLayerOut, l, predictionIsDraw);
		predictionIsLose = isLoseBiggerThanOthers(lastLayerOut, l, predictionIsLose);
			
		checkWinResult(target, l, predictionIsWin);
		checkDrawResult(target, l, predictionIsDraw);
		checkLoseResult(target, l, predictionIsLose);
	}

	private boolean isWinBiggerThanOthers(double[][] lastLayerOut, int l, boolean predictionIsWin) {
			if ((lastLayerOut[0][l] > lastLayerOut[1][l] && lastLayerOut[0][l] > lastLayerOut[2][l])
					&& (lastLayerOut[5][l] > lastLayerOut[3][l] && lastLayerOut[5][l] > lastLayerOut[4][l]))	
				setNumberOfWinPrediction(getNumberOfWinPrediction() + 1);
			return predictionIsWin = true;
	}
	
	private boolean isDrawBiggerThanOthers(double[][] lastLayerOut, int l, boolean predictionIsDraw) {
		if ((lastLayerOut[1][l] > lastLayerOut[0][l] && lastLayerOut[1][l] > lastLayerOut[2][l])
				&& (lastLayerOut[4][l] > lastLayerOut[3][l] && lastLayerOut[4][l] > lastLayerOut[5][l]))
			setNumberOfDrawPrediction(getNumberOfDrawPrediction() + 1);
		return predictionIsDraw = true;
	}

	private boolean isLoseBiggerThanOthers(double[][] lastLayerOut, int l, boolean predictionIsLose) {
		if ((lastLayerOut[2][l] > lastLayerOut[0][l] && lastLayerOut[2][l] > lastLayerOut[1][l])
				&& (lastLayerOut[3][l] > lastLayerOut[4][l] && lastLayerOut[3][l] > lastLayerOut[5][l]))
			setNumberOfLosePrediction(getNumberOfLosePrediction() + 1);
		return predictionIsLose = true;
	}


	private void checkWinResult(double[][] target, int l, boolean isWin) {
		if (target[0][l] == 1){
			setNumberOfWinResults(getNumberOfWinResults() + 1);
			if (isWin)	setNumberOfSuccessWinPredictions(getNumberOfSuccessWinPredictions() + 1);
//			else recordWinFailures(target, l);
		}
	}
	
	private void checkDrawResult(double[][] target, int l, boolean isDraw) {
		if (target[1][l] == 1) {
			setNumberOfDrawResults(getNumberOfDrawResults() + 1);
			if (isDraw)	setNumberOfSuccessDrawPredictions(getNumberOfSuccessDrawPredictions() + 1);
//			else recordDrawFailures(target, l);
		}
	}

	private void checkLoseResult(double[][] target, int l, boolean isLose) {
		if (target[2][l] == 1) {
			setNumberOfLoseResults(getNumberOfLoseResults() + 1);
			if (isLose)	setNumberOfSuccessLosePredictions(getNumberOfSuccessLosePredictions() + 1);
//			else recordLoseFailures(target, l);
		}
	}
	
//	private void recordWinFailures(double[][] target, int l) {
//		getWinFailureMatrix()[0][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[0][l];
//		getWinFailureMatrix()[1][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[1][l];
//		getWinFailureMatrix()[2][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[2][l];
//	}
//	
//	private void recordDrawFailures(double[][] target, int l) {
//		getDrawFailureMatrix()[0][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[0][l];
//		getDrawFailureMatrix()[1][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[1][l];
//		getDrawFailureMatrix()[2][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[2][l];
//	}
//	
//	private void recordLoseFailures(double[][] target, int l) {
//		getLoseFailureMatrix()[0][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[0][l];
//		getLoseFailureMatrix()[1][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[1][l];
//		getLoseFailureMatrix()[2][l] = FeedforwardAlgorithm.getLastLayerOutMatrix()[2][l];
//	}

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

	public double getOverallSuccessRatio() {
		return overallSuccessRatio;
	}

	public void setOverallSuccessRatio(double overallSuccessRatio) {
		this.overallSuccessRatio = overallSuccessRatio;
	}

	public double[][] getWinFailureMatrix() {
		return winFailureMatrix;
	}

	public void setWinFailureMatrix(double[][] winFailureMatrix) {
		this.winFailureMatrix = winFailureMatrix;
	}

	public double[][] getDrawFailureMatrix() {
		return drawFailureMatrix;
	}

	public void setDrawFailureMatrix(double[][] drawFailureMatrix) {
		this.drawFailureMatrix = drawFailureMatrix;
	}

	public double[][] getLoseFailureMatrix() {
		return loseFailureMatrix;
	}

	public void setLoseFailureMatrix(double[][] loseFailureMatrix) {
		this.loseFailureMatrix = loseFailureMatrix;
	}
	
	public int getNumberOfWinPrediction() {
		return numberOfWinPrediction;
	}

	public void setNumberOfWinPrediction(int numberOfWinPrediction) {
		this.numberOfWinPrediction = numberOfWinPrediction;
	}

	public int getNumberOfDrawPrediction() {
		return numberOfDrawPrediction;
	}

	public void setNumberOfDrawPrediction(int numberOfDrawPrediction) {
		this.numberOfDrawPrediction = numberOfDrawPrediction;
	}

	public int getNumberOfLosePrediction() {
		return numberOfLosePrediction;
	}

	public void setNumberOfLosePrediction(int numberOfLosePrediction) {
		this.numberOfLosePrediction = numberOfLosePrediction;
	}

	public int getNumberOfWinResults() {
		return numberOfWinResults;
	}

	public void setNumberOfWinResults(int numberOfWinResults) {
		this.numberOfWinResults = numberOfWinResults;
	}

	public int getNumberOfDrawResults() {
		return numberOfDrawResults;
	}

	public void setNumberOfDrawResults(int numberOfDrawResults) {
		this.numberOfDrawResults = numberOfDrawResults;
	}

	public int getNumberOfLoseResults() {
		return numberOfLoseResults;
	}

	public void setNumberOfLoseResults(int numberOfLoseResults) {
		this.numberOfLoseResults = numberOfLoseResults;
	}

	public int getNumberOfSuccessWinPredictions() {
		return numberOfSuccessWinPredictions;
	}

	public void setNumberOfSuccessWinPredictions(
			int numberOfSuccessWinPredictions) {
		this.numberOfSuccessWinPredictions = numberOfSuccessWinPredictions;
	}

	public int getNumberOfSuccessDrawPredictions() {
		return numberOfSuccessDrawPredictions;
	}

	public void setNumberOfSuccessDrawPredictions(
			int numberOfSuccessDrawPredictions) {
		this.numberOfSuccessDrawPredictions = numberOfSuccessDrawPredictions;
	}

	public int getNumberOfSuccessLosePredictions() {
		return numberOfSuccessLosePredictions;
	}

	public void setNumberOfSuccessLosePredictions(
			int numberOfSuccessLosePredictions) {
		this.numberOfSuccessLosePredictions = numberOfSuccessLosePredictions;
	}
}
