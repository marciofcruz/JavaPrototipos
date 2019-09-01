package br.com.unip.aps.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.unip.aps.ws.dao.ProblemaDao;
import br.com.unip.aps.ws.model.Problema;

@Path("/problema")
public class ProblemaRest {

	@Inject
	private ProblemaDao dao;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Problema> getProblemas() {
		return dao.listaTodos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Problema getProblema(@PathParam("id") long id) {
		return dao.buscaPorIdOcorrencia(id);
	}

}