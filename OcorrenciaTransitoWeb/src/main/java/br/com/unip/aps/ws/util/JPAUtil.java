package br.com.unip.aps.ws.util;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aps-web-service");

	@RequestScoped
	@Produces
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager em) {
		em.close();
	}
	
	@PreDestroy
	public void fechaFactory() {
		emf.close();
	}
}