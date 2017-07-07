package kassio.service;

import kassio.initializer.Initializer;
import kassio.initializer.XoRInitializer;
import kassio.parameters.Parameters;
import kassio.parameters.ParametersForTraining;

import static kassio.GlobalData.dataForTraining;
import static kassio.GlobalData.trainingParameters;

public class InitializeService {

    public static Parameters setParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, int hiddenNeurons) {
        trainingParameters = new ParametersForTraining(learningRate, maxRotinas, thresholdError, numTeams, numTeamsTotal, numRodadas, numRodadasTotal, hiddenNeurons);
        return trainingParameters;
    }

    public static Initializer initializeDataXorForTraining() {
        dataForTraining = new XoRInitializer();
        return dataForTraining;
    }
}
