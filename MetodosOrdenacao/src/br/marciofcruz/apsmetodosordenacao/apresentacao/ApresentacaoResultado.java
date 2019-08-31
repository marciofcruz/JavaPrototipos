package br.marciofcruz.apsmetodosordenacao.apresentacao;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.marciofcruz.apsmetodosordenacao.eficiencia.ListaEficiencia;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoCriterioOrdem;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoExibicaoGrafico;

public class ApresentacaoResultado {

	private int[] faixaDeQuantidades;
	private HashMap<TipoCriterioOrdem, ListaResultado> mapaResultado;
	private HashMap<TipoCriterioOrdem, ListaEficiencia> mapaEficiencia;

	public ApresentacaoResultado(int[] faixaDeQuantidades,
			HashMap<TipoCriterioOrdem, ListaResultado> mapaResultado,
			HashMap<TipoCriterioOrdem, ListaEficiencia> mapaEficiencia) {
		super();

		this.faixaDeQuantidades = faixaDeQuantidades;
		this.mapaResultado = mapaResultado;
		this.mapaEficiencia = mapaEficiencia;
	}

	private String getNomeArquivoHTML(TipoCriterioOrdem tipoCriterioOrdem)
			throws InvalidAlgorithmParameterException {
		switch (tipoCriterioOrdem) {
		case crescente:
			return "resultadoordemcrescente.html";
		case decrescente:
			return "resultadoordemdecrescente.html";
		case embaralhado:
			return "resultadoordemembaralhado.html";
		default:
			throw new InvalidAlgorithmParameterException("Argumento inválido");
		}
	}
	
	private String getTituloRelatorio(TipoCriterioOrdem tipoCriterioOrdem)
			throws InvalidAlgorithmParameterException {
		switch (tipoCriterioOrdem) {
		case crescente:
			return "Aplicação de Métodos de Ordenação em Imagens em Ordem Crescente";
		case decrescente:
			return "Aplicação de Métodos de Ordenação em Imagens em Ordem Decrescente";
		case embaralhado:
			return "Aplicação de Métodos de Ordenação em Imagens Embaralhadas";
		default:
			throw new InvalidAlgorithmParameterException("Argumento inválido");
		}
	}
	

	private List<Grafico> gerarGrafico(TipoCriterioOrdem tipoCriterioOrdem)
			throws InvalidAlgorithmParameterException, IOException {
		
		List<Grafico> retorno = new ArrayList<>();

		for (TipoExibicaoGrafico tipoExibicaoGrafico : TipoExibicaoGrafico
				.values()) {
			retorno.add(new Grafico(mapaResultado.get(tipoCriterioOrdem),
					tipoExibicaoGrafico, tipoCriterioOrdem));
		}
		
		return retorno;
	}

	private void gerarResultadosHTML(Date dataProcessamento, TipoCriterioOrdem tipoCriterioOrdem, List<Grafico> graficos)
			throws IOException, InvalidAlgorithmParameterException {
		StringBuilder texto = new StringBuilder();

		AuxiliarHTML html = new AuxiliarHTML(texto, faixaDeQuantidades, dataProcessamento);
		
		String tituloRelatorio = getTituloRelatorio(tipoCriterioOrdem);

		html.inicioHTML(tituloRelatorio);

		ListaResultado lista = mapaResultado.get(tipoCriterioOrdem);

		List<Resultado> resultados = lista.getResultados();

		html.imprimirTabelaComparativoMetodos(tituloRelatorio, tipoCriterioOrdem, resultados);
		
		html.imprimirTabelaEficiencia(mapaEficiencia.get(tipoCriterioOrdem));

		html.inserirImagem(graficos);
		
		html.inserirDadosProcessamento();

		html.adicionar("</body></html>");

		texto = html.getTexto();

		File f = new File(getNomeArquivoHTML(tipoCriterioOrdem));
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));

		bw.write(texto.toString());

		bw.close();

		Desktop d = Desktop.getDesktop();
		try {
			d.browse(f.toURI());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void apresentarResultados()
			throws InvalidAlgorithmParameterException, IOException {
		
		Date dataProcessamento = new Date();

		for (TipoCriterioOrdem tipoCriterioOrdem : TipoCriterioOrdem.values()) {
			
			List<Grafico> graficos = gerarGrafico(tipoCriterioOrdem);
			
			gerarResultadosHTML(dataProcessamento, tipoCriterioOrdem, graficos);
		}
	}

}
