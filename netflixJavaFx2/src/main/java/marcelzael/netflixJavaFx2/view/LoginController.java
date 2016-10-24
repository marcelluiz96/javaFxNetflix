package marcelzael.netflixJavaFx2.view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.app.CatalogueViewApp;
import marcelzael.netflixJavaFx2.app.LoginViewApp;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class LoginController implements Initializable {
	
	private LoginViewApp loginViewApp;
	
	private UsuarioHibernateDAO usuarioHibernateDAO;
	
	private static Usuario usuarioLogado;
	
	public LoginController() {
		usuarioHibernateDAO = new UsuarioHibernateDAO();
	}
	
	@FXML
	private ImageView ivLogo;
	
	@FXML
	private TextField txLogin;
	
	@FXML
	private PasswordField txSenha;
	
	@FXML
	private Button btLogin;
	
	@FXML
	private void actionLogin(ActionEvent event) {
		String login = txLogin.getText();
		String senha = txSenha.getText();
		
		Usuario usuario = usuarioHibernateDAO.findUser(login, senha);
		
		if (usuario != null) {
			//Usuário encontrado
			usuarioLogado = usuario;
			try {
				new CatalogueViewApp().start(new Stage());
				Stage stage = (Stage) btLogin.getScene().getWindow();
			    stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			//Usuário ou senha incorretos
		}
	}
	
	@FXML
	private void actionLimpar(ActionEvent event) {
		
	}
	
	@FXML
	private void actionCriarConta(ActionEvent event) {
		loginViewApp.showCreateAccountDialog();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public LoginViewApp getLoginViewApp() {
		return loginViewApp;
	}

	public void setLoginViewApp(LoginViewApp loginViewApp) {
		this.loginViewApp = loginViewApp;
	}

	public TextField getTxLogin() {
		return txLogin;
	}

	public void setTxLogin(TextField txLogin) {
		this.txLogin = txLogin;
	}

	public PasswordField getTxSenha() {
		return txSenha;
	}

	public void setTxSenha(PasswordField txSenha) {
		this.txSenha = txSenha;
	}

	public ImageView getIvLogo() {
		return ivLogo;
	}

	public void setIvLogo(ImageView ivLogo) {
		this.ivLogo = ivLogo;
	}

	public UsuarioHibernateDAO getUsuarioHibernateDAO() {
		return usuarioHibernateDAO;
	}

	public void setUsuarioHibernateDAO(UsuarioHibernateDAO usuarioHibernateDAO) {
		this.usuarioHibernateDAO = usuarioHibernateDAO;
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		LoginController.usuarioLogado = usuarioLogado;
	}

	public Button getBtLogin() {
		return btLogin;
	}

	public void setBtLogin(Button btLogin) {
		this.btLogin = btLogin;
	}

	
	
	

}
