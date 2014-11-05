package SoccerGUI;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BuildErrorChart {

	public JFreeChart chart;
	
	public BuildErrorChart(XYSeries chartData) throws IOException {
		XYSeriesCollection chartDataSeries= new XYSeriesCollection();
        chartDataSeries.addSeries(chartData);
		
		chart = ChartFactory.createXYLineChart("", "Ciclo", "Erro", chartDataSeries, PlotOrientation.VERTICAL, false, true, false);
		
		int width=1600;
        int height=900;
        File XYlineChart = new File("XYline_Chart.png");
        ChartUtilities.saveChartAsPNG(XYlineChart, chart, width, height);
	}
	
	public BuildErrorChart(XYSeries minChartData, XYSeries maxChartData, XYSeries averageChartData) throws IOException {
		XYSeriesCollection chartDataSeries= new XYSeriesCollection();
        chartDataSeries.addSeries(minChartData);
        chartDataSeries.addSeries(maxChartData);
        chartDataSeries.addSeries(averageChartData);
		
		chart = ChartFactory.createXYLineChart("", "Ciclo", "Erro", chartDataSeries, PlotOrientation.VERTICAL, false, true, false);
		
		int width=1600;
        int height=900;
        File XYlineChart = new File("XYline_Chart.png");
        ChartUtilities.saveChartAsPNG(XYlineChart, chart, width, height);
	}  
}