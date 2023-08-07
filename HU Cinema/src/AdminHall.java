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

public class AdminHall {
	private static String message="";
	private static String customer ="";
	static Label status = new Label("");
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
	    ChoiceBox<String> choiceBox = new ChoiceBox<String>();
	    for (User u : DataManager.persons) {
	    	choiceBox.getItems().add(u.getUsername());
		}
	    try {
	    	if(customer.equals("")) {	choiceBox.setValue(DataManager.persons.get(0).getUsername());}
	    	else {choiceBox.setValue(customer);}
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				try {
				
					AdminVideo.display(primaryStage, user, film);	
				
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
	    				 customer=choiceBox.getValue();
	    				 int t=0;
	    				 User selectedUser = null;
	    				 for(User u:DataManager.persons) {
	    					 if(u.getUsername().equals(choiceBox.getValue())) {
	    						 selectedUser=u;
	    					
	    						 continue;
	    					 }
	    					 
	    				 }
	    				 for(Hall h:DataManager.halls) {
	    					 if(h.getHallName().equals(hallName)) {
	    						 if(selectedUser.getMemberClub()==true) {
	    							 t=(h.getPrice()*(100-DataManager.discount_percentage)/100);
	    							
	    						 }
	    						 	
	    						 else {
									 t=h.getPrice();}}}
	    		
	    					try {		
		    					seat.setOwner(choiceBox.getValue());
	    						String message2 = "Seat at "+(seat.getColumn()+1)+ "-"+ (seat.getRow()+1)+" is bought for "+choiceBox.getValue()+" for "
	    								+t+" TL succesfully!";
		    					message = message2;
		    					AdminHall.display(primaryStage, hallName, film, user);
								} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								}
	    				}
		    		});
		    		seatButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    			@Override			
		    			public void handle(MouseEvent event) {
		    					
		    					status.setText("Not bought yet!");

		    			}
		            });

	    		GridPane.setConstraints(seatButton,seat.getColumn(),seat.getRow());
	    		grid.getChildren().add(seatButton);
	    		}	    		
	    		else {
	    		customer=choiceBox.getValue();	
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
	    					message = "Seat at "+(seat.getColumn()+1)+ "-"+ (seat.getRow()+1)+" is refunded succesfully!";
	    					seat.setOwner("null");
	    					display(primaryStage, hallName, film, user);
	    				   						
	    				} catch (Exception e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}
	            });
	    		seatButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
	    			@Override			
	    			public void handle(MouseEvent event) {
	    				 int t=0;
	    				 User selectedUser = null;
	    				 for(User u:DataManager.persons) {
	    					 if(u.getUsername().equals(choiceBox.getValue())) {
	    						 selectedUser=u;
	    						 
	    						 continue;
	    					 }
	    				 } 
	    				 for(Hall h:DataManager.halls) {if(h.getHallName().equals(hallName)) {
	    					 if(selectedUser.getMemberClub()==true) {
    							 t=(h.getPrice()*(100-DataManager.discount_percentage)/100);
    							
    						 }
    						 	
    						 else {
								 t=h.getPrice();}
	    					 
	    				 }}
	    			
	    					status.setText("Bought by "+seat.getOwner()+" for "+t+" TL succesfully!");
	    			
	    			}});
	    		
	    		GridPane.setConstraints(seatButton,seat.getColumn(),seat.getRow());
	    		grid.getChildren().add(seatButton);
	    		}
	    	}
	    }
	    HBox bottomBox = new HBox();
	    bottomBox.getChildren().addAll(backButton);
	    HBox airBox = new HBox();
	    airBox.getChildren().add(status);
	    HBox hbox2 = new HBox();
	    HBox infoBox = new HBox();
	    infoBox.getChildren().add(infoLabel);
	    infoBox.setAlignment(Pos.CENTER);
	    hbox2.getChildren().addAll(choiceBox);
	    hbox2.setAlignment(Pos.CENTER);
	    hBox.getChildren().add(message(message));
	    VBox vBox = new VBox();
	    airBox.setAlignment(Pos.CENTER);
	    vBox.setSpacing(20);
	    hBox.setAlignment(Pos.CENTER);
	    vBox.getChildren().addAll(infoBox,grid,hbox2,airBox,hBox,bottomBox);
	    GridPane gridPane=new GridPane();
		gridPane.getChildren().add(vBox);
		gridPane.setAlignment(Pos.CENTER);
	    Scene scene = new Scene(gridPane,750,850);
		window.setScene(scene);
		window.show();
	}
	private static Label message(String messageString) {
		Label messageLabel = new Label(messageString);
		return messageLabel;
	}
}
