package marcelzael.netflixJavaFx2.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	Duration duration;

	@FXML private Slider volSlider;
	@FXML private Slider timeSlider;
	@FXML private Button btPlay;
	@FXML private Button btPause;
	@FXML private Button btSkip;
	@FXML private Button btRewind;
	@FXML private Button btFast;
	@FXML private Button btSlow;
	@FXML private Button btRestart;

	@FXML private Label lbTime;
	@FXML private Label lbTotalTime;

	Midia midiaAExibir;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File movie = new File("/temp/movie");
		midiaHibernateDAO = new MidiaHibernateDAO();

		midiaAExibir = midiaHibernateDAO.getById(Midia.class, CatalogueController.
				getMidiaSelecionada().getId());

		midia = new Media(midiaAExibir.getPathFilme());
		mp = new MediaPlayer(midia);
		duration = mp.getMedia().getDuration();
		lbTotalTime.setText(formatTime(duration, duration));
		mv.setMediaPlayer(mp);
		mp.setAutoPlay(true);

		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));

		volSlider.setValue(mp.getVolume() * 100.0);

		
		
		timeSlider.setOnMousePressed(new EventHandler<MouseEvent>(){
		    @Override
		    public void handle(MouseEvent event) {
		    	mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
		    }
		});
		
		timeSlider.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
			}
		});

		mp.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				updateValues();	
			}
		});

		mp.setOnReady(new Runnable() {
			public void run() {
				duration = mp.getMedia().getDuration();
				updateValues();
			}
		});

		mp.setCycleCount(MediaPlayer.INDEFINITE);
		
	}

	
	
	protected void updateValues() {
		if (lbTime != null && timeSlider != null && volSlider != null) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Duration currentTime = mp.getCurrentTime();
					lbTime.setText(formatTime(currentTime,duration));
					timeSlider.setDisable(duration.isUnknown());

					if (!timeSlider.isDisabled() &&
							duration.greaterThan(Duration.ZERO) &&
							!timeSlider.isValueChanging()) {
						timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
					}

					if (!volSlider.isValueChanging()) {
						volSlider.setValue((int) Math.round(mp.getVolume() * 100));
					}
				}
			});
		}
	}

	public void play(ActionEvent event) {
		if (mp.getStatus().equals(MediaPlayer.Status.STOPPED)) {
			mp.seek(mp.getStartTime());
			timeSlider.setValue(mp.getCurrentTime().divide(duration).toMillis() * 100.0);	
		}
		mp.play();
	}
	public void pause(ActionEvent event) {
		mp.pause();
	}
	public void skip10Secs(ActionEvent event) {
		mp.seek(mp.getCurrentTime().add(new Duration(10000)));
		timeSlider.setValue(mp.getCurrentTime().divide(duration).toMillis() * 100.0);
	}
	public void rewind10Secs(ActionEvent event) {
		mp.seek(mp.getCurrentTime().subtract(new Duration(10000)));
		timeSlider.setValue(mp.getCurrentTime().divide(duration).toMillis() * 100.0);
	}
	public void restart(ActionEvent event) {
		mp.seek(mp.getStartTime());
		timeSlider.setValue(mp.getCurrentTime().divide(duration).toMillis() * 100.0);
		mp.play();
	}
	public void accelerate(ActionEvent event) {
		mp.setRate(mp.getRate() * 2);
	}
	public void decelerate(ActionEvent event) {
		mp.setRate(mp.getRate() * 0.5);
	}
	public void stop(ActionEvent event) {
		mp.stop();
	}
	
	public void close() {
		mp.stop();
		mp.dispose();
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

	private static String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int)Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 
				- elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int)Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60 - 
					durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d", 
						elapsedHours, elapsedMinutes, elapsedSeconds
						);
			} else {
				return String.format("%02d:%02d",
						elapsedMinutes, elapsedSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours, 
						elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d",elapsedMinutes, 
						elapsedSeconds);
			}
		}
	}


}
