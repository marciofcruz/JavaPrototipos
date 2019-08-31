package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

/**
 * Esta classe implementa a defini��o da t�cnica InsertionSort Esta estrutura de
 * dados utilizamos um sub-aranjo para controlar a inser��o
 * 
 * Algoritmo do tipo: Inser��o.
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see br.marciofcruz.apsmetodosordenacao.ordenacao.Ordenacao
 * @version 1.00
 * @since 1.00
 */

public class InsertionSort extends Ordenacao {

	@Override
	protected void Ordenar() {
		if (imagens.length > 1) {

			short ponteiroSubArranjo = 1;

			while (ponteiroSubArranjo < getTamanho()) {

				// varrer do final at� o in�cio do sub-arranjo
				short ponteiroAuxiliar = ponteiroSubArranjo;
				
				boolean sairLoop = false;
				
				for (short i = (short) (ponteiroAuxiliar-1); i >= 0 && !sairLoop; i--) {
					
					if (imagens[ponteiroAuxiliar].hashCode() < imagens[i]
							.hashCode()) {
						ImagemSatelite swap = imagens[i];
						imagens[i] = imagens[ponteiroAuxiliar];
						imagens[ponteiroAuxiliar] = swap;

						ponteiroAuxiliar = i;
					}
					else
					{
						sairLoop = true;
					}
				}

				ponteiroSubArranjo++;
			}
		}
	}

}
