package kassio;

import jxl.read.biff.BiffException;
import kassio.initializer.Initializer;
import kassio.panel.SoccerWizardGUI;
import kassio.panel.TeamsMapBuilder;
import kassio.parameters.ParametersForApplication;
import kassio.parameters.ParametersForTraining;
import kassio.resolvers.BackPropagationAlgorithm;

import java.io.IOException;


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
 
		new SoccerWizardGUI().setVisible(true);
	}
	




	

}