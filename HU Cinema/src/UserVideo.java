import java.io.File;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserVideo {
	static boolean playTime=true;
	
	public static void display(Stage primaryStage,User user,Film filmName) {
		
		Stage window ;
	
	
		
		Slider volumeSlider = new Slider();
	
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		String videoPath = "assets\\trailers\\"+filmName.getPath();
		Media media = new Media(new File(videoPath).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		MediaView mediaView = new MediaView(mediaPlayer);
		
		Button btnPlayOrPause = new Button(">");
		Label errorLabel = new Label("");
		Button btnBack5s = new Button("<<");
		Button btnReset = new Button("|<<");
		Button btnNext5s = new Button(">>");
		Button btnBack = new Button("<BACK");
		Button btnOk = new Button("OK");
		btnBack5s.setPrefSize(50, 25);
		btnReset.setPrefSize(50, 25);
		btnNext5s.setPrefSize(50, 25);
		
		btnOk.setPrefSize(50, 25);
		btnPlayOrPause.setPrefSize(50, 25);
		
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		
		for (Hall hall : DataManager.halls) {
			if(hall.getFilmName().equals(filmName.getName())) {
				
				choiceBox.getItems().add(hall.getHallName());
				choiceBox.setValue(hall.getHallName());
			}
		}
		
		
		volumeSlider.setValue(mediaPlayer.getVolume()*100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				mediaPlayer.setVolume(volumeSlider.getValue()/100);
				
			}
		});
		btnOk.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Boolean isThereHall=false;
				for(Hall h:DataManager.halls) {
					if(h.getFilmName().equals(filmName.getName())) {
						isThereHall=true;
					}
				}
				
				if(isThereHall==true) {
				try {
					mediaPlayer.pause();
					UserHall.display(primaryStage, choiceBox.getValue(),filmName,user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				else {
					errorLabel.setText("There is no hall for selected Film!");
					
				}
			}
        });
		
		btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				try {
					mediaPlayer.pause();
					UserScene.display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		btnBack5s.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				
				try {
					mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-5)));;
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		btnNext5s.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				
				try {
					mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5)));;
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		btnReset.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				
				
				try {
					mediaPlayer.seek(mediaPlayer.getStartTime());;
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		btnPlayOrPause.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				try {
					if(playTime==true) {
						mediaPlayer.play();
						playTime=false;
						btnPlayOrPause.setText("||");
					}
					else {
						mediaPlayer.pause();
						playTime = true;
						btnPlayOrPause.setText(">");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
		
		HBox hBox = new HBox();
		HBox hBox1 = new HBox();
		HBox hBox3 = new HBox();
		VBox vBox1 = new VBox();
		hBox3.getChildren().add(errorLabel);
		hBox3.setAlignment(Pos.CENTER);
		volumeSlider.setOrientation(Orientation.VERTICAL);
		volumeSlider.setPrefHeight(80);
		vBox1.getChildren().addAll(btnPlayOrPause,btnNext5s,btnBack5s,btnReset,volumeSlider);
		hBox1.getChildren().addAll(btnBack,choiceBox,btnOk);
		mediaView.setFitHeight(300);
		mediaView.setFitWidth(500);
		hBox.getChildren().addAll(mediaView,vBox1);
		VBox vBox = new VBox();
		hBox.setSpacing(25);
		hBox1.setSpacing(80);
		vBox1.setSpacing(30);
		vBox.setSpacing(40);
		vBox.getChildren().addAll(hBox,hBox1,hBox3);
		vBox.setPadding(new Insets(10,10,10,10));
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane,700,450);
		mediaPlayer.pause();
		window.setScene(scene);
		window.show();
				
	}
}
