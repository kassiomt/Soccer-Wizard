package kassio.controller;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import kassio.GlobalData;
import kassio.initializer.Initializer;
import kassio.initializer.InitializerForApplication;
import kassio.initializer.InitializerForTraining;
import kassio.panel.TeamsMapBuilder;
import kassio.parameters.Parameters;
import kassio.parameters.ParametersForApplication;
import kassio.resolvers.FeedforwardAlgorithm;
import kassio.service.InitializeService;
import kassio.service.TrainingService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import static kassio.GlobalData.*;

public class SoccerWizardController implements Serializable {

    public static void buttonLoadTrainingActionPerformed() {
        try {
            JFileChooser chooser = new JFileChooser(
                    "C:\\Users\\kassio-chronos\\Desktop\\workspace\\Soccer-Wizard");
            chooser.setFileFilter(new FileNameExtensionFilter(".xls", "xls"));

            int retorno = chooser.showOpenDialog(null);


            if (retorno == JFileChooser.APPROVE_OPTION) {
                Workbook workbook = Workbook.getWorkbook(chooser.getSelectedFile());
                trainingTeamsMapBuilt = buildTeams(workbook);

                JOptionPane.showMessageDialog(null, "Campeonato para treinamento carregado!");
                logger.info("CAMPEONATO PARA TREINAMENTO CARREGADO!\n");
            }
        } catch (FileNotFoundException e) {
            logger.warning("Arquivo não encontrado!" + e.getMessage());
        } catch (BiffException e) {
            logger.warning(e.getMessage());
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

        private static TeamsMapBuilder buildTeams(Workbook workbook){
            return new TeamsMapBuilder(workbook);
        }

    public static void buttonInitializeTrainingActionPerformed(String inputLearningRate,
                                                               String inputMaxRoutine,
                                                               String inputErrorThreshold,
                                                               String inputStartTeamTraining,
                                                               String inputEndTeamTraining,
                                                               String inputStartRoundTraining,
                                                               String inputEndRoundTraining,
                                                               String inputHiddenNeurons) {

        InitializeService.setParametersForTraining(
                Double.parseDouble(inputLearningRate),
                Integer.parseInt(inputMaxRoutine),
                Double.parseDouble(inputErrorThreshold),
                Integer.parseInt(inputStartTeamTraining),
                Integer.parseInt(inputEndTeamTraining),
                Integer.parseInt(inputStartRoundTraining),
                Integer.parseInt(inputEndRoundTraining),
                Integer.parseInt(inputHiddenNeurons));

        logger.info("Learning Rate, Máximo de Rotinas, Erro Mínimo, Rodadas para treino, Times utilizados e Neurônios intermediários ATUALIZADOS\n");

        initializeDataForTraining();
        JOptionPane.showMessageDialog(null, "Valores de entrada atualizados!");

        logger.info("DADOS INICIALIZADOS\n");
    }

        private static Initializer initializeDataForTraining() {
            dataForTraining = new InitializerForTraining(trainingParameters.getStructure(), trainingTeamsMapBuilt.getTeamsMap());
            return dataForTraining;
        }

    public static void buttonTrainActionPerformed() {
        TrainingService.backPropagationAlgorithm();
        logger.info("REDE TREINADA\n");
    }

    public static void buttonLoadPredictionActionPerformed() {
        try {
            JFileChooser chooser = new JFileChooser(
                    "C:\\Users\\kassio-chronos\\Desktop\\workspace\\Soccer-Wizard");
            chooser.setFileFilter(new FileNameExtensionFilter(".xls", "xls"));

            int retorno = chooser.showOpenDialog(null);


            if (retorno == JFileChooser.APPROVE_OPTION) {
                Workbook workbook = Workbook.getWorkbook(chooser.getSelectedFile());
                applicationTeamsMapBuilt = SoccerWizardController.buildTeams(workbook);

                JOptionPane.showMessageDialog(null, "Campeonato para aplicação carregado!");
                logger.info("CAMPEONATO PARA APLICAÇÃO CARREGADO!\n");
            }
        } catch (FileNotFoundException e) {
            logger.warning("Arquivo não encontrado!" + e.getMessage());
        } catch (BiffException e) {
            logger.warning(e.getMessage());
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public static void buttonInitializePredictionActionPerformed(String inputStartTeamPrediction,
                                                                  String inputEndTeamPrediction,
                                                                  String inputStartRoundPrediction,
                                                                  String inputEndRoundPrediction,
                                                                  String inputThresholdOfProbability,
                                                                  String inputStdOfProbability) {
        setParametersForApplication(
                Integer.parseInt(inputStartTeamPrediction),
                Integer.parseInt(inputEndTeamPrediction),
                Integer.parseInt(inputStartRoundPrediction),
                Integer.parseInt(inputEndRoundPrediction),
                Double.parseDouble(inputThresholdOfProbability),
                Double.parseDouble(inputStdOfProbability));

        logger.info("Times e Rodadas para aplicação atualizados!\n");
    }

        private static Parameters setParametersForApplication(int numTeams, int numTeamsTotal, int numRodadas, int numRodadasTotal, double probabilityThreshold, double stdThreshold) {
            applicationParameters = new ParametersForApplication(numTeams, numTeamsTotal, numRodadas, numRodadasTotal, probabilityThreshold, stdThreshold);
            return applicationParameters;
        }

    public static FeedforwardAlgorithm buttonPlayActionPerformed() {
        initializeDataForApplication();
        FeedforwardAlgorithm result = feedforwardAlgorithm();

        logger.info("APLICAÇÃO EXECUTADA!\n");

        return result;
    }

        private static Initializer initializeDataForApplication(){
            dataForApplication = new InitializerForApplication(applicationParameters.getStructure(), applicationTeamsMapBuilt.getTeamsMap(), dataForTraining.getGamesRandom());
            return dataForApplication;
        }

        private static FeedforwardAlgorithm feedforwardAlgorithm() {
            return new FeedforwardAlgorithm(dataForApplication, bias, applicationParameters.getModifiers());
        }
}
