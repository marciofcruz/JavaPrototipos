package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

public class HeapSort extends Ordenacao {

	@Override
	protected void  Ordenar() {
		for (int i = getTamanho(); i > 1; i--) {
			executarHeap(i - 1);
		}
	}

	private void executarHeap(int posicaoRelativa) {
		int cont2, cont1;

		int filhaEsquerda, filhaDireita, filhaCentral, posicaoRaizCentral;
		
		ImagemSatelite swap;

		posicaoRaizCentral = (posicaoRelativa - 1) / 2;

		for (cont1 = posicaoRaizCentral; cont1 >= 0; cont1--) {

			for (cont2 = posicaoRaizCentral; cont2 >= 0; cont2--) {

				filhaEsquerda = (2 * cont2) + 1;
				filhaDireita = (2 * cont2) + 2;

				if ((filhaEsquerda <= posicaoRelativa)
						&& (filhaDireita <= posicaoRelativa)) {

					if (imagens[filhaDireita].hashCode() >= imagens[filhaEsquerda]
							.hashCode()) {
						filhaCentral = filhaDireita;
					} else {
						filhaCentral = filhaEsquerda;
					}
				} else {
					if (filhaDireita > posicaoRelativa) {
						filhaCentral = filhaEsquerda;
					} else {
						filhaCentral = filhaDireita;
					}
				}

				if (imagens[cont2].hashCode() < imagens[filhaCentral].hashCode()) {
					swap = imagens[cont2];
					imagens[cont2] = imagens[filhaCentral];
					imagens[filhaCentral] = swap;
				}
			}
		}

		swap = imagens[0];
		imagens[0] = imagens[posicaoRelativa];
		imagens[posicaoRelativa] = swap;
		
		return;
	}

}
