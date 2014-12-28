package resolvers;

public class ApplicationErrorAnalysis {

	private double winSuccessRatio;
	private double drawSuccessRatio;
	private double loseSuccessRatio;
	private double overallSuccessRatio;
	private int numberOfWinPrediction;
	private int numberOfDrawPrediction;
	private int numberOfLosePrediction;
	private int numberOfWinSuccessPredictions = 0;
	private int numberOfDrawSuccessPredictions = 0;
	private int numberOfLoseSuccessPredictions = 0;
	private int numberOfWinResults = 0;
	private int numberOfDrawResults = 0;
	private int numberOfLoseResults = 0;

	public ApplicationErrorAnalysis(double confidenceInterval, double[][] lastLayerOutMatrix, double[][] target) {
		int[][] predictionMatrix = setPrediction(lastLayerOutMatrix);
		int[][] validationMatrix = setValidation(predictionMatrix, lastLayerOutMatrix);
		int[][] correctPredictionsMatrix = setResultsMatrix(predictionMatrix, validationMatrix, target);
		checkResult(correctPredictionsMatrix, target, validationMatrix);
		

		setWinSuccessRatio((double) getNumberOfSuccessWinPredictions() / (double) getNumberOfWinPrediction());
		setDrawSuccessRatio((double) getNumberOfSuccessDrawPredictions() / (double) getNumberOfDrawPrediction());
		setLoseSuccessRatio((double) getNumberOfSuccessLosePredictions() / (double) getNumberOfLosePrediction());
		setOverallSuccessRatio((double) (getNumberOfSuccessWinPredictions() + getNumberOfSuccessDrawPredictions() + getNumberOfSuccessLosePredictions())
				/ (double) (getNumberOfWinPrediction() + getNumberOfDrawPrediction() + getNumberOfLosePrediction()));
	}

	private int[][] setPrediction(double[][] lastLayerOut) {
		int[][] predictionMatrix = new int[lastLayerOut.length][lastLayerOut[0].length];
		for (int l = 0; l < lastLayerOut[0].length; l++) {
			
			if (predictionIsWin(lastLayerOut, l))
				predictionMatrix[0][l] = 1;
			else if (predictionIsDraw(lastLayerOut, l))
				predictionMatrix[1][l] = 1;
			else if (predictionIsLose(lastLayerOut, l))
				predictionMatrix[2][l] = 1;
		}
		
		return predictionMatrix;
	}
	
	private boolean predictionIsWin(double[][] lastLayerOut, int l) {
		if (lastLayerOut[0][l] > lastLayerOut[1][l] && lastLayerOut[0][l] > lastLayerOut[2][l]) {
			setNumberOfWinPrediction(getNumberOfWinPrediction() + 1);
			return true;
		}
		return false;
	}
	private boolean predictionIsDraw(double[][] lastLayerOut, int l) {
		if (lastLayerOut[1][l] > lastLayerOut[0][l] && lastLayerOut[1][l] > lastLayerOut[2][l]) {
			setNumberOfDrawPrediction(getNumberOfDrawPrediction() + 1);
			return true;
		}
		return false;
	}
	private boolean predictionIsLose(double[][] lastLayerOut, int l) {
		if (lastLayerOut[2][l] > lastLayerOut[0][l] && lastLayerOut[2][l] > lastLayerOut[1][l]) {
			setNumberOfLosePrediction(getNumberOfLosePrediction() + 1);
			return true;
		}
		return false;
	}

	
	private int[][] setValidation(int[][] prediction, double[][] lastLayerOut) {
		int[][] validationMatrix = new int[prediction.length][prediction[0].length];
		double[] sumOfEpochResults = sumOfEpochResults(lastLayerOut);
		double[][] probabilities = probabilities(lastLayerOut, sumOfEpochResults);
		double[] averageOfProbabilities = averageOfProbabilities(probabilities);
		double[] stdOfProbabilities = stdOfProbabilities(probabilities, averageOfProbabilities);
		
		for (int l = 0; l < lastLayerOut[0].length; l++) {
			for (int i = 0; i < validationMatrix.length; i++) {
				validationMatrix[i][l] = validateResult(probabilities, averageOfProbabilities, stdOfProbabilities, i, l);
			}
		}
			
		return validationMatrix;
	}

	private double[] sumOfEpochResults(double[][] lastLayerOut) {
		double[] sumOfEpochResults = new double[lastLayerOut[0].length];
		for (int l = 0; l < lastLayerOut[0].length; l++) {
			sumOfEpochResults[l] = 0;
			for (int i = 0; i < lastLayerOut.length; i++)
				sumOfEpochResults[l] += lastLayerOut[i][l];
			}
		return sumOfEpochResults;
	}
	private double[][] probabilities(double[][] lastLayerOut, double[] sumOfEpochResults) {
		double[][] probability = new double[lastLayerOut.length][lastLayerOut[0].length];
		
		for (int i = 0; i < lastLayerOut.length; i++) {
			for (int l = 0; l < lastLayerOut[0].length; l++) {
				probability[i][l] = 1 - (lastLayerOut[i][l] / sumOfEpochResults[l]);
			}
		}
		return probability;
	}
	private double[] averageOfProbabilities(double[][] probabilities) {
		double[] averageOfProbabilities = {0,0,0};
		for (int i = 0; i < probabilities.length; i++) {
			for (int l = 0; l < probabilities[0].length; l++) {
				averageOfProbabilities[i] += probabilities[i][l];
			}
			averageOfProbabilities[i] = averageOfProbabilities[i] / probabilities[0].length;
		}
		return averageOfProbabilities;
	}
	private double[] stdOfProbabilities(double[][] probabilities, double[] averageOfProbabilities) {
		double[] variance = { 0, 0, 0 };
		double[] stdOfProbabilities = { 0, 0, 0 };

		for (int i = 0; i < probabilities.length; i++) {
			for (int l = 0; l < probabilities[0].length; l++) {
				variance[i] += Math.pow((averageOfProbabilities[i] - probabilities[i][l]), 2);
			}
			variance[i] = variance[i] / probabilities[0].length;
			stdOfProbabilities[i] = Math.sqrt(variance[i]);
		}
		return stdOfProbabilities;
	}
	private int validateResult(double[][] probabilities, double[] averageOfProbabilities, double[] stdOfProbabilities, int condition, int epoch) {

		if (probabilities[condition][epoch] >= 1 && probabilities[condition][epoch] > (averageOfProbabilities[condition] + stdOfProbabilities[condition])) {
			return 1;
		}
		return 0;
	}
	
	
	private int[][] setResultsMatrix(int[][] predictionMatrix, int[][] validationMatrix, double[][] target) {
		int[][] resultsMatrix = new int[target.length][target[0].length];
		for (int l = 0; l < target[0].length; l++) {
			for (int i = 0; i < target.length; i++) {
				if (predictionMatrix[i][l] == 1 && validationMatrix[i][l] == 1 && target[i][l] == 1)
					resultsMatrix[i][l] = 1;
			}
		}
		
		return resultsMatrix;
	}
	
	
	private void checkResult(int[][] correctPredictionsMatrix, double[][] target, int[][] validationMatrix) {
		for (int l = 0; l < target[0].length; l++) {
			if (target[0][l] == 1 && validationMatrix[0][l] == 1) {
				setNumberOfWinResults(getNumberOfWinResults() + 1);
				if (correctPredictionsMatrix[0][l] == 1)
					setNumberOfSuccessWinPredictions(getNumberOfSuccessWinPredictions() + 1);
			}
			if (target[1][l] == 1 && validationMatrix[1][l] == 1) {
				setNumberOfDrawResults(getNumberOfDrawResults() + 1);
				if (correctPredictionsMatrix[1][l] == 1)
					setNumberOfSuccessDrawPredictions(getNumberOfSuccessDrawPredictions() + 1);
			}
			if (target[2][l] == 1 && validationMatrix[2][l] == 1) {
				setNumberOfLoseResults(getNumberOfLoseResults() + 1);
				if (correctPredictionsMatrix[2][l] == 1)
					setNumberOfSuccessLosePredictions(getNumberOfSuccessLosePredictions() + 1);
			}
		}
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

	public double getOverallSuccessRatio() {
		return overallSuccessRatio;
	}

	public void setOverallSuccessRatio(double overallSuccessRatio) {
		this.overallSuccessRatio = overallSuccessRatio;
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
		return numberOfWinSuccessPredictions;
	}

	public void setNumberOfSuccessWinPredictions(int numberOfSuccessWinPredictions) {
		this.numberOfWinSuccessPredictions = numberOfSuccessWinPredictions;
	}

	public int getNumberOfSuccessDrawPredictions() {
		return numberOfDrawSuccessPredictions;
	}

	public void setNumberOfSuccessDrawPredictions(int numberOfSuccessDrawPredictions) {
		this.numberOfDrawSuccessPredictions = numberOfSuccessDrawPredictions;
	}

	public int getNumberOfSuccessLosePredictions() {
		return numberOfLoseSuccessPredictions;
	}

	public void setNumberOfSuccessLosePredictions(int numberOfSuccessLosePredictions) {
		this.numberOfLoseSuccessPredictions = numberOfSuccessLosePredictions;
	}
}
