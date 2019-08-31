package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

/**
 * Esta classe implementa a definição da técnica Quick Sort, que é uma técnica
 * que utiliza o conceito de dividir para conquistar e, por isso, seu tipo é de
 * particionamento. Sua desvantagem é que temos que ter outra estrutura de dados
 * instanciada, no caso, um array auxiliar
 * 
 * Algoritmo do tipo: Particionamento
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see br.marciofcruz.apsmetodosordenacao.ordenacao.Ordenacao
 * @version 1.00
 * @since 1.00
 */

public class QuickSort extends Ordenacao {

	private void quickSort(int indiceInicio, int indiceFim) {
		int menorQue = indiceInicio;

        int maiorQue = indiceFim;

        ImagemSatelite swap;
        
        ImagemSatelite meio = imagens[(indiceInicio + indiceFim) / 2];

        while (menorQue < maiorQue) {

            while (imagens[menorQue].hashCode() < meio.hashCode()) {
                menorQue++;
            }

            while (imagens[maiorQue].hashCode() > meio.hashCode()) {
                maiorQue--;
            }

            if (menorQue <= maiorQue) {

            	swap = imagens[menorQue];

                imagens[menorQue] = imagens[maiorQue];

                imagens[maiorQue] = swap;

                menorQue++;

                maiorQue--;
            }

        }

        if (indiceInicio < maiorQue) {

            quickSort(indiceInicio, maiorQue);

        }

        if (menorQue < indiceFim) {

            quickSort(menorQue, indiceFim);

        }	

	}

	@Override
	protected void  Ordenar() {
		quickSort(0, getTamanho()-1);
	}

}
