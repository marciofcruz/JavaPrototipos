package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unip.aps.ws.model.Problema;

public class ProblemaDao extends Dao<Problema> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3063480185278011735L;

	public ProblemaDao() {
		super(Problema.class);
	}

	public Problema buscaPorIdOcorrencia(Long id) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Problema.class, "p")
				.createAlias("p.ocorrencia", "o")
				.add(Restrictions.eq("o.id", id)).setMaxResults(1)
				.setFetchSize(1);
		return (Problema) criteria.uniqueResult();
	}

	public Problema buscaPorNome(String nome) {

		String query = "select * from problema p where p.nome=:nome";

		List<Problema> lista = em.createQuery(query, Problema.class)
				.setParameter("nome", nome).getResultList();

		return lista == null ? null : lista.get(0);
	}

}
