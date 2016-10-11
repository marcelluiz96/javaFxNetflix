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
	long id;
	
	public String nome;

}
