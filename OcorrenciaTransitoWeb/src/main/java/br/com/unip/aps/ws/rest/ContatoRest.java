package br.com.unip.aps.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.unip.aps.ws.dao.ContatoDao;
import br.com.unip.aps.ws.model.Contato;

@Path("/contato")
public class ContatoRest {

	@Inject
	private ContatoDao dao;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Contato> getContatos() {
		return dao.listaTodos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Contato getContato(@PathParam("id") long id) {
		return dao.buscaPorIdOcorrencia(id);
	}

}