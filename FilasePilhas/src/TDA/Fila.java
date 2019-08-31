package TDA;

public class Fila {

	private int[] numeros;
	private int cursorInicio, cursorFim;

	public void iniciar(int tamanho) {
		numeros = new int[tamanho];
		cursorInicio = 0;
		cursorFim = 0;
	}

	private boolean estaCheia() {
		return cursorFim == numeros.length;
	}

	public void entrar(int dado) {
		if (estaCheia()) {
			throw new RuntimeException("Stack Overflow");
		} else {
			numeros[cursorFim] = dado;
			cursorFim++;
		}
	}

	public boolean estaVazia() {
		return cursorFim == cursorInicio;
	}

	public void sair() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha vazia");
		} else {
			cursorInicio++;
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
		
		for(int i = cursorInicio; i < cursorFim; i++) {
			aux += "-" + numeros[i];
		}
		
		return aux;
		
	}

}
