package br.marciofcruz.apsmetodosordenacao.apresentacao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Iterator;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoCriterioOrdem;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoExibicaoGrafico;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

/**
 * Esta classe gera um arquivo PNG com gráficos, afim de ser inserido na
 * apresentação HTML
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.0
 */

public class Grafico {

	private ListaResultado listaResultado;
	private TipoExibicaoGrafico tipoExibicaoGrafico;
	private TipoCriterioOrdem tipoCriterioOrdem;
	private String nomeEixoY;

	private String getTitulo() throws InvalidAlgorithmParameterException {
		StringBuilder nome = new StringBuilder();

		nome.append(TipoExibicaoGrafico.getDescricao(tipoExibicaoGrafico));
		nome.append(" em ");
		nome.append(TipoCriterioOrdem.getNome(tipoCriterioOrdem));

		return nome.toString();
	}
	
	public String getNomeArquivo() {
		StringBuilder nome = new StringBuilder();
		
		switch (tipoExibicaoGrafico) {
		case tempoProcessamento:
			nome.append("tempoprocessamento");
			break;
		default:
			nome.append("tempoprocessamento");
			break;
		}
		
		switch (tipoCriterioOrdem) {
		case crescente:
			nome.append("crescente");
			break;
		case decrescente:
			nome.append("decrescente");
			break;
		case embaralhado:
			nome.append("embaralhado");
			break;
		default:
			nome.append("embaralhado");
			break;
		
		}
		
		nome.append(".png");
		
		return nome.toString();
	}

	private void gerarGrafico() throws InvalidAlgorithmParameterException,
			IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();

		Iterator<Resultado> iterator = listaResultado.getResultados()
				.iterator();

		while (iterator.hasNext()) {
			Resultado resultado = iterator.next();
			double dado = -1;

			switch (tipoExibicaoGrafico) {
			case tempoProcessamento:
				dado = Math.round(resultado.getTempoEmMicroSegundo());
				break;
			default:
				throw new InvalidAlgorithmParameterException(
						"Parâmetro não permitido");
			}

			ds.addValue(dado, TipoMetodoOrdenacao.getNome(resultado
					.getTipoMetodoOrdenacao()), Integer.toString(resultado
					.getQuantidadeImagens()));

		}

		JFreeChart grafico = ChartFactory.createLineChart(getTitulo(),
				"Imagens", nomeEixoY, ds, PlotOrientation.VERTICAL, true, true,
				false);
		
		grafico.setBorderVisible(true);
		grafico.fireChartChanged();
		
		TextTitle titulo = grafico.getTitle();
		titulo.setExpandToFitSpace(true);
		titulo.setPaint(TipoExibicaoGrafico.getCor(tipoExibicaoGrafico));

		FileOutputStream arquivo = new FileOutputStream(getNomeArquivo());
		ChartUtilities.writeChartAsPNG(arquivo, grafico, 900, 400);

	}

	public Grafico(ListaResultado listaResultado,
			TipoExibicaoGrafico tipoExibicaoGrafico,
			TipoCriterioOrdem tipoCriterioOrdem)
			throws InvalidAlgorithmParameterException, IOException {

		this.tipoCriterioOrdem = tipoCriterioOrdem;
		this.listaResultado = listaResultado;
		this.tipoExibicaoGrafico = tipoExibicaoGrafico;

		switch (tipoExibicaoGrafico) {
		case tempoProcessamento:
			nomeEixoY = "Tempo em µs (microsegundos)";
			break;
		default:
			throw new InvalidAlgorithmParameterException(
					"Parâmetro não permitido");
		}
		
		gerarGrafico();
	}

}
