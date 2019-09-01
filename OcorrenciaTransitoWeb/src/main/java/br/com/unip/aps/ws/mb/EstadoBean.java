package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.EstadoDao;
import br.com.unip.aps.ws.model.Estado;

@ManagedBean
@RequestScoped
public class EstadoBean {

	@Inject
	private EstadoDao dao;
	private Estado estado = new Estado();
	private List<Estado> estados;

	public void grava() {

		try {
			if (estado.getId() == null)
				dao.adiciona(estado);
			else
				dao.atualiza(estado);

			addMessage(estado.getNome() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			estado = new Estado();
			estados = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar a estado!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Estado estado) {
		try {
			dao.remove(estado);
			addMessage(estado.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			estados = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover o estado!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Estado> getEstados() {

		if (estados == null)
			estados = dao.listaTodos();

		return estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
