package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.PaisDao;
import br.com.unip.aps.ws.model.Pais;

@ManagedBean
@RequestScoped
public class PaisBean {

	@Inject
	private PaisDao dao;
	private Pais pais = new Pais();
	private List<Pais> paises;

	public void grava() {

		try {
			if (pais.getId() == null)
				dao.adiciona(pais);
			else
				dao.atualiza(pais);

			addMessage(pais.getNome() + " gravado!", FacesMessage.SEVERITY_INFO);
			pais = new Pais();
			paises = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o país!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Pais pais) {
		try {
			dao.remove(pais);
			addMessage(pais.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			paises = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o país!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Pais> getPaises() {

		if (paises == null)
			paises = dao.listaTodos();

		return paises;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
