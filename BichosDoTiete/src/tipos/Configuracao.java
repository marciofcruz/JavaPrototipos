package tipos;

import java.io.Serializable;

public class Configuracao implements Serializable {
	
	private static final long serialVersionUID = 2024075164270962487L;
	private String localizacao;
	private String usuario;
	private String host; // endereço do servidor
	private String porta;
	private Boolean ehServidor;
	
	public Boolean getEhServidor() {
		return ehServidor;
	}
	public void setEhServidor(Boolean ehServidor) {
		this.ehServidor = ehServidor;
	}
	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	


}
