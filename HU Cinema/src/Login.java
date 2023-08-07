import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import  javafx.scene.media.MediaPlayer;
import javafx.scene.Scene;

public class Login {
	private static int errorTime;
	private static String errorMessage ="";
	private static Label messageLabel=new Label("");

	public static void display(Stage primaryStage) throws Exception {
		errorTime=0;
		Stage window;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		
	    
		Label titleLabel = new Label("Welcome to the HUCS Cinema Reservation System!");
		Label titleLabel2 = new Label("Please enter your credintials below and click LOGIN");
		Label titleLabel3 = new Label("You can create a new account by clicking SIGN UP button");
		Label nameLabel = new Label("Username:");
		TextField nameInput = new TextField();
		Label passwordLabel = new Label("Password:");
										
		PasswordField passwordInput = new PasswordField();
		Button loginButton = new Button("LOG IN");
		Button signUpButton = new Button("SIGN UP");
		VBox vBox1 = new VBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		HBox hBox4 = new HBox();
		vBox1.getChildren().addAll(titleLabel,titleLabel2,titleLabel3);
		hBox1.getChildren().addAll(nameLabel,nameInput);
		hBox2.getChildren().addAll(passwordLabel,passwordInput);
		hBox3.getChildren().addAll(signUpButton,loginButton);
		hBox1.setSpacing(50);
		hBox2.setSpacing(50);
		hBox3.setSpacing(160);
	
		signUpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					
					try {
						SignUp.display(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        });
		loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				boolean userFound=false;
				
				
				for(User user:DataManager.persons) {
					if(user.getUsername().equals(nameInput.getText())) {
					
						
						if(user.getPassword().equals(hashPassword(passwordInput.getText()))) {
							
							userFound=true;
							if(user.getAdmin()==true) {
								try {
								AdminScene.display(primaryStage,user);
								} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}}
							else  {
								try {
									UserScene.display(primaryStage, user);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
				}	
				if(userFound==false) {
					errorTime++;
					passwordInput.clear();
					messageLabel.setText("Error: There is no such credintial!");
					errorPlayer();
					if(errorTime==DataManager.maxError) {
						messageLabel.setText("ERROR: Please wait until end of the "+DataManager.block_time+" seconds to make a new operation!");
						errorTime=0;
						
					}
					
				}
			}
        });
		HBox hBox5 = new HBox();
		
		hBox5.getChildren().addAll(messageLabel);
		hBox5.setAlignment(Pos.CENTER);
		VBox vBox = new VBox();
		GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(vBox1,hBox1,hBox2,hBox3,hBox5);
		vBox.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(gridPane,520,280);
		
		window.setScene(scene);
		window.show();
	}
	public static boolean checkLogin(String username,String password) {
		
		boolean go = false;
		for(User user:DataManager.persons) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					go = true;
				}
			}
		}
		return go;
	}
	
	public static String hashPassword ( String  password ) {
		byte[] bytesOfPassword = password . getBytes (StandardCharsets.UTF_8) ;
		byte[] md5Digest = new byte [0] ;
		try {
		md5Digest = MessageDigest.getInstance( "MD5" ).digest(bytesOfPassword ) ;
		} catch (NoSuchAlgorithmException e) {
		return null ;
		}
		return Base64.getEncoder( ).encodeToString ( md5Digest ) ;
		}
	
	public static void errorPlayer() {
		try {
			Media media = new Media(new File("assets\\effects\\error.mp3").toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setAutoPlay(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	private static Label message(String messageString) {
		Label messageLabel = new Label(messageString);
		return messageLabel;
	}
	
		
	
	
	
	   

}
