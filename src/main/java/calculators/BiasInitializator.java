package calculators;

public class BiasInitializator {

	public static double[][] hiddenBias(double[][] inputMatrix, double[] hiddenNeurons) {
		double[][] hiddenBias = setHiddenRandomBias(inputMatrix, hiddenNeurons);
		hiddenBias = nguyenWidrowInitialization(inputMatrix.length, hiddenNeurons.length, hiddenBias);
		return hiddenBias;
	}

	public static double[][] outputBias(double[] hiddenNeurons, double[][] outputMatrix) {
		double[][] outputBias = setOutputRandomBias(hiddenNeurons, outputMatrix);
		return outputBias;
	}

	
	private static double[][] setHiddenRandomBias(double[][] inputMatrix, double[] hiddenNeurons) {
		double[][] hiddenBias = new double[inputMatrix.length][hiddenNeurons.length];
		for (int i = 0; i < inputMatrix.length; i++) {
			for (int j = 1; j < hiddenNeurons.length; j++) {
				hiddenBias[i][j] = Math.random() - 0.5;
			}
		}
		return hiddenBias;
	}

	private static double[][] setOutputRandomBias(double[] hiddenNeurons, double[][] outputMatrix) {
		double[][] outputBias = new double[hiddenNeurons.length][outputMatrix.length];
		for (int j = 0; j < hiddenNeurons.length; j++) {
			for (int k = 0; k < outputMatrix.length; k++) {
				outputBias[j][k] = Math.random() - 0.5;
			}
		}
		return outputBias;
	}
	
	private static double[][] nguyenWidrowInitialization(int inputLenght, int hiddenLenght, double oldHiddenBias[][]) {
		double[][] biasRootSquare = new double[inputLenght][hiddenLenght];
		double[][] newHiddenBias = new double[inputLenght][hiddenLenght];
		double beta = 0.7 * (Math.pow(hiddenLenght - 1,	(1 / (double) (inputLenght - 1))));
		
		for (int j = 1; j < hiddenLenght; j++) {
			double oldAbsoluteHiddenBias = computeOldAbsoluteHiddenBias(inputLenght, oldHiddenBias, biasRootSquare, j);
			reinitializeWeights(inputLenght, oldHiddenBias, beta, newHiddenBias, j, oldAbsoluteHiddenBias);
		}
		return newHiddenBias;
	}

	private static double computeOldAbsoluteHiddenBias(int inputLenght,	double[][] oldHiddenBias, double[][] biasRootSquare, int j) {
		double biasRootSquareSum = 0;
		for (int i = 1; i < inputLenght; i++) {
			biasRootSquare[i][j] = oldHiddenBias[i][j] * oldHiddenBias[i][j];
			biasRootSquareSum = biasRootSquareSum + biasRootSquare[i][j];
		}
		return Math.sqrt(biasRootSquareSum);
	}
	
	private static void reinitializeWeights(int inputLenght, double[][] oldHiddenBias, double beta, double[][] newHiddenBias,
			int j, double oldAbsoluteHiddenBias) {
		for (int i = 1; i < inputLenght; i++) {
			newHiddenBias[i][j] = beta * oldHiddenBias[i][j] / oldAbsoluteHiddenBias;
		}

		newHiddenBias[0][j] = (Math.random() * 2 * beta) - beta;
	}
}