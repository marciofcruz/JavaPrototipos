package br.marciofcruz.apsmetodosordenacao.tipos;

import java.awt.Color;
import java.security.InvalidAlgorithmParameterException;

public enum TipoExibicaoGrafico {
	tempoProcessamento;

	public static String getDescricao(TipoExibicaoGrafico tipo)
			throws InvalidAlgorithmParameterException {
		switch (tipo) {
		case tempoProcessamento:
			return "Tempo de Processamento";
		default:
			throw new InvalidAlgorithmParameterException("Argumento inválido");

		}
	}

	public static Color getCor(TipoExibicaoGrafico tipo)
			throws InvalidAlgorithmParameterException {
		switch (tipo) {
		case tempoProcessamento:
			return Color.blue;
		default:
			throw new InvalidAlgorithmParameterException("Argumento inválido");

		}

	}
}