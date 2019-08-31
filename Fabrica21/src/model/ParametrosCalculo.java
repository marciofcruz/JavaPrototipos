package model;

import tipos.TipoInvestimentoNatureza;

public class ParametrosCalculo {

	private float despesaBaseDia = 3000;

	// custo base da materia prima
	private final float custoMateriaPrimaPorUnidade = 4;

	private final float precoUnitarioVendaPorUnidade = 100;

	private final int fatorProducaoProdutoAcabado = 5; // Produção: São
														// necessário 10

	// redução da nota da natureza
	private final float reducaoBaseDia = 3;
	private final float reducaoBaseHora = reducaoBaseDia / 24;
	private final float reducaoBaseJogada = reducaoBaseHora / 4;

	// valores da Sustentabilidade
	public float getCustoIvestimento(
			TipoInvestimentoNatureza tipoInvestimentoNatureza) {
		switch (tipoInvestimentoNatureza) {
		case palestraSustentabilidade:
			return 300;
		case filtroFumaca:
			return 600;
		case reciclagem:
			return 900;
		case ecoEficiencia:
			return 1200;
		default:
			return 0;
		}
	}

	public float getReducaoBaseJogada() {
		return reducaoBaseJogada;
	}

	public float getCustoMateriaPrimaPorUnidade(float notaNatureza) {
		float auxiliar1 = custoMateriaPrimaPorUnidade;
		float auxiliar2 = getFatorMultiplicadorCusto(notaNatureza) * auxiliar1;

		return auxiliar2;
	}

	public float getCustoMateriaPrimaCompra() {
		return custoMateriaPrimaPorUnidade;
	}

	public int getFatorProducaoProdutoAcabado() {
		return fatorProducaoProdutoAcabado;
	}

	private float getFatorMultiplicadorCusto(float notaNatureza) {
		/*
		 * Nota de 0 até menor ou igual a 5: aumento do gasto Nota igual a 5:
		 * não á fator de cálculo Nota maior que 5: redução do gasto
		 */

		if (notaNatureza >= 10) {
			notaNatureza = 10;
		}

		float auxiliar = notaNatureza - 5;

		auxiliar = 1 - (auxiliar / 5);

		if (auxiliar == 0.00) {
			auxiliar = 0.1f;
		}

		return auxiliar;
	}

	private float getPorcentagemCustoBaseEmpresa(float notaNatureza) {
		if (notaNatureza >= 10) {
			return 0.01f;
		}
		else if (notaNatureza <= 0) {
			return 100;
		} else {

			/*
			 * Exemplo de cáculo: Para Nota 10: Base Porcentagem é 0; Para nota
			 * 5: Base Porcentagem é 50 Para nota 0: Base Porcentagem é 100
			 */
			float parte1 = notaNatureza / 10;

			float parte2 = 100 - (parte1 * 100);

			return parte2;
		}
	}

	public float getPrecoUnitarioVendaPorUnidade() {
		return precoUnitarioVendaPorUnidade;
	}

	public float getDespesaBaseJogada(float notaNatureza) {
		// despesa base da Jogada
		float despesaBaseHora = despesaBaseDia / 24;

		float parte1 = despesaBaseHora / 4;

		float parte2 = (getPorcentagemCustoBaseEmpresa(notaNatureza) / 100)
				* parte1;

		return parte2;
	}

	public float getDespesaBaseDia(float notaNatureza) {

		return (getPorcentagemCustoBaseEmpresa(notaNatureza) / 100)
				* despesaBaseDia;
	}

}
