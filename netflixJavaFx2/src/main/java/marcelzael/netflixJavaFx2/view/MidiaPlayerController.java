package marcelzael.netflixJavaFx2.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.util.Duration;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.app.MidiaPlayerApp;
import marcelzael.netflixJavaFx2.entity.Midia;

public class MidiaPlayerController implements Initializable{

	MidiaPlayerApp midiaPlayerApp;
	MidiaHibernateDAO midiaHibernateDAO;

	@FXML private MediaView mv;
	MediaPlayer mp;
	Media midia;

	@FXML private Slider volSlider;
	@FXML private Button btPlay;
	@FXML private Button btPause;
	@FXML private Button btSkip;
	@FXML private Button btRewind;
	@FXML private Button btFast;
	@FXML private Button btSlow;
	@FXML private Button btRestart;
	
	Midia midiaAExibir;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File movie = new File("/temp/movie");
		midiaHibernateDAO = new MidiaHibernateDAO();
		ProgressIndicator indicator = new ProgressIndicator();
		
		
		Task<Boolean> task = new Task<Boolean>() {
		    @Override public Boolean call() throws IOException {
		        // do your operation in here
		    	midiaAExibir = midiaHibernateDAO.getById(Midia.class, CatalogueController.
		    			getMidiaSelecionada().getId());
		    	FileUtils.writeByteArrayToFile(movie, midiaAExibir.getConteudoFilme());
		    	
		        return true;
		    }
		};

		task.setOnRunning((e) -> indicator.setVisible(true));
		task.setOnSucceeded((e) -> {
		    indicator.setVisible(false);
		    try {
				Boolean returnValue = task.get();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    // process return value again in JavaFX thread
		});
		task.setOnFailed((e) -> {
		  // eventual error handling by catching exceptions from task.get()  
		});
		new Thread(task).start();
		
		

		midia = new Media(movie.toPath().toUri().toString());
		mp = new MediaPlayer(midia);
		mv.setMediaPlayer(mp);
		mp.setAutoPlay(true);

		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));

		volSlider.setValue(mp.getVolume() * 100);
		volSlider.valueChangingProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				mp.setVolume(volSlider.getValue() / 100);

			}
		});
	}

	public void play(ActionEvent event) {
		mp.play();
	}
	public void pause(ActionEvent event) {
		mp.pause();
	}
	public void skip10Secs(ActionEvent event) {
		mp.seek(mp.getCurrentTime().add(new Duration(10)));
	}
	public void rewind10Secs(ActionEvent event) {
		mp.seek(mp.getCurrentTime().subtract(new Duration(10)));
	}
	public void restart(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
	}
	public void accelerate(ActionEvent event) {
		mp.setRate(1.5);
	}
	public void decelerate(ActionEvent event) {
		mp.setRate(0.5);
	}

	public MidiaPlayerApp getMidiaPlayerApp() {
		return midiaPlayerApp;
	}

	public void setMidiaPlayerApp(MidiaPlayerApp midiaPlayerApp) {
		this.midiaPlayerApp = midiaPlayerApp;
	}

	public MediaView getMv() {
		return mv;
	}

	public void setMv(MediaView mv) {
		this.mv = mv;
	}

	public MediaPlayer getMp() {
		return mp;
	}

	public void setMp(MediaPlayer mp) {
		this.mp = mp;
	}

	public Media getMidia() {
		return midia;
	}

	public void setMidia(Media midia) {
		this.midia = midia;
	}


}
