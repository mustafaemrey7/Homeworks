import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditUsers {
	
	
	@SuppressWarnings("unchecked")
	public static void display(Stage primaryStage,User user) {
		TableView<User> table = new TableView<>();
		Stage window = primaryStage;
		window.getIcons().add(DataManager.icon);
		window.setTitle(DataManager.title);
		TableColumn<User, String> userNameColumn = new TableColumn<>("Username");
		userNameColumn.setMinWidth(200);
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		TableColumn<User, Boolean> clubMemberColumn = new TableColumn<>("Club Member");
		clubMemberColumn.setMinWidth(200);
		clubMemberColumn.setCellValueFactory(new PropertyValueFactory<>("memberClub"));
		TableColumn<User, Boolean> adminColumn = new TableColumn<>("Admin");
		adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));
		adminColumn.setMinWidth(200);
		
		table.setItems(getUser(user));
		table.getColumns().addAll(userNameColumn,clubMemberColumn,adminColumn);
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		Button clubButton = new Button("Promote/Demote Club Member");
		Button adminButton = new Button("Promote/Demote Admin");
		Button backButton = new Button("Back");
		hBox.setSpacing(10);
		vBox.setSpacing(15);
		
		
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
		clubButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				User selectedUser = table.getSelectionModel().getSelectedItem();
				try {
					if(selectedUser.getMemberClub()==true) {
						selectedUser.setMemberClub(false);
					}
					else {
						selectedUser.setMemberClub(true);
					}
					display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		adminButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				User selectedUser = table.getSelectionModel().getSelectedItem();
				try {
					if(selectedUser.getAdmin()==true) {
						selectedUser.setAdmin(false);
					}
					else {
						selectedUser.setAdmin(true);
					}
					display(primaryStage, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
		hBox.getChildren().addAll(backButton,clubButton,adminButton);
		vBox.getChildren().addAll(table,hBox);
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	
	}
	
	public static ObservableList<User> getUser(User user){
		ObservableList<User> users = FXCollections.observableArrayList();
		for(User u:DataManager.persons) {
			if(!user.getUsername().equals(u.getUsername())) {
				users.add(u);
			}
		}
		return users;	
	}
	

	
	
	
	
	
	
	
}
