package marcelzael.netflixJavaFx2.dataModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.entity.TipoFaixaEtaria;
import marcelzael.netflixJavaFx2.entity.TipoFilme;

public class MidiaDataModel {
	
	private SimpleLongProperty idColumnProperty;
	
	private SimpleIntegerProperty anoColumnProperty;

	private SimpleStringProperty nomeColumnProperty;
	private SimpleStringProperty descricaoColumnProperty;
	private SimpleStringProperty tipoFilmeColumnProperty;
	private SimpleStringProperty tempEpisodioColumnProperty;
	private SimpleStringProperty duracaoColumnProperty;
	private SimpleStringProperty categoriaColumnProperty;
	private SimpleStringProperty diretorColumnProperty;
	private SimpleStringProperty atorPrincipalColumnProperty;
	private SimpleStringProperty faixaEtariaColumnProperty;
	
	public MidiaDataModel(Midia midia) {
		idColumnProperty = new SimpleLongProperty(midia.getId());
		
		anoColumnProperty = new SimpleIntegerProperty(midia.getAno());
		
		nomeColumnProperty = new SimpleStringProperty(midia.getNome());
		descricaoColumnProperty = new SimpleStringProperty(midia.getDescricao());
		tipoFilmeColumnProperty = new SimpleStringProperty(midia.getTipoFilme().toString());
		tempEpisodioColumnProperty = new SimpleStringProperty(midia.getTempEpisodio());
		duracaoColumnProperty = new SimpleStringProperty(midia.getDuracao());
		categoriaColumnProperty = new SimpleStringProperty(midia.getCategoria());
		diretorColumnProperty = new SimpleStringProperty(midia.getDiretor());
		atorPrincipalColumnProperty = new SimpleStringProperty(midia.getAtorPrincipal());
		faixaEtariaColumnProperty = new SimpleStringProperty(midia.getFaixaEtaria().toString());
	}
	
	public Midia asMidiaObject() {
		Midia midia = new Midia();
		midia.setId(idColumnProperty.get());
		midia.setAno(anoColumnProperty.get());
		midia.setNome(nomeColumnProperty.get());
		midia.setDescricao(descricaoColumnProperty.get());
		//VAMOS FERVER ESSA VM RAPAZ
		midia.setTipoFilme(TipoFilme.fromString(tipoFilmeColumnProperty.get()));
		midia.setTempEpisodio(tempEpisodioColumnProperty.get());
		midia.setDuracao(duracaoColumnProperty.get());
		midia.setCategoria(categoriaColumnProperty.get());
		midia.setDiretor(diretorColumnProperty.get());
		midia.setAtorPrincipal(atorPrincipalColumnProperty.get());
		midia.setFaixaEtaria(TipoFaixaEtaria.fromString(faixaEtariaColumnProperty.get()));
		
		return midia;
	}

	public SimpleLongProperty getIdColumnProperty() {
		return idColumnProperty;
	}

	public void setIdColumnProperty(SimpleLongProperty idColumnProperty) {
		this.idColumnProperty = idColumnProperty;
	}

	public SimpleIntegerProperty getAnoColumnProperty() {
		return anoColumnProperty;
	}

	public void setAnoColumnProperty(SimpleIntegerProperty anoColumnProperty) {
		this.anoColumnProperty = anoColumnProperty;
	}

	public SimpleStringProperty getNomeColumnProperty() {
		return nomeColumnProperty;
	}

	public void setNomeColumnProperty(SimpleStringProperty nomeColumnProperty) {
		this.nomeColumnProperty = nomeColumnProperty;
	}

	public SimpleStringProperty getDescricaoColumnProperty() {
		return descricaoColumnProperty;
	}

	public void setDescricaoColumnProperty(SimpleStringProperty descricaoColumnProperty) {
		this.descricaoColumnProperty = descricaoColumnProperty;
	}

	public SimpleStringProperty getTipoFilmeColumnProperty() {
		return tipoFilmeColumnProperty;
	}

	public void setTipoFilmeColumnProperty(SimpleStringProperty tipoFilmeColumnProperty) {
		this.tipoFilmeColumnProperty = tipoFilmeColumnProperty;
	}

	public SimpleStringProperty getTempEpisodioColumnProperty() {
		return tempEpisodioColumnProperty;
	}

	public void setTempEpisodioColumnProperty(SimpleStringProperty tempEpisodioColumnProperty) {
		this.tempEpisodioColumnProperty = tempEpisodioColumnProperty;
	}

	public SimpleStringProperty getDuracaoColumnProperty() {
		return duracaoColumnProperty;
	}
	
	

	public SimpleStringProperty getFaixaEtariaColumnProperty() {
		return faixaEtariaColumnProperty;
	}

	public void setFaixaEtariaColumnProperty(SimpleStringProperty faixaEtariaColumnProperty) {
		this.faixaEtariaColumnProperty = faixaEtariaColumnProperty;
	}

	public void setDuracaoColumnProperty(SimpleStringProperty duracaoColumnProperty) {
		this.duracaoColumnProperty = duracaoColumnProperty;
	}

	public SimpleStringProperty getCategoriaColumnProperty() {
		return categoriaColumnProperty;
	}

	public void setCategoriaColumnProperty(SimpleStringProperty categoriaColumnProperty) {
		this.categoriaColumnProperty = categoriaColumnProperty;
	}

	public SimpleStringProperty getDiretorColumnProperty() {
		return diretorColumnProperty;
	}

	public void setDiretorColumnProperty(SimpleStringProperty diretorColumnProperty) {
		this.diretorColumnProperty = diretorColumnProperty;
	}

	public SimpleStringProperty getAtorPrincipalColumnProperty() {
		return atorPrincipalColumnProperty;
	}

	public void setAtorPrincipalColumnProperty(SimpleStringProperty atorPrincipalColumnProperty) {
		this.atorPrincipalColumnProperty = atorPrincipalColumnProperty;
	}
	
	

}
