package br.marciofcruz.apsmetodosordenacao.tipos;

import java.security.InvalidAlgorithmParameterException;

public enum ClassificacaoMetodo {
	porTroca, porSelecao, porInsercao, porParticao, porPosicao, porDistribuicao, nenhum;
	
	static public String getNome(ClassificacaoMetodo classificacaoMetodo) throws InvalidAlgorithmParameterException {
		switch (classificacaoMetodo) {
		case porInsercao:
			return "Por Inserção";
		case porParticao:
			return "Por Partição";
		case porPosicao:
			return "por Posição";
		case porSelecao:
			return "Por Seleção";
		case porTroca:
			return "Por Troca";
		case porDistribuicao:
			return "Por Distribuicao";
		default:
			throw new InvalidAlgorithmParameterException("Parâmetro Inválido");
		}
	}

}
