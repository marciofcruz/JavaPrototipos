package br.com.unip.aps.ws.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645152753746946317L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		try {
			System.out.println("Abrindo a transacao");
			manager.getTransaction().begin();

			// Passando para o JSF tratar a requisicao
			// e pegando o retorno da logica
			Object resultado = ctx.proceed();

			System.out.println("comitando a transacao");
			manager.getTransaction().commit();

			return resultado;
		} catch (Exception ex) {
			
			ex.printStackTrace();

			if (manager.getTransaction().isActive()) {
				System.out.println("voltando a transacao");
				manager.getTransaction().rollback();
			} else {
				System.out.println("Houve um erro no Transaction Interceptor");
			}

			throw ex;
		}
	}

}
