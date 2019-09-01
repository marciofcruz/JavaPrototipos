package br.com.unip.aps.ws.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;

import br.com.unip.aps.ws.dao.OcorrenciaDao;
import br.com.unip.aps.ws.model.Contato;
import br.com.unip.aps.ws.model.Endereco;
import br.com.unip.aps.ws.model.Ocorrencia;
import br.com.unip.aps.ws.model.Problema;

@ManagedBean
@ViewScoped
public class OcorrenciaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3765206373544000150L;
	@Inject
	private OcorrenciaDao dao;
	private Ocorrencia ocorrencia = new Ocorrencia();
	private Problema problema = new Problema();
	private Contato contato = new Contato();
	private Endereco endereco = new Endereco();
	private List<Ocorrencia> ocorrencias;

	public void gravaTudo() {

		try {
			dao.adicionaTudo(ocorrencia, problema, contato, endereco);

			addMessage(ocorrencia.getNome() + " gravado!",
					FacesMessage.SEVERITY_INFO);

			refresh();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar gravar a ocorrência!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void removeTudo(Ocorrencia ocorrencia) {
		try {
			dao.removeTudo(ocorrencia);
			addMessage(ocorrencia.getNome() + " removido!",
					FacesMessage.SEVERITY_INFO);
			ocorrencias = dao.listaTodos();
		} catch (Exception ex) {
			addMessage("Houve um erro ao tentar remover a ocorrência!",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<Ocorrencia> getOcorrencias() {

		if (ocorrencias == null)
			ocorrencias = dao.listaTodos();

		return dao.listaTodos();
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
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

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep(); // passa para o proximo passo do cadastro
	}

	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}

}
