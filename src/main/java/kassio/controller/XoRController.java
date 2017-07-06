package kassio.controller;

import kassio.initializer.Initializer;
import kassio.initializer.XoRInitializer;
import kassio.parameters.Parameters;
import kassio.parameters.ParametersForTraining;
import kassio.resolvers.BackPropagationAlgorithm;
import kassio.resolvers.FeedforwardAlgorithm;

import static kassio.SoccerWizard.*;

public class XoRController {

    public static void botaoInicializarXoRActionPerformed(String inputLearningRate, String inputRotinas, String inputErro) {
        setParametersForTraining(
                Double.parseDouble(inputLearningRate),
                Integer.parseInt(inputRotinas),
                Double.parseDouble(inputErro),
                2, 2, 2, 2, 5);

        System.out.println("Learning Rate, Máximo de Rotinas e Erro Mínimo ATUALIZADOS\n");

        initializeDataXorForTraining();
        System.out.println("DADOS XOR INICIALIZADOS\n");
    }

        public static Parameters setParametersForTraining(double learningRate, int maxRotinas, double thresholdError, int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, int hiddenNeurons) {
            return trainingParameters = new ParametersForTraining(learningRate, maxRotinas, thresholdError, numTeams, numTeamsTotal, numRodadas, numRodadasTotal, hiddenNeurons);
        }


        static Initializer initializeDataXorForTraining() {
            return dataForTraining = new XoRInitializer();
        }


    public static void botaoTreinarXoRActionPerformed() {
        backPropagationAlgorithm();
        System.out.println("REDE TREINADA botaoTreinarActionPerformed\n");
    }

        public static BackPropagationAlgorithm backPropagationAlgorithm() {
            return bias = new BackPropagationAlgorithm(dataForTraining, trainingParameters.getModifiers());
        }

    public static FeedforwardAlgorithm botaoRodarXoRActionPerformed() {
        return xorFeedforwardAlgorithm();
    }

        public static FeedforwardAlgorithm xorFeedforwardAlgorithm() {
            return new FeedforwardAlgorithm(bias);
        }

}
