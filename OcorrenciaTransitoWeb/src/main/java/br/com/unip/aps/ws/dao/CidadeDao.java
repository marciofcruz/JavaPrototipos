package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Cidade;

public class CidadeDao extends Dao<Cidade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6905511793057488872L;

	public CidadeDao() {
		super(Cidade.class);
	}

	public Cidade buscaPorNome(String nome) {

		String query = "select * from cidade c where c.nome=:nome";

		List<Cidade> lista = em.createQuery(query, Cidade.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
