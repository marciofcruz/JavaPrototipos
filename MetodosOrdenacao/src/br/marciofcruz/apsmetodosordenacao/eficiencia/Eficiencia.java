package br.marciofcruz.apsmetodosordenacao.eficiencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

public class Eficiencia {

	private int quantidadeImagens;
	private List<MetodoTempoProcessamento> listaMetodoTempoProcessamento = new ArrayList<>();

	public Eficiencia(int quantidadeImagens) {
		this.quantidadeImagens = quantidadeImagens;
	}
	
	public int getQuantidadeImagens() {
		return quantidadeImagens;
	}
	
	public List<MetodoTempoProcessamento> getListaMetodoTempoProcessamento() {
		return listaMetodoTempoProcessamento;
	}

	public void adicionar(TipoMetodoOrdenacao tipoMetodoOrdenacao,
			long tempoProcessamentoemNanoSegundos) {

		listaMetodoTempoProcessamento.add(new MetodoTempoProcessamento(
				tipoMetodoOrdenacao, tempoProcessamentoemNanoSegundos));
		
		setDadosEficiencia(); 
	}
	
	/*
	 * Este método ordena o array e verifica a eficiência com o registro anterior
	 */
	private void setDadosEficiencia() {
		Collections.sort(listaMetodoTempoProcessamento);
		
		MetodoTempoProcessamento anterior = null;
		Iterator<MetodoTempoProcessamento> iterator = listaMetodoTempoProcessamento.iterator();
		
		while(iterator.hasNext()) {
			MetodoTempoProcessamento atual = iterator.next();
			
			if (anterior != null) {
				double porcentagem = 0;
				
				if (atual.getTempoProcessamentoemNanoSegundos() != 0) {
					double tempoAnterior = (double)anterior.getTempoProcessamentoemMicroSegundos();
					double tempoAtual = (double)atual.getTempoProcessamentoemMicroSegundos(); 
					
					porcentagem = ((tempoAtual/tempoAnterior)-1)*100;
					
					anterior.setEficienciaEmPorcentagem(porcentagem);
				}
			}
			
			anterior = atual;
		}
	}

}
