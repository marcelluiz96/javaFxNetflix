package marcelzael.netflixJavaFx2.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.entity.TipoFaixaEtaria;
import marcelzael.netflixJavaFx2.entity.TipoFilme;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class MidiaHibernateDAO extends GenericHibernateDAO {

	public List<Midia> findMidias(HashMap<String, String> filters) {
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Midia> query = cb.createQuery(Midia.class);

		
		Root<Midia> root = query.from(Midia.class);
		
		query.select(cb.construct(Midia.class, root.get("id"),root.get("nome"),root.get("descricao"),
				root.get("ano"),root.get("tipoFilme"),root.get("capaFilme"),root.get("tempEpisodio"),
				root.get("duracao"),root.get("categoria"),root.get("diretor"),root.get("atorPrincipal"),
				root.get("faixaEtaria")));
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		for (String key : filters.keySet()) {
			String value = filters.get(key);

			if (key == "tipoFilme" ) {
				predicates.add(cb.equal(root.get(key), TipoFilme.fromString(value)));
			} else if (key == "faixaEtaria") {
				predicates.add(cb.equal(root.get(key), TipoFaixaEtaria.fromString(value)));
			} else {	
				predicates.add(cb.like(root.get(key), "%"+value+"%"));
			}
		}
		
		query.orderBy(cb.desc(root.get("faixaEtaria")));
		query.orderBy(cb.desc(root.get("nome")));
		
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
		
		query.select(cb.construct(Midia.class, root.get("id"),root.get("nome"),root.get("descricao"),
				root.get("ano"),root.get("tipoFilme"),root.get("capaFilme"),root.get("tempEpisodio"),
				root.get("duracao"),root.get("categoria"),root.get("diretor"),root.get("atorPrincipal"),
				root.get("faixaEtaria")));
		
		//		Root<Usuario> userRoot = query.from(Usuario.class);

		cb.equal(root.join("favoritantes").get("id"), usuario.getId());


		Query<Midia> q = getCurrentSession().createQuery(query);

		List<Midia> retorno = q.list();

		getCurrentSession().close();

		return retorno;
	}
	
	
	@SuppressWarnings("deprecation")
	public List<Midia> getAllMidias() {
		getCurrentSession().beginTransaction();

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Midia> query = cb.createQuery(Midia.class);


		Root<Midia> root = query.from(Midia.class);
		
		query.select(cb.construct(Midia.class, root.get("id"),root.get("nome"),root.get("descricao"),
				root.get("ano"),root.get("tipoFilme"),root.get("capaFilme"),root.get("tempEpisodio"),
				root.get("duracao"),root.get("categoria"),root.get("diretor"),root.get("atorPrincipal"),
				root.get("faixaEtaria")));

		query.orderBy(cb.desc(root.get("faixaEtaria")));
		query.orderBy(cb.desc(root.get("nome")));

		Query<Midia> q = getCurrentSession().createQuery(query);

		List<Midia> retorno = q.list();

		getCurrentSession().close();

		return retorno;
	}

	public void updateFromProjection(Midia midia) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hqlUpdate = "update Midia m set "
				+ "m.nome = :nome,"
				+ "m.ano = :ano,"
				+ "m.atorPrincipal = :atorPrincipal,"
				+ "m.capaFilme = :capaFilme,"
				+ "m.categoria = :categoria,"
				+ "m.descricao = :descricao,"
				+ "m.diretor = :diretor,"
				+ "m.duracao = :duracao,"
				+ "m.faixaEtaria = :faixaEtaria,"
				+ "m.tipoFilme = :tipoFilme,"
				+ "m.tempEpisodio = :tempEpisodio "
				+ "where m.id = :id";
		// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
		int updatedEntities = session.createQuery( hqlUpdate )
		        .setString( "nome", midia.getNome() )
		        .setLong("id", midia.getId())
		        .setInteger( "ano", midia.getAno() )
		        .setString( "atorPrincipal", midia.getAtorPrincipal() )
		        .setParameter( "capaFilme", midia.getCapaFilme() )
		        .setString( "categoria", midia.getCategoria() )
		        .setString( "descricao", midia.getDescricao() )
		        .setString( "diretor", midia.getDiretor() )
		        .setString( "duracao", midia.getDuracao() )
		        .setInteger( "faixaEtaria", midia.getFaixaEtaria().ordinal() )
		        .setString( "tipoFilme", midia.getTipoFilme().name() )
		        .setString( "tempEpisodio", midia.getTempEpisodio())
		        
		        .executeUpdate();
		tx.commit();
		session.close();
		
	}


}
