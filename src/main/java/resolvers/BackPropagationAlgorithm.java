package resolvers;

import org.jfree.data.xy.XYSeries;

import Initializer.Initializer;
import Parameters.ParametersForTraining;
import calculators.BackpropagationCalculator;
import calculators.BiasInitializator;
import calculators.FeedforwardCalculator;
import calculators.WeightAndErrorCalculator;

public class BackPropagationAlgorithm {

	private double[][] outputBias;
	private double[][] hiddenBias;
	private XYSeries maxErrorChartData;
	private XYSeries minErrorChartData;
	private XYSeries averageErrorChartData;
	private double maxError;
	private double minError;
	private double averageError;
	private int rotinas;

	public BackPropagationAlgorithm(Initializer initializedData, ParametersForTraining.Modifiers modifiers) {

		// STEP 0
		// initialize weights
		setHiddenBias(BiasInitializator.hiddenBias(initializedData.getInputMatrix(), initializedData.getHiddenNeurons()));
		setOutputBias(BiasInitializator.outputBias(initializedData.getHiddenNeurons(), initializedData.getOutputMatrix()));

		// STEP 1
		setMaxErrorChartData(new XYSeries("MaxError"));
		setMinErrorChartData(new XYSeries("MinError"));
		setAverageErrorChartData(new XYSeries("AverageError"));
		setMaxError(1);
		setMinError(1);
		setAverageError(1);
		setRotinas(0);

		while (getMaxError() > modifiers.getThresholdError() && getRotinas() < modifiers.getMaxRotinas()) {
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
				double[] outError = BackpropagationCalculator.outErrorInformationTerm(initializedData.getTargetMatrix(), l, lastLayerOut,
						outDerivative);
				double[][] outCorrection = BackpropagationCalculator.weightCorrectionTerm(modifiers.getLearningRate(), outError, hiddenLayerOut);

				// STEP 7
				double[] midDeltaInput = BackpropagationCalculator.backwardsWeightedInputSignal(outError, getOutputBias());
				double[] midDerivative = BackpropagationCalculator.bipolarSigmoidDerivative(hiddenLayerOut);
				double[] midError = BackpropagationCalculator.midErrorInformationTerm(midDeltaInput, midDerivative);
				double[][] midCorrection = BackpropagationCalculator.weightCorrectionTerm(modifiers.getLearningRate(), midError, firstLayer);

				// Update weights and biases
				// STEP 8
				setOutputBias(WeightAndErrorCalculator.updateWeights(getOutputBias(), outCorrection));
				setHiddenBias(WeightAndErrorCalculator.updateWeights(getHiddenBias(), midCorrection));

				// Error calculation
				epochError[l] = WeightAndErrorCalculator.squaredError(lastLayerOut, initializedData.getTargetMatrix(), l);
			}

			setMaxError(WeightAndErrorCalculator.getMaxError(epochError));
			setMinError(WeightAndErrorCalculator.getMinError(epochError));
			setAverageError(WeightAndErrorCalculator.getAverageError(epochError));
			setRotinas(rotinas + 1);
			getMaxErrorChartData().add(rotinas, maxError);
			getMinErrorChartData().add(rotinas, minError);
			getAverageErrorChartData().add(rotinas, averageError);
		}

		System.out.format("Treinamento concluido com sucesso após:%n%d rotinas%ne com:%n%f de erro máximo%n%n", rotinas, maxError);

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

	public XYSeries getMaxErrorChartData() {
		return maxErrorChartData;
	}

	public void setMaxErrorChartData(XYSeries chartData) {
		this.maxErrorChartData = chartData;
	}

	public double getMaxError() {
		return maxError;
	}

	public void setMaxError(double error) {
		this.maxError = error;
	}

	public int getRotinas() {
		return rotinas;
	}

	public void setRotinas(int rotinas) {
		this.rotinas = rotinas;
	}

	public double getMinError() {
		return minError;
	}

	public void setMinError(double minError) {
		this.minError = minError;
	}

	public double getAverageError() {
		return averageError;
	}

	public void setAverageError(double averageError) {
		this.averageError = averageError;
	}

	public XYSeries getMinErrorChartData() {
		return minErrorChartData;
	}

	public void setMinErrorChartData(XYSeries minErrorChartData) {
		this.minErrorChartData = minErrorChartData;
	}

	public XYSeries getAverageErrorChartData() {
		return averageErrorChartData;
	}

	public void setAverageErrorChartData(XYSeries averageErrorChartData) {
		this.averageErrorChartData = averageErrorChartData;
	}
}
