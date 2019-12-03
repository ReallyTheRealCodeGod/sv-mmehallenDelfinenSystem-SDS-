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

            // Indsæt forside/login dialog GUI her ( Mads )

            // Kasser GUI efter login skærm -
            GridPane grid = new GridPane();
            grid.setGridLinesVisible(false);
            Button visMedlemKasser = new Button("Vis Medlemmer");
            Button logUdKasser = new Button("Log Ud");

            visMedlemKasser.setMinSize(150, visMedlemKasser.getHeight());
            visMedlemKasser.setFont(Font.font("", FontWeight.THIN, 20));
            logUdKasser.setMinSize(150, logUdKasser.getHeight());
            logUdKasser.setFont(Font.font("", FontWeight.THIN, 20));

            grid.setVgap(125);
            grid.setHgap(-10);
            grid.setMinSize(720, 580);
            grid.setAlignment(Pos.CENTER);

            grid.add(visMedlemKasser, 0, 0);
            grid.add(logUdKasser, 0, 1);
            grid.setStyle("-fx-background-color: ALICEBLUE;");


            // Formand GUI efter login skærm
            GridPane grid1 = new GridPane();
            grid1.setGridLinesVisible(false);
            Button opretMedlem = new Button("Opret Medlem");
            Button visMedlemFormand = new Button("Vis Medlemmer");
            Button logUd = new Button("Log Ud");

            opretMedlem.setMinSize(150, opretMedlem.getHeight());
            opretMedlem.setFont(Font.font("", FontWeight.THIN, 20));
            visMedlemFormand.setMinSize(150, visMedlemFormand.getHeight());
            visMedlemFormand.setFont(Font.font("", FontWeight.THIN, 20));
            logUd.setMinSize(150, logUd.getHeight());
            logUd.setFont(Font.font("", FontWeight.THIN, 20));

            grid1.setVgap(125);
            grid1.setHgap(-10);
            grid1.setMinSize(720, 580);
            grid1.setAlignment(Pos.CENTER);

            grid1.add(opretMedlem, 0, 0);
            grid1.add(visMedlemFormand, 2, 0);
            grid1.add(logUd, 1, 1);
            grid1.setStyle("-fx-background-color: ALICEBLUE;");


            Group root = new Group(grid);

            primaryStage.setTitle("Svømmeklubben System");
            primaryStage.setScene(new Scene(root, 720, 580)); // ændre scene størrelse
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
