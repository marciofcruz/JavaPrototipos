package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.LogradouroDao;
import br.com.unip.aps.ws.model.Logradouro;

@ManagedBean
@RequestScoped
public class LogradouroBean {

	@Inject
	private LogradouroDao dao;
	private Logradouro logradouro = new Logradouro();
	private List<Logradouro> logradouros;

	public void atualiza() {

		try {
			dao.atualiza(logradouro);
			addMessage(logradouro.getNome() + " atualizado!",
					FacesMessage.SEVERITY_INFO);

			logradouro = new Logradouro();
			logradouros = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar atualizar o logradouro!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Logradouro> getLogradouros() {

		if (logradouros == null)
			logradouros = dao.listaTodos();

		return logradouros;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
