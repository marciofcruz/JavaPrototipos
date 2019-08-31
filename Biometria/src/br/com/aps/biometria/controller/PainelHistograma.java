package br.com.aps.biometria.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.aps.biometria.tipos.Frequencia;

public class PainelHistograma extends JPanel {

	private static final long serialVersionUID = 7751091147060793494L;
	private JFreeChart grafico;
	private ChartPanel chartPanel;
	private int[][] frequencia = new int[256][3];
	private Frequencia frequenciaImagem;

	public PainelHistograma() {
		loadLayouts();
		loadHistogram();
		customizeComponents();
		addComponents();
	}

	private void loadHistogram() {
		grafico = ChartFactory.createHistogram(null, null, null, null,
				PlotOrientation.VERTICAL, false, true, true);
		
		grafico.setBorderVisible(true);
		chartPanel = new ChartPanel(grafico, true);
	}

	public void clear() {
		JFreeChart grafico = ChartFactory.createHistogram(null, null, null,
				null, PlotOrientation.VERTICAL, false, true, true);
		grafico.setBorderVisible(true);
		
		chartPanel.setChart(grafico);
		this.grafico = grafico;
	}

	private void loadLayouts() {
		setLayout(new BorderLayout());
	}

	private void customizeComponents() {
		setBorder(BorderFactory.createTitledBorder("Histograma"));
		grafico.setBorderPaint(Color.lightGray);
		chartPanel.setPreferredSize(new Dimension(500, 0));
	}

	private void addComponents() {
		add(chartPanel, BorderLayout.CENTER);
	}

	public void setFrequenciaImagem(Frequencia frequenciaImagem) {
		this.frequenciaImagem = frequenciaImagem;
		this.criaChart();
	}

	private void criaChart() {
		this.frequencia = this.frequenciaImagem.getFrequenciaRGB();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// insere os valores no dataset para carregar no grafico
		for (int x = 0; x < this.frequencia.length; x++) {
			String v2 = String.valueOf(x);
			// String v2 = x == 0 || 16 % x == 0? String.valueOf(x) : "";
			dataset.addValue(frequencia[x][0], "R", v2);
			dataset.addValue(frequencia[x][1], "G", v2);
			dataset.addValue(frequencia[x][2], "B", v2);
		}

		JFreeChart grafico = ChartFactory.createLineChart("Histograma", // chart
																		// title
				"Pixels", // domain axis label
				"Quantidade", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chartPanel.setChart(grafico);

		this.grafico = grafico;
	}// fim do criaChart

}// fim da classe

