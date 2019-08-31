package br.marciofcruz.apsmetodosordenacao.ordenacao;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;

/**
 * Esta classe implementa a definição da técnica Merge Sort, que é uma técnica que utiliza
 * o conceito de dividir para conquistar e, por isso, seu tipo é de particionamento.
 * Sua desvantagem é que temos que ter outra estrutura de dados instanciada, no caso, um array
 * auxiliar
 * 
 * Algoritmo do tipo: Particionamento
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see br.marciofcruz.apsmetodosordenacao.ordenacao.Ordenacao
 * @version 1.00
 * @since 1.00
 */

public class MergeSort extends Ordenacao {

	private ImagemSatelite[] arrayAuxiliar;
	
	private void mergeSort(int indiceInicio, int indiceFim) {
		if (indiceInicio < indiceFim) {
			int indiceMetade = indiceInicio + (indiceFim - indiceInicio) / 2;

			mergeSort(indiceInicio, indiceMetade); // ordenando primeira metade
			mergeSort(indiceMetade + 1, indiceFim); // Ordenando a segunda metade

			// combinando as partes
			combinar(indiceInicio, indiceMetade, indiceFim);
		}
	}

	private void combinar(int indiceInicio, int indiceMetade, int indiceFim) {

		// Copiar as partes para os arrays
		for (int i = indiceInicio; i <= indiceFim; i++) {
			arrayAuxiliar[i] = imagens[i];
		}

		int i = indiceInicio;
		int j = indiceMetade + 1;
		int k = indiceInicio;

		// Copiar os valores menores do primeiro array esquerdo ou direito para
		// o array ImagemSatelite
		while (i <= indiceMetade && j <= indiceFim) {
			if (arrayAuxiliar[i].hashCode() <= arrayAuxiliar[j].hashCode()) {
				imagens[k] = arrayAuxiliar[i];
				i++;
			} else {
				imagens[k] = arrayAuxiliar[j];
				j++;
			}
			
			k++;
		}

		// copiar o resto do array da primeira parte até o Array Original
		while (i <= indiceMetade) {
			imagens[k] = arrayAuxiliar[i];
			k++;
			i++;
		}

	}

	@Override
	protected void Ordenar() {
		arrayAuxiliar = new ImagemSatelite[getTamanho()]; // criando array auxiliar

		mergeSort(0, getTamanho() - 1);
	}

}
