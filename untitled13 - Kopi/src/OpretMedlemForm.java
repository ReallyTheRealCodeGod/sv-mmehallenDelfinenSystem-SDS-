import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.io.InputStream;
import java.net.URL;

public class OpretMedlemForm {
    Stage stage;

    OpretMedlemForm(Stage stage) throws Exception {
        this.stage = stage;

        //Setting size of window
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        double stagesizex = primScreenBounds.getWidth() / 2;
        double stagesizey = primScreenBounds.getHeight() / 1.5;


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

        //Make button do stuff
        buttonGem.setOnAction((event -> System.out.println("Hello World")));
        buttonAflys.setOnAction((event -> System.out.println("Hello World")));

        //laver gridpane
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(stagesizex / 2, stagesizey);

        //padding
        gridPane.setPadding(new Insets(50));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(25);
        gridPane.setHgap(40);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.TOP_LEFT);

        //nodes i grid
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameText, 1, 0);

        gridPane.add(ageLabel, 0, 1);
        gridPane.add(datepicker, 1, 1);

        gridPane.add(adresseLabel, 0, 2);
        gridPane.add(adresseText, 1, 2);

        gridPane.add(nummerLabel, 0, 3);
        gridPane.add(nummerText, 1, 3);

        gridPane.add(postNummerLabel, 0, 4);
        gridPane.add(postNummerText, 1, 4);

        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailText, 1, 5);

        gridPane.add(genderLabel, 0, 6);
        gridPane.add(hboxGender, 1, 6);

        gridPane.add(medlemstypeLabel, 0, 7);
        gridPane.add(medlemsTypeBox, 1, 7);

        gridPane.add(aktivitetsTypeLabel, 0, 8);
        gridPane.add(aktivitetsTypeBox, 1, 8);

        gridPane.add(hboxKnap, 1, 10);

        gridPane.setGridLinesVisible(false);
        gridPane.setMaxSize(stagesizex / 2, stagesizey);

        //imporetere billede

        InputStream logo = new URL("https://i.imgur.com/jb8srK2.png%22").openStream();
        Image image = new Image(logo);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(stagesizex / 2);
        imageView.setFitHeight(stagesizey);
        imageView.setPreserveRatio(false);

        //laver ekstra gridpane
        GridPane bigGrid = new GridPane();
        bigGrid.add(gridPane, 0, 0);
        bigGrid.add(imageView, 1, 0);

        Scene omf = new Scene(bigGrid, stagesizex, stagesizey);
        omf.getStylesheets().add("sample/CSS.css");

        stage.setScene(omf);
        stage.setTitle("Svømmeklubben System");
        stage.show();
    }
}
