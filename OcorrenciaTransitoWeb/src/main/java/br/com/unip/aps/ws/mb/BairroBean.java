package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.BairroDao;
import br.com.unip.aps.ws.model.Bairro;

@ManagedBean
@RequestScoped
public class BairroBean {

	@Inject
	private BairroDao dao;
	private Bairro bairro = new Bairro();
	private List<Bairro> bairros;

	public void grava() {

		try {
			if (bairro.getId() == null)
				dao.adiciona(bairro);
			else
				dao.atualiza(bairro);

			addMessage(bairro.getNome() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			bairro = new Bairro();
			bairros = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o bairro!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Bairro bairro) {
		try {
			dao.remove(bairro);
			addMessage(bairro.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			bairros = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o bairro!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Bairro> getBairros() {

		if (bairros == null)
			bairros = dao.listaTodos();

		return bairros;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
