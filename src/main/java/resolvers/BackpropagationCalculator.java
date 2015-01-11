package resolvers;

public class BackpropagationCalculator {
	public static double[] bipolarSigmoidDerivative(double[] layerOutput) {
		double[] derivative = new double[layerOutput.length];

		for (int j = 0; j < derivative.length; j++) {
			derivative[j] = 0.5 * (1 + layerOutput[j]) * (1 - layerOutput[j]);
		}
		return derivative;
	}

	public static double[] outErrorInformationTerm(double[][] targetMatrix, int patternCounter, double[] layerOutput, double[] layerDerivative) {

		double[] error = new double[layerOutput.length];

		for (int j = 0; j < error.length; j++) {
			error[j] = (targetMatrix[j][patternCounter] - layerOutput[j]) * layerDerivative[j];
		}
		return error;
	}

	public static double[][] weightCorrectionTerm(double learningRate, double[] followingError, double[] previousOutput) {
		double[][] correctionTerm = new double[previousOutput.length][followingError.length];

		for (int i = 0; i < previousOutput.length; i++) {
			for (int j = 0; j < followingError.length; j++) {
				correctionTerm[i][j] = learningRate * followingError[j] * previousOutput[i];
			}
		}
		return correctionTerm;
	}

	public static double[] backwardsWeightedInputSignal(double[] inputUnit, double[][] bias) {
		double[] deltaInput = new double[bias.length];

		for (int i = 1; i < bias.length; i++) {
			for (int j = 0; j < inputUnit.length; j++) {
				deltaInput[i] = deltaInput[i] + inputUnit[j] * bias[i][j];
			}
		}
		return deltaInput;
	}

	public static double[] midErrorInformationTerm(double[] deltaInput, double[] derivative) {
		double[] error = new double[deltaInput.length];

		for (int j = 0; j < error.length; j++) {
			error[j] = deltaInput[j] * derivative[j];
		}
		return error;
	}
}
