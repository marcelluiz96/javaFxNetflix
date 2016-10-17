package marcelzael.netflixJavaFx2.app;

import java.io.IOException;

import org.hibernate.service.spi.Startable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.view.LoginController;

public class LoginViewApp extends Application {

	private Stage stage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("netFliX by Marcel & Izael V0.01");

//		this.stage.getIcons().add(new Image("file:resources/images/NetflixLogo.png"));

		initRootLayout();

	}

	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
				loader.setLocation(LoginViewApp.class.getResource("/marcelzael/netflixJavaFx2/view/LoginView.fxml"));
		rootLayout = (AnchorPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);

		LoginController controller = loader.getController();
		controller.setLoginViewApp(this);
		
		controller.getIvLogo().setImage(new Image("file:resources/images/NetflixLogo.png"));

		stage.show();
	}

	public static void main( String[] args ) {
		launch(args);
	}
}
