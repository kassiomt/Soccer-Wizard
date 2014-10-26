package resolvers;

import calculators.FeedforwardCalculator;

public class FeedforwardAlgorithm {

	private double[][] lastLayerOutMatrix;
	private double winSuccessRatio;
	private double drawSuccessRatio;
	private double loseSuccessRatio;
	private double anyoneSuccessRatio;
	private double bothSuccessRatio;
	
	
	public FeedforwardAlgorithm(Initializer initializedData, BackPropagationAlgorithm bias) {

		System.out.println("Começando aplicação");
		double[][] target = initializedData.getTargetMatrix();
		setLastLayerOutMatrix(new double[initializedData.resultsDataColumns().length][initializedData.getInputMatrix()[0].length]);
		
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
			
		}
		
		//double erro = bias.getError();
		int numberOfSuccessWin = 0;
		int numberOfSuccessDraw = 0;
		int numberOfSuccessLose = 0;
		int[] controlOfSuccessAnyone = {0,0,0};
		int numberOfSuccessAnyone = 0;
		int numberOfSuccessBoth = 0;
		
		for (int l = 0; l < initializedData.getInputMatrix()[0].length; l++) {
			if ( (target[0][l]-0.5 < getLastLayerOutMatrix()[0][l] && getLastLayerOutMatrix()[0][l] < target[0][l]+0.5)// ||
				 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]+erro && getLastLayerOutMatrix()[0][l]+erro < target[0][l]+0.1) ||
				 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]-erro && getLastLayerOutMatrix()[0][l]-erro < target[0][l]+0.1)
					) {
				numberOfSuccessWin++;
				controlOfSuccessAnyone[0] = 1;
			}
			if ( (target[1][l]-0.5 < getLastLayerOutMatrix()[1][l] && getLastLayerOutMatrix()[1][l] < target[1][l]+0.5)// ||
				 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]+erro && getLastLayerOutMatrix()[0][l]+erro < target[0][l]+0.1) ||
				 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]-erro && getLastLayerOutMatrix()[0][l]-erro < target[0][l]+0.1)
					) {
				numberOfSuccessDraw++;
				controlOfSuccessAnyone[1] = 1;
			}
			if ( (target[2][l]-0.5 < getLastLayerOutMatrix()[2][l] && getLastLayerOutMatrix()[2][l] < target[2][l]+0.5)// ||
					 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]+erro && getLastLayerOutMatrix()[0][l]+erro < target[0][l]+0.1) ||
					 //(target[0][l]-0.1 < getLastLayerOutMatrix()[0][l]-erro && getLastLayerOutMatrix()[0][l]-erro < target[0][l]+0.1)
						) {
				numberOfSuccessLose	++;
				controlOfSuccessAnyone[2] = 1;
			}
			if ( controlOfSuccessAnyone[0] == 1 || controlOfSuccessAnyone[1] == 1 || controlOfSuccessAnyone[2] == 1) {
				numberOfSuccessAnyone++;
			}
			if ( controlOfSuccessAnyone[0] == 1 && controlOfSuccessAnyone[1] == 1 && controlOfSuccessAnyone[2] == 1) {
				numberOfSuccessBoth++;
			}	
			controlOfSuccessAnyone[0] = 0;
			controlOfSuccessAnyone[1] = 0;
			controlOfSuccessAnyone[2] = 0;
		}
		
		setWinSuccessRatio((double) numberOfSuccessWin/ (double) initializedData.getInputMatrix()[0].length);
		setDrawSuccessRatio((double) numberOfSuccessDraw/ (double) initializedData.getInputMatrix()[0].length);
		setLoseSuccessRatio((double) numberOfSuccessLose/ (double) initializedData.getInputMatrix()[0].length);
		setAnyoneSuccessRatio((double) numberOfSuccessAnyone/ (double) initializedData.getInputMatrix()[0].length);
		setBothSuccessRatio((double) numberOfSuccessBoth/ (double) initializedData.getInputMatrix()[0].length);

		System.out.println("Aplicação terminada");
	}

	public FeedforwardAlgorithm(BackPropagationAlgorithm bias) {

		System.out.println("Começando aplicação XOR");
		setLastLayerOutMatrix(new double[1][4]);

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

	public void setLastLayerOutMatrix(double[][] lastLayerOutMatrix) {
		this.lastLayerOutMatrix = lastLayerOutMatrix;
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
	
	public double getAnyoneSuccessRatio() {
		return anyoneSuccessRatio;
	}

	public void setAnyoneSuccessRatio(double anyoneSuccessRatio) {
		this.anyoneSuccessRatio = anyoneSuccessRatio;
	}

	public double getBothSuccessRatio() {
		return bothSuccessRatio;
	}

	public void setBothSuccessRatio(double bothSuccessRatio) {
		this.bothSuccessRatio = bothSuccessRatio;
	}
}