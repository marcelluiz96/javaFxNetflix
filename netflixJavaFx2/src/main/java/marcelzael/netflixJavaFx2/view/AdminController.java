package marcelzael.netflixJavaFx2.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.controller.AdminViewApp;
import marcelzael.netflixJavaFx2.controller.LoginViewApp;
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

	@FXML
	public void initialize() {
		cbTipoFaixaEtaria.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFaixaEtariaUsuario.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFaixaEtariaCadastro.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFilmeCadastro.getItems().setAll(TipoFilme.values());
		
		filmeACadastrar = new Midia();
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
			
		} catch (Exception e) {
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



	public AdminViewApp getAdminViewApp() {
		return adminViewApp;
	}

	public void setAdminViewApp(AdminViewApp adminViewApp) {
		this.adminViewApp = adminViewApp;
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
