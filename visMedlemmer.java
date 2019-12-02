package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene sc = new Scene(root, 720, 580);
        sc.getStylesheets().add("sample/Tester.css");
        String[] s = {"Navn", "Alder", "Adresse", "Medlemstype", "Aktivitetstype", "Betalingsdato"};
        generateTable(s, sc);


        primaryStage.setTitle("Medlem liste");
        primaryStage.setResizable(false);
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    void generateTable(String[] columns, Scene sc){
        MedlemsListe ml = new MedlemsListe();
        ArrayList<Medlem> al = ml.getListe();
        TableView table = (TableView)sc.lookup("#table");

        ObservableList<Medlem> ol = FXCollections.observableArrayList();
        for(Medlem m: al){
            ol.add(m);
        }
        table.setItems(ol);

        for(String s: columns) {
            TableColumn<String, Medlem> col = new TableColumn<String, Medlem>(s);
            col.setCellValueFactory(new PropertyValueFactory<>(s));
            table.getColumns().add(col);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
