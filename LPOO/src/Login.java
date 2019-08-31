import java.security.NoSuchAlgorithmException;

public interface Login {

	public boolean login(String usuario, char[] senha);

	public byte[] encripta(char[] senha) throws NoSuchAlgorithmException;
}