package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connector.HibernateUtil;

/**
 * 
 * @author marce
 * Classe com algumas funções do Hibernate para os DAOs seguintes
 *
 */
public class GenericHibernateDAO extends HibernateTransactionWrapper{

	SessionFactory sessionFactory;

	public GenericHibernateDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean persist(final Object object) {
		HibernateTransactionWrapper wrapper = new HibernateTransactionWrapper();
		try {
			wrapper.run(new TransactionalCode() {
				
				@Override
				public void execute(Session session, Transaction transaction) {
					session.persist(object);
					
				}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public Session getNewSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}

	//Retorna a session atual, ou uma nova session caso a atual seja nula. Geralmente vai ser. O método é apenas um failsafe
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession() != null ? sessionFactory.getCurrentSession() : getNewSession();
	}



}
