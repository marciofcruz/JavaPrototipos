package br.com.unip.aps.ws.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.unip.aps.ws.model.Contato;
import br.com.unip.aps.ws.model.Endereco;
import br.com.unip.aps.ws.model.Ocorrencia;
import br.com.unip.aps.ws.model.Problema;
import br.com.unip.aps.ws.tx.Transactional;

public class OcorrenciaDao extends Dao<Ocorrencia> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4198787839468917237L;

	public OcorrenciaDao() {
		super(Ocorrencia.class);
	}

	@Transactional
	public void adicionaTudo(Ocorrencia t, Problema p, Contato c, Endereco e) {
		t.setProblema(p);
		t.setContato(c);
		t.setEndereco(e);
		p.setOcorrencia(t);
		c.setOcorrencia(t);
		e.setOcorrencia(t);
		em.persist(t);
		em.persist(p);
		em.persist(c);
		em.persist(e.getLogradouro());
		em.persist(e);
	}

	@Transactional
	public void removeTudo(Ocorrencia t) {
		em.remove(t.getProblema());
		em.remove(t.getContato());
		em.remove(t.getEndereco());
		em.remove(t);
	}

	public List<Ocorrencia> listaTodosPorCidade(String cidade) {

		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Ocorrencia.class, "o")
				.createAlias("o.endereco", "e").createAlias("e.cidade", "c")
				.add(Restrictions.eq("c.nome", cidade).ignoreCase());

		@SuppressWarnings("unchecked")
		List<Ocorrencia> lista = (List<Ocorrencia>) criteria.list();

		return lista;
	}

}
