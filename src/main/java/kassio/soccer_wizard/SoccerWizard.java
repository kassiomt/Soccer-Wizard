package kassio.soccer_wizard;

import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import resolvers.BackPropagationAlgorithm;
import resolvers.FeedforwardAlgorithm;
import resolvers.Initializer;
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
		return dataForTraining = new Initializer();
	}
	
	public static ParametersForTraining setParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, int hiddenNeurons) {
		return trainingParameters = new ParametersForTraining(learningRate, maxRotinas, thresholdError, numTeams, numTeamsTotal, numRodadas, numRodadasTotal, hiddenNeurons);
	}
	
	public static Initializer initializeDataForTraining() {
		return dataForTraining = new Initializer(trainingParameters.getNumTeams(),
				trainingParameters.getNumTeamsTotal(), trainingParameters.getNumRodadas(),
				trainingParameters.getNumRodadasTotal(), trainingParameters.getHiddenNeurons(), 
				teamsMapBuilt.getTeamsMap());
	}
	
	public static ParametersForApplication setParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double confidenceInterval) {
		return applicationParameters = new ParametersForApplication(numTeams, numTeamsTotal, numRodadas, numRodadasTotal, confidenceInterval);
	}
	
	public static Initializer initializeDataToApplyTrainedNet(){
		return dataForApplication = new Initializer(applicationParameters.getNumTeams(),
				applicationParameters.getNumTeamsTotal(), applicationParameters.getNumRodadas(),
				applicationParameters.getNumRodadasTotal(), teamsMapBuilt.getTeamsMap(), dataForTraining.getGamesRandom());
	}
	
	public static BackPropagationAlgorithm backPropagationAlgorithm() {
		return bias = new BackPropagationAlgorithm(dataForTraining, trainingParameters.getLearningRate(), trainingParameters.getThresholdError(), trainingParameters.getMaxRotinas());
	}
	
	public static FeedforwardAlgorithm xorFeedforwardAlgorithm() {
		return new FeedforwardAlgorithm(bias);
	}
	
	public static FeedforwardAlgorithm feedforwardAlgorithm() {
		return new FeedforwardAlgorithm(dataForApplication, bias, applicationParameters.getConfidenceInterval());
	}
	
	public static TeamsMapBuilder buildTeams(Workbook workbook){
		return teamsMapBuilt = new TeamsMapBuilder(workbook);
	}
	

}