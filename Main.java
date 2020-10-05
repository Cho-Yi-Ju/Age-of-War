import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static Stage currentStage;
	public static Scene menuScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		currentStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		menuScene = new Scene(root);
		
		currentStage.setTitle("Age of War");
		currentStage.setScene(menuScene);
		currentStage.show();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
