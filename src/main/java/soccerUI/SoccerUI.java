//package soccerUI;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//
//import org.jfree.chart.ChartPanel;
//
//import resolvers.BackPropagationAlgorithm;
//
//@SuppressWarnings("serial")
//public class SoccerUI extends JFrame {
//	
//	public SoccerUI() {
//		montaTela();
//	}
//
//	private JFrame janela;
//	private JPanel painelPrincipal;
//	private ChartPanel painelGrafico;
//	private JPanel painelBotoes;
//	private JTabbedPane abas;
//	private JPanel painelAbaUm;
//
//	public static void main(String[] args) {
//		new SoccerUI();
//	}
//
//	private void montaTela() {
//		preparaJanela();
//		preparaPainelPrincipal();
//		preparaAbas();
//		preparaPainelAbaUm();
//		preparaChart();
//		preparaPainelBotoes();
//		preparaBotaoCarregar();
//		preparaBotaoSair();
//		mostraJanela();
//	}
//
//	private void preparaJanela() {
//		janela = new JFrame("Soccer Wizard");
//		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	private void preparaPainelPrincipal() {
//		painelPrincipal = new JPanel();
//		painelPrincipal.setLayout(new BorderLayout());
//		janela.add(painelPrincipal);
//	}
//	
//	private void preparaAbas() {
//		abas = new JTabbedPane();
//		abas.add("Label 1", null);
//		abas.add("Gráfico de Erro", null);
//		painelPrincipal.add(abas);
//	}
//	
//	private void preparaPainelAbaUm() {
//		painelAbaUm = new JPanel(new BorderLayout());
//		abas.setComponentAt(0, painelAbaUm);
//	}
//	
//	private void preparaChart() {
//		abas.setComponentAt(1, new JPanel());
////		painelPrincipal.add(new JPanel(), BorderLayout.CENTER);
//	}
//	
//	private void preparaPainelBotoes() {
//		painelBotoes = new JPanel(new GridLayout());
//		painelAbaUm.add(painelBotoes, BorderLayout.SOUTH);
//	}
//
//	private void preparaBotaoCarregar() {
//		JButton botaoCarregar = new JButton("Carregar Excel");
//		botaoCarregar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new ExcelSelector().escolhe();
//			}
//		});
//		painelBotoes.add(botaoCarregar);
//	}
//
//	private void preparaBotaoSair() {
//		JButton botaoSair = new JButton("Sair");
//		botaoSair.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		painelBotoes.add(botaoSair);
//	}
//
//	private void mostraJanela() {
//		janela.pack();
//		janela.setSize(1280, 720);
//		janela.setVisible(true);
//	}
//	
//	public void montaChart(BackPropagationAlgorithm bias) {
//		try {
//			BuildErrorChart errorChart = new BuildErrorChart(bias.getChartData());
//			painelGrafico = new ChartPanel(errorChart.chart);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		abas.setComponentAt(1, painelGrafico);
////		painelPrincipal.add(painelGrafico, BorderLayout.CENTER);
//	}
//	
//}