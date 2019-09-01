package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import br.com.unip.aps.ws.model.Parametro;

public class ParametroDao extends Dao<Parametro> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320201162555558845L;

	public ParametroDao() {
		super(Parametro.class);
	}

	public Parametro buscaPorChave(String chave) {

		String query = "select * from parametro p where p.chave=:chave";

		List<Parametro> lista = em.createQuery(query, Parametro.class)
				.setParameter("chave", chave).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
