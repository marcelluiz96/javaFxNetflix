package marcelzael.netflixJavaFx2.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.NumberStringConverter;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.app.AdminViewApp;
import marcelzael.netflixJavaFx2.app.CatalogueViewApp;
import marcelzael.netflixJavaFx2.app.LoginViewApp;
import marcelzael.netflixJavaFx2.dataModel.MidiaDataModel;
import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.entity.TipoFaixaEtaria;
import marcelzael.netflixJavaFx2.entity.TipoFilme;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class AdminController {

	private AdminViewApp adminViewApp;

	private UsuarioHibernateDAO usuarioHibernateDAO;
	private MidiaHibernateDAO midiaHibernateDAO;

	private Midia filmeSelecionado;
	private Midia filmeACadastrar;

	public AdminController() {
		usuarioHibernateDAO = new UsuarioHibernateDAO();
		midiaHibernateDAO = new MidiaHibernateDAO();
	}
	//Aba 1: Gerenciamento de Filmes
	@FXML private TextField txNomeFilme;
	@FXML private TextField txAno;
	@FXML private TextField txDuracao;
	@FXML private TextField txCategoria;
	@FXML private TextField txTempEpisodio;
	@FXML private TextField txDiretor;
	@FXML private TextField txAtorPrincipal;

	@FXML private TextArea taDescricao;
	@FXML ChoiceBox<TipoFilme> cbTipoFilme;
	@FXML ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtaria;

	@FXML Button btSalvar;
	@FXML Button btDeletar;
	@FXML Button btAlterarCapa;
	@FXML Button btAlterarMidia;

	@FXML private TableView<MidiaDataModel> tvFilmes;
	@FXML private TableColumn<MidiaDataModel, Long> columnIdFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnNomeFilme;
	@FXML private TableColumn<MidiaDataModel, Integer> columnAnoFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnDuracaoFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnCategoriaFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnTipoFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnTempEpiFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnAtorPrincipalFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnDiretorFilme;
	@FXML private TableColumn<MidiaDataModel, String> columnFaixaEtariaFilme;

	//Aba 2: Cadastro de Filmes
	@FXML private TextField txNomeCadastro;
	@FXML private TextField txAnoCadastro;
	@FXML private TextField txDuracaoCadastro;
	@FXML private TextField txCategoriaCadastro;
	@FXML private TextField txTempEpisodioCadastro;
	@FXML private TextField txDiretorCadastro;
	@FXML private TextField txAtorPrincipalCadastro;
	@FXML private TextArea taDescricaoCadastro;
	@FXML ChoiceBox<TipoFilme> cbTipoFilmeCadastro;
	@FXML ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtariaCadastro;

	@FXML Button btEscolherCapa;
	@FXML Button btEscolherArquivo;
	@FXML Button btCriarFilme;

	@FXML ImageView ivCapaFilmeCadastro;

	//Aba 3: Cadastro de usuários
	@FXML private TextField txLoginUsuario;
	@FXML private PasswordField pfSenhaUsuario;
	@FXML private ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtariaUsuario;
	@FXML private CheckBox checkBoxAdministradorUsuario;
	@FXML private Button btCadastrarUsuario;

	//Aba 4: Voltar

	@FXML private TabPane tabPane; 

	@FXML
	public void initialize() {
		cbTipoFaixaEtaria.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFaixaEtariaUsuario.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFaixaEtariaCadastro.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFilmeCadastro.getItems().setAll(TipoFilme.values());
		cbTipoFilme.getItems().setAll(TipoFilme.values());

		filmeACadastrar = new Midia();

		inicializarListeners();
		inicializarTabelaFilmes();

	}

	public void inicializarTabelaFilmes() {

		// OBRIGADO CODE.MAKERY OBRIGADO LAMBDAS OBRIGADO JESUS

		columnIdFilme.setCellValueFactory(cellData -> cellData.getValue().getIdColumnProperty().asObject());
		columnAnoFilme.setCellValueFactory(cellData -> cellData.getValue().getAnoColumnProperty().asObject());


		columnNomeFilme.setCellValueFactory(cellData -> cellData.getValue().getNomeColumnProperty());
		columnDuracaoFilme.setCellValueFactory(cellData -> cellData.getValue().getDuracaoColumnProperty());
		columnCategoriaFilme.setCellValueFactory(cellData -> cellData.getValue().getCategoriaColumnProperty());
		columnTipoFilme.setCellValueFactory(cellData -> cellData.getValue().getTipoFilmeColumnProperty());
		columnTempEpiFilme.setCellValueFactory(cellData -> cellData.getValue().getTempEpisodioColumnProperty());
		columnAtorPrincipalFilme.setCellValueFactory(cellData -> cellData.getValue().getAtorPrincipalColumnProperty());
		columnDiretorFilme.setCellValueFactory(cellData -> cellData.getValue().getDiretorColumnProperty());
		columnFaixaEtariaFilme.setCellValueFactory(cellData -> cellData.getValue().getFaixaEtariaColumnProperty());
		columnNomeFilme.setCellValueFactory(cellData -> cellData.getValue().getNomeColumnProperty());

		//Realizando bind com TextFields
		tvFilmes.getSelectionModel().selectedItemProperty().addListener((observableValue, midiaDataModel, midiaDataModel2) -> {
			txNomeFilme.textProperty().bindBidirectional(midiaDataModel2.getNomeColumnProperty());
			taDescricao.textProperty().bindBidirectional(midiaDataModel2.getDescricaoColumnProperty());
			txAno.textProperty().bindBidirectional(midiaDataModel2.getAnoColumnProperty(), new NumberStringConverter());
			txDuracao.textProperty().bindBidirectional(midiaDataModel2.getDuracaoColumnProperty());
			txCategoria.textProperty().bindBidirectional(midiaDataModel2.getCategoriaColumnProperty());
			txTempEpisodio.textProperty().bindBidirectional(midiaDataModel2.getTempEpisodioColumnProperty());
			txDiretor.textProperty().bindBidirectional(midiaDataModel2.getDiretorColumnProperty());
			txAtorPrincipal.textProperty().bindBidirectional(midiaDataModel2.getAtorPrincipalColumnProperty());

			//CHOICEBOX ENUM: A complexidade do código aumenta
			//***StackOverflow Intensifies***
			//Não deu. Acho que por hoje é só pessoal.
			//			cbTipoFilme.valueProperty().bind(midiaDataModel2.getTipoFilmeColumnProperty());
		});

	}

	private void inicializarListeners() {
		tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() == 3) {
					try {
						new CatalogueViewApp().start(new Stage());
						Stage stage = (Stage) tabPane.getScene().getWindow();
						stage.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}	
			}
		});
	}


	@FXML
	public void cadastrarUsuario(ActionEvent event) {
		try {
			usuarioHibernateDAO.cadastrarUsuario(txLoginUsuario.getText(), pfSenhaUsuario.getText(), 
					cbTipoFaixaEtariaUsuario.getSelectionModel().getSelectedItem(), checkBoxAdministradorUsuario.isSelected());

			Alert alert = new Alert(AlertType.INFORMATION, "Usuário cadastrado com sucesso!", ButtonType.OK);
			alert.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Erro ao cadastrar usuário! Verifique se todos os campos foram preenchidos corretamente"
					+ "ou tente utilizar outro nome de usuário", ButtonType.OK);
			alert.show();
		}
	}

	@FXML
	public void cadastrarFilme(ActionEvent event) {
		try {
			filmeACadastrar.setNome(txNomeCadastro.getText());
			filmeACadastrar.setAno(Integer.valueOf(txAnoCadastro.getText()));
			filmeACadastrar.setDescricao(txDuracaoCadastro.getText());
			filmeACadastrar.setCategoria(txCategoriaCadastro.getText());
			filmeACadastrar.setTempEpisodio(txTempEpisodioCadastro.getText());
			filmeACadastrar.setDiretor(txDiretorCadastro.getText());
			filmeACadastrar.setAtorPrincipal(txAtorPrincipalCadastro.getText());
			filmeACadastrar.setFaixaEtaria(cbTipoFaixaEtariaCadastro.getValue());
			filmeACadastrar.setTipoFilme(cbTipoFilmeCadastro.getValue());

			midiaHibernateDAO.persist(filmeACadastrar);
			filmeACadastrar = new Midia();
			tvFilmes.setItems(adminViewApp.recarregarTabelaFilmes());

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Erro ao cadastrar Filme! Verifique se todos os campos foram preenchidos corretamente"
					+ "ou tente utilizar outro nome de usuário", ButtonType.OK);
			alert.show();
		}
	}

	@FXML
	public void escolherCapa(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Escolha a capa");
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Apenas (*.png, *.jpg)", "*.png,*.jpg"));
			File tempFile = fileChooser.showOpenDialog(btEscolherCapa.getScene().getWindow());
			if (tempFile != null) {
				ivCapaFilmeCadastro.setImage(new Image(tempFile.toURI().toString()));
				filmeACadastrar.setCapaFilme(Files.readAllBytes(tempFile.toPath()));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	public void escolherFilme(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Escolha o filme");
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Apenas (*.mp4, *.wav, *.flv)", "*.mp4,*.wav,*.flv"));
			File tempFile = fileChooser.showOpenDialog(btEscolherCapa.getScene().getWindow());
			if (tempFile != null) {
				filmeACadastrar.setConteudoFilme(Files.readAllBytes(tempFile.toPath()));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void selecionarFilmeTabela(MouseEvent event) {
		filmeSelecionado = tvFilmes.getSelectionModel().getSelectedItem().asMidiaObject();
		cbTipoFilme.getSelectionModel().select(filmeSelecionado.getTipoFilme());
		cbTipoFaixaEtaria.getSelectionModel().select(filmeSelecionado.getFaixaEtaria());
	}

	@FXML
	public void deletarFilme(ActionEvent event) {
		if (filmeSelecionado.getId() != 0) {
			midiaHibernateDAO.delete(filmeSelecionado);
			tvFilmes.getSelectionModel().clearSelection();
			tvFilmes.setItems(adminViewApp.recarregarTabelaFilmes());
		} else {
			//TODO gerar dialog de erro
		}
	}

	@FXML
	public void salvarAlteracoesFilme(ActionEvent event) {
		if (filmeSelecionado.getId() != 0) {
			filmeSelecionado.setNome(txNomeFilme.getText());
			filmeSelecionado.setTipoFilme(cbTipoFilme.getValue());
			filmeSelecionado.setFaixaEtaria(cbTipoFaixaEtaria.getValue());

			if (filmeSelecionado.getConteudoFilme() == null) {
				midiaHibernateDAO.updateFromProjection(filmeSelecionado);
			} else {
				midiaHibernateDAO.update(filmeSelecionado);
			}

		}
	}

	@FXML
	public void alterarCapaFilme(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Escolha o filme");
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Apenas (*.mp4, *.wav, *.flv)", "*.mp4,*.wav,*.flv"));
			File tempFile = fileChooser.showOpenDialog(btEscolherCapa.getScene().getWindow());
			if (tempFile != null) {
				filmeSelecionado.setCapaFilme(Files.readAllBytes(tempFile.toPath()));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void alterarConteudoFilme(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Escolha o filme");
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Apenas (*.mp4, *.wav, *.flv)", "*.mp4,*.wav,*.flv"));
			File tempFile = fileChooser.showOpenDialog(btEscolherCapa.getScene().getWindow());
			if (tempFile != null) {
				filmeSelecionado.setConteudoFilme(Files.readAllBytes(tempFile.toPath()));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void exibirCapaFilme(ActionEvent event) {

	}


	//MÉTODO IMPORTANTE DEVIDO AO QUE OCORRE APÓS O SET. É AQUI QUE SÃO CONECTADOS OS DADOS DO APP À VIEW
	public void setAdminViewApp(AdminViewApp adminViewApp) {
		this.adminViewApp = adminViewApp;

		tvFilmes.setItems(adminViewApp.getListaMidias());
	}


	public AdminViewApp getAdminViewApp() {
		return adminViewApp;
	}



	public TextField getTxLoginUsuario() {
		return txLoginUsuario;
	}

	public void setTxLoginUsuario(TextField txLoginUsuario) {
		this.txLoginUsuario = txLoginUsuario;
	}

	public PasswordField getPfSenhaUsuario() {
		return pfSenhaUsuario;
	}

	public void setPfSenhaUsuario(PasswordField pfSenhaUsuario) {
		this.pfSenhaUsuario = pfSenhaUsuario;
	}

	public ChoiceBox<TipoFaixaEtaria> getCbTipoFaixaEtariaUsuario() {
		return cbTipoFaixaEtariaUsuario;
	}

	public void setCbTipoFaixaEtariaUsuario(ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtariaUsuario) {
		this.cbTipoFaixaEtariaUsuario = cbTipoFaixaEtariaUsuario;
	}

	public CheckBox getCheckBoxAdministradorUsuario() {
		return checkBoxAdministradorUsuario;
	}

	public void setCheckBoxAdministradorUsuario(CheckBox checkBoxAdministradorUsuario) {
		this.checkBoxAdministradorUsuario = checkBoxAdministradorUsuario;
	}

	public Button getBtCadastrarUsuario() {
		return btCadastrarUsuario;
	}

	public void setBtCadastrarUsuario(Button btCadastrarUsuario) {
		this.btCadastrarUsuario = btCadastrarUsuario;
	}

	public UsuarioHibernateDAO getUsuarioHibernateDAO() {
		return usuarioHibernateDAO;
	}


	public void setUsuarioHibernateDAO(UsuarioHibernateDAO usuarioHibernateDAO) {
		this.usuarioHibernateDAO = usuarioHibernateDAO;
	}


	public MidiaHibernateDAO getMidiaHibernateDAO() {
		return midiaHibernateDAO;
	}


	public void setMidiaHibernateDAO(MidiaHibernateDAO midiaHibernateDAO) {
		this.midiaHibernateDAO = midiaHibernateDAO;
	}


	public Midia getFilmeSelecionado() {
		return filmeSelecionado;
	}


	public void setFilmeSelecionado(Midia filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}


	public TextField getTxNomeFilme() {
		return txNomeFilme;
	}


	public void setTxNomeFilme(TextField txNomeFilme) {
		this.txNomeFilme = txNomeFilme;
	}


	public TextField getTxAno() {
		return txAno;
	}


	public void setTxAno(TextField txAno) {
		this.txAno = txAno;
	}


	public TextField getTxDuracao() {
		return txDuracao;
	}


	public void setTxDuracao(TextField txDuracao) {
		this.txDuracao = txDuracao;
	}


	public TextField getTxCategoria() {
		return txCategoria;
	}


	public void setTxCategoria(TextField txCategoria) {
		this.txCategoria = txCategoria;
	}


	public TextField getTxTempEpisodio() {
		return txTempEpisodio;
	}


	public void setTxTempEpisodio(TextField txTempEpisodio) {
		this.txTempEpisodio = txTempEpisodio;
	}


	public TextField getTxDiretor() {
		return txDiretor;
	}


	public void setTxDiretor(TextField txDiretor) {
		this.txDiretor = txDiretor;
	}


	public TextField getTxAtorPrincipal() {
		return txAtorPrincipal;
	}


	public void setTxAtorPrincipal(TextField txAtorPrincipal) {
		this.txAtorPrincipal = txAtorPrincipal;
	}


	public TextArea getTaDescricao() {
		return taDescricao;
	}


	public void setTaDescricao(TextArea taDescricao) {
		this.taDescricao = taDescricao;
	}


	public ChoiceBox<TipoFilme> getCbTipoFilme() {
		return cbTipoFilme;
	}


	public void setCbTipoFilme(ChoiceBox<TipoFilme> cbTipoFilme) {
		this.cbTipoFilme = cbTipoFilme;
	}


	public ChoiceBox<TipoFaixaEtaria> getCbTipoFaixaEtaria() {
		return cbTipoFaixaEtaria;
	}


	public void setCbTipoFaixaEtaria(ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtaria) {
		this.cbTipoFaixaEtaria = cbTipoFaixaEtaria;
	}


	public Button getBtSalvar() {
		return btSalvar;
	}


	public void setBtSalvar(Button btSalvar) {
		this.btSalvar = btSalvar;
	}


	public Button getBtDeletar() {
		return btDeletar;
	}


	public void setBtDeletar(Button btDeletar) {
		this.btDeletar = btDeletar;
	}


	public Button getBtAlterarCapa() {
		return btAlterarCapa;
	}


	public void setBtAlterarCapa(Button btAlterarCapa) {
		this.btAlterarCapa = btAlterarCapa;
	}




}
