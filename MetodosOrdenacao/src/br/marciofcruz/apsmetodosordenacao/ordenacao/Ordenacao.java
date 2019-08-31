package br.marciofcruz.apsmetodosordenacao.ordenacao;

/**
 * Esta é uma classe abstrata que será utilizada por vários métodos de ordenação a serem implementados
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.00
 */

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;


import br.marciofcruz.apsmetodosordenacao.apresentacao.Resultado;
import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

public abstract class Ordenacao {

	protected ImagemSatelite[] imagens;
	private String nomeClasse = "";
	private Resultado resultado;
	private int tamanho;

	/**
	 * Construtor Ordenacao O construtor recebe o array de imagens que deve ser
	 * ordenado
	 * 
	 * @param imagens
	 * @throws InvalidAlgorithmParameterException 
	 */

	public Ordenacao() {

		String nomeCompletoClasse = getClass().toString();
		this.nomeClasse = nomeCompletoClasse.substring(
				nomeCompletoClasse.indexOf(".") + 1,
				nomeCompletoClasse.length());
	}
	
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Este método vai ser chamado por quem instanciar determinado método de
	 * ordenção Já possui todo o controle contagem dos indices do resultado como
	 * tempo, comparaçóes, etc
	 * @throws InvalidAlgorithmParameterException 
	 */

	public Resultado processar(ImagemSatelite[] imagens) throws InvalidAlgorithmParameterException {
		this.imagens = imagens.clone();
		this.tamanho = imagens.length;
		resultado = new Resultado(TipoMetodoOrdenacao.getTipoPorNome(nomeClasse), imagens.length);
		
		long tempoInicial, tempoFinal; 
		
		tempoInicial = System.nanoTime();
		Ordenar(); // método será definido na classe que
										// extende Ordenação
		tempoFinal = System.nanoTime();
		
		resultado.setTempoemNs(tempoFinal-tempoInicial);
		
		testarOrdenacaoArray();
		
		return resultado;
	}

	/**
	 * Este método deve ser implementado para quem estender este classe Como
	 * este método é utilizado internamente nesta classe abstrata, repassamos
	 * como retorno o resultado
	 * 
	 * @param resultado
	 * @return resultado
	 */

	protected abstract void Ordenar();

	/*
	 * Depois de ordenar, temos que chamar este método para certificar que o
	 * método implementado fez a ordenação crescente corretamente
	 */
	private void testarOrdenacaoArray() {
		int ultimaChave = -1;

		for (int i = 0; i < imagens.length; i++) {
			if (imagens[i].hashCode() < ultimaChave) {
				throw new AssertionError(
						"Método de ordenação foi aplicado mas, array de imagens não está ordenado corretamente: "
								+ "\nMétodo: "
								+ TipoMetodoOrdenacao.getNome(resultado.getTipoMetodoOrdenacao())
								+ "\nQtd. Imagens: "
								+ resultado.getQuantidadeImagens());

			}

			ultimaChave = imagens[i].hashCode();
		}
	}

	@Override
	public String toString() {
		return nomeClasse + " " + "Itens: " + resultado.getQuantidadeImagens()
				+ "\t" + "Tempo em ns: " + resultado.getTempoEmMicroSegundo() + "\t"+
				Arrays.toString(imagens);

	}
}
