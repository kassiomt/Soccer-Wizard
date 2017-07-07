package kassio;

import kassio.initializer.Initializer;
import kassio.panel.TeamsMapBuilder;
import kassio.parameters.ParametersForApplication;
import kassio.parameters.ParametersForTraining;
import kassio.resolvers.BackPropagationAlgorithm;

import java.util.logging.Logger;

public class GlobalData {

    public static ParametersForTraining trainingParameters;
    public static ParametersForApplication applicationParameters;

    public static Initializer dataForTraining;
    public static Initializer dataForApplication;

    public static TeamsMapBuilder trainingTeamsMapBuilt;
    public static TeamsMapBuilder applicationTeamsMapBuilt;

    public static BackPropagationAlgorithm bias;

    public static Logger logger = Logger.getLogger(SoccerWizard.class.getName());
}
