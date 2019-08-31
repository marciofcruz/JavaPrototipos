public class Hanoi {

	private Pilha[] pilha = new Pilha[3];

	int jogadas = 0;

	private void imprimirStatusPilha() {
		System.out.println("Jogada: " + jogadas);
		System.out.println(pilha[0].toString());
		System.out.println(pilha[1].toString());
		System.out.println(pilha[2].toString());
		System.out.println("");
	}

	private int getIndicePilhaMenorTopo(int posicaoAtual) {
		int menorTopo = -1;
		int indice = -1;

		for (int cont = 0; cont < pilha.length; cont++) {
			if (pilha[cont].topo() != -1) {
				if (cont != posicaoAtual) {
					if (menorTopo != -1 || menorTopo < pilha[cont].topo()) {
						
						if (cont == pilha.length-1) {
							if (menorTopo < pilha[cont].topo()) {
								break;
							}
						}
						else
						{
							menorTopo = pilha[cont].topo();
							indice = cont;
						}
					}
				}

			}

		}

		return indice;
	}

	public void iniciar(int tamanho) {

		pilha[0] = new Pilha("A");
		pilha[1] = new Pilha("B");
		pilha[2] = new Pilha("C");

		for (int cont = 0; cont < pilha.length; cont++) {
			pilha[cont].iniciarPilha(tamanho);
		}

		// inicializar com valores padrões a primeira pilha
		for (int cont = tamanho; cont >= 1; cont--) {
			pilha[0].empilhar(cont);
		}
	}

	public void resolver() {
		int indiceMenorTopo = 0;
		int ultimaVisita = 0;

		while (!pilha[2].estaCheia()) {

			if (jogadas == 0) {

				if ((pilha[0].tamanhoDaPilha() % 2) == 0) {
					pilha[1].moverDe(pilha[0]);
					jogadas++;
					ultimaVisita = 1;
				} else {
					pilha[2].moverDe(pilha[0]);
					jogadas++;
					ultimaVisita = 2;
				}
			} else {
				indiceMenorTopo = getIndicePilhaMenorTopo(ultimaVisita);

				if (indiceMenorTopo == 2) {
					if (pilha[1].topo() < pilha[0].topo()) {
						pilha[1].moverDe(pilha[2]);
						jogadas++;
						ultimaVisita = 1;
					} else {
						pilha[0].moverDe(pilha[2]);
						jogadas++;
						ultimaVisita = 0;
					}

				} else {

					for (int cont = 0; cont < pilha.length; cont++) {
						if (cont != indiceMenorTopo) {
							if (cont != ultimaVisita) {
								pilha[cont].moverDe(pilha[indiceMenorTopo]);
								jogadas++;
								ultimaVisita = cont;
								break;
							}
						}
					}
				}
			}

			imprimirStatusPilha();

		}
	}
}
