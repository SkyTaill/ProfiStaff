import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("DataBaseInterface.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("DB");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
