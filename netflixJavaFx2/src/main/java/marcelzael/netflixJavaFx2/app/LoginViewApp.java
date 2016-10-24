package marcelzael.netflixJavaFx2.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import marcelzael.netflixJavaFx2.view.CreateAccountDialogController;
import marcelzael.netflixJavaFx2.view.LoginController;

public class LoginViewApp extends Application {

	private Stage stage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("netFliX by Marcel & Izael V0.01");

//		this.stage.getIcons().add(new Image("file:resources/images/NetflixLogo.png"));
		//this makes all stages close and the app exit when the main stage is closed
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

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
	
	public boolean showCreateAccountDialog() {
		  try {
			    // Load the fxml file and create a new stage for the popup
			    FXMLLoader loader = new FXMLLoader(LoginViewApp.class.getResource("/marcelzael/netflixJavaFx2/view/CreateAccountDialogView.fxml"));
			    AnchorPane page = (AnchorPane) loader.load();
			    Stage dialogStage = new Stage();
			    dialogStage.setTitle("Edit Person");
			    dialogStage.initModality(Modality.WINDOW_MODAL);
			    dialogStage.initOwner(stage);
			    Scene scene = new Scene(page);
			    dialogStage.setScene(scene);

			    // Set the person into the controller
			    CreateAccountDialogController controller = loader.getController();
			    controller.setDialogStage(dialogStage);
			    

			    // Show the dialog and wait until the user closes it
			    dialogStage.showAndWait();

			    return controller.isOkClicked();

			  } catch (IOException e) {
			    // Exception gets thrown if the fxml file could not be loaded
			    e.printStackTrace();
			    return false;
			  }
	}

	public static void main( String[] args ) {
		launch(args);
	}
}
