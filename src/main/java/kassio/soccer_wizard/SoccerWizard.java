package kassio.soccer_wizard;

import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import resolvers.BackPropagationAlgorithm;
import resolvers.FeedforwardAlgorithm;
import resolvers.Initializer;
import SoccerGUI.SoccerGUI;


public class SoccerWizard {
	
    public static TrainingParameters trainingParameters = new TrainingParameters();
    public static Initializer initializedData;
	public static BackPropagationAlgorithm bias;
	public static Initializer dataForApplication;

	public static TeamsMapBuilder teamsMapBuilt;
    

	public static void main(String[] args) throws BiffException, IOException {
		System.out.println("Hello world! Let's get the party started!\n");
 
		new SoccerGUI().setVisible(true);
		
	}
	
	public static Initializer initializeDataXorForTraining() {
		return initializedData = new Initializer();
	}
	
	public static Initializer initializeDataForTraining() {
		return initializedData = new Initializer(trainingParameters.getNumTeamsForTraining(),
				trainingParameters.getNumTeamsTotalForTraining(), trainingParameters.getNumRodadasForTraining(),
				trainingParameters.getNumRodadasTotalForTraining(), trainingParameters.getHiddenNeurons(), 
				teamsMapBuilt.getTeamsMap());
	}
	
	public static Initializer initializeDataToApplyTrainedNet(){
		return dataForApplication = new Initializer(trainingParameters.getNumTeamsForApplication(),
				trainingParameters.getNumTeamsTotalForApplication(), trainingParameters.getNumRodadasForApplication(),
				trainingParameters.getNumRodadaTotalForApplication(), teamsMapBuilt.getTeamsMap(), initializedData.getGamesRandom());
	}
	
	public static BackPropagationAlgorithm backPropagationAlgorithm() {
		return bias = new BackPropagationAlgorithm(initializedData, trainingParameters.getLearningRate(), trainingParameters.getThresholdError(), trainingParameters.getMaxRotinas());
	}
	
	public static FeedforwardAlgorithm xorFeedforwardAlgorithm() {
		return new FeedforwardAlgorithm(bias);
	}
	
	public static FeedforwardAlgorithm feedforwardAlgorithm() {
		return new FeedforwardAlgorithm(dataForApplication, bias, trainingParameters.getConfidenceInterval());
	}
	
	public static TeamsMapBuilder buildTeams(Workbook workbook){
		return teamsMapBuilt = new TeamsMapBuilder(workbook);
	}
	

}