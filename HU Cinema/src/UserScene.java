import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserScene {
	
	public static void display(Stage primaryStage,User user) throws Exception {
		Stage window ;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		String extraString="";
		if (user.getMemberClub()==true) {extraString="(Club Member)";}
		Label infoLabel = new Label("Welcome "+user.getUsername()+" "+extraString+"!\nSelect a film and then click OK to continue.");
		
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		
		for (Film film : DataManager.films) {
			choiceBox.getItems().add(film.getName());
		}
		if(DataManager.films.size()==0) {}
		else {choiceBox.setValue(DataManager.films.get(0).getName());}
		
	
		
		Button logOutButton = new Button("LOG OUT");
		
		Button okButton = new Button("OK");
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(choiceBox,okButton);
	
		
	
		
		
		
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
				Film choseFilm=null;
				for (Film film : DataManager.films) {
					if(film.getName().equals(choiceBox.getValue())) {
						choseFilm=film;
					}
				}
				
				
				try {
					UserVideo.display(primaryStage,user,choseFilm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		VBox vBox = new VBox();
		hBox.setSpacing(20);
		vBox.getChildren().addAll(infoLabel,hBox,logOutButton);
		vBox.setSpacing(30);
		vBox.setPadding(new Insets(10,50,10,50));
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane,550,300);
		window.setScene(scene);
		window.show();
	
	}

	

}
