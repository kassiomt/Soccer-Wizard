//package soccerUI;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//import javax.swing.BoxLayout;
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
//public class ModernSoccerUI extends JFrame {
//	
//	public ModernSoccerUI() {
//		montaTela();
//	}
//
//	private JFrame janela;
//	private JPanel painelPrincipal;
//	private JPanel painelGrafico;
//	private JPanel painelBotoes;
//	private JTabbedPane abas;
//	private JPanel painelAbaUm;
//	private JPanel painelMenu;
//	private JPanel painelChart;
//	private JPanel painelResultados;
//	private ChartPanel painelChartPanel;
//
//	public static void main(String[] args) {
//		new ModernSoccerUI();
//	}
//
//	private void montaTela() {
//		preparaJanela();
//		preparaPainelPrincipal();
//		preparaPainelMenu();
//		preparaPainelChart();
//		preparaPainelGrafico();
//		preparaPainelResultados();
//		preparaBotaoErroFinal();
//		preparaBotaoNumeroRotinas();
////		preparaAbas();
////		preparaPainelAbaUm();
////		preparaChart();
////		preparaPainelBotoes();
////		preparaBotaoCarregar();
////		preparaBotaoSair();
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
//		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.X_AXIS));
//		painelPrincipal.setSize(1280, 720);
//		janela.add(painelPrincipal);
//	}
//	
//	private void preparaPainelMenu() {
//		painelMenu = new JPanel();
//		painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
//		painelMenu.setAlignmentX(0);
//		painelMenu.setSize(730, 720);
//		painelPrincipal.add(painelMenu);
//	}
//	
//	private void preparaPainelChart() {
//		painelChart = new JPanel();
//		painelChart.setLayout(new BoxLayout(painelChart, BoxLayout.Y_AXIS));
//		painelChart.setAlignmentX(550);
//		painelChart.setSize(550, 550);
//		painelPrincipal.add(painelChart);
//	}
//
//	private void preparaPainelGrafico() {
//		painelGrafico = new JPanel();
//		painelGrafico.setAlignmentY(0);
//		painelGrafico.setSize(550, 550);
//		painelChart.add(painelGrafico);
//	}
//
//	private void preparaPainelResultados() {
//		painelResultados = new JPanel();
//		painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.X_AXIS));
//		painelResultados.setAlignmentY(550);
//		painelResultados.setSize(550, 170);
//		painelChart.add(painelResultados);
//	}
//
//	private void preparaBotaoErroFinal() {
//		JButton botaoErroFinal = new JButton("Erro Final");
//		botaoErroFinal.setAlignmentX(100);
//		botaoErroFinal.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//new ExcelSelector().escolhe();
//			}
//		});
//		painelResultados.add(botaoErroFinal);
//	}
//
//	private void preparaBotaoNumeroRotinas() {
//		JButton botaoNumeroRotinas = new JButton("Número de Rotinas");
//		botaoNumeroRotinas.setAlignmentX(350);
//		botaoNumeroRotinas.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//new ExcelSelector().escolhe();
//			}
//		});
//		painelResultados.add(botaoNumeroRotinas);
//	}
//	
//	
//	
//	
//	
//	
//	
////	private void preparaAbas() {
////		abas = new JTabbedPane();
////		abas.add("Label 1", null);
////		abas.add("Gráfico de Erro", null);
////		painelPrincipal.add(abas);
////	}
////	
////	private void preparaPainelAbaUm() {
////		painelAbaUm = new JPanel(new BorderLayout());
////		abas.setComponentAt(0, painelAbaUm);
////	}
////	
////	private void preparaChart() {
////		abas.setComponentAt(1, new JPanel());
//////		painelPrincipal.add(new JPanel(), BorderLayout.CENTER);
////	}
////	
////	private void preparaPainelBotoes() {
////		painelBotoes = new JPanel(new GridLayout());
////		painelAbaUm.add(painelBotoes, BorderLayout.SOUTH);
////	}
////
////	private void preparaBotaoCarregar() {
////		JButton botaoCarregar = new JButton("Carregar Excel");
////		botaoCarregar.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				new ExcelSelector().escolhe();
////			}
////		});
////		painelBotoes.add(botaoCarregar);
////	}
////
////	private void preparaBotaoSair() {
////		JButton botaoSair = new JButton("Sair");
////		botaoSair.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				System.exit(0);
////			}
////		});
////		painelBotoes.add(botaoSair);
////	}
////
//	private void mostraJanela() {
//		janela.pack();
//		janela.setSize(1280, 720);
//		janela.setVisible(true);
//	}
//	
//	public void montaChart(BackPropagationAlgorithm bias) {
//		try {
//			BuildErrorChart errorChart = new BuildErrorChart(bias.getChartData());
//			painelChartPanel = new ChartPanel(errorChart.chart);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		painelGrafico.add(painelChartPanel, BorderLayout.CENTER);
//		mostraJanela();
//	}
//	
//}