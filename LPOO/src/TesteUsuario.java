import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class TesteUsuario {
	
	
	public static void main(String[] args) {
		Set<Usuario> usuarios = new HashSet<>();
		
		for (int cont = 0; cont <= 100; cont++) {
				usuarios.add(new Usuario("nome "+cont, "sobrenome "+cont, true));
		}
		
		for (int cont = 0; cont <= 100; cont++) {
			usuarios.add(new Usuario("nome "+cont, "sobrenome "+cont, true));
	}
		

		Iterator<Usuario> iterator =  usuarios.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
}
