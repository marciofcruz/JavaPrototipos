package TDA;

public class TesteLista {

	public static void main(String[] args) {
		
		Lista lista = new Lista();
		
		lista.iniciar(30);
		
		lista.adicionar(1);
		lista.adicionar(2);
		lista.adicionar(3);
		lista.adicionar(4);
		lista.adicionar(8);		
		lista.adicionar(5);
		lista.adicionar(6);
		lista.adicionar(7);
		lista.adicionar(3);		
		lista.adicionar(8);
		lista.adicionar(9);
		
		lista.inserir(3, 10);
				
		System.out.println(lista);
		
		lista.remover(8);
		System.out.println(lista);
	

	}

}
