package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.ContatoDao;
import br.com.unip.aps.ws.model.Contato;

@ManagedBean
@RequestScoped
public class ContatoBean {

	@Inject
	private ContatoDao dao;
	private Contato contato = new Contato();
	private List<Contato> contatos;

	public void grava() {

		try {
			if (contato.getId() == null)
				dao.adiciona(contato);
			else
				dao.atualiza(contato);

			addMessage(contato.getEmail() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			contato = new Contato();
			contatos = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o contato!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Contato contato) {
		try {
			dao.remove(contato);
			addMessage(contato.getEmail() + " removido!",
					FacesMessage.SEVERITY_INFO);
			contatos = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o contato!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Contato> getContatos() {

		if (contatos == null)
			contatos = dao.listaTodos();

		return contatos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
