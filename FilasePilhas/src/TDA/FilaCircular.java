package TDA;

public class FilaCircular {

	private int[] numeros;
	private int cursorInicio, cursorFim;
	private int tamanho;

	public void iniciar(int tamanho) {
		numeros = new int[tamanho];
		cursorInicio = 0;
		cursorFim = 0;
		tamanho = 0;
	}

	private boolean estaCheia() {
		return tamanho == numeros.length;
	}

	public void entrar(int dado) {
		if (estaCheia()) {
			throw new RuntimeException("Stack Overflow");
		} else {
			numeros[cursorFim] = dado;
			cursorFim = verificarCursor(cursorFim);
			tamanho++;
		}
	}

	public boolean estaVazia() {
		return tamanho == 0;
	}

	private int verificarCursor(int cursor) {
		cursor++;

		if (cursor > numeros.length - 1) {
			return 0;
		} else {
			return cursor;
		}

	}

	public void sair() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha vazia");
		} else {

			numeros[cursorInicio] = 0;

			cursorInicio = verificarCursor(cursorInicio);
			tamanho--;

			if (tamanho == 0) {
				cursorFim = 0;
			}
		}
	}

	public int pesquisaFrente() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha vazia");
		} else {
			return numeros[cursorInicio];
		}
	}

	@Override
	public String toString() {
		String aux = "";

		aux += "cursorInicio = " + cursorInicio + "\n" + "cursorFim = "
				+ cursorFim + "\n";

		for (int i = 0; i < numeros.length; i++) {

			if (numeros[i] != 0) {
				aux += "-" + numeros[i];
			}
		}

		return aux;

	}

}
