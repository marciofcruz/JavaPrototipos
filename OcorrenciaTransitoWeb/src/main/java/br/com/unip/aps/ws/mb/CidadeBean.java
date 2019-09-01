package br.com.unip.aps.ws.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.unip.aps.ws.dao.CidadeDao;
import br.com.unip.aps.ws.model.Cidade;

@ManagedBean
@RequestScoped
public class CidadeBean {

	@Inject
	private CidadeDao dao;
	private Cidade cidade = new Cidade();
	private List<Cidade> cidades;

	public void grava() {

		try {
			if (cidade.getId() == null)
				dao.adiciona(cidade);
			else
				dao.atualiza(cidade);

			addMessage(cidade.getNome() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			cidade = new Cidade();
			cidades = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar a cidade!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remove(Cidade cidade) {
		try {
			dao.remove(cidade);
			addMessage(cidade.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			cidades = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover a cidade!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Cidade> getCidades() {

		if (cidades == null)
			cidades = dao.listaTodos();

		return cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
