package kassio.service;

import kassio.resolvers.BackPropagationAlgorithm;

import static kassio.GlobalData.bias;
import static kassio.GlobalData.dataForTraining;
import static kassio.GlobalData.trainingParameters;

public class TrainingService {

    public static BackPropagationAlgorithm backPropagationAlgorithm() {
        bias = new BackPropagationAlgorithm(dataForTraining, trainingParameters.getModifiers());
        return bias;
    }
}
