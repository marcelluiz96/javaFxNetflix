package marcelzael.netflixJavaFx2.view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.controller.LoginViewApp;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class LoginController implements Initializable {
	
	private LoginViewApp loginViewApp;
	
	private UsuarioHibernateDAO usuarioHibernateDAO;
	
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
	private void actionLogin(ActionEvent event) {
		String login = txLogin.getText();
		String senha = txSenha.getText();
		
		Usuario usuario = usuarioHibernateDAO.findUser(login, senha);
		
		if (usuario != null) {
			
		}
	}
	
	@FXML
	private void actionLimpar(ActionEvent event) {
		
	}
	
	@FXML
	private void actionEsqueciSenha(ActionEvent event) {
		
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

	
	
	

}
