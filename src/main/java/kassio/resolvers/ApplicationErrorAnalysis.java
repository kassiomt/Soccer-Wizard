package kassio.resolvers;

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
	private int numberOfValidWinResults = 0;
	private int numberOfValidDrawResults = 0;
	private int numberOfValidLoseResults = 0;

	public ApplicationErrorAnalysis(double[][] lastLayerOutMatrix, double[][] target, double probabilityThreshold, double stdThreshold) {
		int[][] predictionMatrix = setPrediction(lastLayerOutMatrix);
		boolean[][] validationMatrix = setValidation(predictionMatrix, lastLayerOutMatrix, probabilityThreshold, stdThreshold);

		checkResult(predictionMatrix, target, validationMatrix);

		System.out.println("Number of Valid Results :"
				+ (getNumberOfValidWinResults() + getNumberOfValidDrawResults() + getNumberOfValidLoseResults()));
		System.out.format("Percentage of Valid Results :" + "%.2f" + "%%%n",
				(double) (getNumberOfValidWinResults() + getNumberOfValidDrawResults() + getNumberOfValidLoseResults()) * 100
						/ (double) lastLayerOutMatrix[0].length);
		setWinSuccessRatio((double) getNumberOfSuccessWinPredictions() / (double) getNumberOfValidWinResults());
		setDrawSuccessRatio((double) getNumberOfSuccessDrawPredictions() / (double) getNumberOfValidDrawResults());
		setLoseSuccessRatio((double) getNumberOfSuccessLosePredictions() / (double) getNumberOfValidLoseResults());
		setOverallSuccessRatio((double) (getNumberOfSuccessWinPredictions() + getNumberOfSuccessDrawPredictions() + getNumberOfSuccessLosePredictions())
				/ (double) (getNumberOfValidWinResults() + getNumberOfValidDrawResults() + getNumberOfValidLoseResults()));
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

	private boolean[][] setValidation(int[][] prediction, double[][] lastLayerOut, double probabilityThreshold, double stdThreshold) {
		boolean[][] validationMatrix = new boolean[prediction.length][prediction[0].length];
		double[] sumOfEpochResults = sumOfEpochResults(lastLayerOut);
		double[][] probabilities = probabilities(lastLayerOut, sumOfEpochResults);
		double[] averageOfProbabilities = averageOfProbabilities(probabilities);
		double[] stdOfProbabilities = stdOfProbabilities(probabilities, averageOfProbabilities);

		for (int l = 0; l < lastLayerOut[0].length; l++) {
			for (int i = 0; i < validationMatrix.length; i++) {
				validationMatrix[i][l] = validateResult(probabilities, averageOfProbabilities, stdOfProbabilities, probabilityThreshold, stdThreshold, i, l);
			}
		}

		return validationMatrix;
	}

	private double[] sumOfEpochResults(double[][] lastLayerOut) {
		double[] sumOfEpochResults = new double[lastLayerOut[0].length];
		for (int l = 0; l < lastLayerOut[0].length; l++) {
			sumOfEpochResults[l] = 0;
			for (int i = 0; i < lastLayerOut.length; i++)
				sumOfEpochResults[l] += 1 + lastLayerOut[i][l];
		}
		return sumOfEpochResults;
	}

	private double[][] probabilities(double[][] lastLayerOut, double[] sumOfEpochResults) {
		double[][] probability = new double[lastLayerOut.length][lastLayerOut[0].length];

		for (int i = 0; i < lastLayerOut.length; i++) {
			for (int l = 0; l < lastLayerOut[0].length; l++) {
				probability[i][l] = (1 + lastLayerOut[i][l]) / sumOfEpochResults[l];
			}
		}
		return probability;
	}

	private double[] averageOfProbabilities(double[][] probabilities) {
		double[] averageOfProbabilities = { 0, 0, 0 };
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

	private boolean validateResult(double[][] probabilities, double[] averageOfProbabilities, double[] stdOfProbabilities, double probabilityThreshold, double stdThreshold, int condition, int epoch) {

		if (probabilities[condition][epoch] >= probabilityThreshold
				&& probabilities[condition][epoch] >= (averageOfProbabilities[condition] + stdThreshold * stdOfProbabilities[condition])) {
			return true;
		}
		return false;
	}

	private void checkResult(int[][] predictionsMatrix, double[][] target, boolean[][] validationMatrix) {
		for (int l = 0; l < target[0].length; l++) {
			if (target[0][l] == 1 && validationMatrix[0][l]) {
				setNumberOfValidWinResults(getNumberOfValidWinResults() + 1);
				if (predictionsMatrix[0][l] == 1)
					setNumberOfSuccessWinPredictions(getNumberOfSuccessWinPredictions() + 1);
			}
			if (target[1][l] == 1 && validationMatrix[1][l]) {
				setNumberOfValidDrawResults(getNumberOfValidDrawResults() + 1);
				if (predictionsMatrix[1][l] == 1)
					setNumberOfSuccessDrawPredictions(getNumberOfSuccessDrawPredictions() + 1);
			}
			if (target[2][l] == 1 && validationMatrix[2][l]) {
				setNumberOfValidLoseResults(getNumberOfValidLoseResults() + 1);
				if (predictionsMatrix[2][l] == 1)
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

	public int getNumberOfValidWinResults() {
		return numberOfValidWinResults;
	}

	public void setNumberOfValidWinResults(int numberOfValidWinResults) {
		this.numberOfValidWinResults = numberOfValidWinResults;
	}

	public int getNumberOfValidDrawResults() {
		return numberOfValidDrawResults;
	}

	public void setNumberOfValidDrawResults(int numberOfValidDrawResults) {
		this.numberOfValidDrawResults = numberOfValidDrawResults;
	}

	public int getNumberOfValidLoseResults() {
		return numberOfValidLoseResults;
	}

	public void setNumberOfValidLoseResults(int numberOfLoseResults) {
		this.numberOfValidLoseResults = numberOfLoseResults;
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
