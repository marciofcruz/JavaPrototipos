package br.marciofcruz.apsmetodosordenacao.apresentacao;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

/**
 * Esta classe representa os números alcançados através do processamento de determinado método em
 * função de determinada quantidade de imagens
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.0
 */

public class Resultado {
	
	private TipoMetodoOrdenacao tipoMetodoOrdenacao;
	private int quantidadeImagens;
	private long tempoemNs;
	
	
	public TipoMetodoOrdenacao getTipoMetodoOrdenacao() {
		return tipoMetodoOrdenacao;
	}
	
	@Override
	public String toString() {
		return "Resultado [nomeMetodo=" + TipoMetodoOrdenacao.getNome(tipoMetodoOrdenacao)+ ", quantidadeImagens="
				+ quantidadeImagens +", tempoemNs=" + tempoemNs + "]";
	}

	public int getQuantidadeImagens() {
		return quantidadeImagens;
	}

	public Resultado(TipoMetodoOrdenacao tipoMetodoOrdenacao, int quantidadeImagens) {
		this.tipoMetodoOrdenacao = tipoMetodoOrdenacao;
		this.quantidadeImagens = quantidadeImagens;

		tempoemNs = 0;
	}

	public long getTempoemNanoSegundo() {
		return tempoemNs;
	}
	
	public long getTempoEmMicroSegundo() {
		return tempoemNs/1000;
	}
	
	
	public void setTempoemNs(long tempoemNs) {
		this.tempoemNs = tempoemNs;
	}
}
