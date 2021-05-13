package phonehome.phone;

import java.net.Socket;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import phonehome.Comm;

/**
 * (G)UI of client side of PhoneHome. Displays messages, gets messages to send,
 * etc. Graphically handles all the user interaction.
 * 
 * @author Stevie Alvarez
 */
public class PhoneHome extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
         * Reference for a typical run:
         *      Get username
         *      Send username to server
         *      Start thread to recieve and display messages
         *      Start thread to get messages from user and send them
         */

        /**
         * Model is a {@link Comm}, which is used to communicate
         * with the server. 
         */

        // model
        Comm phone = new Comm(new Socket("localhost", 6403));

         // contains label to display messages and textbox for user input
        VBox phoneline = new VBox();
        phoneline.setPrefSize(600, 400);


        /**
         * Main display components
         */

        /**
         * A {@link Service} which receives messages from the server and
         * updates the current application's stage for the user to see.
         */
        final Service receive = new Service<String>(){

            @Override
            protected Task<String> createTask() {
                return new Task<String>(){

                    @Override
                    protected String call() throws Exception {
                        String msg = ">> Enter username:";
                        updateMessage(msg);

                        while (!isCancelled()) {
                            msg += phone.read() + "\n";
                            updateMessage(msg);
                        }

                        return msg;
                    }
                };
            }
        };

        // label to display messages
        Label display = new Label();
        display.textProperty().bind(receive.messageProperty());
        //display.setBorder(new Border(new BorderStroke(arg0, arg1, arg2, arg3)));

        receive.start();

        /**
         * User input
         */
        // NEEDS ADAPTABLE HEIGHT FOR TEXT BOX
        HBox line = new HBox();
        //line.setBorder(new Border(new BorderStroke(arg0, arg1, arg2, arg3)));
        Label inputPrompter = new Label(">>");

        // textbox for user input, NEEDS ADAPTABLE HEIGHT FOR USER INPUT
        TextField input = new TextField();
        input.setPromptText("Carlson239");  // change to set a random username as prompt text
        input.setMaxWidth(Double.MAX_VALUE);

        // when user submits text, send it.
        input.setOnAction((e) -> {
            phone.send(input.getText());
            input.setText("");
            input.setPromptText("Hey, how're you doing?");  // change to randomly set prompt text from a list of options
        });


        /**
         * Setup and show stage
         */
        
        line.getChildren().addAll(inputPrompter, input);

        phoneline.getChildren().addAll(display, line);

        stage.setTitle("PhoneHome");
        stage.setScene(new Scene(phoneline));
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
