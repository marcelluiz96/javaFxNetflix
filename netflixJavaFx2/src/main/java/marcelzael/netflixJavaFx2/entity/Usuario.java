package marcelzael.netflixJavaFx2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_seq")
	@SequenceGenerator(name="usuario_seq", sequenceName="usuario_seq", allocationSize=1)
	@Column(name = "usuario_id", unique = true, nullable = false)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	private String senha;
	
	private boolean admin;
	
	@Enumerated(EnumType.STRING)
	private TipoFaixaEtaria idade;
	
	@ManyToMany
	private List<Midia> favoritos;
	
	
	public Usuario() {
		super();
	}

	public Usuario(String login, String senha, boolean admin, TipoFaixaEtaria idade) {
		super();
		this.login = login;
		this.senha = senha;
		this.admin = admin;
		this.idade = idade;
	}

	public long getId() {
		return id;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoFaixaEtaria getIdade() {
		return idade;
	}

	public void setIdade(TipoFaixaEtaria idade) {
		this.idade = idade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Midia> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Midia> favoritos) {
		this.favoritos = favoritos;
	}
	
	
	
	

}
