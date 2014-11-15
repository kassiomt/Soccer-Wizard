package Initializer;

public class XoRInitializer extends Initializer{
	
	public XoRInitializer() {
		
		gamesRandom = new int[] {0,1,2,3};
		
		setInputMatrix();

		setHiddenNeurons(new double[5]);
		setOutputMatrix(new double[1][4]);

		setTargerMatrix();
	}

	private void setTargerMatrix() {
		targetMatrix = new double[1][4];
		targetMatrix[0][0] = -1;
		targetMatrix[0][1] = 1;
		targetMatrix[0][2] = 1;
		targetMatrix[0][3] = -1;
	}

	private void setInputMatrix() {
		inputMatrix = new double [3][4];
		
		inputMatrix[0][0] = 1;		inputMatrix[1][0] = 0;		inputMatrix[2][0] = 0;
		inputMatrix[0][1] = 1;		inputMatrix[1][1] = 0;		inputMatrix[2][1] = 1;
		inputMatrix[0][2] = 1;		inputMatrix[1][2] = 1;		inputMatrix[2][2] = 0;
		inputMatrix[0][3] = 1;		inputMatrix[1][3] = 1;		inputMatrix[2][3] = 1;		
	}

	public int[] getGamesRandom() {
		return gamesRandom;
	}
}