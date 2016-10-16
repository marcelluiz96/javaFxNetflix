package marcelzael.netflixJavaFx2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	@Column(name = "midia_id", unique = true, nullable = false)
	private long id;

	private String nome;

	private String descricao;

	private int ano;

	@Enumerated(EnumType.STRING)
	private TipoFilme tipoFilme;
	
	@Column( name = "conteudoFilme" )
	@Lob
	private byte[] conteudoFilme;
	
	@Column (name="capaFilme")
	@Lob
	private byte[] capaFilme;

	private String tempEpisodio;

	private String duracao;

	private String categoria;

	private String diretor;

	private String atorPrincipal;

	@Enumerated(EnumType.STRING)
	private TipoFaixaEtaria faixaEtaria;
	
	public Midia() {
		super();
	}

	public Midia(String nome, String descricao, int ano, TipoFilme tipoFilme, byte[] conteudoFilme, byte[] capaFilme,
			String tempEpisodio, String duracao, String categoria, String diretor, String atorPrincipal,
			TipoFaixaEtaria faixaEtaria) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.ano = ano;
		this.tipoFilme = tipoFilme;
		this.conteudoFilme = conteudoFilme;
		this.capaFilme = capaFilme;
		this.tempEpisodio = tempEpisodio;
		this.duracao = duracao;
		this.categoria = categoria;
		this.diretor = diretor;
		this.atorPrincipal = atorPrincipal;
		this.faixaEtaria = faixaEtaria;
	}

	/**
	 * 
	 * Getters & Setters
	 */

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public TipoFilme getTipoFilme() {
		return tipoFilme;
	}

	public void setTipoFilme(TipoFilme tipoFilme) {
		this.tipoFilme = tipoFilme;
	}

	public byte[] getConteudoFilme() {
		return conteudoFilme;
	}

	public void setConteudoFilme(byte[] conteudoFilme) {
		this.conteudoFilme = conteudoFilme;
	}

	public byte[] getCapaFilme() {
		return capaFilme;
	}

	public void setCapaFilme(byte[] capaFilme) {
		this.capaFilme = capaFilme;
	}

	public String getTempEpisodio() {
		return tempEpisodio;
	}



	public void setTempEpisodio(String tempEpisodio) {
		this.tempEpisodio = tempEpisodio;
	}



	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getAtorPrincipal() {
		return atorPrincipal;
	}

	public void setAtorPrincipal(String atorPrincipal) {
		this.atorPrincipal = atorPrincipal;
	}

	public TipoFaixaEtaria getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(TipoFaixaEtaria faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
