package br.marciofcruz.apsmetodosordenacao.tipos;

import java.security.InvalidAlgorithmParameterException;

import br.marciofcruz.apsmetodosordenacao.ordenacao.*;

public enum TipoMetodoOrdenacao {
	bubbleSort, selectionSort, /*heapSort,*/ insertionSort, mergeSort, quickSort, radixSort;

	static public String getNome(TipoMetodoOrdenacao tipoMetodoOrdenacao) {
		switch (tipoMetodoOrdenacao) {
		case bubbleSort:
			return "Bubble Sort";
		case selectionSort:
			return "Selection Sort";
		/*case heapSort:
			return "Heap Sort"; */
		case insertionSort:
			return "Insertion Sort";
		case mergeSort:
			return "Merge Sort";
		case quickSort:
			return "Quick Sort";
		case radixSort:
			return "Radix Sort";
		default:
			return null;
		}
	}

	static public Ordenacao getInstanciaDe(
			TipoMetodoOrdenacao tipoMetodoOrdenacao) {
		switch (tipoMetodoOrdenacao) {
		case bubbleSort:
			return new BubbleSort();
		case selectionSort:
			return new SelectionSort();
		/*case heapSort:
			return new HeapSort(); */
		case insertionSort:
			return new InsertionSort();
		case mergeSort:
			return new MergeSort();
		case quickSort:
			return new QuickSort();
		case radixSort:
			return new RadixSort();
		default:
			return null;
		}
	}

	static public TipoMetodoOrdenacao getTipoPorNome(String nomeClasse)
			throws InvalidAlgorithmParameterException {

		switch (nomeClasse) {
		case "marciofcruz.apsmetodosordenacao.ordenacao.BubbleSort":
			return TipoMetodoOrdenacao.bubbleSort;
		case "marciofcruz.apsmetodosordenacao.ordenacao.SelectionSort":
			return TipoMetodoOrdenacao.selectionSort;
		/*case "marciofcruz.apsmetodosordenacao.ordenacao.HeapSort":
			return TipoMetodoOrdenacao.heapSort; */
		case "marciofcruz.apsmetodosordenacao.ordenacao.InsertionSort":
			return TipoMetodoOrdenacao.insertionSort;
		case "marciofcruz.apsmetodosordenacao.ordenacao.MergeSort":
			return TipoMetodoOrdenacao.mergeSort;
		case "marciofcruz.apsmetodosordenacao.ordenacao.QuickSort":
			return TipoMetodoOrdenacao.quickSort;
		case "marciofcruz.apsmetodosordenacao.ordenacao.RadixSort":
			return TipoMetodoOrdenacao.radixSort;
		default:
			throw new InvalidAlgorithmParameterException(
					"Nâo é possível traduzir o tipo de método de ordenação da classe: "
							+ nomeClasse);
		}

	}

	static public ClassificacaoMetodo getTipoClassificacaoMetodo(
			TipoMetodoOrdenacao tipoMetodoOrdenacao)
			throws InvalidAlgorithmParameterException {
		switch (tipoMetodoOrdenacao) {
		case bubbleSort:
			return ClassificacaoMetodo.porTroca;
		case insertionSort:
			return ClassificacaoMetodo.porInsercao;
		case selectionSort:
			return ClassificacaoMetodo.porSelecao;
		/*case heapSort:
			return ClassificacaoMetodo.porSelecao; */
		case mergeSort:
			return ClassificacaoMetodo.porParticao;
		case quickSort:
			return ClassificacaoMetodo.porParticao;
		case radixSort:
			return ClassificacaoMetodo.porDistribuicao;
			
		default:
			throw new InvalidAlgorithmParameterException("Parâmetro inválido");
		}
	}

}