package kassio.controller;

import kassio.resolvers.FeedforwardAlgorithm;
import kassio.service.InitializeService;
import kassio.service.RunService;
import kassio.service.TrainingService;

import static kassio.GlobalData.logger;

public class XoRController {

    public static void botaoInicializarXoRActionPerformed(String inputLearningRate, String inputRotinas, String inputErro) {
        InitializeService.setParametersForTraining(
                Double.parseDouble(inputLearningRate),
                Integer.parseInt(inputRotinas),
                Double.parseDouble(inputErro),
                2, 2, 2, 2, 5);

        logger.info("Learning Rate, Máximo de Rotinas e Erro Mínimo ATUALIZADOS\n");

        InitializeService.initializeDataXorForTraining();
        logger.info("DADOS XOR INICIALIZADOS\n");
    }

    public static void botaoTreinarXoRActionPerformed() {
        TrainingService.backPropagationAlgorithm();
        logger.info("REDE TREINADA\n");
    }

    public static FeedforwardAlgorithm botaoRodarXoRActionPerformed() {
        return RunService.xorFeedforwardAlgorithm();
    }
}
