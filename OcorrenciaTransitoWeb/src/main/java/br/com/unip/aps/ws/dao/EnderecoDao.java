package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unip.aps.ws.model.Endereco;

public class EnderecoDao extends Dao<Endereco> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1644752877955586148L;

	public EnderecoDao() {
		super(Endereco.class);
	}

	public Endereco buscaPorIdOcorrencia(Long id) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Endereco.class, "e")
				.createAlias("e.ocorrencia", "o")
				.add(Restrictions.eq("o.id", id)).setMaxResults(1)
				.setFetchSize(1);
		return (Endereco) criteria.uniqueResult();
	}

	public List<Endereco> buscaPorCidade(String nome) {

		String query = "select * from endereco e "
				+ "inner join cidade c on c.id = e.cidade "
				+ "where c.nome=:nome";

		List<Endereco> lista = em.createQuery(query, Endereco.class)
				.setParameter("nome", nome).getResultList();

		return lista;
	}

}
