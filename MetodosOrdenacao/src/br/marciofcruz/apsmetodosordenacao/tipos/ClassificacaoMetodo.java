package br.marciofcruz.apsmetodosordenacao.tipos;

import java.security.InvalidAlgorithmParameterException;

public enum ClassificacaoMetodo {
	porTroca, porSelecao, porInsercao, porParticao, porPosicao, porDistribuicao, nenhum;
	
	static public String getNome(ClassificacaoMetodo classificacaoMetodo) throws InvalidAlgorithmParameterException {
		switch (classificacaoMetodo) {
		case porInsercao:
			return "Por Inser��o";
		case porParticao:
			return "Por Parti��o";
		case porPosicao:
			return "por Posi��o";
		case porSelecao:
			return "Por Sele��o";
		case porTroca:
			return "Por Troca";
		case porDistribuicao:
			return "Por Distribuicao";
		default:
			throw new InvalidAlgorithmParameterException("Par�metro Inv�lido");
		}
	}

}
