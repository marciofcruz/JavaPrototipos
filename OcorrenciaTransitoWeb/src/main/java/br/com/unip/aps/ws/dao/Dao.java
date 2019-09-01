package br.com.unip.aps.ws.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.unip.aps.ws.tx.Transactional;

public class Dao<T> {

	private final Class<T> clazz;

	@Inject
	protected EntityManager em;

	public Dao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Transactional
	public void adiciona(T t) {
		em.persist(t);
	}

	@Transactional
	public void remove(T t) {
		em.remove(em.merge(t));
	}

	@Transactional
	public void atualiza(T t) {
		em.merge(t);
	}

	public T buscaPorId(Long id) {
		T instancia = em.find(clazz, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) em.createQuery(
				"select count(n) from " + clazz.getSimpleName() + " n")
				.getSingleResult();

		return (int) result;
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		query.select(query.from(clazz));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		query.select(query.from(clazz));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		return lista;
	}

}
