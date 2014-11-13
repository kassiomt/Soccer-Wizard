package resolvers;

import calculators.FeedforwardCalculator;

public class FeedforwardAlgorithm {

	private double[][] lastLayerOutMatrix;
	private ApplicationErrorAnalysis errorAnalysis;
	
	public FeedforwardAlgorithm(Initializer initializedData, BackPropagationAlgorithm bias, double confidenceInterval) {

		System.out.println("Começando aplicação");
		double[][] target = initializedData.getTargetMatrix();
		lastLayerOutMatrix = new double[initializedData.outputDataColumns().length*2][initializedData.getInputMatrix()[0].length];
		
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
			getLastLayerOutMatrix()[3][l] = lastLayerOut[3];
			getLastLayerOutMatrix()[4][l] = lastLayerOut[4];
			getLastLayerOutMatrix()[5][l] = lastLayerOut[5];
			
		}
		
		setErrorAnalysis(new ApplicationErrorAnalysis(initializedData, confidenceInterval, lastLayerOutMatrix, target));

		System.out.println("Aplicação terminada");
	}

	public FeedforwardAlgorithm(BackPropagationAlgorithm bias) {

		System.out.println("Começando aplicação XOR");
		lastLayerOutMatrix = new double[1][4];

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
	
	public ApplicationErrorAnalysis getErrorAnalysis() {
		return errorAnalysis;
	}

	public void setErrorAnalysis(ApplicationErrorAnalysis errorAnalysis) {
		this.errorAnalysis = errorAnalysis;
	}
}