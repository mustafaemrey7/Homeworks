import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUp {
	private static String message;
	private static Label messageLabel=new Label("");
	public static void display(Stage primaryStage) throws Exception {
		Stage window ;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		Label nameLabel = new Label("Username");
		
		TextField nameInput = new TextField();
	
	
		Label passwordLabel = new Label("Password");
		Label infoLabel1 = new Label("Welcome to the HUCS Cinema Reservation System!");
		Label infoLabel2 = new Label("Fill the form below to create a new account.");
		Label infoLabel3 = new Label("You can go to Log In page by clicking LOG IN Button.");
	
		PasswordField passwordInput = new PasswordField();
		passwordInput.setMaxSize(200, 80);
		
		Label passwordLabel2 = new Label("Password:");
		
		PasswordField passwordInput2 = new PasswordField();
		passwordInput.setMaxSize(200, 80);
		
		
		Button loginButton = new Button("LOG IN");
	
		Button signUpButton = new Button("SIGN UP");
	
		HBox hBox = new HBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		HBox hBox4 = new HBox();
		
		hBox.getChildren().addAll(nameLabel,nameInput);
		hBox1.getChildren().addAll(passwordLabel,passwordInput);
		hBox2.getChildren().addAll(passwordLabel2,passwordInput2);
		hBox3.getChildren().addAll(loginButton,signUpButton);
		
		
		signUpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				boolean go = true;
				for(User u:DataManager.persons) {
					if(u.getUsername().equals(nameInput.getText())) {
						
						messageLabel.setText("Error: This username already exists!");
						go = false;
					}
				}
				if(!passwordInput.getText().equals(passwordInput2.getText())) {go=false;messageLabel.setText("Error: Passwords do not match!");
				}
				if(passwordInput.getText().equals("")) {messageLabel.setText("Error: Password cannot be empty!");go=false;}
				if(nameInput.getText().equals("")) {messageLabel.setText("Error: Username cannot be empty!");go=false;}
				if(go==true) {
					DataManager.persons.add(new User(nameInput.getText(),Login.hashPassword( passwordInput.getText()) , false, false));
					
					messageLabel.setText("SUCCESS: You have succesfully registered with your new credentials!");
				}
				else {
					Login.errorPlayer();
				}
				
				hBox4.getChildren().add(message(message));
				
			}
        });
		
		
		loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		VBox vBox = new VBox();
		HBox iBox1=new HBox();
		HBox iBox2=new HBox();
		HBox iBox3=new HBox();
		VBox iBox = new VBox();
		hBox4.getChildren().addAll(messageLabel);
		
		hBox4.setAlignment(Pos.CENTER);
		iBox.getChildren().addAll(iBox1,iBox2,iBox3);
		iBox1.getChildren().add(infoLabel1);
		iBox2.getChildren().add(infoLabel2);
		iBox3.getChildren().add(infoLabel3);
		iBox1.setAlignment(Pos.CENTER);
		iBox2.setAlignment(Pos.CENTER);
		iBox3.setAlignment(Pos.CENTER);
		hBox.setAlignment(Pos.CENTER);
		hBox1.setAlignment(Pos.CENTER);
		hBox2.setAlignment(Pos.CENTER);
		hBox3.setAlignment(Pos.CENTER);
	
		hBox.setSpacing(25);
		hBox1.setSpacing(30);
		hBox2.setSpacing(30);
		hBox3.setSpacing(130);
		vBox.setSpacing(15);
		vBox.getChildren().addAll(iBox,hBox,hBox1,hBox2,hBox3,hBox4);
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(10,10,10,10));
		Scene scene = new Scene(gridPane,400,300);
		window.setScene(scene);
		window.show();

	
	}
	private static Label message(String messageString) {
		Label messageLabel = new Label(messageString);
		return messageLabel;
	}
	
	
}
