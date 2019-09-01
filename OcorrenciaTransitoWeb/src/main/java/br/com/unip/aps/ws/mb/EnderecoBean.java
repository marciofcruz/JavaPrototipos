package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.EnderecoDao;
import br.com.unip.aps.ws.model.Endereco;

@ManagedBean
@RequestScoped
public class EnderecoBean {

	@Inject
	private EnderecoDao dao;
	private Endereco endereco = new Endereco();
	private List<Endereco> enderecos;

	public void grava() {

		try {
			if (endereco.getId() == null)
				dao.adiciona(endereco);
			else
				dao.atualiza(endereco);

			addMessage("Endereco gravado!", FacesMessage.SEVERITY_INFO);

			endereco = new Endereco();
			enderecos = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o endereco!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Endereco endereco) {
		try {
			dao.remove(endereco);
			addMessage("Endereco removido!", FacesMessage.SEVERITY_INFO);
			enderecos = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o endereco!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Endereco> getEnderecos() {

		if (enderecos == null)
			enderecos = dao.listaTodos();

		return enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
