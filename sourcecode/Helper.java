import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Helper extends Application{
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Parent rootParent = FXMLLoader.load(getClass().getResource("get_help_page.fxml"));
		
		Scene scene = new Scene(rootParent);
		stage.setTitle("Help Page");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

}
