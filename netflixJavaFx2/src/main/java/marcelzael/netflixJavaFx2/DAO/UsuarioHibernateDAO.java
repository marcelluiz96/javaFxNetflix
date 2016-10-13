package marcelzael.netflixJavaFx2.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import marcelzael.netflixJavaFx2.entity.Usuario;

public class UsuarioHibernateDAO extends GenericHibernateDAO {
	
	public Usuario findUser(String login, String senha) {
		getCurrentSession().beginTransaction();
		
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		
		
		Root<Usuario> root = query.from(Usuario.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(root.get("login"), login));
		predicates.add(cb.equal(root.get("senha"), senha));
	
		query.select(root).where(predicates.toArray(new Predicate[]{}));
		
		Query<Usuario> q = getCurrentSession().createQuery(query);
		
		List<Usuario> retorno = q.list();
		
		getCurrentSession().close();
		
		if (retorno != null && !(retorno.isEmpty()))
			return retorno.get(0);
		else return null;
	}

}
