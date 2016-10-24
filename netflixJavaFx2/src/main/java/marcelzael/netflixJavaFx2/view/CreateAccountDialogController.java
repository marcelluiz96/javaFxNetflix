package marcelzael.netflixJavaFx2.view;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.entity.TipoFaixaEtaria;

public class CreateAccountDialogController {

	private UsuarioHibernateDAO usuarioHibernateDAO;
	private Stage dialogStage;
	private boolean okClicked = false;

	@FXML private TextField txLoginUsuario;
	@FXML private PasswordField pfSenhaUsuario;
	@FXML private ChoiceBox<TipoFaixaEtaria> cbTipoFaixaEtariaUsuario;
	@FXML private CheckBox cboxTermosECondicoes;
	@FXML private Button btCadastrarUsuario;

	@FXML
	public void initialize() {
		cbTipoFaixaEtariaUsuario.getItems().setAll(TipoFaixaEtaria.values());
		
		usuarioHibernateDAO = new UsuarioHibernateDAO();
	}

	@FXML
	public void cadastrarUsuario(ActionEvent event) {
		try {
			if (cboxTermosECondicoes.isSelected()) {
				usuarioHibernateDAO.cadastrarUsuario(txLoginUsuario.getText(), pfSenhaUsuario.getText(), 
						cbTipoFaixaEtariaUsuario.getSelectionModel().getSelectedItem(), false);

				Alert alert = new Alert(AlertType.INFORMATION, "Usuário cadastrado com sucesso!", ButtonType.OK);
				alert.setTitle("Info");
				alert.setHeaderText("Usuário cadastrado com sucesso!");
				alert.setContentText("Clique em OK para retornar à tela de login");
				Optional<ButtonType> result = alert.showAndWait();
				
				if (result.get() == ButtonType.OK){
				    Stage stage = (Stage) btCadastrarUsuario.getScene().getWindow();
				    stage.close();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("YOU CANNOT ESCAPE FROM THIS");
				alert.setHeaderText("ACEITE OS TERMOS E CONDIÇÕES");
				alert.setContentText("Q: What must we give in return? A: EVERYTHING!");
				alert.show();	
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Erro ao cadastrar usuário! Verifique se todos os campos foram preenchidos corretamente"
					+ "ou tente utilizar outro nome de usuário", ButtonType.OK);
			alert.show();
			
		}
	}


	public UsuarioHibernateDAO getUsuarioHibernateDAO() {
		return usuarioHibernateDAO;
	}


	public void setUsuarioHibernateDAO(UsuarioHibernateDAO usuarioHibernateDAO) {
		this.usuarioHibernateDAO = usuarioHibernateDAO;
	}


	public Stage getDialogStage() {
		return dialogStage;
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	public boolean isOkClicked() {
		return okClicked;
	}


	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
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


	public CheckBox getCboxTermosECondicoes() {
		return cboxTermosECondicoes;
	}


	public void setCboxTermosECondicoes(CheckBox cboxTermosECondicoes) {
		this.cboxTermosECondicoes = cboxTermosECondicoes;
	}


	public Button getBtCadastrarUsuario() {
		return btCadastrarUsuario;
	}


	public void setBtCadastrarUsuario(Button btCadastrarUsuario) {
		this.btCadastrarUsuario = btCadastrarUsuario;
	}
	
	

}
