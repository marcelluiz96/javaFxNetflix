package marcelzael.netflixJavaFx2.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class MidiaHibernateDAO extends GenericHibernateDAO {

	public List<Midia> findMidias(HashMap<String, String> filters) {
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Midia> query = cb.createQuery(Midia.class);


		Root<Midia> root = query.from(Midia.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		for (String key : filters.keySet()) {
			String value = filters.get(key);	
			predicates.add(cb.like(root.get(key), "%"+value+"%"));
		}

		query.select(root).where(predicates.toArray(new Predicate[]{}));

		Query<Midia> q = getCurrentSession().createQuery(query);

		List<Midia> retorno = q.list();

		getCurrentSession().close();

		return retorno;
	}
	
	@SuppressWarnings("deprecation")
	public List<Midia> getFavoritos(Usuario usuario) {
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Midia> query = cb.createQuery(Midia.class);


		Root<Midia> root = query.from(Midia.class);
//		Root<Usuario> userRoot = query.from(Usuario.class);

		cb.equal(root.join("favoritantes").get("id"), usuario.getId());

	
		Query<Midia> q = getCurrentSession().createQuery(query);

		List<Midia> retorno = q.list();

		getCurrentSession().close();

		return retorno;
	}


}
