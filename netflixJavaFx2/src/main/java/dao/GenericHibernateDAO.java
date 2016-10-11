package dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import connector.HibernateUtil;
import entity.Midia;

/**
 * 
 * @author marce
 * Classe com algumas funções do Hibernate para os DAOs seguintes
 * MEU AMIGO ESSE CÓDIGO TÁ FICANDO LINDO DRUDRUDRSURSDHU
 *
 */
public class GenericHibernateDAO extends HibernateTransactionWrapper{

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	
	public GenericHibernateDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	//PERSISTE QUALQUER OBJETO ENVIADO. TODO TRATAR EXCEÇÕES
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
	
	public <T> List<T> listAll(Class<T> classe) {
		return listAll(classe, false);
	}
	
	public <T> List<T> listAll(Class<T> classe, boolean cache) {
		getCurrentSession().beginTransaction();
		CriteriaQuery<T> query = getCurrentSession().getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		Query<T> q = getCurrentSession().createQuery(query);
		q.setCacheable(cache);
		List<T> retorno = q.list();
		
		getCurrentSession().close();
		return retorno;
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
