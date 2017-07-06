package kassio.resolvers;

import java.util.Arrays;

public class WeightAndErrorCalculator {
	public static double[][] updateWeights(double[][] oldBias, double[][] correctionTerm) {
		double[][] newBias = new double[oldBias.length][oldBias[0].length];
		for (int i = 0; i < oldBias.length; i++) {
			for (int j = 0; j < oldBias[0].length; j++) {
				newBias[i][j] = oldBias[i][j] + correctionTerm[i][j];
			}
		}
		return newBias;
	}

	public static double squaredError(double[] lastLayerOut, double[][] targetMatrix, int patternCounter) {
		double error = 0;
		for (int i = 0; i < targetMatrix.length; i++) {
			double base = targetMatrix[i][patternCounter] - lastLayerOut[i];
			error = error + Math.pow(base, 2);
		}
		return error;
	}

	public static double getMaxError(double[] localError) {
		Arrays.sort(localError);
		return localError[localError.length - 1];
	}

	public static double getMinError(double[] localError) {
		Arrays.sort(localError);
		return localError[0];
	}

	public static double getAverageError(double[] localError) {
		double sum = 0;
		for (double erro : localError) {
			sum += erro;
		}
		double average = (double) sum / (double) localError.length;
		return average;
	}

}