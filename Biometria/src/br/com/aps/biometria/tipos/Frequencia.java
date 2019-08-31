package br.com.aps.biometria.tipos;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class Frequencia implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(frequenciaRGB);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frequencia other = (Frequencia) obj;
		if (!Arrays.deepEquals(frequenciaRGB, other.frequenciaRGB))
			return false;
		return true;
	}

	private static final long serialVersionUID = -196988968721419103L;

	private String nomeArquivo;
	private String nomeUsuario;
	private int[][] frequenciaRGB;
	private int nivelAcesso; // 1 a 3

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public Frequencia(String nomeArquivo) throws IOException {
		Imagem imagem = new Imagem(nomeArquivo);

		this.nomeArquivo = nomeArquivo;
		this.frequenciaRGB = imagem.getFrequencia();
		this.nivelAcesso = (1 + (int) (Math.random() * 3));

		File file = new File(nomeArquivo);
		this.nomeUsuario = "USUARIO " + file.getName();
	}

	public int[][] getFrequenciaRGB() {
		return frequenciaRGB;
	}

	@Override
	public String toString() {
		return "Frequencia [nomeArquivo=" + nomeArquivo + ", frequenciaRGB="
				+ Arrays.toString(frequenciaRGB) + "]";
	}

}
