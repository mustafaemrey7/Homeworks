import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminScene {
	public static void display(Stage primaryStage,User user) throws Exception {
		Stage window ;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		String extraString="(Admin)";

		if (user.getMemberClub()==true) {extraString="(Admin-Club Member)";}
		Label infoLabel = new Label("Welcome "+user.getUsername()+" "+extraString+"!\nSelect a film and then click OK to continue.");
		Label errorLabel = new Label("");
	
		
		
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		
		boolean isThereFilm=false;
		for (Film film : DataManager.films) {
			choiceBox.getItems().add(film.getName());
		}
		if(DataManager.films.size()==0) {isThereFilm=true;}
		else {choiceBox.setValue(DataManager.films.get(0).getName());}
			
		Button logOutButton = new Button("LOG OUT");	
		Button okButton = new Button("OK");	
		Button addButton = new Button("Add Film");	
		Button removeButton = new Button("Remove Film");	
		Button editButton = new Button("Edit Users");	
		HBox hBox = new HBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		
		hBox.getChildren().addAll(choiceBox,okButton);
		hBox1.getChildren().addAll(addButton,removeButton,editButton);
		hBox2.getChildren().addAll(logOutButton);
		
		
		editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					EditUsers.display(primaryStage,user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					RemoveFilm.display(primaryStage, user);
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
					AddFilm.display(primaryStage,user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		
	
		
		logOutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Login.display(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if(DataManager.films.size()==0) {
					errorLabel.setText("There is no film!");
					
				}
				else{
				
				Film choseFilm=null;
				for (Film film : DataManager.films) {
					if(film.getName().equals(choiceBox.getValue())) {
						choseFilm=film;
					}
				}
				
				
				try {
					AdminVideo.display(primaryStage,user,choseFilm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
        });
		VBox vBox = new VBox();
		hBox.setSpacing(25);
		hBox1.setSpacing(30);
		hBox2.setSpacing(30);
		vBox.setSpacing(30);
		vBox.getChildren().addAll(infoLabel,hBox,hBox1,hBox2,errorLabel);
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(10,50,10,50));
		
		Scene scene = new Scene(gridPane,550,300);
		
		window.setScene(scene);
		window.show();
	}
	
}
