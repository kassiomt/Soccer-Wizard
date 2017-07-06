package kassio.panel;

import kassio.SoccerWizard;
import kassio.controller.SoccerWizardController;
import kassio.controller.XoRController;
import kassio.resolvers.FeedforwardAlgorithm;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("Since15")
public class SoccerWizardGUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TAHOMA = "Tahoma";
    private static final String AGENCY_FB = "Agency FB";
    private static final String PATTERN = "0.000000";

    public SoccerWizardGUI() {
        initComponents();
    }

    private void initComponents() {

        initializeVariables();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1280, 720));


        prepareXoRResultPanel();
        mountXoRResultPanel();

        prepareXoRMenuPanel();
        mountXoRMenuPanel();

        mountXoRTab();
        jTabbedPane1.addTab("XOR de Teste", xorTab);


        prepareSoccerWizardResultPanel();
        mountSoccerWizardResultPanel();

        prepareSoccerWizardMenuPanel();
        mountSoccerWizardMenuPanel();

        mountSoccerWizardTab();
        jTabbedPane1.addTab("Soccer Wizard", soccerWizardTab);


        mountAll();
        pack();
    }

    private void initializeVariables() {
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        xorTab = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        resultPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputMaxRoutineXoR = new javax.swing.JTextField();
        inputLearningRateXoR = new javax.swing.JTextField();
        inputErrorThresholdXoR = new javax.swing.JTextField();
        buttonTrainXoR = new javax.swing.JButton();
        buttonPlayXoR = new javax.swing.JButton();
        buttonInitializeXoR = new javax.swing.JButton();
        soccerWizardTab = new javax.swing.JPanel();
        chartPanel2 = new javax.swing.JPanel();
        resultPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPaneErroMinimoFinal = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPaneRotinas = new javax.swing.JTextPane();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPaneAllRatio = new javax.swing.JTextPane();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPaneWinRatio = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextPaneDrawRatio = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextPaneLoseRatio = new javax.swing.JTextPane();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPaneErroMaximoFinal = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextPaneErroFinal2 = new javax.swing.JTextPane();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        menuPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        inputMaxRoutine = new javax.swing.JTextField();
        inputLearningRate = new javax.swing.JTextField();
        inputErrorThreshold = new javax.swing.JTextField();
        buttonTrain = new javax.swing.JButton();
        buttonInitializeTraining = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        inputStartRoundTraining = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        inputStartTeamTraining = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        inputHiddenNeurons = new javax.swing.JTextField();
        buttonPlay = new javax.swing.JButton();
        buttonInitializePrediction = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        inputStartRoundPrediction = new javax.swing.JTextField();
        inputStartTeamPrediction = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        inputThresholdOfProbability = new javax.swing.JTextField();
        inputEndRoundTraining = new javax.swing.JTextField();
        inputEndTeamTraining = new javax.swing.JTextField();
        inputEndRoundPrediction = new javax.swing.JTextField();
        inputEndTeamPrediction = new javax.swing.JTextField();
        buttonLoadTraining = new javax.swing.JButton();
        buttonLoadPrediction = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        inputStdOfProbability = new javax.swing.JTextField();
    }

    private void prepareXoRResultPanel() {
        xorTab.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 18))); // NOI18N

        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gráfico do erro ao longo das rotinas de treino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 18))); // NOI18N
        chartPanel.setLayout(new java.awt.BorderLayout());

        resultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel1.setText("Erro Final:");

        jLabel2.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel2.setText("Rotinas:");

        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane3.setViewportView(jTextPane3);

        jScrollPane2.setViewportView(jTextPane2);

        jScrollPane7.setViewportView(jTextPane7);

        jScrollPane4.setViewportView(jTextPane4);

        jScrollPane5.setViewportView(jTextPane5);

        jLabel6.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel6.setText("00");

        jLabel7.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel7.setText("10");

        jLabel8.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel8.setText("01");

        jLabel10.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel10.setText("11");
    }

    private void mountXoRResultPanel() {
        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
                resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18))
                                        .addGroup(resultPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(9, 9, 9)))
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane4)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                        .addComponent(jScrollPane7))
                                .addContainerGap())
        );
        resultPanelLayout.setVerticalGroup(
                resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultPanelLayout.createSequentialGroup()
                                .addContainerGap(27, Short.MAX_VALUE)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }

    private void prepareXoRMenuPanel() {
        jLabel3.setText("Alfa:");
        inputLearningRateXoR.setText("0.2");

        jLabel4.setText("Máximo de rotinas:");
        inputMaxRoutineXoR.setText("5000");

        jLabel5.setText("Limiar de erro:");
        inputErrorThresholdXoR.setText("0.001");

        buttonInitializeXoR.setText("INICIALIZAR DADOS");
        buttonInitializeXoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoRController.botaoInicializarXoRActionPerformed(inputLearningRateXoR.getText(), inputMaxRoutineXoR.getText(), inputErrorThresholdXoR.getText());
            }
        });

        buttonTrainXoR.setText("TREINAR");
        buttonTrainXoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(jPopupMenu1, "O treinamento pode demorar para ser concluido, AGUARDE a mensagem de confirmação!", "CUIDADO!", 2);

                XoRController.botaoTreinarXoRActionPerformed();
                setTextsAndBuildChartXoR();

                JOptionPane.showMessageDialog(rootPane, "O treinamento foi concluido. Você pode agora prosseguir com a aplicação da rede!", "TREINAMENTO CONCLUIDO", 1);
            }
        });

        buttonPlayXoR.setText("RODAR");
        buttonPlayXoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeedforwardAlgorithm xorResult = XoRController.botaoRodarXoRActionPerformed();
                setResultsXoR(xorResult);
            }
        });
    }

    private void mountXoRMenuPanel() {
        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(menuPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(inputMaxRoutineXoR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                        .addComponent(inputLearningRateXoR, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputErrorThresholdXoR)))
                                        .addGroup(menuPanelLayout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(buttonTrainXoR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(buttonPlayXoR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonInitializeXoR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(85, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(inputLearningRateXoR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(inputMaxRoutineXoR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(inputErrorThresholdXoR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
                                .addComponent(buttonInitializeXoR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonTrainXoR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonPlayXoR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
        );
    }

    private void mountXoRTab() {
        javax.swing.GroupLayout xorTabLayout = new javax.swing.GroupLayout(xorTab);
        xorTab.setLayout(xorTabLayout);
        xorTabLayout.setHorizontalGroup(
                xorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, xorTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(xorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        xorTabLayout.setVerticalGroup(
                xorTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(xorTabLayout.createSequentialGroup()
                                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void prepareSoccerWizardResultPanel() {
        soccerWizardTab.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 12))); // NOI18N

        chartPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gráfico do erro ao longo das rotinas de treino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 18))); // NOI18N
        chartPanel2.setMaximumSize(new java.awt.Dimension(1280, 720));
        chartPanel2.setLayout(new java.awt.BorderLayout());

        resultPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(AGENCY_FB, 1, 18))); // NOI18N

        jLabel19.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel19.setText("Erro Mínimo Final:");

        jLabel20.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel20.setText("Rotinas:");

        jScrollPane13.setViewportView(jTextPaneErroMinimoFinal);

        jScrollPane14.setViewportView(jTextPaneRotinas);

        jLabel21.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Win Ratio");

        jTextPaneAllRatio.setFont(new java.awt.Font(TAHOMA, 1, 14)); // NOI18N
        jScrollPane18.setViewportView(jTextPaneAllRatio);

        jTextPaneWinRatio.setFont(new java.awt.Font(TAHOMA, 1, 14)); // NOI18N
        jScrollPane19.setViewportView(jTextPaneWinRatio);

        jTextPaneDrawRatio.setFont(new java.awt.Font(TAHOMA, 1, 14)); // NOI18N
        jScrollPane20.setViewportView(jTextPaneDrawRatio);

        jTextPaneLoseRatio.setFont(new java.awt.Font(TAHOMA, 1, 14)); // NOI18N
        jScrollPane21.setViewportView(jTextPaneLoseRatio);

        jLabel22.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Draw Ratio");

        jLabel23.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("All Ratio");

        jLabel24.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Lose Ratio");

        jScrollPane15.setViewportView(jTextPaneErroMaximoFinal);

        jScrollPane16.setViewportView(jTextPaneErroFinal2);

        jLabel35.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel35.setText("Erro Máximo Final:");

        jLabel36.setFont(new java.awt.Font(TAHOMA, 1, 12)); // NOI18N
        jLabel36.setText("Erro Médio Final:");
    }

    private void mountSoccerWizardResultPanel() {
        javax.swing.GroupLayout resultPanel2Layout = new javax.swing.GroupLayout(resultPanel2);
        resultPanel2.setLayout(resultPanel2Layout);
        resultPanel2Layout.setHorizontalGroup(
                resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel35))
                                .addGap(10, 10, 10)
                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane13)
                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel20)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel22)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel24)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(43, 43, 43)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        resultPanel2Layout.setVerticalGroup(
                resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(40, 40, 40))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanel2Layout.createSequentialGroup()
                                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                                                .addGap(1, 1, 1)
                                                                                .addComponent(jScrollPane13)))
                                                                .addGap(41, 41, 41)))
                                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane19))
                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(resultPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }

    private void prepareSoccerWizardMenuPanel() {
        jLabel25.setText("Alfa:");
        inputLearningRate.setText("0.01");

        jLabel26.setText("Máximo de rotinas:");
        inputMaxRoutine.setText("10000");

        jLabel27.setText("Limiar de erro:");
        inputErrorThreshold.setText("0.1");

        jLabel28.setText("Rodadas:");
        inputStartRoundTraining.setText("31");
        inputEndRoundTraining.setText("31");

        jLabel29.setText("Times:");
        inputStartTeamTraining.setText("20");
        inputEndTeamTraining.setText("20");

        jLabel30.setText("Neurônios:");
        inputHiddenNeurons.setText("40");

        jLabel32.setText("Rodadas para execução:");
        inputStartRoundPrediction.setText("31");
        inputEndRoundPrediction.setText("31");

        jLabel33.setText("Times para execução:");
        inputStartTeamPrediction.setText("20");
        inputEndTeamPrediction.setText("20");

        jLabel34.setText("Limiar de probabilidade:");
        inputThresholdOfProbability.setText("0.25");

        jLabel37.setText("Std de probabilidade:");
        inputStdOfProbability.setText("-0.5");

        buttonLoadTraining.setText("CARREGAR CAMPEONATO - treino");
        buttonLoadTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoccerWizardController.buttonLoadTrainingActionPerformed();
            }
        });

        buttonInitializeTraining.setText("INICIALIZAR DADOS PARA TREINAMENTO");
        buttonInitializeTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoccerWizardController.buttonInitializeTrainingActionPerformed(
                        inputLearningRate.getText(),
                        inputMaxRoutine.getText(),
                        inputErrorThreshold.getText(),
                        inputStartTeamTraining.getText(),
                        inputEndTeamTraining.getText(),
                        inputStartRoundTraining.getText(),
                        inputEndRoundTraining.getText(),
                        inputHiddenNeurons.getText());
            }
        });

        buttonTrain.setText("TREINAR");
        buttonTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(jPopupMenu1, "O treinamento pode demorar para ser concluido, AGUARDE a mensagem de confirmação!", "CUIDADO!", 2);

                SoccerWizardController.buttonTrainActionPerformed();
                setTextsAndBuildChartSoccerWizard();

                JOptionPane.showMessageDialog(rootPane, "O treinamento foi concluido. Você pode agora prosseguir com a aplicação da rede!", "TREINAMENTO CONCLUIDO", 1);
            }
        });

        buttonLoadPrediction.setText("CARREGAR CAMPEONATO - predição");
        buttonLoadPrediction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoccerWizardController.buttonLoadPredictionActionPerformed();
            }
        });

        buttonInitializePrediction.setText("INICIALIZAR DADOS PARA PREDIÇÃO");
        buttonInitializePrediction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoccerWizardController.buttonInitializePredictionActionPerformed(
                        inputStartTeamPrediction.getText(),
                        inputEndTeamPrediction.getText(),
                        inputStartRoundPrediction.getText(),
                        inputEndRoundPrediction.getText(),
                        inputThresholdOfProbability.getText(),
                        inputStdOfProbability.getText());

                JOptionPane.showMessageDialog(rootPane, "Aplicação preparada, agora é só rodar sua Rede Neural Artificial!", "APLICAÇÃO PREPARADA", 1);
            }
        });

        buttonPlay.setText("RODAR");
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeedforwardAlgorithm result = SoccerWizardController.buttonPlayActionPerformed();
                setResultsSoccerWizard(result);
            }
        });
    }

    private void mountSoccerWizardMenuPanel() {
        javax.swing.GroupLayout menuPanel2Layout = new javax.swing.GroupLayout(menuPanel2);
        menuPanel2.setLayout(menuPanel2Layout);
        menuPanel2Layout.setHorizontalGroup(
                menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuPanel2Layout.createSequentialGroup()
                                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel27)
                                                                        .addComponent(jLabel25)
                                                                        .addComponent(jLabel26))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(inputMaxRoutine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                                        .addComponent(inputLearningRate, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(inputErrorThreshold)))
                                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel28)
                                                                        .addComponent(jLabel29)
                                                                        .addComponent(jLabel30))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(inputStartRoundTraining)
                                                                        .addComponent(inputStartTeamTraining, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                                        .addComponent(inputHiddenNeurons))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputEndRoundTraining, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                                        .addComponent(inputEndTeamTraining))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel32)
                                                                .addGap(46, 46, 46))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuPanel2Layout.createSequentialGroup()
                                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inputStartTeamPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputThresholdOfProbability, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputStartRoundPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputStdOfProbability, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(7, 7, 7)))
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inputEndTeamPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputEndRoundPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(13, Short.MAX_VALUE))
                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(buttonPlay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonInitializePrediction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonTrain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonInitializeTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonLoadTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonLoadPrediction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanel2Layout.setVerticalGroup(
                menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25)
                                        .addComponent(inputLearningRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel26)
                                        .addComponent(inputMaxRoutine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel27)
                                        .addComponent(inputErrorThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel28)
                                        .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(inputStartRoundTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputEndRoundTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel29)
                                        .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(inputStartTeamTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputEndTeamTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel30)
                                        .addComponent(inputHiddenNeurons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel32)
                                                        .addComponent(inputStartRoundPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputEndRoundPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(52, 52, 52))
                                        .addGroup(menuPanel2Layout.createSequentialGroup()
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(inputStartTeamPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel33)
                                                        .addComponent(inputEndTeamPrediction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(inputThresholdOfProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel34))))
                                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputStdOfProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(buttonLoadTraining)
                                .addGap(18, 18, 18)
                                .addComponent(buttonInitializeTraining)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonTrain)
                                .addGap(46, 46, 46)
                                .addComponent(buttonLoadPrediction)
                                .addGap(18, 18, 18)
                                .addComponent(buttonInitializePrediction)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPlay)
                                .addGap(88, 88, 88))
        );
    }

    private void mountSoccerWizardTab() {
        javax.swing.GroupLayout soccerWizardTabLayout = new javax.swing.GroupLayout(soccerWizardTab);
        soccerWizardTab.setLayout(soccerWizardTabLayout);
        soccerWizardTabLayout.setHorizontalGroup(
                soccerWizardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soccerWizardTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menuPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(soccerWizardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resultPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chartPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        soccerWizardTabLayout.setVerticalGroup(
                soccerWizardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(soccerWizardTabLayout.createSequentialGroup()
                                .addComponent(chartPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(menuPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void mountAll() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }



    private void setTextsAndBuildChartXoR() {

        //set texts
        DecimalFormat df = new DecimalFormat(PATTERN);
        jTextPane1.setText( String.valueOf(df.format(SoccerWizard.bias.getMaxError())) );
        jTextPane3.setText( String.valueOf(SoccerWizard.bias.getRotinas()));

        //buildChart
        BuildErrorChart errorChart;
        chartPanel.removeAll();
        try {
            errorChart = new BuildErrorChart(SoccerWizard.bias.getMaxErrorChartData());

            chartPanel.add(new ChartPanel(errorChart.chart));
            chartPanel.setVisible(false);
            chartPanel.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(SoccerWizardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setResultsXoR(FeedforwardAlgorithm xorResult) {
        DecimalFormat df = new DecimalFormat(PATTERN);
        jTextPane4.setText( String.valueOf(df.format(xorResult.getLastLayerOutMatrix()[0][0])) );
        jTextPane2.setText( String.valueOf(df.format(xorResult.getLastLayerOutMatrix()[0][1])) );
        jTextPane5.setText( String.valueOf(df.format(xorResult.getLastLayerOutMatrix()[0][2])) );
        jTextPane7.setText( String.valueOf(df.format(xorResult.getLastLayerOutMatrix()[0][3])) );
    }


    private void setTextsAndBuildChartSoccerWizard() {
        //set texts
		DecimalFormat df = new DecimalFormat(PATTERN);
		jTextPaneErroMinimoFinal.setText( String.valueOf(df.format(SoccerWizard.bias.getMinError())));
		jTextPaneErroMaximoFinal.setText( String.valueOf(df.format(SoccerWizard.bias.getMaxError())));
		jTextPaneErroFinal2.setText( String.valueOf(df.format(SoccerWizard.bias.getAverageError())));
		jTextPaneRotinas.setText( String.valueOf(SoccerWizard.bias.getRotinas()));

        //build chart
		BuildErrorChart errorChart;
        chartPanel2.removeAll();
		try {
			errorChart = new BuildErrorChart(SoccerWizard.bias.getMinErrorChartData(), SoccerWizard.bias.getMaxErrorChartData(),
					SoccerWizard.bias.getAverageErrorChartData(), SoccerWizard.bias.getInitialMinErrorChartData(),
					SoccerWizard.bias.getInitialMaxErrorChartData(), SoccerWizard.bias.getInitialAverageErrorChartData());

            chartPanel2.add(new ChartPanel(errorChart.chart));
            chartPanel2.setVisible(false);
            chartPanel2.setVisible(true);

		} catch (IOException ex) {
			Logger.getLogger(SoccerWizardGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    private void setResultsSoccerWizard(FeedforwardAlgorithm result) {

        DecimalFormat df = new DecimalFormat("00.00%");
    	jTextPaneWinRatio.setText( String.valueOf(df.format(result.getErrorAnalysis().getWinSuccessRatio())) );
    	jTextPaneDrawRatio.setText( String.valueOf(df.format(result.getErrorAnalysis().getDrawSuccessRatio())) );
    	jTextPaneLoseRatio.setText( String.valueOf(df.format(result.getErrorAnalysis().getLoseSuccessRatio())) );
//    	jTextPaneAnyRatio.setText( String.valueOf(df.format(result.getAnyoneSuccessRatio())) );
    	jTextPaneAllRatio.setText( String.valueOf(df.format(result.getErrorAnalysis().getOverallSuccessRatio())) );

        JOptionPane.showMessageDialog(rootPane, "Aplicação executada!", "APLICAÇÃO EXECUTADA", 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SoccerWizardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SoccerWizardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SoccerWizardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SoccerWizardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SoccerWizardGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonInitializeTraining;
    private javax.swing.JButton buttonInitializePrediction;
    private javax.swing.JButton buttonInitializeXoR;
    private javax.swing.JButton buttonLoadTraining;
    private javax.swing.JButton buttonLoadPrediction;
    private javax.swing.JButton buttonPlay;
    private javax.swing.JButton buttonPlayXoR;
    private javax.swing.JButton buttonTrain;
    private javax.swing.JButton buttonTrainXoR;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel chartPanel2;
    private javax.swing.JTextField inputErrorThresholdXoR;
    private javax.swing.JTextField inputErrorThreshold;
    private javax.swing.JTextField inputHiddenNeurons;
    private javax.swing.JTextField inputLearningRateXoR;
    private javax.swing.JTextField inputLearningRate;
    private javax.swing.JTextField inputEndRoundTraining;
    private javax.swing.JTextField inputStartRoundTraining;
    private javax.swing.JTextField inputEndRoundPrediction;
    private javax.swing.JTextField inputStartRoundPrediction;
    private javax.swing.JTextField inputMaxRoutineXoR;
    private javax.swing.JTextField inputMaxRoutine;
    private javax.swing.JTextField inputStdOfProbability;
    private javax.swing.JTextField inputThresholdOfProbability;
    private javax.swing.JTextField inputEndTeamTraining;
    private javax.swing.JTextField inputStartTeamTraining;
    private javax.swing.JTextField inputEndTeamPrediction;
    private javax.swing.JTextField inputStartTeamPrediction;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPaneAllRatio;
    private javax.swing.JTextPane jTextPaneDrawRatio;
    private javax.swing.JTextPane jTextPaneErroFinal2;
    private javax.swing.JTextPane jTextPaneErroMaximoFinal;
    private javax.swing.JTextPane jTextPaneErroMinimoFinal;
    private javax.swing.JTextPane jTextPaneLoseRatio;
    private javax.swing.JTextPane jTextPaneRotinas;
    private javax.swing.JTextPane jTextPaneWinRatio;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel menuPanel2;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JPanel resultPanel2;
    private javax.swing.JPanel soccerWizardTab;
    private javax.swing.JPanel xorTab;
    // End of variables declaration//GEN-END:variables
}
