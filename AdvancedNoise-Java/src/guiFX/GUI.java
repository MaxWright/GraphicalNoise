package guiFX;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	 * Alignments
	 * Pos.TOP_LEFT		Pos.TOP_CENTER		Pos.TOP_RIGHT
	 * Pos.CENTER_LEFT	Pos.CENTER			Pos.CENTER_RIGHT
	 * Pos.BOTTOM_LEFT	Pos.BOTTOM_CENTER	Pos.BOTTOM_RIGHT
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
			image = new Image("https://raw.githubusercontent.com/UWB-Biocomputing/BrainGrid/master/docs/images/CheckMavenVersion.png");
			ImageView imageView = new ImageView(image);
			hBox = new HBox(imageView);
		} catch (NullPointerException e)  {
			hBox = error("URL is null");
		} catch (IllegalArgumentException e){
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
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Graphical Noise");
		
		//primaryStage.setScene(hBox_Example());
		//primaryStage.setScene(vBox_Example());
		//primaryStage.setScene( vBox_Alignment_Example(Pos.CENTER));
		//primaryStage.setScene( hBox_Alignment_Example(Pos.BOTTOM_CENTER));
		//primaryStage.setScene( img_http_example() );
		primaryStage.setScene( img_scale_example() );
		
		primaryStage.show();
		
	}

}