package br.marciofcruz.apsmetodosordenacao.imagem;

/**
 * Esta classe representa cada imagem capturada no satélite
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;

public class ImagemSatelite {

	private int hashCode;
	private String nomeImagem;

	public String getNomeImagem() {
		return nomeImagem;
	}
	
	/**
	 * Construtor recebe o nome completo da imagem
	 * @param caminhoCompletoImagem
	 * @throws Exceção no caso do nome do arquivo passado como parâmetro estar vazio
	 */

	public ImagemSatelite(String caminhoCompletoImagem)
			throws FileNotFoundException {
		File file = new File(caminhoCompletoImagem);

		if (!file.exists()) {
			throw new FileNotFoundException("Arquivo " + caminhoCompletoImagem
					+ " não encontrado!");
		}

		nomeImagem = file.getName();

		hashCode = criarHashCode(nomeImagem);
	}

	@Override
	public String toString() {
		return "[" + hashCode + "]";
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemSatelite other = (ImagemSatelite) obj;
		if (hashCode != other.hashCode)
			return false;
		return true;
	}

	/**
	 * Método criarHashCode
	 * Estou utilizando a estrutura do hashCode, afim de me trazer um número que representar o 
	 * nome do arquivo indexado
	 * @param nomArquivo
	 * @throws Exceção no caso do nome do arquivo passado como parâmetro estar vazio
	 */

	private int criarHashCode(String nomeArquivo) {
		if (nomeArquivo.isEmpty()) {
			throw new IllegalArgumentException(getClass().toString()
					+ ": nome Arquivo está vazio!");
		}

		StringBuilder auxiliar = new StringBuilder();

		for (int i = 0; i < nomeArquivo.length(); i++) {
			char c = nomeArquivo.charAt(i);

			if (Character.isDigit(c)) {
				auxiliar.append(c);

			}
		}

		return Integer.parseInt(auxiliar.toString());
	}
}
