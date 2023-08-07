import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddHall {
	private static String message;

	public static void display(Stage primaryStage,Film film,User user) {
		Stage window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		Label titleLabel = new Label(film.getName()+"("+film.getDuration()+")");
		Label rowLabel = new Label("Row:");
		Label columnLabel = new Label("Column");
		Label nameLabel = new Label("Name: ");
		Label priceLabel = new Label("Price");
		TextField nameInput = new TextField();
		TextField priceInput = new TextField();
		Button backButton = new Button("BACK");
		Button okButton = new Button("OK");
		ChoiceBox<Integer> choiceBoxRow = new ChoiceBox<>();
		for(int i =3;i<11;i++) {
			choiceBoxRow.getItems().add(i);
		}
		choiceBoxRow.setValue(3);
		ChoiceBox<Integer> choiceBoxColumn = new ChoiceBox<>();
		for(int i =3;i<11;i++) {
			choiceBoxColumn.getItems().add(i);
		}
		choiceBoxColumn.setValue(3);
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
					AdminVideo.display(primaryStage, user,film);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				try {
					if(checkHall(nameInput.getText(), priceInput.getText()).equals("go")) {
						DataManager.halls.add(new Hall(film.getName(),nameInput.getText(),Integer.valueOf(priceInput.getText()),
								choiceBoxRow.getValue(),choiceBoxColumn.getValue()));
						message = "Succes:Hall successfully created!";
						createSeat(film.getName(), nameInput.getText(), choiceBoxRow.getValue(),
									choiceBoxColumn.getValue(), priceInput.getText());
						display(primaryStage, film, user);
					}
					else {
						message = checkHall(nameInput.getText(), priceInput.getText());
						display(primaryStage, film, user);
					}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		HBox hBox6 = new HBox();
		Label messageLabel= new Label(message);
		hBox6.getChildren().addAll(messageLabel);
		
		
		hBox1.setSpacing(10);
		hBox2.setSpacing(10);
		hBox3.setSpacing(10);
		hBox4.setSpacing(10);
		hBox5.setSpacing(50);
		hBox1.getChildren().addAll(rowLabel,choiceBoxRow);
		hBox2.getChildren().addAll(columnLabel,choiceBoxColumn);
		hBox3.getChildren().addAll(nameLabel,nameInput);
		hBox4.getChildren().addAll(priceLabel,priceInput);
		hBox5.getChildren().addAll(backButton,okButton);
		vBox.getChildren().addAll(titleLabel,hBox1,hBox2,hBox3,hBox4,hBox5,hBox6);
		
		
		
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(10,10,10,10));
		Scene scene = new Scene(gridPane,300,400);
		window.setScene(scene);
		window.show();
			
	}
	private static String checkHall(String name,String price) {
		String st="go";
		if(name.equals("")) {
			st="Error:Hall name could not be empty";
		}
		if(price.equals("")) {
			st="Error:Price could not be empty";
		}
		for(Hall h:DataManager.halls){
			if(h.getHallName().equals(name)) {
				st="This Hall name already exist.";
			}
		}
		try {
			Integer.valueOf(price);
		} catch (Exception e) {
			st="Error: Price could be positive integer.";
		}
		return st;
		
	}
	private static void createSeat(String filmName,String hallName,int row,int column,String price) {
		
		for(int i=0;i<row;i++) {
			for(int k = 0;k<column;k++) {
				DataManager.seats.add(new Seat(filmName, hallName, i, k, "null", Integer.valueOf(price)));
			}
		}
	}
	
}
