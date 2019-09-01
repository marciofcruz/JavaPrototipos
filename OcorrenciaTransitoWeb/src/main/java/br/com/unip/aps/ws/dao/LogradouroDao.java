package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Logradouro;

public class LogradouroDao extends Dao<Logradouro> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8912381038480363582L;

	public LogradouroDao() {
		super(Logradouro.class);
	}

	public Logradouro buscaPorNome(String nome) {

		String query = "select * from logradouro l where l.nome=:nome";

		List<Logradouro> lista = em.createQuery(query, Logradouro.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
