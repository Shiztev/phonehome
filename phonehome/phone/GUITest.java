package phonehome.phone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUITest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();

        TextArea area = new TextArea();
        
        border.setCenter(area);
        border.setPrefSize(500, 500);
        
        stage.setTitle("Test stage");
        stage.setScene(new Scene(border));
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
