package kassio.soccer_wizard;

import initializer.Initializer;
import initializer.InitializerForApplication;
import initializer.InitializerForTraining;
import initializer.XoRInitializer;

import java.io.IOException;

import parameters.Parameters;
import parameters.ParametersForApplication;
import parameters.ParametersForTraining;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import resolvers.BackPropagationAlgorithm;
import resolvers.FeedforwardAlgorithm;
import SoccerGUI.SoccerGUI;
import SoccerGUI.TeamsMapBuilder;


public class SoccerWizard {
	
    public static ParametersForTraining trainingParameters;
    public static ParametersForApplication applicationParameters;
    public static Initializer dataForTraining;
	public static BackPropagationAlgorithm bias;
	public static Initializer dataForApplication;

	public static TeamsMapBuilder trainingTeamsMapBuilt;
	public static TeamsMapBuilder applicationTeamsMapBuilt;
	

	public static void main(String[] args) throws BiffException, IOException {
		System.out.println("Hello world! Let's get the party started!\n");
 
		new SoccerGUI().setVisible(true);
		
	}
	
	public static Initializer initializeDataXorForTraining() {
		return dataForTraining = new XoRInitializer();
	}
	
	public static Parameters setParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, int hiddenNeurons) {
		return trainingParameters = new ParametersForTraining(learningRate, maxRotinas, thresholdError, numTeams, numTeamsTotal, numRodadas, numRodadasTotal, hiddenNeurons);
	}
	
	public static Initializer initializeDataForTraining() {
		return dataForTraining = new InitializerForTraining(trainingParameters.getStructure(), trainingTeamsMapBuilt.getTeamsMap());
	}
	
	public static Parameters setParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double confidenceInterval) {
		return applicationParameters = new ParametersForApplication(numTeams, numTeamsTotal, numRodadas, numRodadasTotal, confidenceInterval);
	}
	
	public static Initializer initializeDataForApplication(){
		return dataForApplication = new InitializerForApplication(applicationParameters.getStructure(), applicationTeamsMapBuilt.getTeamsMap(), dataForTraining.getGamesRandom());
	}
	
	public static BackPropagationAlgorithm backPropagationAlgorithm() {
		return bias = new BackPropagationAlgorithm(dataForTraining, trainingParameters.getModifiers());
	}
	
	public static FeedforwardAlgorithm xorFeedforwardAlgorithm() {
		return new FeedforwardAlgorithm(bias);
	}
	
	public static FeedforwardAlgorithm feedforwardAlgorithm() {
		return new FeedforwardAlgorithm(dataForApplication, bias, applicationParameters.getModifiers().getConfidenceInterval());
	}
	
	public static TeamsMapBuilder buildTeams(Workbook workbook){
		return new TeamsMapBuilder(workbook);
	}
	

}