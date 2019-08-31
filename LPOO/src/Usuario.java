
public class Usuario extends PaiUsuario {
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	public Usuario(String nome, String sobrenome, Boolean ativo) {
		super();
		setNome(nome);
		setSobrenome(sobrenome);
		setAtivo(ativo);
	}
	
	@Override
	public String toString() {
		return this.nome+"|"+this.sobrenome+"|"+this.ativo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		final int prime2 = 7;
		int result = 1;
		
		result = (int) this.id * prime *
				 nome.hashCode()*
				 sobrenome.hashCode()+
				 nome.length()+
				 sobrenome.length();
		
		result = (int) (Math.abs(result) / prime2);
		
		/*result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((sobrenome == null) ? 0 : sobrenome.hashCode());  
		*/
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		else if (getClass() != obj.getClass()) {
			return false;
		}
		
		Usuario outro = (Usuario)obj;
		
		if ((ativo != outro.getAtivo()) ||
			(nome != outro.nome) ||
			(sobrenome != outro.sobrenome)) {
			return false;
		}
		
		return true;
	}
	
}
