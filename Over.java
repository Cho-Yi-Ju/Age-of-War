package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Over implements Initializable{
	
	@FXML
	public void onBackPressed() {
		Main.currentStage.setScene(Main.menuScene);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	

}
