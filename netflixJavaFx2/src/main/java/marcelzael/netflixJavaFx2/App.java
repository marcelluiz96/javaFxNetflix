package marcelzael.netflixJavaFx2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connector.HibernateUtil;
import dao.MidiaHibernateDAO;
import entity.Midia;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        Conexao c = new Conexao("PostgreSql", "localhost", "5432", "netflix", "postgres", "admin");
//        c.conect();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NONJTAPersistenceUnit");
//        EntityManager em = emf.createEntityManager();
//        
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Midia> cq = cb.createQuery(Midia.class);
//        Root<Midia> root = cq.from(Midia.class);
//        
        
//        
//        
        
        
        Midia midia = new Midia();
        midia.setNome("Amo a momo");
        
        MidiaHibernateDAO miDao = new MidiaHibernateDAO();
        miDao.persist(midia);
        
        midia = miDao.getById(Midia.class, 1);
        
        
//        Transaction tx1 = session.beginTransaction();
//        
////        session.save(midia);
//        session.persist(midia);
//        tx1.commit();
//        session.flush();
        
        System.out.println("que nojo");
        System.exit(0);
    }
}
