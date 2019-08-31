package br.marciofcruz.apsmetodosordenacao.tipos;

import java.security.InvalidAlgorithmParameterException;

public enum TipoCriterioOrdem {
	embaralhado, decrescente, crescente;
	
	static public String getNome(TipoCriterioOrdem tipoCriterioOrdem) throws InvalidAlgorithmParameterException {
		switch (tipoCriterioOrdem) {
		case crescente:
			return "Imagens em Ordem Crescente";
		case decrescente:
			return "Imagens em Ordem Decrescente";
		case embaralhado:
			return "Imagens Embaralhadas";
		default:
			throw new InvalidAlgorithmParameterException("Tipo de ordenação não definido");
			
		}
	}

}
