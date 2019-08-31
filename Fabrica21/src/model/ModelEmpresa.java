package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tipos.PeriodoDia;
import tipos.TipoInvestimentoNatureza;

public class ModelEmpresa {

	ParametrosCalculo parametrosCalculo = new ParametrosCalculo();

	private boolean fimDeSemana;
	private boolean horarioComercial;

	private float notaNatureza = 5;

	private void addNotaNatureza(float valor) {
		float auxiliar = notaNatureza + valor;
		
		if (auxiliar > 11) {
			notaNatureza = 11;
		}
		else if (auxiliar < 0) {
			notaNatureza = 0;
		} else {
			notaNatureza = auxiliar;
		}
	}

	public float getNotaNatureza() {
		return notaNatureza;
	}

	private int diasOperacao; // Qtde de dias que a empresa esta funcionando

	private Calendar dataFimJogo = new GregorianCalendar();
	private Calendar dataAtual = new GregorianCalendar();

	private float saldoDiaAnterior = 0;
	private float receitaDia = 0;
	private float despesaDia = 0;

	private int estoqueMateriaPrima = 0;
	private int estoqueProdutoAcabado = 0;

	private PeriodoDia periodoDia;

	public Integer getEstoqueAtualMateriaPrima() {
		return estoqueMateriaPrima;
	}

	public Integer getEstoqueAtualProdutoAcabado() {
		return estoqueProdutoAcabado;
	}

	private boolean podeMovimentar(String mensagemBloqueio) {
		boolean auxiliar = false;

		if (!getGameOver()) {

			if (!getNegociacaoemAberto()) {
				throw new RuntimeException(mensagemBloqueio);
			}

			auxiliar = true;
		}

		return auxiliar;
	}

	public void investirSustentabilidade(
			TipoInvestimentoNatureza tipoInvestimentoNatureza) {
		if (podeMovimentar("Fora do horário comercial!")) {
			int qtde = 1;
			
			if (getNotaNatureza() >= 10) {
				throw new RuntimeException(
						"Não é necessário investir em Sustentabilidade neste momento!");
			}

			float custoOperacao = parametrosCalculo
					.getCustoIvestimento(tipoInvestimentoNatureza) * qtde;

			if ((getSaldoAtual() - custoOperacao) < 0) {
				throw new RuntimeException(
						"Não há saldo disponível para realizar esta operação!");
			}

			float aumentoNota = custoOperacao / 600; // razão de aumento da nota
														// da Natureza em função
														// do investimento

			addNotaNatureza(aumentoNota);

			despesaDia = despesaDia + custoOperacao;
		}

	}

	public void comprarMateriaPrima(int qtde) {
		if (podeMovimentar("Fornecedor está fechado!")) {
			float custoOperacao = parametrosCalculo
					.getCustoMateriaPrimaCompra() * qtde;

			if ((getSaldoAtual() - custoOperacao) < 0) {
				throw new RuntimeException(
						"Não há saldo disponível para realizar esta operação!");
			}

			estoqueMateriaPrima = estoqueMateriaPrima + qtde;

			despesaDia = despesaDia + custoOperacao;
		}

	}

	public float getPrecoVenda() {
		return parametrosCalculo.getPrecoUnitarioVendaPorUnidade();
	}

	private float getCustoOperacionalProducao(int qtdeProdutoAcabado) {
		return parametrosCalculo.getCustoMateriaPrimaPorUnidade(notaNatureza)
				* qtdeProdutoAcabado;

	}

	public void fabricarProdutoAcabado(int qtdeProdutoAcabado) {

		if (podeMovimentar("Fabrica está fechada neste momento!")) {
			int qtdeNecessariaMateriaPrima = qtdeProdutoAcabado
					* parametrosCalculo.getFatorProducaoProdutoAcabado();

			float custoOperacionalProducao = getCustoOperacionalProducao(qtdeProdutoAcabado);

			if ((getSaldoAtual() - custoOperacionalProducao) < 0) {
				throw new RuntimeException(
						"Não há saldo suficiente para executar ordem de produção!");
			}

			if (estoqueMateriaPrima - qtdeNecessariaMateriaPrima < 0) {
				throw new RuntimeException(
						"Não há estoque suficiente de Materia Prima para produção!");
			}

			// produzir as peças
			estoqueProdutoAcabado = estoqueProdutoAcabado + qtdeProdutoAcabado;
			estoqueMateriaPrima = estoqueMateriaPrima
					- qtdeNecessariaMateriaPrima;

			// calcular nova despesa
			despesaDia = despesaDia + custoOperacionalProducao;

			addNotaNatureza(-1 * parametrosCalculo.getReducaoBaseJogada());

		}
	}

	public void venderProdutoAcabado(int qtdeProdutoAcabadoVenda) {

		if (podeMovimentar("Fabrica está fechada neste momento!")) {
			if (estoqueProdutoAcabado - qtdeProdutoAcabadoVenda < 0) {
				throw new RuntimeException(
						"Não há estoque produto de produto acabado");
			}

			float auxiliarTotalVenda = qtdeProdutoAcabadoVenda
					* parametrosCalculo.getPrecoUnitarioVendaPorUnidade();

			receitaDia = receitaDia + auxiliarTotalVenda;
			estoqueProdutoAcabado = estoqueProdutoAcabado
					- qtdeProdutoAcabadoVenda;
		}

	}

	public boolean getNegociacaoemAberto() {
		if (fimDeSemana || periodoDia == PeriodoDia.amanhecer
				|| periodoDia == PeriodoDia.inicioAnoitecer
				|| periodoDia == PeriodoDia.noite) {
			return false;
		} else {
			return true;
		}

	}

	private float getDespesaBaseJogada() {
		float auxiliar = parametrosCalculo.getDespesaBaseJogada(notaNatureza);

		// de final de semana e fora do horário comercial, a empresa gasta 80%
		// menos
		// dinheiro
		if (!getNegociacaoemAberto()) {
			auxiliar = auxiliar * 0.8f; // a Despesa a noite é 80% menor
		}

		return auxiliar;
	}

	public float getSaldoDiaAnterior() {
		return saldoDiaAnterior;
	}

	public float getSaldoAtual() {
		return saldoDiaAnterior + receitaDia - despesaDia;
	}

	public float getDespesaDia() {
		return despesaDia;
	}

	public float getReceitaDia() {
		return receitaDia;
	}

	public boolean getfimDeSemana() {
		return fimDeSemana;
	}

	public boolean gethorarioComercial() {
		return horarioComercial;
	}

	public void iniciarJogo() throws ParseException {
		diasOperacao = 1;

		dataAtual.set(1992, 6, 15, 8, 0); // Fim da Eco 92

		setDataAtual(dataAtual);

		dataFimJogo.set(2002, 8, 26, 0, 0); // Inicio Cúpula Mundial sobre
											// Desenvolvimento Sustentável

		saldoDiaAnterior = 10000f;
		receitaDia = 0;
		despesaDia = 0;
	}

	public Calendar getDataAtual() {
		return dataAtual;
	}

	public void novaJogada() {
		if (!getGameOver()) {
			Calendar anterior = (Calendar) dataAtual.clone();

			int somaMinuto = 15;
			int multiplicadorReceitaDespesa = 1;

			if (getfimDeSemana()) {
				multiplicadorReceitaDespesa = 8;
			} else if (!gethorarioComercial()) {
				multiplicadorReceitaDespesa = 4;
			}

			somaMinuto = somaMinuto * multiplicadorReceitaDespesa;

			dataAtual.add(Calendar.MINUTE, somaMinuto);

			if (dataAtual.get(Calendar.DAY_OF_YEAR) != anterior
					.get(Calendar.DAY_OF_YEAR)) {
				saldoDiaAnterior = getSaldoAtual();
				receitaDia = 0;
				despesaDia = 0;

				diasOperacao++;
			} else {

				float auxiliarDespesa = getDespesaBaseJogada()
						* multiplicadorReceitaDespesa;

				if ((getSaldoAtual() - auxiliarDespesa) < 0) {
					throw new RuntimeException("Você perdeu");
				}

				despesaDia = despesaDia + auxiliarDespesa;
			}

			// redução constante da nota da natureza em qualquer dia e horário
			addNotaNatureza(-1 * parametrosCalculo.getReducaoBaseJogada());

			setDataAtual(dataAtual);

		} else {
			throw new RuntimeException("Você perdeu");
		}
	}

	private void setDataAtual(Calendar gc) {
		dataAtual = gc;

		int hora = dataAtual.get(Calendar.HOUR_OF_DAY);
		int diaDaSemana = dataAtual.get(Calendar.DAY_OF_WEEK);

		// 1/3 - setando a variável de final de semana -
		fimDeSemana = (diaDaSemana == 1) || (diaDaSemana == 7);

		// 2/3 - identificando o período do dia (vai ser usado na cena, se o
		// programador da preferir
		if ((hora >= 0 && hora <= 6) || (hora >= 20)) {
			periodoDia = PeriodoDia.noite;
		} else if (hora >= 6 && hora <= 7) {
			periodoDia = PeriodoDia.amanhecer;
		} else if (hora >= 7 && hora <= 8) {
			periodoDia = PeriodoDia.manha08;
		} else if (hora >= 8 && hora <= 9) {
			periodoDia = PeriodoDia.manha09;
		} else if (hora >= 9 && hora <= 10) {
			periodoDia = PeriodoDia.manha10;
		} else if (hora >= 10 && hora <= 11) {
			periodoDia = PeriodoDia.manha11;
		} else if (hora >= 11 && hora <= 12) {
			periodoDia = PeriodoDia.manha12;
		} else if (hora >= 12 && hora <= 13) {
			periodoDia = PeriodoDia.tarde13;
		} else if (hora >= 13 && hora <= 14) {
			periodoDia = PeriodoDia.tarde14;
		} else if (hora >= 14 && hora <= 15) {
			periodoDia = PeriodoDia.tarde15;
		} else if (hora >= 15 && hora <= 16) {
			periodoDia = PeriodoDia.tarde16;
		} else if (hora >= 16 && hora <= 17) {
			periodoDia = PeriodoDia.tarde17;
		} else if (hora >= 17 && hora <= 18) {
			periodoDia = PeriodoDia.tarde18;
		} else {
			periodoDia = PeriodoDia.inicioAnoitecer;
		}

		// 3/3 - Identificar se é horário comercial
		horarioComercial = (hora >= 8 && hora < 18);
	}

	public PeriodoDia getPeriodoDia() {
		return periodoDia;
	}

	public boolean getFimDeSemana() {
		return fimDeSemana;
	}

	public boolean getGameOver() {
		float saldoFinal = getSaldoAtual() - getDespesaBaseJogada();

		return saldoFinal <= 0;
	}

	public int getDiasOperacao() {
		return diasOperacao;
	}

	public float getCustoFabricaDia() {
		return parametrosCalculo.getDespesaBaseDia(notaNatureza);
	}

	public float getPrecoCustoProdutoAcabado() {
		float precoCusto = parametrosCalculo.getCustoMateriaPrimaCompra()
				* parametrosCalculo.getFatorProducaoProdutoAcabado();

		return precoCusto + getCustoOperacionalProducao(1);
	}

}
