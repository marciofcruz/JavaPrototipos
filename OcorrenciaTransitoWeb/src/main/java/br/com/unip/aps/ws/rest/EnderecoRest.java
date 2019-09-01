package br.com.unip.aps.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.unip.aps.ws.dao.EnderecoDao;
import br.com.unip.aps.ws.model.Endereco;

@Path("/endereco")
public class EnderecoRest {

	@Inject
	private EnderecoDao dao;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Endereco> getEnderecos() {
		return dao.listaTodos();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Endereco getEndereco(@PathParam("id") long id) {
		return dao.buscaPorIdOcorrencia(id);
	}

}