package marcelzael.netflixJavaFx2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import connector.Conexao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Conexao c = new Conexao("PostgreSql", "localhost", "5432", "netflix", "postgres", "admin");
        c.conect();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NONJTAPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        System.out.println("que nojo");
    }
}
