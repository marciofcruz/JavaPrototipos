import java.io.IOException;

public class TesteHanoi {

	private static Hanoi hanoi = new Hanoi();

	public static void main(String[] args) throws IOException {
		try {
			hanoi.iniciar(3);

			hanoi.resolver();
		} catch (ArrayIndexOutOfBoundsException E) {
			System.out.println(E.getMessage());
		}
	}
}
