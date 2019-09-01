package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Estado;

public class EstadoDao extends Dao<Estado> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1807410302737862360L;

	public EstadoDao() {
		super(Estado.class);
	}

	public Estado buscaPorNome(String nome) {

		String query = "select * from estado e where e.nome=:nome";

		List<Estado> lista = em.createQuery(query, Estado.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
