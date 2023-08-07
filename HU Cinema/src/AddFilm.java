import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class AddFilm {
	static Label errorLabel = new Label("");
	public static void display(Stage primaryStage,User user) {
		Stage window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		Label titleLabel = new Label("Please give name,relative path of the trailer and duration of the film.");
		Label nameLabel = new Label("Name:");
		
		Label trailerLabel = new Label("Trailer(Path)");
		Label durationLabel = new Label("Duration(m)");
		TextField nameInput = new TextField();
		TextField trailerInput = new TextField();
		TextField durationInput = new TextField();
		Button backButton = new Button("BACK");
		Button okButton = new Button("OK");
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		HBox hBox4 = new HBox();
		HBox hBox5 = new HBox();
		VBox vBox = new VBox();
		
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				try {
					AdminScene.display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String type="";
				try {
					type = addFillm(nameInput.getText(), trailerInput.getText(), durationInput.getText());
					if(type.equals("go")) {
						DataManager.films.add(new Film(nameInput.getText(), trailerInput.getText(), durationInput.getText()));
						errorLabel.setText("The film succesfully added!");
						
					}
					else  {
						
						errorLabel.setText(type);
						Login.errorPlayer();
					}
					display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
		
		hBox1.getChildren().addAll(nameLabel,nameInput);
		hBox2.getChildren().addAll(trailerLabel,trailerInput);
		hBox3.getChildren().addAll(durationLabel,durationInput);
		hBox4.getChildren().addAll(backButton,okButton);
		hBox5.getChildren().add(errorLabel);
		vBox.getChildren().addAll(titleLabel,hBox1,hBox2,hBox3,hBox4,hBox5);
		
		vBox.setSpacing(20);
		hBox1.setSpacing(50);
		hBox2.setSpacing(10);
		hBox3.setSpacing(10);
		hBox4.setSpacing(100);
		vBox.setAlignment(Pos.CENTER);
		hBox1.setAlignment(Pos.CENTER);
		hBox2.setAlignment(Pos.CENTER);
		hBox3.setAlignment(Pos.CENTER);
		hBox4.setAlignment(Pos.CENTER);
		hBox5.setAlignment(Pos.CENTER);
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(10,10,10,10));
		Scene scene = new Scene(gridPane,500,300);
		window.setScene(scene);
		window.show();
		
	}
	private static String addFillm(String name,String path,String duration) {
		Boolean isConvertInt = true;
		String errorMessagae="go";
		if(name.equals("")) {
			errorMessagae ="Username is not will be space";
		}
		else if (path.equals("")) {
			errorMessagae ="Path is not will be space";
		}
		try {
			Integer.valueOf(duration);
		} catch (Exception e) {
			isConvertInt = false;
			errorMessagae="Duration must be positive Integer";
		}
		try {
			String videoPath = "assets\\trailers\\"+path;
			String videoPath1 = videoPath;
			Media media = new Media(new File(videoPath1).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			
		} catch (Exception e) {
			errorMessagae="Path couldn't find";
		}
		
		return errorMessagae;
		
	}
	

}
