package application;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuController {

	@FXML
	public void onStartPressed() throws IOException{
		Parent game = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene gameScene = new Scene(game);
		gameScene.getRoot().requestFocus();                  
		Main.currentStage.setScene(gameScene);
	}
	@FXML
	public void onExitPressed() {
		Main.currentStage.close();
	}

}
