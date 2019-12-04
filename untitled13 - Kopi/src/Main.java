import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {


        @Override
        public void start(Stage primaryStage) throws Exception{
            UserInterface userInterface = new UserInterface(primaryStage);
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
