package br.com.unip.aps.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.unip.aps.ws.dao.CidadeDao;
import br.com.unip.aps.ws.model.Cidade;

@Path("/cidade")
public class CidadeRest {

	@Inject
	private CidadeDao dao;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Cidade> getCidades() {
		return dao.listaTodos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Cidade getCidade(@PathParam("id") long id) {
		return dao.buscaPorId(id);
	}

}