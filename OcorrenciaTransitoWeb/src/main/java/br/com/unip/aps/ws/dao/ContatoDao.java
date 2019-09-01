package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unip.aps.ws.model.Contato;

public class ContatoDao extends Dao<Contato> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595353112067142139L;

	public ContatoDao() {
		super(Contato.class);
	}

	public Contato buscaPorIdOcorrencia(Long id) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Contato.class, "c")
				.createAlias("c.ocorrencia", "o")
				.add(Restrictions.eq("o.id", id)).setMaxResults(1)
				.setFetchSize(1);
		return (Contato) criteria.uniqueResult();
	}

	public Contato buscaPorEmail(String email) {

		String query = "select * from contato c where c.email=:email";

		List<Contato> lista = em.createQuery(query, Contato.class)
				.setParameter("email", email).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
