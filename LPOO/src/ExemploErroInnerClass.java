public class ExemploErroInnerClass {

	public static Aluno criarAluno(){

		return new ExemploErroInnerClass().new Aluno();
	}

	public class Aluno {
		public Aluno(){

		}

		public void mostra(){
			System.out.println("oi2");
		}
	}



	public static void main(String [] args) {
		Aluno aluno = criarAluno();
		aluno.mostra();
	}
}