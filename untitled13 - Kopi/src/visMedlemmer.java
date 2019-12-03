import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class visMedlemmer{

   visMedlemmer(Stage primaryStage) throws Exception{
        VBox root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Delfinen Svømmeklub");

        // Find computerens resolution og sæt størrelsen på stage ud fra disse.
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        double stagesizex = primScreenBounds.getWidth() / 2;
        double stagesizey = primScreenBounds.getHeight() / 1.5;

        Scene sc = new Scene(root, stagesizex, stagesizey);
        sc.getStylesheets().add("sample/Tester.css");
        String[] s = {"Navn", "Alder", "Adresse", "Medlemstype", "Aktivitetstype"};
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
}
