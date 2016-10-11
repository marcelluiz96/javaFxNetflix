package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author marcel
 * Midia = Filme ou Série. Nome genérico para garantir.
 *
 */
@Entity
public class Midia {
	@Id
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
