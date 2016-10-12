package marcelzael.netflixJavaFx2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 
 * @author marcel
 * Midia = Filme ou Série. Nome genérico para garantir.
 *
 */
@Entity
@Table(name = "midia")
public class Midia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="midia_seq")
	@SequenceGenerator(name="midia_seq", sequenceName="midia_seq", allocationSize=1)
	private long id;
	
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
