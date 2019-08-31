public class ExecutaLogin {

	public ExecutaLogin(Login login, String usuario, char[] senha) {
		if(login.login(usuario, senha)){
			System.out.println("usuario logado");
		}else{
			System.out.println("login falhou");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

}