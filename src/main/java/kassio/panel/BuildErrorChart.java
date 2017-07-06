package kassio.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BuildErrorChart {

	public JFreeChart chart;

	public BuildErrorChart(XYSeries chartData) throws IOException {
		XYSeriesCollection chartDataSeries = new XYSeriesCollection();
		chartDataSeries.addSeries(chartData);

		chart = ChartFactory.createXYLineChart("", "Ciclo", "Erro", chartDataSeries, PlotOrientation.VERTICAL, false, true, false);

		int width = 1600;
		int height = 900;
		File XYlineChart = new File("XYline_Chart.png");
		ChartUtilities.saveChartAsPNG(XYlineChart, chart, width, height);
	}

	public BuildErrorChart(XYSeries minChartData, XYSeries maxChartData, XYSeries averageChartData, XYSeries initialMinChartData,
			XYSeries initialMaxChartData, XYSeries initialAverageChartData) throws IOException {
		XYSeriesCollection chartDataSeries = new XYSeriesCollection();
		chartDataSeries.addSeries(minChartData);
		chartDataSeries.addSeries(maxChartData);
		chartDataSeries.addSeries(averageChartData);
		chartDataSeries.addSeries(initialMinChartData);
		chartDataSeries.addSeries(initialMaxChartData);
		chartDataSeries.addSeries(initialAverageChartData);

		chart = ChartFactory.createXYLineChart("", "Ciclo", "Erro", chartDataSeries, PlotOrientation.VERTICAL, false, true, false);

		XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
		r1.setSeriesPaint(0, Color.green);
		r1.setSeriesStroke(0, new BasicStroke(3.0f));
		r1.setSeriesPaint(1, Color.red);
		r1.setSeriesStroke(1, new BasicStroke(3.0f));
		r1.setSeriesPaint(2, Color.blue);
		r1.setSeriesStroke(2, new BasicStroke(3.0f));
		r1.setSeriesPaint(3, Color.black);
		r1.setSeriesStroke(3, new BasicStroke(3.0f));
		r1.setSeriesPaint(4, Color.black);
		r1.setSeriesStroke(4, new BasicStroke(3.0f));
		r1.setSeriesPaint(5, Color.black);
		r1.setSeriesStroke(5, new BasicStroke(3.0f));
		r1.setSeriesShapesVisible(0, false);
		r1.setSeriesShapesVisible(1, false);
		r1.setSeriesShapesVisible(2, false);
		r1.setSeriesShapesVisible(3, false);
		r1.setSeriesShapesVisible(4, false);
		r1.setSeriesShapesVisible(5, false);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setRenderer(r1);

		int width = 1600;
		int height = 900;
		File XYlineChart = new File("XYline_Chart.png");
		ChartUtilities.saveChartAsPNG(XYlineChart, chart, width, height);
	}
}