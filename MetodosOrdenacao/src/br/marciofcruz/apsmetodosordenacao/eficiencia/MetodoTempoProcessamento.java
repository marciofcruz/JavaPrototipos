package br.marciofcruz.apsmetodosordenacao.eficiencia;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

public class MetodoTempoProcessamento implements
		Comparable<MetodoTempoProcessamento> {

	private TipoMetodoOrdenacao tipoMetodoOrdenacao;
	private long tempoProcessamentoemNanoSegundos = 0;
	private double eficienciaEmPorcentagem;

	public MetodoTempoProcessamento(TipoMetodoOrdenacao tipoMetodoOrdenacao,
			long tempoProcessamentoemNanoSegundos) {
		this.tipoMetodoOrdenacao = tipoMetodoOrdenacao;
		this.tempoProcessamentoemNanoSegundos = tempoProcessamentoemNanoSegundos;

	}

	public TipoMetodoOrdenacao getTipoMetodoOrdenacao() {
		return tipoMetodoOrdenacao;
	}

	public int getTempoProcessamentoemMicroSegundos() {
		return (int) tempoProcessamentoemNanoSegundos / 1000;
	}

	public long getTempoProcessamentoemNanoSegundos() {
		return tempoProcessamentoemNanoSegundos;
	}

	@Override
	public int hashCode() {
		return (int) (tempoProcessamentoemNanoSegundos / 1000);
	}

	@Override
	public int compareTo(MetodoTempoProcessamento o) {
		if (this.getTempoProcessamentoemNanoSegundos() < o
				.getTempoProcessamentoemNanoSegundos()) {
			return -1;
		} else if (this.getTempoProcessamentoemNanoSegundos() < o
				.getTempoProcessamentoemNanoSegundos()) {
			return 1;

		} else {
			return 0;
		}

	}

	/**
	 * @return the eficienciaEmPorcentagem
	 */
	public double getEficienciaEmPorcentagem() {
		return eficienciaEmPorcentagem;
	}

	/**
	 * @param eficienciaEmPorcentagem the eficienciaEmPorcentagem to set
	 */
	public void setEficienciaEmPorcentagem(double eficienciaEmPorcentagem) {
		this.eficienciaEmPorcentagem = eficienciaEmPorcentagem;
	}
}
