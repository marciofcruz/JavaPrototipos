package br.marciofcruz.apsmetodosordenacao.main;

/**
 * Esta classe é a principal do sistema e faz as seguintes fases do processo:
 * 1 - Obtenção das imagens ordenadas de determinado diretório
 * 2 - Alimentação de N faixas de quantidade de imagens ordenadas e embaralhadas
 * 3 - Execução dos métodos de ordenação nas várias faixas dos arrays embaralhados
 * 4 - Apresentação dos resultados
 *  
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.00
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.marciofcruz.apsmetodosordenacao.apresentacao.ApresentacaoResultado;
import br.marciofcruz.apsmetodosordenacao.apresentacao.ListaResultado;
import br.marciofcruz.apsmetodosordenacao.apresentacao.Resultado;
import br.marciofcruz.apsmetodosordenacao.eficiencia.Eficiencia;
import br.marciofcruz.apsmetodosordenacao.eficiencia.ListaEficiencia;
import br.marciofcruz.apsmetodosordenacao.imagem.ArrayImagens;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoCriterioOrdem;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

public class Principal {

	/**
	 * Vamos fazer teste com N faixas de quantidade de imagens
	 */

	final static int limiteQuantidade = 10000;

	private static int[] faixaPadrao = { 10, 100, 2000, 4000, 6000, 8000, 10000};
	private static int[] faixaDeQuantidades;

	private HashMap<TipoCriterioOrdem, ListaResultado> mapaResultado = new HashMap<>();
	private HashMap<TipoCriterioOrdem, ListaEficiencia> mapaEficiencia = new HashMap<>();

	private static Scanner in;

	private ArrayImagens[] criarArrayImagens() throws FileNotFoundException {

		String path = System.getProperty("user.dir") + "\\imagensSatelite";

		ArrayImagens[] lista = new ArrayImagens[faixaDeQuantidades.length];
		for (short i = 0; i < faixaDeQuantidades.length; i++) {
			String mensagem = Integer.toString(i + 1) + "/"
					+ Integer.toString(faixaDeQuantidades.length)
					+ " - Criando array embaralhado para "
					+ faixaDeQuantidades[i] + " imagens.";
			System.out.println(mensagem);

			lista[i] = new ArrayImagens(faixaDeQuantidades[i], path);
		}

		return lista;
	}

	private void executarProcessoOrdenacao(ArrayImagens[] lista)
			throws InvalidAlgorithmParameterException {
		for (TipoCriterioOrdem ordem : TipoCriterioOrdem.values()) {

			ListaResultado listaResultado = new ListaResultado();

			for (TipoMetodoOrdenacao metodo : TipoMetodoOrdenacao.values()) {

				System.out.println(" -" + TipoMetodoOrdenacao.getNome(metodo)
						+ " em " + TipoCriterioOrdem.getNome(ordem));

				for (int i = 0; i < faixaDeQuantidades.length; i++) {
					listaResultado.adicionar(TipoMetodoOrdenacao
							.getInstanciaDe(metodo).processar(
									lista[i].getImagensOrdem(ordem)));
				}
			}

			mapaResultado.put(ordem, listaResultado);
		}

	}

	private static String listaFaixaValores(int[] faixa) {
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < faixa.length; i++) {
			s.append(faixa[i]);

			if (i != faixa.length - 1) {
				s.append(", ");
			}
		}

		return s.toString();
	}

	private void gerarMapaEficienciaMetodos()
			throws InvalidAlgorithmParameterException {

		for (TipoCriterioOrdem tipoCriterioOrdem : TipoCriterioOrdem.values()) {

			ListaEficiencia listaEficiencia = new ListaEficiencia();

			for (int i = 0; i < faixaDeQuantidades.length; i++) {

				Eficiencia eficiencia = new Eficiencia(faixaDeQuantidades[i]);

				for (TipoMetodoOrdenacao tipoMetodoOrdenacao : TipoMetodoOrdenacao
						.values()) {

					ListaResultado listaResultado = mapaResultado
							.get(tipoCriterioOrdem);
					Resultado resultado = listaResultado
							.getRetornoPorQuantidadeImagens(
									tipoMetodoOrdenacao, faixaDeQuantidades[i]);

					eficiencia.adicionar(tipoMetodoOrdenacao,
							resultado.getTempoemNanoSegundo());
				}

				listaEficiencia.adicionar(eficiencia);
			}

			mapaEficiencia.put(tipoCriterioOrdem, listaEficiencia);
		}
	}

	public void processar() throws IOException,
			InvalidAlgorithmParameterException, InstantiationException,
			IllegalAccessException {
		System.out
				.println("Passo 1 de 4 - Criando os vetores de imagens embaralhadas:");
		ArrayImagens[] lista = this.criarArrayImagens();

		System.out
				.println("\nPasso 2 de 4 - Executando metodos de ordenacoes:");
		executarProcessoOrdenacao(lista);

		System.out
				.println("\nPasso 3 de 4 - Criando comparativo da Eficiencia dos metodos...");
		gerarMapaEficienciaMetodos();

		System.out.println("\nPasso 4 de 4 - Gerando a pagina de resultado...");
		ApresentacaoResultado apresentacao = new ApresentacaoResultado(
				faixaDeQuantidades, mapaResultado, mapaEficiencia);
		apresentacao.apresentarResultados();
	}

	private static int[] lerNovaFaixaQuantidade() {

		System.out.println("Limite de quantidade: " + limiteQuantidade);

		List<Integer> lista = new ArrayList<>();

		int numero = 0;

		do {
			System.out.println("Digite a faixa de quantidades ou \"0\" para finalizar:");

			in = new Scanner(System.in);

			numero = in.nextInt();

			if (numero > limiteQuantidade || numero <= 1) {
				System.out.println("Quantidade deve ser entre 2 e "
						+ limiteQuantidade);
			} else {
				if (lista.contains(numero)) {
					System.out.println("Elmento já foi inserido");
				} else {
					lista.add(numero);
					Collections.sort(lista);

					System.out.println(lista);
				}
			}

		} while (numero != 0);

		int[] retorno = new int[lista.size()];
		int posicao = 0;
		Iterator<Integer> iterator = lista.iterator();
		while (iterator.hasNext()) {
			retorno[posicao] = iterator.next();
			posicao++;
		}

		return retorno;
	}

	private static void definirOpcaoFaixa() throws IOException {

		StringBuilder s = new StringBuilder();

		s.append("Trabalho de APS CC2P13/CC3P13 - 2013 - Marcio, Mauricio, Renato e Rodolfo\n");
		s.append("Comparativo entre métodos de Ordenação\n\n");

		s.append("Faixa Padrão de Quantidades: ");
		s.append(listaFaixaValores(faixaPadrao));

		s.append("\n");
		s.append("Deseja manter estes valores (<S>/N)?");

		System.out.println(s.toString());

		String opcao = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			opcao = br.readLine();
		} catch (IOException ioe) {
			System.out.println("Erro ao ler string");
		}

		opcao = opcao.toUpperCase();

		switch (opcao) {
		case "S":
			faixaDeQuantidades = faixaPadrao;
			break;
		case "N":
			System.out.println("\n\n");
			faixaDeQuantidades = lerNovaFaixaQuantidade();
			break;
		default:
			faixaDeQuantidades = faixaPadrao;
		}
	}

	public static void main(String[] args)
			throws InvalidAlgorithmParameterException, IOException,
			InstantiationException, IllegalAccessException {
		Principal principal = new Principal();

		Principal.definirOpcaoFaixa();

		principal.processar();
	}

}
