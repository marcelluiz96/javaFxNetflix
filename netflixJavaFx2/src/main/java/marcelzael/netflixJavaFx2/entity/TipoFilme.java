package marcelzael.netflixJavaFx2.entity;

public enum TipoFilme {
	FILME("Filme"),
	SERIADO("Seriado");
	
	String descricao;
	
	TipoFilme(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}


}
