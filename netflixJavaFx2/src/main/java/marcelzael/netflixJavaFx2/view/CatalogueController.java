package marcelzael.netflixJavaFx2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.controller.AdminViewApp;
import marcelzael.netflixJavaFx2.controller.CatalogueViewApp;

public class CatalogueController {
	
	private CatalogueViewApp catalogueViewApp;
	
	private UsuarioHibernateDAO usuarioHibernateDAO;
	private MidiaHibernateDAO midiaHibernateDAO;
	
	
	public CatalogueController() {
		usuarioHibernateDAO = new UsuarioHibernateDAO();
		midiaHibernateDAO = new MidiaHibernateDAO();
	}
	
	@FXML private Button btPainelAdmin;
	
	@FXML
	public void initialize() {
		//TODO Esconder o botão de painel de ADMIN se o usuário não for admin!
	}
	
	@FXML
	public void acessarPainelAdmin() {
		try {
			new AdminViewApp().start(new Stage());
			Stage stage = (Stage) btPainelAdmin.getScene().getWindow();
		    stage.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public CatalogueViewApp getCatalogueViewApp() {
		return catalogueViewApp;
	}

	public void setCatalogueViewApp(CatalogueViewApp catalogueViewApp) {
		this.catalogueViewApp = catalogueViewApp;
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

	public Button getBtPainelAdmin() {
		return btPainelAdmin;
	}

	public void setBtPainelAdmin(Button btPainelAdmin) {
		this.btPainelAdmin = btPainelAdmin;
	}
	
	
	

}
