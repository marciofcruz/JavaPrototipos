package br.com.unip.aps.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.unip.aps.ws.dao.OcorrenciaDao;
import br.com.unip.aps.ws.model.Ocorrencia;

@Path("/ocorrencia")
public class OcorrenciaRest {

	@Inject
	private OcorrenciaDao dao;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Ocorrencia> getOcorrencias() {
		return dao.listaTodos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Ocorrencia getOcorrencia(@PathParam("id") long id) {
		return dao.buscaPorId(id);
	}

	@GET
	@Path("/cidade/{cidade}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Ocorrencia> getOcorrenciasPorCidade(
			@PathParam("cidade") String cidade) {
		return dao.listaTodosPorCidade(cidade);
	}

}