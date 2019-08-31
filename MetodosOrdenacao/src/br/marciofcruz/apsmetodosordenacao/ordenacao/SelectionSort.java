package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

/**
 * Esta classe implementa a definição da técnica SelectionSort Esta estrutura de
 * dados define encontrar a menor chave a partir de um ponteiro que devemos
 * aumentar a cada troca
 * 
 * Algoritmo do tipo: Seleção. Análise de complexidade de algoritmo:
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see br.marciofcruz.apsmetodosordenacao.ordenacao.Ordenacao
 * @version 1.00
 * @since 1.00
 */

public class SelectionSort extends Ordenacao {

	@Override
	protected void Ordenar() {
		short pivo = 0;
		
		short tamanho = (short) getTamanho();
		
		while (pivo < tamanho) {
			
			for (short i = (short) (pivo + 1); i < tamanho; i++) {
				
				if (imagens[pivo].hashCode() > imagens[i].hashCode()) {
					
					ImagemSatelite swap = imagens[pivo];
					imagens[pivo] = imagens[i];
					imagens[i] = swap;
				}
				
			}
			
			pivo++;
		}
	}

}
