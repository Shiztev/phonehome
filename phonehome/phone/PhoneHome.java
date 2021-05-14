package phonehome.phone;

import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import phonehome.Comm;

/**
 * (G)UI of client side of PhoneHome. Displays messages, gets messages to send,
 * etc. Graphically handles all the user interaction.
 * 
 * @author Stevie Alvarez
 */
public class PhoneHome extends Application {

    /**
     * Font for messages.
     */
    private static final Font MSG_FONT;

    /**
     * Font for user text prompt.
     */
    private static final Font PROMPT_FONT;

    /**
     * Font for server notifications.
     */
    private static final Font SERVER_FONT;

    static {
        // "IBM Plex Mono" for messages
        MSG_FONT = new Font("IBM Plex Mono", 15);
        // "IBM Plex Mono Bold" for the >> prompts/notifications
        PROMPT_FONT = new Font("IBM Plex Mono Bold", 15);
        // "IBM Plex Mono Bold Italic" for server notifications
        SERVER_FONT = new Font("IBM Plex Mono Bold Italic", 15);
        
    }

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


        /**
         * Main display components
         */

        // label to display messages
        Label display = new Label();
        display.setFont(MSG_FONT);

        // scrollpane to allow scrolling
        ScrollPane scroll = new ScrollPane(display);
        scroll.setPadding(new Insets(0, 0, 0, 0));  // removes border?

        Label vposLabel = new Label();
        /**
         * A {@link Service} which receives messages from the server and
         * updates the current application's stage for the user to see.
         */
        final Service<String> receive = new Service<>(){

            @Override
            protected Task<String> createTask() {
                return new Task<String>(){

                    @Override
                    protected String call() throws Exception {
                        double bottom = 1.0;
                        String readMsg = "";

                        // initial message and positioning
                        String msg = ">> Enter username:";
                        updateMessage(msg);

                        while (!isCancelled()) {
                            // auto scroll to the bottom if user was at bottom
                            if (bottom == 1.0) {
                                Platform.runLater(() -> {
                                scroll.layout();
                                scroll.setVvalue(1.0d);
                                });
                            }

                            readMsg = phone.read();
                            bottom = scroll.getVvalue();
                            msg += readMsg + "\n";
                            updateMessage(msg);
                        }

                        return msg;
                    }
                };
            }
        };

        // set display text to updated message property
        display.textProperty().bind(receive.messageProperty());

        receive.start();

        /**
         * User input
         */
        HBox line = new HBox();
        line.setPadding(new Insets(15, 0, 0, 0));

        //line.setBorder(new Border(new BorderStroke(arg0, arg1, arg2, arg3)));
        Label inputPrompter = new Label(">>");
        inputPrompter.setFont(PROMPT_FONT);
        inputPrompter.setPadding(new Insets(0, 15, 0, 0));

        // textbox for user input, NEEDS ADAPTABLE HEIGHT FOR USER INPUT
        TextField input = new TextField();
        input.setFont(MSG_FONT);
        input.setPromptText("Carlson239");  // change to set a random username as prompt text
        input.setMaxWidth(Double.MAX_VALUE);

        // when user submits text, send it.
        input.setOnAction((e) -> {
            phone.send(input.getText());
            input.setText("");
            input.setPromptText("Hey, how're you doing?");  // change to randomly set prompt text from a list of options
            vposLabel.setText("" + scroll.getVvalue());
        });


        /**
         * Setup and show stage
         */
        
        line.getChildren().addAll(inputPrompter, input, vposLabel);

        // contains label to display messages and textbox for user input
        GridPane phoneline = new GridPane();
        phoneline.setPadding(new Insets(15));
        phoneline.setPrefSize(500, 750);
        phoneline.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        phoneline.add(scroll, 0, 0);
        phoneline.add(line, 0, 1);

        scroll.prefHeightProperty().bind(phoneline.heightProperty());
        scroll.prefWidthProperty().bind(phoneline.widthProperty());

        stage.setTitle("PhoneHome");
        stage.setScene(new Scene(phoneline));
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
