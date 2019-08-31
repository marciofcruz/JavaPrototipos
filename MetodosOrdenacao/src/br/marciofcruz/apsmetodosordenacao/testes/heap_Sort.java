package br.marciofcruz.apsmetodosordenacao.testes;

public class heap_Sort {

	public static void main(String a[]) {
		int i;
		int arr[] = { 14, 3, 44, 4, 5, 2 };

		System.out.println("\n  Heap Sort\n---------------\n");
		System.out.println("\n  Unsorted Array\n\n");
		for (i = 0; i < arr.length; i++)
			System.out.print(" " + arr[i]);

		for (i = arr.length; i > 1; i--) {
			fnSortHeap(arr, i - 1);
		}

		System.out.println("\n  Sorted array\n---------------\n");
		for (i = 0; i < arr.length; i++)
			System.out.print(" " + arr[i]);
	}

	public static void fnSortHeap(int array[], int posicaoRelativa) {
		int i, o;

		int filhaEsquerda, filhaDireita, filhaCentral, posicaoRaizCentral, swap;

		posicaoRaizCentral = (posicaoRelativa - 1) / 2;

		for (o = posicaoRaizCentral; o >= 0; o--) {

			for (i = posicaoRaizCentral; i >= 0; i--) {

				filhaEsquerda = (2 * i) + 1;
				filhaDireita = (2 * i) + 2;

				if ((filhaEsquerda <= posicaoRelativa)
						&& (filhaDireita <= posicaoRelativa)) {

					if (array[filhaDireita] >= array[filhaEsquerda]) {
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

				if (array[i] < array[filhaCentral]) {
					swap = array[i];
					array[i] = array[filhaCentral];
					array[filhaCentral] = swap;
				}
			}
		}
		
		swap = array[0];
		array[0] = array[posicaoRelativa];
		array[posicaoRelativa] = swap;
		return;
	}
}