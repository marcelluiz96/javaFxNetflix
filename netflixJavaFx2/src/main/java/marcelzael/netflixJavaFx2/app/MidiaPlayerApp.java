package marcelzael.netflixJavaFx2.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import marcelzael.netflixJavaFx2.view.CatalogueController;
import marcelzael.netflixJavaFx2.view.MidiaPlayerController;

public class MidiaPlayerApp extends Application {

	private Stage stage;
	private BorderPane rootLayout;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle(CatalogueController.getMidiaSelecionada().getNome());


		initRootLayout();
		
	}
	
	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
				loader.setLocation(LoginViewApp.class.getResource("/marcelzael/netflixJavaFx2/view/MidiaPlayerView.fxml"));
		rootLayout = (BorderPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);

		MidiaPlayerController controller = loader.getController();
		controller.setMidiaPlayerApp(this);
		
//		controller.getIvLogo().setImage(new Image("file:resources/images/NetflixLogo.png"));

		stage.show();
	}
	
	
}
