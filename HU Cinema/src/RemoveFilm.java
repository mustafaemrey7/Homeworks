

import java.sql.DatabaseMetaData;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RemoveFilm {

	public static void display(Stage primaryStage,User user) throws Exception {
		Stage window ;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		GridPane grid =  new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(1);
		Label infoLabel = new Label("Select the film that you desire to remove and then click OK");
		Label infoLabel1 = new Label("");
		GridPane.setConstraints(infoLabel, 0, 0);
		
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();
		
		for (Film film : DataManager.films) {
			choiceBox.getItems().add(film.getName());
		}
		if(DataManager.films.size()==0) {}
		else {choiceBox.setValue(DataManager.films.get(0).getName());}
		GridPane.setConstraints(choiceBox, 0,1);
		
		
	
		
		Button backButton = new Button("BACK");
		GridPane.setConstraints(backButton, 1,2);
		Button okButton = new Button("OK");
		GridPane.setConstraints(okButton, 1,1);
		
	
		
		
		GridPane.setConstraints(infoLabel1, 0, 2);
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
				Film choseFilm=null;
				if(DataManager.films.size()==0){
					infoLabel1.setText("There is no film selected!");

				}
				else{
				for (Film film : DataManager.films) {
					if(film.getName().equals(choiceBox.getValue())) {
						choseFilm=film;
					}
				}
				for(int i = 0;i<DataManager.halls.size();i++) {
					if(DataManager.halls.get(i).getFilmName().equals(choseFilm.getName())) {
						DataManager.halls.remove(DataManager.halls.get(i));
					}
				}
				
				
				try {
					
					DataManager.films.remove(choseFilm);
					display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
        });
		
		
		
		
		grid.getChildren().addAll(infoLabel,backButton,choiceBox,okButton);
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid,500,200);
		
		window.setScene(scene);
		window.show();
	}

	


}
