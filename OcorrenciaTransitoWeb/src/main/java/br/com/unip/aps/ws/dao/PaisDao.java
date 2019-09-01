package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Pais;

public class PaisDao extends Dao<Pais> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2926177340279832639L;

	public PaisDao() {
		super(Pais.class);
	}

	public Pais buscaPorNome(String nome) {

		String query = "select * from pais p where p.nome=:nome";

		List<Pais> lista = em.createQuery(query, Pais.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
