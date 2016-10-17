package marcelzael.netflixJavaFx2.entity;

public enum TipoFaixaEtaria {
	NA("Não possui"),
	MaioresDe10("Maiores de 10"),
	MaioresDe14("Maiores de 14"),
	MaioresDe18("Maiores de 18");

	String descricao;

	TipoFaixaEtaria(final String descricao) {
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
	
	public static TipoFaixaEtaria fromString(String text) {
	    if (text != null) {
	      for (TipoFaixaEtaria b : TipoFaixaEtaria.values()) {
	        if (text.equalsIgnoreCase(b.descricao)) {
	          return b;
	        }
	      }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	  }
}
