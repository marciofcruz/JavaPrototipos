package TDA;

public class TestaPilha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pilha pilha = new Pilha();
		
		pilha.iniciar(10);
		
		pilha.empilhar(1);
		pilha.empilhar(2);
		pilha.empilhar(3);
		pilha.empilhar(4);
		pilha.desimpilhar();
		pilha.desimpilhar();
		pilha.desimpilhar();
		
		pilha.empilhar(56);
		pilha.desimpilhar();
		
		System.out.println(pilha.topo());
		

	}

}
