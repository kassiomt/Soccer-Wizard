package resolvers;

public class FeedforwardCalculator {
	public static double[] receivesInputSignal(double inputMatrix[][], int l) {
		double[] inputUnits = new double[inputMatrix.length];

		for (int i = 0; i < inputMatrix.length; i++) {
			inputUnits[i] = inputMatrix[i][l];
		}
		return inputUnits;
	}

	public static double[] weightedInputSignal(double[] inputUnit, double[][] bias) {
		double[] netInput = new double[bias[0].length];

		for (int j = 0; j < bias[0].length; j++) {
			for (int i = 0; i < inputUnit.length; i++) {
				netInput[j] = netInput[j] + inputUnit[i] * bias[i][j];
			}
		}
		return netInput;
	}

	public static double[] bipolarSigmoidActivationFunction(double[] layerInput) {
		double[] layerOutput = new double[layerInput.length];

		for (int j = 0; j < layerOutput.length; j++) {
			layerOutput[j] = -1 + 2 / (1 + Math.exp(-layerInput[j]));
		}
		return layerOutput;
	}
}