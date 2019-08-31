package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

/**
 * Esta classe implementa a definição da técnica BubbleSort Esta estrutura de
 * dados define que devemos trocar valores em posições subsequentes. E,
 * repetimos este processo para todos os itens do Array.
 * 
 * Algoritmo do tipo: Troca. Análise de complexidade de algoritmo:
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see br.marciofcruz.apsmetodosordenacao.ordenacao.Ordenacao
 * @version 1.00
 * @since 1.00
 */

public class BubbleSort extends Ordenacao {

	@Override
	protected void Ordenar() {
		
		short limite = (short) getTamanho();
		
		boolean houveTroca = true;

		while (limite > 0 && houveTroca) {

			houveTroca = false;
			
			for (short i = 0; i < limite-1; i++) {

				if (imagens[i + 1].hashCode() < imagens[i].hashCode()) {
					ImagemSatelite swap = imagens[i];
					imagens[i] = imagens[i + 1];
					imagens[i + 1] = swap;
					
					houveTroca = true;
				}
			}
			
			limite--;
		}
	}

}
