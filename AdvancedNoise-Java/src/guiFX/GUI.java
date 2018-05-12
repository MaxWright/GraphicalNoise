package guiFX;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public HBox hBox_Generator() {
		Label lbl_frequency = new Label("Frequency");
		Label lbl_octave = new Label("Octave");
		Label lbl_persistence = new Label("Persistence");

		HBox hbox = new HBox(lbl_frequency, lbl_octave, lbl_persistence);
		return hbox;
	}

	public VBox vBox_Generator() {
		Label lbl_frequency = new Label("Frequency");
		Label lbl_octave = new Label("Octave");
		Label lbl_persistence = new Label("Persistence");

		VBox vbox = new VBox(lbl_frequency, lbl_octave, lbl_persistence);

		return vbox;
	}

	public Scene hBox_Example() {
		/**
		 * Create Horizontal box of Attribute Labels
		 */

		Scene scene = new Scene(hBox_Generator());
		return scene;
	}

	public Scene vBox_Example() {
		/**
		 * Create Verticle box of Attribute Labels
		 */

		Scene scene = new Scene(vBox_Generator());
		return scene;
	}

	/**
	 * Alignments Pos.TOP_LEFT Pos.TOP_CENTER Pos.TOP_RIGHT Pos.CENTER_LEFT
	 * Pos.CENTER Pos.CENTER_RIGHT Pos.BOTTOM_LEFT Pos.BOTTOM_CENTER
	 * Pos.BOTTOM_RIGHT
	 */
	public Scene hBox_Alignment_Example(Pos position) {
		/**
		 * Create Verticle box of Attribute Labels
		 */
		HBox hBox = hBox_Generator();
		hBox.setAlignment(position);
		// ... , width, height
		Scene scene = new Scene(hBox, 300, 100);
		return scene;
	}

	public Scene vBox_Alignment_Example(Pos position) {
		/**
		 * Create Verticle box of Attribute Labels
		 */
		VBox vBox = vBox_Generator();
		vBox.setAlignment(position);
		// ... , width, height
		Scene scene = new Scene(vBox, 300, 100);
		return scene;
	}

	private HBox error(String message) {
		Label lbl_img_not_found = new Label(message);
		HBox hBox = new HBox(lbl_img_not_found);
		return hBox;
	}

	public Scene img_local_example() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("guiFX/cat.jpg");
		Image image = null;
		HBox hBox = null;
		try {
			image = new Image(input);
			ImageView imageView = new ImageView(image);
			hBox = new HBox(imageView);
		} catch (NullPointerException e) {
			hBox = error("Img not found");
		}

		hBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(hBox);

		return scene;
	}

	public Scene img_http_example() {
		Image image = null;
		HBox hBox = null;
		try {
			image = new Image(
					"https://raw.githubusercontent.com/UWB-Biocomputing/BrainGrid/master/docs/images/CheckMavenVersion.png");
			ImageView imageView = new ImageView(image);
			hBox = new HBox(imageView);
		} catch (NullPointerException e) {
			hBox = error("URL is null");
		} catch (IllegalArgumentException e) {
			hBox = error("URL is invalid or unsupported");
		}

		hBox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(hBox);

		return scene;
	}

	public Scene img_size_example() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("guiFX/cat.jpg");
		Image image = null;
		HBox hBox = null;
		try {
			image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(300);
			imageView.setFitWidth(100);
			hBox = new HBox(imageView);
		} catch (NullPointerException e) {
			hBox = error("Img not found");
		}

		hBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(hBox);

		return scene;
	}

	public Scene img_scale_example() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("guiFX/cat.jpg");
		Image image = null;
		HBox hBox = null;
		try {
			image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
			imageView.setFitHeight(300);
			imageView.setFitWidth(100);
			hBox = new HBox(imageView);
		} catch (NullPointerException e) {
			hBox = error("Img not found");
		}

		hBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(hBox);

		return scene;
	}

	private Image getImage(String src) throws NullPointerException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(src);
		Image image = new Image(input);
		return image;
	}

	private Image getCatImage() throws NullPointerException {
		return getImage("guiFX/cat.jpg");
	}

	private ImageView getImageView(String src) throws NullPointerException {
		ImageView imageView = new ImageView(getImage(src));
		return imageView;
	}

	private ImageView getCatImageView() throws NullPointerException {
		ImageView imageView = new ImageView(getCatImage());
		return imageView;
	}

	public Scene img_padding_example() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("guiFX/cat.jpg");
		Image image = null;
		HBox hBox = null;
		try {
			image = new Image(input);
			ImageView imageView1 = new ImageView(image);
			ImageView imageView2 = new ImageView(image);
			ImageView imageView3 = new ImageView(image);
			hBox = new HBox(10, imageView1, imageView2, imageView3);
			hBox.setPadding(new Insets(30));
		} catch (NullPointerException e) {
			hBox = error("Img not found");
		}

		hBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(hBox);

		return scene;
	}

	private GridPane getCatGrid() {
		GridPane gridpane = new GridPane();

		// what x y
		gridpane.add(getCatImageView(), 0, 0);
		gridpane.add(getCatImageView(), 1, 0);
		gridpane.add(getCatImageView(), 1, 1);
		gridpane.add(getCatImageView(), 0, 1);
		return gridpane;
	}

	public Scene grid_layout() {
		GridPane gridpane = getCatGrid();

		Scene scene = new Scene(gridpane);

		return scene;
	}

	public Scene grid_layout_debug() {
		GridPane gridpane = getCatGrid();

		gridpane.setGridLinesVisible(true);

		Scene scene = new Scene(gridpane);

		return scene;
	}

	public Scene grid_layout_gaps() {
		GridPane gridpane = getCatGrid();

		// gridpane.setGridLinesVisible(true);

		gridpane.setVgap(10.5);
		gridpane.setHgap(20);
		gridpane.setPadding(new Insets(30));
		Scene scene = new Scene(gridpane);

		return scene;
	}

	public Scene grid_of_box() {
		GridPane cats = getCatGrid();
		VBox vBox = vBox_Generator();
		GridPane gridpane = new GridPane();

		gridpane.add(cats, 0, 1);
		gridpane.add(vBox, 1, 1);

		Scene scene = new Scene(gridpane);

		return scene;
	}

	private Label lbl_1;

	public Scene buttonExample() {
		lbl_1 = new Label("Click the  button");
		Button btn_1 = new Button("Click me");
		btn_1.setOnAction(new ButtonClickHandler());
		VBox vbox = new VBox(10, lbl_1, btn_1);

		Scene scene = new Scene(vbox, 300, 100);
		return scene;
	}

	class ButtonClickHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			lbl_1.setText("You clicked the button");
		}

	}

	private TextField txtfld_kilo;
	private Label lbl_result;

	public Scene textFieldExample() {
		Label lbl_prompt = new Label("Input: ");

		txtfld_kilo = new TextField();

		Button btn_calc = new Button("Calculate");

		btn_calc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				double kilo = Double.parseDouble(txtfld_kilo.getText());

				double mi = kilo * 0.6214;

				lbl_result.setText(String.format("%,.2f miles", mi));

			}

		});

		lbl_result = new Label();

		HBox hbox = new HBox(lbl_prompt, txtfld_kilo);
		VBox vbox = new VBox(10, hbox, btn_calc, lbl_result);

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		Scene scene = new Scene(vbox);
		return scene;
	}

	public Scene textFieldExampleLambda() {
		Label lbl_prompt = new Label("Input: ");

		txtfld_kilo = new TextField();

		Button btn_calc = new Button("Calculate");

		btn_calc.setOnAction(event -> {
			double kilo = Double.parseDouble(txtfld_kilo.getText());

			double mi = kilo * 0.6214;

			lbl_result.setText(String.format("%,.2f miles", mi));

		});

		lbl_result = new Label();

		HBox hbox = new HBox(lbl_prompt, txtfld_kilo);
		VBox vbox = new VBox(10, hbox, btn_calc, lbl_result);

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		Scene scene = new Scene(vbox);
		return scene;
	}

	public Scene borderPaneExample() {
		GridPane center = getCatGrid();
		center.setAlignment(Pos.CENTER);

		VBox left = vBox_Generator();

		VBox right = vBox_Generator();
		right.setAlignment(Pos.BOTTOM_CENTER);

		HBox bottom = hBox_Generator();
		bottom.setAlignment(Pos.CENTER);

		HBox top = hBox_Generator();
		top.setAlignment(Pos.CENTER);

		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(center);
		borderpane.setLeft(left);
		borderpane.setRight(right);
		borderpane.setBottom(bottom);
		borderpane.setTop(top);

		Scene scene = new Scene(borderpane);
		return scene;

	}

	public Scene addallremoveExample() {
		Label lbl_frequency = new Label("Frequency");
		Label lbl_octave = new Label("Octave");
		Label lbl_persistence = new Label("Persistence");

		HBox hbox = new HBox();
		hbox.getChildren().addAll(lbl_frequency, lbl_octave, lbl_persistence);
		hbox.getChildren().remove(lbl_octave);
		Scene scene = new Scene(hbox);
		return scene;
	}

	public Scene cssDemo1() {
		VBox vbox = vBox_Generator();
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add("guiFX/demo1.css");
		return scene;
	}

	public Scene cssDemo2() {
		Scene scene = textFieldExampleLambda();
		scene.getStylesheets().add("guiFX/demo2.css");
		return scene;
	}

	public Scene cssDemo3() {
		Scene scene = textFieldExampleLambda();
		scene.getStylesheets().add("guiFX/demo3.css");
		return scene;
	}

	public Scene cssDemo4() {
		Scene scene = textFieldExampleLambda();
		scene.getStylesheets().add("guiFX/demo4.css");
		return scene;
	}

	public Scene cssDemo5() {
		Label lbl_prompt = new Label("Input: ");

		txtfld_kilo = new TextField();

		Button btn_calc = new Button("Calculate");

		btn_calc.setOnAction(event -> {
			double kilo = Double.parseDouble(txtfld_kilo.getText());

			double mi = kilo * 0.6214;

			lbl_result.setText(String.format("%,.2f miles", mi));

		});
		btn_calc.getStyleClass().add("button-white");

		Button btn_calc2 = new Button("Calculate");
		btn_calc2.setOnAction(event -> {
			double kilo = Double.parseDouble(txtfld_kilo.getText());

			double mi = kilo * 0.6214;

			lbl_result.setText(String.format("%,.2f miles", mi));

		});
		btn_calc2.getStyleClass().add("button-black");

		lbl_result = new Label();

		HBox hbox = new HBox(lbl_prompt, txtfld_kilo);
		VBox vbox = new VBox(10, hbox, btn_calc, btn_calc2, lbl_result);

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add("guiFX/demo5.css");
		return scene;

	}

	public Scene cssDemoIDSelector() {
		Label lbl_prompt = new Label("By Id");
		lbl_prompt.setId("label-error");

		lbl_result = new Label("Directly Manipulated");
		lbl_result.setStyle("-fx-font-size: 24pt");

		HBox hbox = new HBox(lbl_prompt);
		VBox vbox = new VBox(10, hbox, lbl_result);

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add("guiFX/demo6.css");
		return scene;
	}

	private RadioButton rdbtn_cat1;
	private RadioButton rdbtn_cat2;
	private RadioButton rdbtn_cat3;
	private ImageView imageView;

	public Scene radioBtnExample() {
		rdbtn_cat1 = new RadioButton("Cat 1");
		rdbtn_cat2 = new RadioButton("Cat 2");
		rdbtn_cat3 = new RadioButton("Cat 3");

		rdbtn_cat1.setOnAction(event -> {
			imageView.setImage(getCatImage());
		});
		rdbtn_cat2.setOnAction(event -> {
			imageView.setImage(getImage("guiFX/cat2.jpg"));
		});
		rdbtn_cat3.setOnAction(event -> {
			imageView.setImage(getImage("guiFX/cat3.jpg"));
		});

		rdbtn_cat1.setSelected(true);

		ToggleGroup radioGroup = new ToggleGroup();

		rdbtn_cat1.setToggleGroup(radioGroup);
		rdbtn_cat2.setToggleGroup(radioGroup);
		rdbtn_cat3.setToggleGroup(radioGroup);

		imageView = getCatImageView();

		VBox vbox = new VBox(10, rdbtn_cat1, rdbtn_cat2, rdbtn_cat3);

		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(imageView);
		borderpane.setLeft(vbox);

		Scene scene = new Scene(borderpane);

		return scene;
	}

	private CheckBox chkbx_cat1;
	private CheckBox chkbx_cat2;
	private ImageView imageView1;
	private ImageView imageView2;

	public Scene chkBxExample() {

		imageView1 = getCatImageView();
		imageView2 = getCatImageView();
		VBox v_cats = new VBox(imageView1, imageView2);

		chkbx_cat1 = new CheckBox("Cat 1");
		chkbx_cat1.setOnAction(event -> {
			if (chkbx_cat1.isSelected()) {
				imageView1.setImage(getImage("guiFX/cat2.jpg"));
			} else {
				imageView1.setImage(getCatImage());
			}
		});

		chkbx_cat2 = new CheckBox("Cat 2");
		chkbx_cat2.setOnAction(event -> {
			if (chkbx_cat2.isSelected()) {
				imageView2.setImage(getImage("guiFX/cat3.jpg"));
			} else {
				imageView2.setImage(getCatImage());
			}
		});
		VBox v_chkbx = new VBox(30, chkbx_cat1, chkbx_cat2);

		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(v_cats);
		borderpane.setLeft(v_chkbx);

		Scene scene = new Scene(borderpane);

		return scene;
	}

	private ListView<String> catListView;
	Label lbl_select;

	public Scene listViewInExample() {

		catListView = new ListView<>();
		catListView.setPrefSize(120, 100);
		catListView.getItems().addAll("Cat 1", "Cat 2", "Cat 3", "Cat 4");

		lbl_select = new Label("Select-a-cat");

		Button btn_select = new Button();
		btn_select.setOnAction(event -> {
			if (catListView.getSelectionModel().getSelectedIndex() != -1) {
				String selected = catListView.getSelectionModel().getSelectedItem();
				lbl_select.setText(selected);
			} else {
				lbl_select.setText("Nothing selected, you cheeky bugger");
			}

		});

		VBox vbox = new VBox(catListView, lbl_select, btn_select);
		Scene scene = new Scene(vbox);

		return scene;
	}

	public Scene listViewInExample2() {

		catListView = new ListView<>();
		catListView.setPrefSize(120, 100);
		catListView.getItems().addAll("Cat 1", "Cat 2", "Cat 3", "Cat 4");
		catListView.getSelectionModel().selectedItemProperty().addListener(event -> {
			String selected = catListView.getSelectionModel().getSelectedItem();
			lbl_select.setText(selected);
		});
		lbl_select = new Label("Select-a-cat");

		VBox vbox = new VBox(catListView, lbl_select);
		Scene scene = new Scene(vbox);

		return scene;
	}

	public Scene listViewInExample3() {

		catListView = new ListView<>();
		catListView.setPrefSize(120, 100);
		catListView.getItems().addAll("Cat 1", "Cat 2", "Cat 3", "Cat 4");
		catListView.setOrientation(Orientation.HORIZONTAL);
		catListView.getSelectionModel().selectedItemProperty().addListener(event -> {
			String selected = catListView.getSelectionModel().getSelectedItem();
			lbl_select.setText(selected);
		});
		lbl_select = new Label("Select-a-cat");

		VBox vbox = new VBox(catListView, lbl_select);
		Scene scene = new Scene(vbox);

		return scene;
	}

	private ListView<String> catListViewOut;
	final double WIDTH_4 = 120, HEIGHT_4 = 100;

	public Scene listViewInExample4() {
		// String catArray = {"Cat 1", "Cat 2", "Cat 3", "Cat 4"};
		ArrayList<String> catArray = new ArrayList<String>();
		catArray.add("Cat 1");
		catArray.add("Cat 2");
		catArray.add("Cat 3");
		catArray.add("Cat 4");
		ObservableList<String> strList = FXCollections.observableArrayList(catArray);

		catListView = new ListView<>(strList);
		catListView.setPrefSize(WIDTH_4, HEIGHT_4);

		catListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		catListViewOut = new ListView<>();
		catListView.setPrefSize(WIDTH_4, HEIGHT_4);

		Button btn_select = new Button();
		btn_select.setOnAction(event -> {
			ObservableList<String> selections = catListView.getSelectionModel().getSelectedItems();

			catListViewOut.getItems().setAll(selections);

		});

		HBox hbox = new HBox(10, catListView, catListViewOut);
		VBox vbox = new VBox(hbox, btn_select);
		Scene scene = new Scene(vbox);

		return scene;
	}

	private ListView<ImageView> catListImageView;

	public Scene listViewInExample5() {
		ImageView catView1 = getCatImageView();
		catView1.setPreserveRatio(true);
		catView1.setFitWidth(200);

		ImageView catView2 = getImageView("guiFX/cat2.jpg");
		catView2.setPreserveRatio(true);
		catView2.setFitWidth(200);

		ImageView catView3 = getImageView("guiFX/cat3.jpg");
		catView3.setPreserveRatio(true);
		catView3.setFitWidth(200);

		catListImageView = new ListView<ImageView>();
		catListImageView.getItems().addAll(catView1, catView2, catView3);
		catListImageView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		catListImageView.setPrefSize(250, 600);
		HBox hbox = new HBox(10, catListImageView);
		VBox vbox = new VBox(hbox);
		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	private String[] cat_array = { "Cat 1", "Cat 2", "Cat 3", "Cat 4" };
	private ArrayList<String> cat_arraylist;

	private void initCatArrayList() {
		cat_arraylist = new ArrayList<String>();
		cat_arraylist.add("Cat 1");
		cat_arraylist.add("Cat 2");
		cat_arraylist.add("Cat 3");
		cat_arraylist.add("Cat 4");
	}

	private ObservableList<String> getObservableListOfCats() {
		initCatArrayList();
		ObservableList<String> strList = FXCollections.observableArrayList(cat_arraylist);
		return strList;
	}

	private ArrayList<ImageView> cat_img_arraylist;

	private void initCatImgArrayList() {
		cat_img_arraylist = new ArrayList<ImageView>();
		ImageView catView1 = getCatImageView();
		catView1.setPreserveRatio(true);
		catView1.setFitWidth(200);

		ImageView catView2 = getImageView("guiFX/cat2.jpg");
		catView2.setPreserveRatio(true);
		catView2.setFitWidth(200);

		ImageView catView3 = getImageView("guiFX/cat3.jpg");
		catView3.setPreserveRatio(true);
		catView3.setFitWidth(200);

		cat_img_arraylist.add(catView1);
		cat_img_arraylist.add(catView2);
		cat_img_arraylist.add(catView3);
	}

	private ObservableList<ImageView> getObservableListOfCatImages() {
		initCatImgArrayList();
		ObservableList<ImageView> strList = FXCollections.observableArrayList(cat_img_arraylist);
		return strList;
	}

	public Scene comboBoxExample1() {
		ComboBox<String> cbox_cats = new ComboBox<String>(getObservableListOfCats());

		VBox vbox = new VBox(cbox_cats);
		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	// Bug here somewhere. Does not like repainting the selected image after
	// selection in dropdown menu.
	public Scene comboBoxExample2() {

		ComboBox<ImageView> cbox_cats = new ComboBox<ImageView>(getObservableListOfCatImages());
		ImageView selected = getCatImageView();

		cbox_cats.setOnAction(event -> {
			selected.setImage(cbox_cats.getValue().getImage());
		});

		VBox vbox = new VBox(cbox_cats, selected);
		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	public Scene comboBoxExample3() {

		ComboBox<String> cbox = new ComboBox<String>();
		cbox.setEditable(true);
		cbox.getItems().addAll("England", "Scotland", "Ireland", "Wales");
		VBox vbox = new VBox(cbox);

		Scene scene = new Scene(vbox, 500, 500);

		return scene;

	}

	TextField sliderout;

	public Scene sliderExample1() {

		sliderout = new TextField();
		Slider hslider = new Slider(0.0, 50.0, 25);
		hslider.setShowTickMarks(true);
		hslider.setMajorTickUnit(10);
		hslider.setMinorTickCount(5);
		hslider.setShowTickLabels(true);
		hslider.setPrefWidth(300.0);

		hslider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
			double val = hslider.getValue();
			sliderout.setText("" + val);
		});

		Slider vslider = new Slider(-1.0, 1.0, 0.0);
		vslider.setOrientation(Orientation.VERTICAL);
		vslider.setShowTickMarks(true);
		vslider.setMajorTickUnit(0.25);
		vslider.setMinorTickCount(5);
		vslider.setShowTickLabels(true);
		vslider.setPrefHeight(300.0);

		vslider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
			double val = vslider.getValue();
			sliderout.setText("" + val);
		});

		VBox vbox = new VBox(hslider, sliderout, vslider);
		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	public Scene textAreaExample1() {
		TextArea textarea = new TextArea("Initial sentence");
		textarea.setPrefColumnCount(80);
		textarea.setPrefRowCount(100);

		VBox vbox = new VBox(textarea);

		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	Menu fileMenu;
	Menu textMenu;
	RadioMenuItem blackItem;
	RadioMenuItem redItem;
	RadioMenuItem greenItem;
	CheckMenuItem visibleItem;
	Label out;

	public void menuExample1(Stage primaryStage) {

		MenuBar menuBar = new MenuBar();
		out = new Label("Color");

		buildFileMenu(primaryStage);
		buildTextMenu();
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(textMenu);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setBottom(out);
		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setScene(scene);
	}

	private void buildFileMenu(Stage primaryStage) {
		fileMenu = new Menu("_File");
		MenuItem exitItem = new MenuItem("E_xit");
		fileMenu.getItems().add(exitItem);
		exitItem.setOnAction(event -> {
			primaryStage.close();
		});
	}

	private void buildTextMenu() {
		textMenu = new Menu("Text");

		blackItem = new RadioMenuItem("Black");
		redItem = new RadioMenuItem("Red");
		greenItem = new RadioMenuItem("Green");
		visibleItem = new CheckMenuItem("Visible");

		blackItem.setSelected(true);
		visibleItem.setSelected(true);

		ToggleGroup textToggleGroup = new ToggleGroup();
		blackItem.setToggleGroup(textToggleGroup);
		redItem.setToggleGroup(textToggleGroup);
		greenItem.setToggleGroup(textToggleGroup);

		blackItem.setOnAction(event -> {
			out.setStyle("-fx-text-fill: black");
		});
		redItem.setOnAction(event -> {
			out.setStyle("-fx-text-fill: red");
		});
		greenItem.setOnAction(event -> {
			out.setStyle("-fx-text-fill: green");
		});

		visibleItem.setOnAction(event -> {
			if (out.isVisible()) {
				out.setVisible(false);
			} else {
				out.setVisible(true);
			}
		});

		textMenu.getItems().add(blackItem);
		textMenu.getItems().add(redItem);
		textMenu.getItems().add(greenItem);
		textMenu.getItems().add(new SeparatorMenuItem());
		textMenu.getItems().add(visibleItem);
	}

	public void fileGUI(Stage primaryStage) {
		out = new Label("Color");
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			String filename = selectedFile.getPath();
			out.setText("You selected: " + filename);
		}

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(out);
		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setScene(scene);
	}

	public Scene consoleDebugExample() {

		ComboBox<ImageView> cbox_cats = new ComboBox<ImageView>(getObservableListOfCatImages());
		ImageView selected = getCatImageView();

		cbox_cats.setOnAction(event -> {
			selected.setImage(cbox_cats.getValue().getImage());

			// Debug
			String name = cbox_cats.getValue().getImage().toString();
			System.out.println("Combo box selected: " + name);
		});

		VBox vbox = new VBox(cbox_cats, selected);
		Scene scene = new Scene(vbox, 500, 500);

		return scene;
	}

	public Scene drawingShapesExample() {

		Line line1 = new Line();
		line1.setStartX(0);
		line1.setStartY(0);
		line1.setStartX(200);
		line1.setStartY(200);

		Line line2 = new Line(80, 120, 400, 520);
		line2.setStroke(Color.RED);

		Circle circle = new Circle(200, 200, 150);
		Circle circle2 = new Circle(200, 200, 75, Color.RED);
		Circle circle3 = new Circle(100, 100, 75);
		circle3.setStroke(Color.AQUA);
		circle3.setFill(Color.BISQUE);

		Rectangle rect = new Rectangle(100, 100, 10, 50);
		rect.setFill(Color.PLUM);

		Ellipse ellipse = new Ellipse(320, 240, 140, 100);

		Pane pane = new Pane(circle, circle2, circle3, ellipse, rect, line1, line2);

		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene drawingShapesExample2() {

		Arc arc1 = new Arc(100, 100, 50, 50, 50, 50);
		arc1.setFill(Color.RED);
		arc1.setType(ArcType.ROUND);

		Arc arc2 = new Arc(200, 100, 50, 50, 50, 50);
		arc2.setFill(Color.RED);
		arc2.setType(ArcType.CHORD);

		Arc arc3 = new Arc(300, 100, 50, 50, 100, 100);
		arc3.setFill(Color.RED);
		arc3.setType(ArcType.OPEN); // Bug?

		Pane pane = new Pane(arc1, arc2, arc3);

		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene drawingShapesExample3() {

		Polygon poly1 = new Polygon(100, 20, 20, 100, 100, 100);

		Polyline polyline1 = new Polyline(150, 150, 200, 250, 175, 125, 125, 75);
		Pane pane = new Pane(poly1, polyline1);

		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene drawingText() {

		Text text1 = new Text(10, 10, "Hello World!");

		Pane pane = new Pane(text1);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene rotateExample1() {

		Polygon poly1 = new Polygon(100, 20, 20, 100, 100, 100);

		Polyline polyline1 = new Polyline(150, 150, 200, 250, 175, 125, 125, 75);
		Pane pane = new Pane(poly1, polyline1);
		pane.setRotate(45);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene scaleExample1() {

		Polygon poly1 = new Polygon(100, 20, 20, 100, 100, 100);

		Polyline polyline1 = new Polyline(150, 150, 200, 250, 175, 125, 125, 75);
		Pane pane = new Pane(poly1, polyline1);
		pane.setScaleX(1.5);
		pane.setScaleY(.75);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene translateTransition1() {
		Polygon poly1 = new Polygon(100, 20, 20, 100, 100, 100);
		TranslateTransition ttrans = new TranslateTransition(new Duration(3000), poly1);
		ttrans.setFromX(0);
		ttrans.setFromY(50);
		ttrans.setToX(100);
		ttrans.setToY(50);
		ttrans.play();
		Pane pane = new Pane(poly1);

		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene rotateTransition1() {
		Polygon poly1 = new Polygon(100, 20, 20, 100, 100, 100);

		RotateTransition rtrans = new RotateTransition(new Duration(5000), poly1);

		rtrans.setFromAngle(0.0);
		rtrans.setToAngle(360);
		rtrans.play();
		rtrans.setOnFinished(event -> {
			rtrans.play();
		});
		Pane pane = new Pane(poly1);

		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene scaleTransition1() {
		Circle circ = new Circle(200, 200, 50);

		ScaleTransition strans = new ScaleTransition(new Duration(5000), circ);
		strans.setFromX(1.0);
		strans.setFromY(1.0);
		strans.setToX(2.0);
		strans.setToY(2.0);
		strans.play();

		Pane pane = new Pane(circ);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene strokeTransition1() {
		Circle circ = new Circle(200, 200, 50);

		StrokeTransition strans = new StrokeTransition(new Duration(5000), circ, Color.RED, Color.AZURE);
		strans.play();

		Pane pane = new Pane(circ);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene fillTransition1() {
		Circle circ = new Circle(200, 200, 50);

		FillTransition ftrans = new FillTransition(new Duration(5000), circ, Color.RED, Color.BLUE);
		ftrans.play();

		Pane pane = new Pane(circ);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene fadeTransition1() {
		ImageView cat = getCatImageView();

		FadeTransition ftrans = new FadeTransition(new Duration(5000), cat);
		ftrans.setFromValue(1.0);
		ftrans.setToValue(0.1);
		ftrans.play();

		Pane pane = new Pane(cat);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene interpolate1() {
		ImageView cat = getCatImageView();

		FadeTransition ftrans = new FadeTransition(new Duration(5000), cat);
		ftrans.setFromValue(1.0);
		ftrans.setToValue(0.1);
		// EASE_BOTH
		// EASE_IN
		// EASE_OUT
		// DISCRETE
		// LINEAR
		ftrans.setInterpolator(Interpolator.EASE_BOTH);
		ftrans.play();

		Pane pane = new Pane(cat);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene dropShadow() {
		Circle circ = new Circle(200, 200, 50);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
		dropShadow.setRadius(50);
		// dropShadow.setOffsetX(20);
		// dropShadow.setOffsetY(10);
		circ.setEffect(dropShadow);

		Pane pane = new Pane(circ);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene innerShadow() {
		Circle circ = new Circle(200, 200, 50);

		circ.setFill(Color.RED);
		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setBlurType(BlurType.THREE_PASS_BOX);
		innerShadow.setRadius(40);
		innerShadow.setOffsetX(10);
		innerShadow.setOffsetY(10);
		// innerShadow.setColor(Color.PINK);
		circ.setEffect(innerShadow);

		Pane pane = new Pane(circ);
		Scene scene = new Scene(pane, 500, 550);

		return scene;
	}

	public Scene colorAdjust() {
		ImageView base = getCatImageView();
		ImageView cat = getCatImageView();

		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setHue(0.25);
		colorAdjust.setSaturation(0.5);
		colorAdjust.setBrightness(-0.25);
		colorAdjust.setContrast(0.1);

		cat.setEffect(colorAdjust);

		VBox vbox = new VBox(base, cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene boxBlur1() {
		BoxBlur bblur = new BoxBlur();
		ImageView cat = getCatImageView();
		cat.setEffect(bblur);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene boxBlur2() {
		BoxBlur bblur = new BoxBlur();
		bblur.setWidth(10);
		bblur.setHeight(10);
		ImageView cat = getCatImageView();
		cat.setEffect(bblur);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene boxBlur3() {
		BoxBlur bblur = new BoxBlur();
		bblur.setWidth(10);
		bblur.setHeight(10);
		bblur.setIterations(3);
		ImageView cat = getCatImageView();
		cat.setEffect(bblur);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene gaussianBlur() {
		GaussianBlur gblur = new GaussianBlur();
		gblur.setRadius(20.0);
		ImageView cat = getCatImageView();
		cat.setEffect(gblur);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene sepiaTone() {
		SepiaTone stone = new SepiaTone();

		ImageView cat = getCatImageView();
		cat.setEffect(stone);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene glow() {
		Glow glow = new Glow();
		glow.setLevel(0.5);
		ImageView cat = getCatImageView();
		cat.setEffect(glow);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene reflection1() {
		Reflection refl = new Reflection();
		ImageView cat = getCatImageView();
		cat.setEffect(refl);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene reflection2() {
		Reflection refl = new Reflection();
		refl.setTopOffset(10.0);
		refl.setFraction(0.25);
		refl.setTopOpacity(0.5);
		refl.setBottomOpacity(0.0);
		ImageView cat = getCatImageView();
		cat.setEffect(refl);
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene sound1() {

		File soundFile = new File("src/guiFX/song.mp3");
		Media media = new Media(soundFile.toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);
		ImageView cat = getCatImageView();
		VBox vbox = new VBox(cat);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	public Scene video1() {

		File videoFile = new File("src/guiFX/video.mp4");
		Media media = new Media(videoFile.toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);

		MediaView view = new MediaView(player);
		view.setFitWidth(400);
		view.setFitHeight(500);
		view.setRotate(90);

		VBox vbox = new VBox(view);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox, 500, 550);

		return scene;
	}

	boolean pressed;
	public Scene keyEvent() {
		File videoFile = new File("src/guiFX/video.mp4");
		Media media = new Media(videoFile.toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(() -> {
			player.stop();
			player.play();
		});

		MediaView view = new MediaView(player);
		view.setFitWidth(400);
		view.setFitHeight(500);
		view.setRotate(90);

		VBox vbox = new VBox(view);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox, 500, 550);

		pressed = false;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (!pressed) {
					pressed = true;
					if (arg0.getCode() == KeyCode.SPACE) {
						if (player.getStatus() == MediaPlayer.Status.PLAYING) {
							player.pause();
						} else {
							player.play();
						}
					} 
					//System.out.println("Pause/Play");

				}
			}

		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				pressed = false;
			}

		});
		return scene;

	}
	
	public Scene mouseEvent() {
		out = new Label("Position");
		File videoFile = new File("src/guiFX/video.mp4");
		Media media = new Media(videoFile.toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(() -> {
			player.stop();
			player.play();
		});

		MediaView view = new MediaView(player);
		view.setFitWidth(400);
		view.setFitHeight(500);
		view.setRotate(90);

		HBox hbox = new HBox(view, out);
		hbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(hbox, 500, 550);

		pressed = false;
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				out.setText(event.getX() + " " + event.getY());
			}

		});
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (!pressed) {
					pressed = true;
						if (player.getStatus() == MediaPlayer.Status.PLAYING) {
							player.pause();
						} else {
							player.play();
						}
					//System.out.println("Pause/Play");

				}
			}

		});
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				pressed = false;
			}


		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				out.setText(event.getX() + " " + event.getY());
			}
			
		});
		return scene;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Graphical Noise");

		// primaryStage.setScene(hBox_Example());
		// primaryStage.setScene(vBox_Example());
		// primaryStage.setScene( vBox_Alignment_Example(Pos.CENTER));
		// primaryStage.setScene( hBox_Alignment_Example(Pos.BOTTOM_CENTER));
		// primaryStage.setScene( img_http_example() );
		// primaryStage.setScene( img_scale_example() );
		// primaryStage.setScene( img_padding_example() );
		// primaryStage.setScene(grid_layout());
		// primaryStage.setScene(grid_layout_debug());
		// primaryStage.setScene(grid_layout_gaps());
		// primaryStage.setScene(grid_of_box());
		// primaryStage.setScene(buttonExample());
		// primaryStage.setScene(textFieldExample());
		// primaryStage.setScene(textFieldExampleLambda());
		// primaryStage.setScene(borderPaneExample());
		// primaryStage.setScene(addallremoveExample() );
		// primaryStage.setScene( cssDemo1() );
		// primaryStage.setScene( cssDemo2() );
		// primaryStage.setScene( cssDemo3() );
		// primaryStage.setScene(cssDemo4());
		// primaryStage.setScene(cssDemo5());
		// primaryStage.setScene(cssDemoIDSelector());
		// primaryStage.setScene(radioBtnExample());
		// primaryStage.setScene(chkBxExample());
		// primaryStage.setScene(listViewInExample());
		// primaryStage.setScene(listViewInExample2());
		// primaryStage.setScene(listViewInExample3());
		// primaryStage.setScene(listViewInExample4());
		// primaryStage.setScene(listViewInExample5());
		// primaryStage.setScene(comboBoxExample1() );
		// primaryStage.setScene(comboBoxExample2() );
		// primaryStage.setScene(comboBoxExample3() );
		// primaryStage.setScene(sliderExample1());
		// primaryStage.setScene(textAreaExample1());

		// menuExample1(primaryStage);
		// fileGUI(primaryStage);

		// primaryStage.setScene( consoleDebugExample() );
		// primaryStage.setScene(drawingShapesExample());
		// primaryStage.setScene(drawingShapesExample2());
		// primaryStage.setScene(drawingShapesExample3());
		// primaryStage.setScene(drawingText());

		// primaryStage.setScene(rotateExample1());
		// primaryStage.setScene(scaleExample1());
		// primaryStage.setScene(translateTransition1());
		// primaryStage.setScene(rotateTransition1());
		// primaryStage.setScene(scaleTransition1());
		// primaryStage.setScene(strokeTransition1());
		// primaryStage.setScene(fillTransition1());
		// primaryStage.setScene(fadeTransition1());
		// primaryStage.setScene( interpolate1());
		// primaryStage.setScene(dropShadow());
		// primaryStage.setScene(innerShadow());
		// primaryStage.setScene(colorAdjust());
		// primaryStage.setScene(boxBlur1());
		// primaryStage.setScene(boxBlur2());
		// primaryStage.setScene(boxBlur3());
		// primaryStage.setScene(gaussianBlur());
		// primaryStage.setScene(sepiaTone());
		// primaryStage.setScene(glow());
		// primaryStage.setScene(reflection1());
		// primaryStage.setScene(reflection2());
		// primaryStage.setScene(sound1());
		// primaryStage.setScene(video1());
		// primaryStage.setScene(keyEvent());
		primaryStage.setScene( mouseEvent() );
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		// primaryStage.setScene();
		primaryStage.show();

	}

}
