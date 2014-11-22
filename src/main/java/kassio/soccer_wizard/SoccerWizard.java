package kassio.soccer_wizard;

import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import resolvers.BackPropagationAlgorithm;
import resolvers.FeedforwardAlgorithm;
import Initializer.Initializer;
import Initializer.InitializerForApplication;
import Initializer.InitializerForTraining;
import Initializer.XoRInitializer;
import Parameters.Parameters;
import Parameters.ParametersForApplication;
import Parameters.ParametersForTraining;
import SoccerGUI.SoccerGUI;


public class SoccerWizard {
	
    public static ParametersForTraining trainingParameters;
    public static ParametersForApplication applicationParameters;
    public static Initializer dataForTraining;
	public static BackPropagationAlgorithm bias;
	public static Initializer dataForApplication;

	public static TeamsMapBuilder teamsMapBuilt;
    

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
		return dataForTraining = new InitializerForTraining(trainingParameters.getStructure(), teamsMapBuilt.getTeamsMap());
	}
	
	public static Parameters setParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double confidenceInterval) {
		return applicationParameters = new ParametersForApplication(numTeams, numTeamsTotal, numRodadas, numRodadasTotal, confidenceInterval);
	}
	
	public static Initializer initializeDataForApplication(){
		return dataForApplication = new InitializerForApplication(applicationParameters.getStructure(), teamsMapBuilt.getTeamsMap(), dataForTraining.getGamesRandom());
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
		return teamsMapBuilt = new TeamsMapBuilder(workbook);
	}
	

}