package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.ProblemaDao;
import br.com.unip.aps.ws.model.Problema;

@ManagedBean
@SessionScoped
public class ProblemaBean {

	@Inject
	private ProblemaDao dao;
	private Problema problema = new Problema();
	private List<Problema> problemas;

	public void grava() {

		try {
			if (problema.getId() == null)
				dao.adiciona(problema);
			else
				dao.atualiza(problema);

			addMessage(problema.getNome() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			problema = new Problema();
			problemas = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o problema!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Problema problema) {
		try {
			dao.remove(problema);
			addMessage(problema.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			problemas = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o problema!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Problema> getProblemas() {

		if (problemas == null)
			problemas = dao.listaTodos();

		return problemas;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
