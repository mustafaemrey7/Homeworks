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

public class RemoveHall {
	public static void display(Stage primaryStage,User user,Film f) throws Exception {
		Stage window ;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		GridPane grid =  new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(1);
		Label infoLabel = new Label("Select the Hall that you desire to remove and then click OK");
		ChoiceBox<String> choiceBox = new ChoiceBox<String>();	
		for (Hall hall : DataManager.halls) {
			if(hall.getFilmName().equals(f.getName())) {
					choiceBox.getItems().add(hall.getHallName());
					choiceBox.setValue(hall.getHallName());
			}
		}	
		Button backButton = new Button("BACK");
		Button okButton = new Button("OK");

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
				Hall choseHall=null;
				for (Hall H: DataManager.halls) {
					if(H.getHallName().equals(choiceBox.getValue())) {
						choseHall=H;
					}
				}
				try {
					DataManager.halls.remove(choseHall);
					display(primaryStage, user,f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		HBox hBox = new HBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		VBox vBox = new VBox();
		hBox.getChildren().add(infoLabel);
		
		hBox1.getChildren().addAll(choiceBox,okButton);
		hBox1.setSpacing(40);
		
		hBox2.getChildren().add(backButton);
		vBox.getChildren().addAll(hBox,hBox1,hBox2);
		vBox.setAlignment(Pos.CENTER);
		hBox.setAlignment(Pos.CENTER);
		grid.getChildren().addAll(vBox);
		grid.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		Scene scene = new Scene(grid,500,200);
		
		window.setScene(scene);
		window.show();
	}

	



}
