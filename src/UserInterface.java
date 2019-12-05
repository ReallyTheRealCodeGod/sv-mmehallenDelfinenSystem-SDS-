import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class UserInterface extends Application {
    private Stage stage;

    //opretter medlemsliste
    MedlemsListe medlemmer;

    //Setting size of window
    private Rectangle2D primScreenBounds;
    private double stagesizex;
    private double stagesizey;

    public void start(Stage primaryStage) throws Exception{
        medlemmer = new MedlemsListe();
        this.stage = primaryStage;
        primScreenBounds = Screen.getPrimary().getVisualBounds();
        stagesizex = primScreenBounds.getWidth() / 2;
        stagesizey = primScreenBounds.getHeight() / 1.5;
        stage.setTitle("Svømmeklubben System");

        sceneManager("login");
    }

    public void sceneManager(String side){
        try {
            switch (side) {
                case "login":
                case "logud":
                    loginSide();
                    break;

                case "formand":
                    formandStartSide();
                    break;
                case "kasser":
                    kasserStartSide();
                    break;

               case "visMedlemmerKasser":
                    visMedlemmer("Kasser");
                    break;
                case "visMedlemmerFormand":
                    visMedlemmer("Formand");
                    break;

                case "opretMedlem":
                    opretMedlemForm();
                    break;
            }
        }catch(Exception e){
            System.out.print("det fucked: " + e);
        }
    }

    public void updateStage(Parent root){
        Scene scene = new Scene(root, stagesizex, stagesizey);
        stage.setScene(scene); // ændre scene størrelse
        stage.show();
    }

    public void loginSide() throws IOException {
        String passwordFormand = "test";
        String passwordKasserer = "test2";


        // Lav knapper og style dem
        Button formand = new Button("Formand");
        Button kasserer = new Button("Kasserer");
        formand.setMinSize(150, formand.getHeight());
        kasserer.setMinSize(150, kasserer.getHeight());
        formand.setFont(Font.font("", FontWeight.THIN, 20));
        kasserer.setFont(Font.font("", FontWeight.THIN, 20));

        // Lav "wrapper" grid pane som v-bokse pakkes ind i
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: ALICEBLUE; ");

        // Lav vbox til buttons (venstre side af programmet) samt style det og tilføj knapper
        VBox vBoxButtons = new VBox();
        vBoxButtons.setMaxSize(stagesizex / 2, stagesizey);
        vBoxButtons.setMinSize(stagesizex / 2, stagesizey);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(150);
        VBox.setMargin(formand, new Insets(20, 20, 20, 20));
        VBox.setMargin(kasserer, new Insets(20, 20, 20, 20));
        vBoxButtons.getChildren().addAll(formand, kasserer);

        // Lav vbox til logo (højre side af programmet)
        VBox vBoxLogo = new VBox();
        vBoxLogo.setMaxSize(stagesizex / 2, stagesizey);

        // Indlæs billede via URL og brug imageView til at vise billedet. Style billedet.
        InputStream logo = new URL("https://i.imgur.com/jb8srK2.png").openStream();
        Image image = new Image(logo);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(stagesizex / 2);
        imageView.setFitHeight(stagesizey);
        imageView.setPreserveRatio(false);

        // Tilføj billede til højre vbox
        vBoxLogo.getChildren().add(imageView);

        // TIlføj vboxe til wrapper grid
        grid.add(vBoxButtons, 0, 0);
        grid.add(vBoxLogo, 1, 0);
        grid.setGridLinesVisible(false);


        /* https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html#resultProperty--
        Once a Dialog is instantiated and fully configured, the next step is to show it. More often than not,
        dialogs are shown in a modal and blocking fashion.
        'Modal' means that the dialog prevents user interaction with the owning application whilst it is showing,
        and 'blocking' means that code execution stops at the point in which the dialog is shown.
        This means that you can show a dialog, await the user response,
        and then continue running the code that directly follows the show call,
        giving developers the ability to immediately deal with the user input from the dialog (if relevant).

        JavaFX dialogs are modal by default (you can change this via the initModality(javafx.stage.Modality) API).
        To specify whether you want blocking or non-blocking dialogs,
        developers simply choose to call showAndWait() or show() (respectively).
        By default most developers should choose to use showAndWait(), given the ease of coding in these situations.
        Shown below is three code snippets, showing three equally valid ways of showing a dialog:
         */

        // Dialog box til login
        Dialog<String> dialogLogin = new Dialog<>();
        dialogLogin.setTitle("Login");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuller", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogLogin.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

        HBox dialogHBox = new HBox();
        dialogHBox.setSpacing(10);
        Label passwordLabel = new Label("Kodeord: ");
        PasswordField passwordField = new PasswordField();
        dialogHBox.getChildren().addAll(passwordLabel, passwordField);
        HBox.setMargin(passwordField, new Insets(10, 10, 0, 0));
        HBox.setMargin(passwordLabel, new Insets(10, 10, 0, 10));

        Platform.runLater(() -> passwordField.requestFocus());
        Node loginButton = dialogLogin.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialogLogin.getDialogPane().setContent(dialogHBox);

        // Når tryk på logind, valider om kodeord passer til password variabler.
        // Hvis validering er OK, ændre scene til kasser/formand brugerside.
        dialogLogin.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                String tempPassword = passwordField.getText();
                passwordField.clear();
                return tempPassword;
            } else if (dialogButton == cancelButtonType) {
                passwordField.clear();
            }
            return null;
        });

        /* Freaky shit going on here
        ActionEvent for begge knapper som viser dialogboksen
        Ved tryk på knap vises en login dialog boks
        Det password der indtastes i passwordField valideres imod en String variabel som indeholder koden
        for henholdsvis formand og kasserer.
         */
        formand.setOnAction(e -> {
            Optional<String> result = dialogLogin.showAndWait();


            result.ifPresent(password -> {
                if (password.equals(passwordFormand)) {
                    System.out.println("Formand pass er OK");
                    sceneManager("formand");
                } else {
                    errorDialog("Fejl", "Forkert kodeord. Prøv igen.");
                    dialogLogin.showAndWait();
                    System.out.println("Formand pass ikke OK");
                }
            });
        });

        kasserer.setOnAction(e -> {
            Optional<String> result = dialogLogin.showAndWait();

            result.ifPresent(password -> {
                if (password.equals(passwordKasserer)) {
                    System.out.println("Kasserer pass er OK");
                    sceneManager("kasser");
                } else {
                    System.out.println("Kasserer pass ikke OK");
                }
            });

        });

        // Instansier scenen, sæt primarystage til at vise den og start stage
        Scene scene = new Scene(grid, stagesizex, stagesizey);
        stage.setScene(scene);
        stage.show();
    }

    public void errorDialog(String title, String message) {
        // Dialog til forkert login
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle(title);

        Label label = new Label(message);
        Button button = new Button("OK");
        VBox vb = new VBox();

        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(label, button);
        dialog.getDialogPane().setContent(vb);

        button.setOnAction(e -> {
            dialog.close();
            dialog.setResult(Boolean.TRUE);
        });

        // ButtonType bt = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        // dialog.getDialogPane().getButtonTypes().add(bt);
        dialog.showAndWait();
    }

    public void kasserStartSide() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        Button visMedlemKasser = new Button("Vis Medlemmer");
        Button logUdKasser = new Button("Log Ud");
        //gui kontrol
        visMedlemKasser.setOnAction(e -> { sceneManager("visMedlemmerKasser");});
        logUdKasser.setOnAction(e -> { sceneManager("logud"); });

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

        // gui kontrol
        opretMedlem.setOnAction(e -> { sceneManager("opretMedlem");});
        visMedlemFormand.setOnAction(e -> { sceneManager("visMedlemmerFormand");});
        logUd.setOnAction(e -> { sceneManager("logud"); });

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

    public void opretMedlemForm() throws Exception {

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
        buttonGem.setDisable(true);

        //laver lsitener til fields
/*
            nameText.textProperty().addListener((observable, oldValue, newValue) -> {
                buttonGem.setDisable(newValue.trim().isEmpty());

            });

        adresseText.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            buttonGem.setDisable(newValue1.trim().isEmpty());
        });
        emailText.textProperty().addListener((observable2, oldValue2, newValue2) -> {
            buttonGem.setDisable(newValue2.trim().isEmpty());
        });
        nummerText.textProperty().addListener((observable3, oldValue3, newValue3) -> {
            buttonGem.setDisable(newValue3.trim().isEmpty());
        });
    }

 */

        //hbox til knapper
        HBox hboxKnap = new HBox();
        hboxKnap.setSpacing(10);
        hboxKnap.getChildren().addAll(buttonGem, buttonAflys);
        hboxKnap.setAlignment(Pos.BOTTOM_RIGHT);

        //Date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        //Make button do stuff
        buttonGem.setOnAction((event ->{
                if (medlemmer.verificerAddMedlemInput(nameText.getText(),
                        datepicker.getValue(),
                        ((RadioButton) groupGender.getSelectedToggle()).getText(),
                        (adresseText.getText()+" "+ nummerText.getText()),
                        emailText.getText(),
                        aktivitetsTypeBox.getValue().toString(),
                        medlemsTypeBox.getValue().toString()
                )) {
                    sceneManager("formand");
                }
                else System.out.println("");
        }
        ));

        //Make method go to formand side
        buttonAflys.setOnAction((event -> {
            System.out.println("Go to formand page");
            sceneManager("formand");
        }));

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
        postNummerText.setText("-- IKKE IMPLEMENTERET ENDNU --");

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

        //imporeterer billede
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

        //bigGrid.getStylesheets().add("sample/CSS.css");
        updateStage(bigGrid);
        }

    public void visMedlemmer(String bruger) throws Exception{
        VBox root = new VBox();
        String[] columns;
        HBox options;

        TableView tb = new TableView();
        tb.setId("table");
        TextField tf = new TextField("Search:");
        tf.setId("Search");

        if(bruger.equals("Formand")) {
            options = FXMLLoader.load(getClass().getResource("Formand.fxml"));
            columns = new String[] {"Navn", "Fodselsdato", "Adresse", "Medlemstype", "Aktivitetstype"};
        }
        else //if(bruger.equals("kasser"))
            {
            options = FXMLLoader.load(getClass().getResource("Kasser.fxml"));
            columns = new String[] {"Navn", "Pris", "Adresse", "Email", "Aktivitetstype"};
        }

        root.getChildren().add(tf);
        root.getChildren().add(tb);
        root.getChildren().add(options);

        generateTable(columns, root);
        updateStage(root);

    }

    void generateTable(String[] columns, Parent root){
        MedlemsListe ml = new MedlemsListe();
        ArrayList<Medlem> al = ml.getListe();
        TableView table = (TableView)root.lookup("#table");

        ObservableList<Medlem> ol = FXCollections.observableArrayList();
        for(Medlem m: al){
            ol.add(m);
        }
        table.setItems(ol);

        for(String s: columns) {
            TableColumn<String, Medlem> col = new TableColumn<String, Medlem>(s);
            col.setCellValueFactory(new PropertyValueFactory<>(s));
            if(s.equals("Fodselsdato")){
                col.setText("F"+"\u00F8"+"dselsdato");
            }
            table.getColumns().add(col);
        }
    }
    }

