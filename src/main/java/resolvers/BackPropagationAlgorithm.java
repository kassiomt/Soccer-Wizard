package resolvers;

import org.jfree.data.xy.XYSeries;

import calculators.BackpropagationCalculator;
import calculators.BiasInitializator;
import calculators.FeedforwardCalculator;
import calculators.WeightAndErrorCalculator;

public class BackPropagationAlgorithm {

	private double[][] outputBias;
	private double[][] hiddenBias;
	private XYSeries chartData;
    private double error;
    private int rotinas;

	public BackPropagationAlgorithm(Initializer initializedData, double learningRate, double minError, int maxRotinas) {

	// STEP 0
	// initialize weights
	setHiddenBias(BiasInitializator.hiddenBias(initializedData.getInputMatrix(), initializedData.getHiddenNeurons()));
	setOutputBias(BiasInitializator.outputBias(initializedData.getHiddenNeurons(), initializedData.getOutputMatrix()));
	
	// STEP 1	
	setChartData(new XYSeries("Error"));
    setError(1);
    setRotinas(0);
	
	while (getError() > minError && getRotinas() < maxRotinas) {
		// STEP 2
		double[] epochError = new double[initializedData.getInputMatrix()[0].length];
		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
			// Feedforward
			// STEP 3
			double[] firstLayer = FeedforwardCalculator.receivesInputSignal(initializedData.getInputMatrix(), l);

			// STEP 4
			double[] hiddenLayerIn = FeedforwardCalculator.weightedInputSignal(firstLayer, getHiddenBias());
			double[] hiddenLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(hiddenLayerIn);
			hiddenLayerOut[0] = 1;

			// STEP 5
			double[] lastLayerIn = FeedforwardCalculator.weightedInputSignal(hiddenLayerOut, getOutputBias());
			double[] lastLayerOut = FeedforwardCalculator.bipolarSigmoidActivationFunction(lastLayerIn);

			// Backpropagation of Error
			// STEP 6
			double[] outDerivative = BackpropagationCalculator.bipolarSigmoidDerivative(lastLayerOut);
			double[] outError = BackpropagationCalculator.outErrorInformationTerm(initializedData.getTargetMatrix(), l, lastLayerOut, outDerivative);
			double[][] outCorrection = BackpropagationCalculator.weightCorrectionTerm(learningRate, outError, hiddenLayerOut);

			// STEP 7
			double[] midDeltaInput = BackpropagationCalculator.backwardsWeightedInputSignal(outError, getOutputBias());
			double[] midDerivative = BackpropagationCalculator.bipolarSigmoidDerivative(hiddenLayerOut);
			double[] midError = BackpropagationCalculator.midErrorInformationTerm(midDeltaInput, midDerivative);
			double[][] midCorrection = BackpropagationCalculator.weightCorrectionTerm(learningRate, midError, firstLayer);


			// Update weights and biases
			// STEP 8
			setOutputBias(WeightAndErrorCalculator.updateWeights(getOutputBias(), outCorrection));
			setHiddenBias(WeightAndErrorCalculator.updateWeights(getHiddenBias(), midCorrection));

			// Error calculation
			epochError[l] = WeightAndErrorCalculator.squaredError(lastLayerOut, initializedData.getTargetMatrix(), l);
		}

		setError(WeightAndErrorCalculator.getMaxError(epochError));
		setRotinas(rotinas+1);
		getChartData().add(rotinas, error);
	}

	System.out.format("Treinamento concluido com sucesso após:%n%d rotinas%ne com:%n%f de erro máximo%n%n", rotinas, error);

	}

	public double[][] getOutputBias() {
		return outputBias;
	}

	public void setOutputBias(double[][] outputBias) {
		this.outputBias = outputBias;
	}

	public double[][] getHiddenBias() {
		return hiddenBias;
	}

	public void setHiddenBias(double[][] hiddenBias) {
		this.hiddenBias = hiddenBias;
	}

	public XYSeries getChartData() {
		return chartData;
	}

	public void setChartData(XYSeries chartData) {
		this.chartData = chartData;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public int getRotinas() {
		return rotinas;
	}

	public void setRotinas(int rotinas) {
		this.rotinas = rotinas;
	}
}
