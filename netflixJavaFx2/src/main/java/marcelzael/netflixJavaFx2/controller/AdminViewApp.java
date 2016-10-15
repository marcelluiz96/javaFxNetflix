package marcelzael.netflixJavaFx2.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.view.AdminController;

public class AdminViewApp extends Application{

	private Stage stage;
	private AnchorPane rootLayout;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Tela de catálogo");

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



}
