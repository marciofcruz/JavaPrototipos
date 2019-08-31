package TDA;

public class Pilha {

	int[] numeros;
	int cursor;

	public void iniciar(int tamanho) {
		numeros = new int[tamanho];
		cursor = -1;
	}

	public void esvaziar() {
		cursor = -1;
	}

	public int topo() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha vazia");
		} else {
			return numeros[cursor];
		}
	}

	private boolean estaVazia() {
		return cursor == -1;
	}

	private boolean estaCheia() {
		return cursor == numeros.length - 1;
	}

	public void empilhar(int dado) {
		if (estaCheia()) {
			throw new RuntimeException("Stack Overflow");
		} else {
			cursor++;
			numeros[cursor] = dado;
		}
	}

	public int tamanho() {
		return cursor + 1;
	}

	public void desimpilhar() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha vazia");
		} else {
			cursor--;
		}
	}

	@Override
	public String toString() {
		String aux = "";

		for (int i = 0; i < tamanho(); i++) {
			aux += "-" + numeros[i];
		}

		return aux;
	}

}
