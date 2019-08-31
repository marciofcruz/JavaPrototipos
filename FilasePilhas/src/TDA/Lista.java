package TDA;

public class Lista {

	private int[] numeros;
	private int cursor;

	public void iniciar(int tMax) {
		numeros = new int[tMax];
		cursor = -1;
	}

	private boolean estaCheia() {
		return cursor == numeros.length - 1;
	}

	public void inserir(int posicao, int dado) {
		if (estaCheia()) {
			throw new RuntimeException("Lista Cheia");
		} else {
			empurrar(posicao);
			numeros[posicao] = dado;
			cursor++;

		}
	}

	private int busca(int dado) {
		for(int i = 0; i < tamanho(); i++) {
			if (numeros[i] == dado) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void adicionar(int dado) {
		if (estaCheia()) {
			throw new RuntimeException("Lista Cheia");
		} else {
			cursor++;
			numeros[cursor] = dado;
		}
	}
	
	private void empurrar(int posicao) {
		if (posicao-1 > cursor) {
			throw new RuntimeException("Posição inválida para empurrar: "
					+ posicao);
		} else {

			for (int i = cursor + 1; i >= posicao + 1; i--) {
				numeros[i] = numeros[i - 1];
			}
		}
	}

	public void remover(int dado) {
		int posicao = busca(dado);
		
		if (posicao != -1) {
			for (int i = posicao; i <= cursor-1; i++) {
				numeros[i] = numeros[i+1];
			}
			
			cursor--;
			
			remover(dado);
		}
	}

	public int tamanho() {
		return cursor + 1;
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
