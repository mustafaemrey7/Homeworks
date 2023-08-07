import java.io.File;

import javax.xml.transform.ErrorListener;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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

public class AdminVideo {
	static boolean playTime=true;
	
	public static void display(Stage primaryStage,User user,Film filmName) {
		
		Stage window ;
		Slider volumeSlider = new Slider();
		Label errorLabel=new Label("");
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		String videoPath = "assets\\trailers\\"+filmName.getPath();
		Media media = new Media(new File(videoPath).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		MediaView mediaView = new MediaView(mediaPlayer);
		Label infoLabel = new Label((filmName.getName()+"("+filmName.getDuration()+" minutes)"));
		Button btnPlayOrPause = new Button(">");
		
		Button btnBack5s = new Button("<<");
		Button btnReset = new Button("|<<");
		Button btnNext5s = new Button(">>");
		Button btnBack = new Button("<BACK");
		Button btnOk = new Button("OK");
		Button addButton = new Button("Add Hall");
		Button removeButton = new Button("Remove Hall");
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
		removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					RemoveHall.display(primaryStage, user,filmName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
		addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					AddHall.display(primaryStage,filmName,user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			
					AdminHall.display(primaryStage, choiceBox.getValue(),filmName,user);
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
					AdminScene.display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		btnBack5s.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				System.out.println("girdim");
				
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
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();
		hBox2.getChildren().add(infoLabel);
		hBox2.setAlignment(Pos.CENTER);
		hBox3.getChildren().add(errorLabel);
		hBox3.setAlignment(Pos.CENTER);
		
		volumeSlider.setPrefHeight(180);
		vBox1.getChildren().addAll(btnPlayOrPause,btnNext5s,btnBack5s,btnReset,volumeSlider);
		hBox1.getChildren().addAll(btnBack,addButton,removeButton,choiceBox,btnOk);
		mediaView.setFitHeight(400);
		mediaView.setFitWidth(600);
		
		
		vBox2.getChildren().addAll(infoLabel,mediaView,hBox1,hBox3);
		vBox2.setSpacing(20);
		hBox.setSpacing(25);
		hBox1.setSpacing(50);
		hBox.getChildren().addAll(vBox2,vBox1);
		volumeSlider.setOrientation(Orientation.VERTICAL);
		vBox1.setSpacing(30);
		hBox.setSpacing(20);
		
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(hBox);
		gridPane.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10,10,10,10));
		Scene scene = new Scene(gridPane,900,500);
		mediaPlayer.pause();
		window.setScene(scene);
		window.show();
		
		
		
	
		
					
	}
	
	

}
