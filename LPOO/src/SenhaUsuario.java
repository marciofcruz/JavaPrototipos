
public class SenhaUsuario {
	
	private int id = 0;
	private String senhaHasheada = null;
	
	public SenhaUsuario(int id, String senhaHasheada) {
		super();
		this.id = id;
		this.senhaHasheada = senhaHasheada;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSenhaHasheada() {
		return senhaHasheada;
	}
	
	public void setSenhaHasheada(String senhasHasheada){
		this.senhaHasheada = senhasHasheada;
	}
	
	@Override
	public int hashCode() {
		final int prime = 47;
		
		int result = 1;
		result = prime * result * id;
		result = prime * result + ((senhaHasheada == null ) ? 0: senhaHasheada.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		else if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		SenhaUsuario outro = (SenhaUsuario) obj;
		
		if (id!=outro.id) {
			return false;
		}
		else if (senhaHasheada != outro.senhaHasheada) {
			return false;
		}

		return true;
	}
}
