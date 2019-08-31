public class Pilha {

	int[] numeros;
	int cursorTopo = -1;

	String nome = "";

	public Pilha(String n) {
		super();
		nome = n;
	}

	void iniciarPilha(int tamanho) {
		numeros = new int[tamanho];
	}

	public void esvaziar() {
		cursorTopo = -1;
	}

	public int tamanhoDaPilha() {
		return cursorTopo + 1;
	}

	public boolean estaCheia() {
		return (cursorTopo == numeros.length - 1);
	}

	public boolean estaVazia() {
		return cursorTopo == -1;
	}
	
	public void moverDe(Pilha origem) {
		this.empilhar(origem.topo());
		origem.desempilhar();
	}

	public void empilhar(int numero) {
		if (estaCheia()) {
			throw new ArrayIndexOutOfBoundsException("Stack overflow na Pilha "
					+ nome);
		}

		cursorTopo++;
		numeros[cursorTopo] = numero;

	}

	public void desempilhar() {
		if (estaVazia()) {
			throw new ArrayIndexOutOfBoundsException(
					"Stack underflow na Pilha " + nome);
		}
		else
		{
			cursorTopo--;
		}
	}

	public int topo() {
		if (estaVazia()) {
			return -1;
		} else {
			return numeros[cursorTopo];
		}
	}

	@Override
	public String toString() {
		String linha = nome + ": ";
		for (int cont = 0; cont < cursorTopo+1; cont++) {

			if (numeros[cont] > 0) {
				if (cont > 0) {
					linha += "-";
				}

				linha += numeros[cont];
			}

		}

		return linha;
	}

}
