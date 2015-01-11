package resolvers;

import initializer.Initializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.jfree.data.xy.XYSeries;

import parameters.ParametersForTraining;

public class BackPropagationAlgorithm {

	private double[][] outputBias;
	private double[][] hiddenBias;
	private XYSeries maxErrorChartData;
	private XYSeries minErrorChartData;
	private XYSeries averageErrorChartData;
	private XYSeries initialMaxErrorChartData;
	private XYSeries initialMinErrorChartData;
	private XYSeries initialAverageErrorChartData;
	private double maxError;
	private double minError;
	private double averageError;
	private double[] epochError;
	private int rotinas;

	public BackPropagationAlgorithm(Initializer initializedData, ParametersForTraining.Modifiers modifiers) {

		trainingIteration(initializedData, modifiers, false);

		initializedData = removeTrashData(initializedData, epochError, 5);

		trainingIteration(initializedData, modifiers, true);
		
		initializedData = removeTrashData(initializedData, epochError, 5);

		trainingIteration(initializedData, modifiers, true);
			
		try {
			exportInputAndErrorToCSV(epochError, initializedData.getInputMatrix());
		} catch (IOException e) {
			System.out.println("Falha ao exportar dados de entrada e erro para arquivo .CSV");
			e.printStackTrace();
		}
		System.out.format("Treinamento concluido com sucesso após:%n%d rotinas%ne com:%n%f de erro máximo%n%n", rotinas, maxError);

	}

	private void trainingIteration(Initializer initializedData, ParametersForTraining.Modifiers modifiers, boolean isFinalTraining) {
		// STEP 0
		// initialize weights
		setHiddenBias(BiasInitializator.hiddenBias(initializedData.getInputMatrix(), initializedData.getHiddenNeurons()));
		setOutputBias(BiasInitializator.outputBias(initializedData.getHiddenNeurons(), initializedData.getOutputMatrix()));

		// STEP 1
		if (isFinalTraining) {
			setMaxErrorChartData(new XYSeries("MaxError"));
			setMinErrorChartData(new XYSeries("MinError"));
			setAverageErrorChartData(new XYSeries("AverageError"));
		} else {
			setInitialMaxErrorChartData(new XYSeries("InitialMaxError"));
			setInitialMinErrorChartData(new XYSeries("InitialMinError"));
			setInitialAverageErrorChartData(new XYSeries("InitialAverageError"));
		}

		setMaxError(1);
		setMinError(1);
		setAverageError(1);
		setRotinas(0);

		while (getMaxError() > modifiers.getThresholdError() && getRotinas() < modifiers.getMaxRotinas()) {
			// STEP 2
			epochError = new double[initializedData.getInputMatrix()[0].length];
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

			setRotinas(rotinas + 1);
			setErrors();
			if (isFinalTraining)
				getPosCorrectionChartInfo();
			else
				getPreCorrectionChartInfo();
		}
	}

	private void setErrors() {
		setMaxError(WeightAndErrorCalculator.getMaxError(epochError));
		setMinError(WeightAndErrorCalculator.getMinError(epochError));
		setAverageError(WeightAndErrorCalculator.getAverageError(epochError));
	}

	private void getPosCorrectionChartInfo() {
		getMaxErrorChartData().add(rotinas, maxError);
		getMinErrorChartData().add(rotinas, minError);
		getAverageErrorChartData().add(rotinas, averageError);
	}

	private void getPreCorrectionChartInfo() {
		getInitialMaxErrorChartData().add(rotinas, maxError);
		getInitialMinErrorChartData().add(rotinas, minError);
		getInitialAverageErrorChartData().add(rotinas, averageError);
	}

	private Initializer removeTrashData(Initializer initializedData, double[] error, double percentageOfTrashToRemove) {
		int numberOfElementsToRemove = (int) Math.round(error.length * ( percentageOfTrashToRemove / 100));
		double[] errorAux = error.clone();
		Arrays.sort(errorAux);

		int[] indexesToRemove = findOriginalIndexesOfHigherElements(error, errorAux, numberOfElementsToRemove);

		for (int i = indexesToRemove.length - 1; i >= 0; i--) {
			error = ArrayUtils.remove(error, indexesToRemove[i]);
			initializedData.setInputMatrix(removeSelectedIndexFromMatrix(initializedData.getInputMatrix(), indexesToRemove[i]));
			initializedData.setOutputMatrix(removeSelectedIndexFromMatrix(initializedData.getOutputMatrix(), indexesToRemove[i]));
			initializedData.setTargetMatrix(removeSelectedIndexFromMatrix(initializedData.getTargetMatrix(), indexesToRemove[i]));
		}

		return initializedData;
	}

	private double[][] removeSelectedIndexFromMatrix(double[][] matrix, int index) {
		for (int j = 0; j < matrix.length; j++) {
			double[] matrixArray = matrix[j];
			matrixArray = ArrayUtils.remove(matrixArray, index);
			matrix[j] = matrixArray;
		}
		return matrix;
	}

	private int[] findOriginalIndexesOfHigherElements(double[] error, double[] errorAux, int numberOfElementsToRemove) {
		int[] indexesToRemove = new int[numberOfElementsToRemove];
		for (int i = 0; i < numberOfElementsToRemove; i++) {
			for (int j = 0; j < error.length; j++) {
				if (errorAux[errorAux.length - 1 - i] == error[j]) {
					indexesToRemove[i] = j;
				}
			}
		}
		Arrays.sort(indexesToRemove);
		return indexesToRemove;
	}

	private void exportInputAndErrorToCSV(double[] error, double[][] inputMatrix) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Exported Errors.csv"));
		bw.append("Error,");
		for (double result : error) {
			bw.append(Double.toString(result));
			bw.append(",");
		}
		bw.newLine();
		int neuronCounter = 0;
		for (double[] neuron : inputMatrix) {
			neuronCounter++;
			bw.append("InputMatrix" + neuronCounter + ",");
			for (double result : neuron) {
				bw.append(Double.toString(result));
				bw.append(",");
			}
			bw.newLine();
		}

		bw.close();
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

	public XYSeries getMaxErrorChartData() {
		return maxErrorChartData;
	}

	public void setMaxErrorChartData(XYSeries chartData) {
		this.maxErrorChartData = chartData;
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

	public XYSeries getInitialMaxErrorChartData() {
		return initialMaxErrorChartData;
	}

	public void setInitialMaxErrorChartData(XYSeries initialMaxErrorChartData) {
		this.initialMaxErrorChartData = initialMaxErrorChartData;
	}

	public XYSeries getInitialMinErrorChartData() {
		return initialMinErrorChartData;
	}

	public void setInitialMinErrorChartData(XYSeries initialMinErrorChartData) {
		this.initialMinErrorChartData = initialMinErrorChartData;
	}

	public XYSeries getInitialAverageErrorChartData() {
		return initialAverageErrorChartData;
	}

	public void setInitialAverageErrorChartData(XYSeries initialAverageErrorChartData) {
		this.initialAverageErrorChartData = initialAverageErrorChartData;
	}
}
