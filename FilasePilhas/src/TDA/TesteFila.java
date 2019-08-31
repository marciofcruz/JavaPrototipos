package TDA;

public class TesteFila {

	public static void main(String[] args) {
		FilaCircular fila = new FilaCircular();
		
		fila.iniciar(10);
		
		fila.entrar(1);
		fila.entrar(2);
		fila.entrar(3);
		fila.entrar(4);
		fila.entrar(5);
		fila.entrar(6);
		fila.entrar(7);
		fila.entrar(8);
		fila.entrar(9);
		fila.entrar(10);
		
		fila.sair();
		fila.sair();
		fila.sair();
		fila.sair();
		
		
		System.out.println(fila.pesquisaFrente());
		

	}

}
