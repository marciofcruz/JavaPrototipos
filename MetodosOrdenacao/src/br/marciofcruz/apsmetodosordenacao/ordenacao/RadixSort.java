package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

public class RadixSort extends Ordenacao {

	private int bits = 4;

	@Override
	protected void Ordenar() {
		ImagemSatelite[] imagensClone = new ImagemSatelite[getTamanho()];
		ImagemSatelite[] imagensCloneOrig = imagensClone;

		int direitaReferencia = 0;
		for (int mascara = ~(-1 << bits); mascara != 0; mascara <<= bits, direitaReferencia += bits) {
			
			int[] contaLista = new int[1 << bits];

			for (int posicao = 0; posicao < getTamanho(); posicao++) {
				int chave = (imagens[posicao].hashCode() & mascara) >> direitaReferencia;

				++contaLista[chave];
			}

			for (int posicao = 1; posicao < contaLista.length; posicao++) {
				contaLista[posicao] += contaLista[posicao - 1];
			}

			for (int posicao = getTamanho() - 1; posicao >= 0; posicao--) {

				int chave = (imagens[posicao].hashCode() & mascara) >> direitaReferencia;

				--contaLista[chave];

				imagensClone[contaLista[chave]] = imagens[posicao];
			}

			ImagemSatelite[] swap = imagensClone;
			imagensClone = imagens;
			imagens = swap;
		}

		if (imagens == imagensCloneOrig) {
			System.arraycopy(imagens, 0, imagensClone, 0, getTamanho());
		}
	}
}
