package br.marciofcruz.apsmetodosordenacao.apresentacao;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

public class ListaResultado {

	private List<Resultado> resultados = new ArrayList<>();
	
	public List<Resultado> getResultados() {
		return resultados;
	}
	
	public void adicionar(final Resultado resultado) {
		resultados.add(resultado);
	}
	
	public Resultado getRetornoPorQuantidadeImagens(TipoMetodoOrdenacao tipoMetodoOrdenacao, int quantidadeImagens) throws InvalidAlgorithmParameterException {
		Iterator<Resultado> iterator = resultados.iterator();
		
		while(iterator.hasNext()) {
			Resultado retorno = iterator.next();
			
			if (retorno.getQuantidadeImagens() == quantidadeImagens &&
				retorno.getTipoMetodoOrdenacao() == tipoMetodoOrdenacao) {
				return retorno;
			}
		}
		
		throw new InvalidAlgorithmParameterException("Argumento inválido");
	}
}
