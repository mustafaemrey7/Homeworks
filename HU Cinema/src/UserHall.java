import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserHall {
	private static String message="";
	public static void display(Stage primaryStage,String hallName,Film film,User user) throws FileNotFoundException {
		Stage window;
		window = primaryStage;
		window.setTitle(DataManager.title);
		window.getIcons().add(DataManager.icon);
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    Label infoLabel = new Label(film.getName()+"("+film.getDuration()+") "+"Hall: "+hallName);
	    Button backButton=new Button("BACK");
	    
	    HBox hBox = new HBox();
	   
	   
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				try {
					UserVideo.display(primaryStage, user, film);		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
	    for(Seat seat:DataManager.seats) {
	    	if(seat.getHallName().equals(hallName)) {
	    		FileInputStream input = new FileInputStream("assets\\icons\\empty_seat.png");
	    		FileInputStream input2 = new FileInputStream("assets\\icons\\reserved_seat.png");
	    		if(seat.getOwner().equals("null")) {
	    		
	    		Image image=new Image(input);
	    		ImageView imageView = new ImageView(image);
	    		imageView.setFitHeight(40);
	    		imageView.setFitWidth(40);
	    		Button seatButton = new Button("",imageView);
	    		seatButton.setPrefSize(50, 50);	 
	    		seatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    			@Override	
	    			public void handle(MouseEvent event) {	
	    				 int t=0;
	    				 for(Hall h:DataManager.halls) {
	    					 if(h.getHallName().equals(hallName)) {
	    						 if(user.getMemberClub()==true) {
	    							 t=h.getPrice()*(100-DataManager.discount_percentage)/100;
	    						 }
	    						 else {
									 t=h.getPrice();
								}
	    						
	    						 
	    					 }
	    					 
	    				 }
	    		
	    					try {		
		    					seat.setOwner(user.getUsername());
	    						String message2 = "Seat "+(seat.getColumn()+1)+ " "+ (seat.getRow()+1)+" has bought succesfly "+t+" TL";
		    					message = message2;
		    					UserHall.display(primaryStage, hallName, film, user);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    			}
	            });

	    		GridPane.setConstraints(seatButton,seat.getColumn(),seat.getRow());
	    		grid.getChildren().add(seatButton);
	    		}
	    		else if (seat.getOwner().equals(user.getUsername())) {
	    			Image image=new Image(input2);
		    		ImageView imageView = new ImageView(image);
		    		imageView.setFitHeight(40);
		    		imageView.setFitWidth(40);
		    		Button seatButton = new Button("",imageView);
		    		seatButton.setPrefSize(50, 50);
		    	
		    		seatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    			@Override			
		    			public void handle(MouseEvent event) {
		    				try {
		    					message = "Seat at "+(seat.getColumn()+1)+"-"+(seat.getRow()+1)+ " is refunded succesfully!";
		    					seat.setOwner("null");
		    					UserHall.display(primaryStage, hallName, film, user);    						
		    				} catch (Exception e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		    			}
		            });
		    		
		    		GridPane.setConstraints(seatButton,seat.getColumn(),seat.getRow());
		    		grid.getChildren().add(seatButton);
	    			
					
				}
	    		
	    		
	    		else {
	    			
	    		Image image=new Image(input2);
	    		ImageView imageView = new ImageView(image);
	    		imageView.setFitHeight(40);
	    		imageView.setFitWidth(40);
	    		Button seatButton = new Button("",imageView);
	    		seatButton.setPrefSize(50, 50);
	    		seatButton.setDisable(true);
	    		seatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    			@Override			
	    			public void handle(MouseEvent event) {
	    				try {
	    					String message = seat.getColumn()+" "+seat.getRow()+ " "+ seat.getOwner();
	    					Label messageLabel = new Label(message);
	    					hBox.getChildren().add(messageLabel);    						
	    				} catch (Exception e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}
	            });
	    		
	    		GridPane.setConstraints(seatButton,seat.getColumn(),seat.getRow());
	    		grid.getChildren().add(seatButton);
	    		}
	    	}
	    }
	    HBox infoHBox = new HBox();
	    infoHBox.getChildren().addAll(infoLabel);
	    HBox bottomBox = new HBox();
	    bottomBox.getChildren().add(backButton);
	    infoHBox.setAlignment(Pos.CENTER);
	    hBox.getChildren().add(message(message));
	    hBox.setAlignment(Pos.CENTER);
	    
	    VBox vBox = new VBox();
	    hBox.setSpacing(150);
	    vBox.setSpacing(30);
	    vBox.getChildren().addAll(infoHBox,grid,hBox,bottomBox);
	    
	    GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
	    Scene scene = new Scene(gridPane,700,850);
		window.setScene(scene);
		window.show();
	}
	private static Label message(String messageString) {
		Label messageLabel = new Label(messageString);
		return messageLabel;
	}
}
