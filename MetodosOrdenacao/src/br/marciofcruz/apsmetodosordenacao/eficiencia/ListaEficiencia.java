package br.marciofcruz.apsmetodosordenacao.eficiencia;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaEficiencia {
	
	private List<Eficiencia> listaEficiencia = new ArrayList<>();

	public List<Eficiencia> getListaEficiencia() {
		return listaEficiencia;
	}
	
	public void adicionar(Eficiencia eficiencia) {
		listaEficiencia.add(eficiencia);
	}
	
	public Eficiencia getEficienciaPorQuantidadeImagens(int quantidadeImagens) throws InvalidAlgorithmParameterException {
		Iterator<Eficiencia> iterator = listaEficiencia.iterator();
		
		while(iterator.hasNext()) {
			Eficiencia retorno = iterator.next();
			
			if (retorno.getQuantidadeImagens() == quantidadeImagens) {
				return retorno;
			}
		}
		
		throw new InvalidAlgorithmParameterException("Argumento inválido");
	}	
	
}
