package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Bairro;

public class BairroDao extends Dao<Bairro> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8699341393158039947L;

	public BairroDao() {
		super(Bairro.class);
	}

	public Bairro buscaPorNome(String nome) {

		String query = "select * from bairro b where b.nome=:nome";

		List<Bairro> lista = em.createQuery(query, Bairro.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
