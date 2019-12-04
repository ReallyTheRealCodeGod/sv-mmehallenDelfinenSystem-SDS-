import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    private Stage stage;
    //Setting size of window
    private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    private double stagesizex = primScreenBounds.getWidth() / 2;
    private double stagesizey = primScreenBounds.getHeight() / 1.5;

    UserInterface(Stage stage){
        this.stage = stage;
        stage.setTitle("Svømmeklubben System");
    }

    public void updateStage(Parent root){
        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene); // ændre scene størrelse
        stage.show();
    }

    public void kasserStartSide() {
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

        updateStage(grid);
    }

    public void formandStartSide() {
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
        updateStage(grid1);
    }

    public void OpretMedlemForm() throws Exception {

        //Labels og fields
        Text nameLabel = new Text("Navn");
        TextField nameText = new TextField();

        Text ageLabel = new Text("Fødselsdato");

        DatePicker datepicker = new DatePicker();
        datepicker.setPrefSize(270, 20);

        Text adresseLabel = new Text("Adresse");
        TextField adresseText = new TextField();

        Text nummerLabel = new Text("Husnummer");
        TextField nummerText = new TextField();

        Text postNummerLabel = new Text("Postnummer");
        TextField postNummerText = new TextField();

        Text emailLabel = new Text("E-Mail");
        TextField emailText = new TextField();

        //Køn label og en gruppe for denne
        Text genderLabel = new Text("Køn");

        ToggleGroup groupGender = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Mand");
        maleRadio.setToggleGroup(groupGender);
        RadioButton femaleRadio = new RadioButton("Kvinde");
        femaleRadio.setToggleGroup(groupGender);
        RadioButton otherRadio = new RadioButton("Andet");
        otherRadio.setToggleGroup(groupGender);

        //hbox til køn
        HBox hboxGender = new HBox();
        hboxGender.setSpacing(10);
        hboxGender.getChildren().addAll(maleRadio, femaleRadio, otherRadio);

        Text medlemstypeLabel = new Text("Medlemstype");

        //Choice box til drop down
        ChoiceBox medlemsTypeBox = new ChoiceBox();
        medlemsTypeBox.getItems().addAll("Aktiv", "Passiv");
        medlemsTypeBox.setPrefWidth(270);


        Text aktivitetsTypeLabel = new Text("Aktivitetstype");

        //Choice box til drop down
        ChoiceBox aktivitetsTypeBox = new ChoiceBox();
        aktivitetsTypeBox.getItems().addAll("Konkurrence", "Motionist");
        aktivitetsTypeBox.setPrefWidth(270);
        //knapper
        Button buttonGem = new Button("Gem");
        Button buttonAflys = new Button("Aflys");
        buttonGem.setPrefSize(180, 20);
        buttonAflys.setPrefSize(80, 20);
        //hbox til knapper
        HBox hboxKnap = new HBox();
        hboxKnap.setSpacing(10);
        hboxKnap.getChildren().addAll(buttonGem, buttonAflys);
        hboxKnap.setAlignment(Pos.BOTTOM_RIGHT);

        //Date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        //Make button do stuff
        buttonGem.setOnAction((event ->
                System.out.println(nameText.getText() +
                        datepicker.getValue() +
                        ((RadioButton) groupGender.getSelectedToggle()).getText()+
                        (adresseText.getText()+" "+nummerText.getText())+
                        emailText.getText() +
                        aktivitetsTypeBox.getValue() +
                        medlemsTypeBox.getValue()
                )));

        //Make method go to formand side
        buttonAflys.setOnAction((event -> System.out.println("Go to formand page")));

        //laver gridpane
        GridPane gridPaneOpret = new GridPane();
        gridPaneOpret.setMinSize(stagesizex / 2, stagesizey);

        //padding
        gridPaneOpret.setPadding(new Insets(50));

        //Setting the vertical and horizontal gaps between the columns
        gridPaneOpret.setVgap(25);
        gridPaneOpret.setHgap(40);

        //Setting the Grid alignment
        gridPaneOpret.setAlignment(Pos.TOP_LEFT);

        //nodes i grid
        gridPaneOpret.add(nameLabel, 0, 0);
        gridPaneOpret.add(nameText, 1, 0);

        gridPaneOpret.add(ageLabel, 0, 1);
        gridPaneOpret.add(datepicker, 1, 1);

        gridPaneOpret.add(adresseLabel, 0, 2);
        gridPaneOpret.add(adresseText, 1, 2);

        gridPaneOpret.add(nummerLabel, 0, 3);
        gridPaneOpret.add(nummerText, 1, 3);

        gridPaneOpret.add(postNummerLabel, 0, 4);
        gridPaneOpret.add(postNummerText, 1, 4);

        gridPaneOpret.add(emailLabel, 0, 5);
        gridPaneOpret.add(emailText, 1, 5);

        gridPaneOpret.add(genderLabel, 0, 6);
        gridPaneOpret.add(hboxGender, 1, 6);

        gridPaneOpret.add(medlemstypeLabel, 0, 7);
        gridPaneOpret.add(medlemsTypeBox, 1, 7);

        gridPaneOpret.add(aktivitetsTypeLabel, 0, 8);
        gridPaneOpret.add(aktivitetsTypeBox, 1, 8);

        gridPaneOpret.add(hboxKnap, 1, 10);

        gridPaneOpret.setGridLinesVisible(false);
        gridPaneOpret.setMaxSize(stagesizex / 2, stagesizey);

        //imporeterer billede
        InputStream logo = new URL("https://i.imgur.com/jb8srK2.png%22").openStream();
        Image image = new Image(logo);
        ImageView imageViewOpret = new ImageView(image);
        imageViewOpret.setFitWidth(stagesizex / 2);
        imageViewOpret.setFitHeight(stagesizey);
        imageViewOpret.setPreserveRatio(false);

        //laver ekstra gridpane
        GridPane bigGrid = new GridPane();
        bigGrid.add(gridPaneOpret, 0, 0);
        bigGrid.add(imageViewOpret, 1, 0);

        bigGrid.getStylesheets().add("sample/CSS.css");
        updateStage(bigGrid);
    }
}

