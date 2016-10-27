package marcelzael.netflixJavaFx2.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.dataModel.MidiaDataModel;
import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.view.AdminController;
import marcelzael.netflixJavaFx2.view.LoginController;

public class AdminViewApp extends Application{

	private Stage stage;
	private AnchorPane rootLayout;
	private ObservableList<MidiaDataModel> listaMidias = FXCollections.observableArrayList(); 
	
	public AdminViewApp() {
		for (Midia midia : new MidiaHibernateDAO().getAllMidias(LoginController.getUsuarioLogado())) {
			listaMidias.add(new MidiaDataModel(midia));
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Tela de catálogo");
		stage.setResizable(false);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

		initRootLayout();
	}

	private void initRootLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AdminViewApp.class.getResource("/marcelzael/netflixJavaFx2/view/AdminView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			
			AdminController controller = loader.getController();
			controller.setAdminViewApp(this);
			
//			controller.getIvLogo().setImage(new Image("file:resources/images/NetflixLogo.png"));
			
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<MidiaDataModel> recarregarTabelaFilmes() {
		listaMidias.clear();
		for (Midia midia : new MidiaHibernateDAO().listAll(Midia.class)) {
			listaMidias.add(new MidiaDataModel(midia));
		}
		return listaMidias;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public AnchorPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public ObservableList<MidiaDataModel> getListaMidias() {
		return listaMidias;
	}
	
	

	public void setListaMidias(ObservableList<MidiaDataModel> listaMidias) {
		this.listaMidias = listaMidias;
	}

	


}
