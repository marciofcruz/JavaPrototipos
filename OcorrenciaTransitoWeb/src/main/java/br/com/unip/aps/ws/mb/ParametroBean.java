package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.ParametroDao;
import br.com.unip.aps.ws.model.Parametro;

@ManagedBean
@RequestScoped
public class ParametroBean {

	@Inject
	private ParametroDao dao;
	private Parametro parametro = new Parametro();
	private List<Parametro> parametros;

	public void grava() {

		try {
			if (parametro.getId() == null)
				dao.adiciona(parametro);
			else
				dao.atualiza(parametro);

			addMessage("Parametro gravado!", FacesMessage.SEVERITY_INFO);

			parametro = new Parametro();
			parametros = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar o parametro!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Parametro parametro) {
		try {
			dao.remove(parametro);
			addMessage("Parametro removido!", FacesMessage.SEVERITY_INFO);
			parametros = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o parametro!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Parametro> getParametros() {

		if (parametros == null)
			parametros = dao.listaTodos();

		return parametros;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
