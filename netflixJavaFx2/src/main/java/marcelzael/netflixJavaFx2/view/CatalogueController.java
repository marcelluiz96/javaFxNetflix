package marcelzael.netflixJavaFx2.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcelzael.netflixJavaFx2.DAO.MidiaHibernateDAO;
import marcelzael.netflixJavaFx2.DAO.UsuarioHibernateDAO;
import marcelzael.netflixJavaFx2.app.AdminViewApp;
import marcelzael.netflixJavaFx2.app.CatalogueViewApp;
import marcelzael.netflixJavaFx2.entity.Midia;
import marcelzael.netflixJavaFx2.entity.TipoFaixaEtaria;
import marcelzael.netflixJavaFx2.entity.TipoFilme;
import marcelzael.netflixJavaFx2.entity.Usuario;

public class CatalogueController {

	private CatalogueViewApp catalogueViewApp;

	private UsuarioHibernateDAO usuarioHibernateDAO;
	private MidiaHibernateDAO midiaHibernateDAO;
	private List<Midia> midias;
	private Midia midiaSelecionada;

	//TODO usar meu arquivo css pelo amor de deus
	private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
	private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";


	public CatalogueController() {
		usuarioHibernateDAO = new UsuarioHibernateDAO();
		midiaHibernateDAO = new MidiaHibernateDAO();
	}

	//Panes
	@FXML private GridPane gridPane;
	@FXML private ScrollPane scrollPane;

	//Botões
	@FXML private Button btPainelAdmin;
	@FXML private Button btAdicionarAosFavoritos;
	@FXML private Button btPlay;
	@FXML private Button btFiltrar;

	//checkboxes de ativação dos filtros
	@FXML private CheckBox cNomeFilme;
	@FXML private CheckBox cCategoriaFilme;
	@FXML private CheckBox cAtorPrincipalFilme;
	@FXML private CheckBox cDiretorFilme;
	@FXML private CheckBox cTipoFilme;
	@FXML private CheckBox cFaixaEtariaFilme;

	//Campos de filtro
	@FXML private TextField txNomeFilme;
	@FXML private TextField txCategoriaFilme;
	@FXML private TextField txAtorPrincipalFilme;
	@FXML private TextField txDiretorFilme;
	@FXML private ChoiceBox<TipoFilme> cbTipoFilme;
	@FXML private ChoiceBox<TipoFaixaEtaria> cbFaixaEtariaFilme;

	//Labels do filme selecionado
	@FXML private Label lbNome;
	@FXML private Label lbDescricao;
	@FXML private Label lbAno;
	@FXML private Label lbDuracao;
	@FXML private Label lbCategoria;
	@FXML private Label lbTipo;
	@FXML private Label lbTempEpisodio;
	@FXML private Label lbDiretor;
	@FXML private Label lbAtorPrincipal;
	@FXML private Label lbFaixaEtaria;

	@FXML
	public void initialize() {
		//TODO Esconder o botão de painel de ADMIN se o usuário não for admin!
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		midias =  midiaHibernateDAO.getAllMidias();
		carregarTabelaFilmes();

		cbFaixaEtariaFilme.getItems().setAll(TipoFaixaEtaria.values());
		cbTipoFilme.getItems().setAll(TipoFilme.values());

	}

	@FXML public void filtrar() {
		HashMap<String, String> filters = new HashMap<>();
		//TODO colocar if checkboxes dos filtros estão marcados antes de adicionar cada filtro
		if (cNomeFilme.isSelected())
			filters.put("nome", txNomeFilme.getText());
		if (cCategoriaFilme.isSelected())
			filters.put("categoria", txCategoriaFilme.getText());
		if (cAtorPrincipalFilme.isSelected())
			filters.put("atorPrincipal", txAtorPrincipalFilme.getText());
		if (cDiretorFilme.isSelected())
			filters.put("diretor", txDiretorFilme.getText());
		if (cTipoFilme.isSelected())
			filters.put("tipoFilme", cbTipoFilme.getSelectionModel().getSelectedItem().toString());
		if (cFaixaEtariaFilme.isSelected())
			filters.put("faixaEtaria", cbFaixaEtariaFilme.getSelectionModel().getSelectedItem().toString());
		midias = midiaHibernateDAO.findMidias(filters);
		carregarTabelaFilmes();
	}

	public void carregarTabelaFilmes() {
		//Adicionando os filmes
		int colIndex = 0;
		int rowIndex = 0;
		int height = 337;
		gridPane.getChildren().clear();
		gridPane.setGridLinesVisible(true);
		//		gridPane.setVgap(12);
		//		gridPane.setHgap(8);

		//		gridPane.setMinHeight(((int)midias.size() / 4) * 338.5);
		for (Midia midia : midias) {
			Image image;
			if (midia.getCapaFilme() != null) {
				image = new Image(new ByteArrayInputStream(midia.getCapaFilme()));
			} else {
				File file = new File("resources//images//noImage.jpg");
				image = new Image(file.toURI().toString());
			}

			ImageView img = new ImageView(image);
			img.setFitWidth(222);
			img.setFitHeight(334);

			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(0.0);

			img.setEffect(colorAdjust);

			img.setOnMouseEntered(e -> {

				Timeline fadeInTimeline = new Timeline(
						new KeyFrame(Duration.seconds(0), 
								new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
						new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust.brightnessProperty(), -0.3, Interpolator.LINEAR)
								));
				fadeInTimeline.setCycleCount(1);
				fadeInTimeline.setAutoReverse(false);
				fadeInTimeline.play();

			});

			Tooltip t = new Tooltip(midia.getNome());
			Tooltip.install(img.getParent(), t);

			img.setOnMouseExited(e -> {

				Timeline fadeOutTimeline = new Timeline(
						new KeyFrame(Duration.seconds(0), 
								new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
						new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
								));
				fadeOutTimeline.setCycleCount(1);
				fadeOutTimeline.setAutoReverse(false);
				fadeOutTimeline.play();

			});

			img.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					int col = GridPane.getColumnIndex(img);
					int row = GridPane.getRowIndex(img);
					selecionarMidia(midias.get((row * 4) + col));
				}

			});


//						GridPane.setHalignment(img, HPos.CENTER);
//						GridPane.setValignment(img, VPos.CENTER);
//						GridPane.setHgrow(img, Priority.ALWAYS);
//						GridPane.setVgrow(img, Priority.ALWAYS);
						Label labelNome = new Label(midia.getNome());
						labelNome.setMinWidth(220);
						labelNome.setTextAlignment(TextAlignment.CENTER);
						labelNome.setAlignment(Pos.BOTTOM_CENTER);
						labelNome.setContentDisplay(ContentDisplay.CENTER);
						labelNome.setStyle("-fx-background-color: rgba(100, 100, 100, 0.7);-fx-text-fill:#FFF;-fx-font-size: 20; -fx-font-style: italic;-fx-background-radius: 2;");
						
						GridPane.setHalignment(labelNome, HPos.CENTER);
						GridPane.setValignment(labelNome, VPos.BOTTOM);


			if (colIndex == 4) {
				colIndex = 0;
				rowIndex++;
				height += 337;
				gridPane.setMinHeight(height);
			}

			gridPane.add(img, colIndex, rowIndex);
			gridPane.add(labelNome, colIndex, rowIndex);
			colIndex++;
		}
	}

	@FXML
	public void adicionarOuRemoverFavorito(ActionEvent event) {
		Usuario user = usuarioHibernateDAO.getById(Usuario.class, getUsuarioLogado().getId());
		List<Midia> favoritos = midiaHibernateDAO.getFavoritos(user);
		if (!favoritos.contains(midiaSelecionada)) {
			favoritos.add(midiaSelecionada);
			user.setFavoritos(favoritos);
			btAdicionarAosFavoritos.setText("Remover dos favoritos");
		} else {
			favoritos.remove(midiaSelecionada);
			user.setFavoritos(favoritos);
			btAdicionarAosFavoritos.setText("Adicionar aos favoritos");
		}
		usuarioHibernateDAO.update(user);


	}

	public void selecionarMidia(Midia midia) {
		midiaSelecionada = midia;
		lbNome.setText(midia.getNome());
		lbAno.setText(String.valueOf(midia.getAno()));
		lbAtorPrincipal.setText(midia.getAtorPrincipal());
		lbCategoria.setText(midia.getCategoria());
		lbDescricao.setText(midia.getDescricao());
		lbDiretor.setText(midia.getDiretor());
		lbDuracao.setText(midia.getDuracao());
		lbFaixaEtaria.setText(midia.getFaixaEtaria().toString());
		lbTempEpisodio.setText(midia.getTempEpisodio());
		lbTipo.setText(midia.getTipoFilme().toString());

		Usuario user = usuarioHibernateDAO.getById(Usuario.class, getUsuarioLogado().getId());
		List<Midia> favoritos = midiaHibernateDAO.getFavoritos(user);
		if (favoritos.contains(midiaSelecionada)) {
			btAdicionarAosFavoritos.setText("Remover dos favoritos");
		} else {
			btAdicionarAosFavoritos.setText("Adicionar aos favoritos");
		}
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

	public Usuario getUsuarioLogado() {
		return LoginController.getUsuarioLogado();
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
