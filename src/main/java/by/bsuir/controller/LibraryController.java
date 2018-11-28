package by.bsuir.controller;

import by.bsuir.App;
import by.bsuir.model.Library;
import by.bsuir.model.Library;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.*;

public class LibraryController implements Initializable {

    @FXML
    private TreeTableView<Library> vocabularyTreeTableView;//класс помогающий отобразить данные в виде таблицы
    @FXML
    private TreeTableColumn<Library, String> firstTreeTableColumn;
    @FXML
    private TreeTableColumn<Library, String> secondTreeTableColumn;
    @FXML
    private TreeTableColumn<Library, String> thirdTreeTableColumn;

    private TreeItem<Library> rootTreeItem = new TreeItem<>();

    //private ObservableList<Library> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vocabularyTreeTableView.setRoot(rootTreeItem);
        firstTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().firstProperty());
        secondTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().secondProperty());
        thirdTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().thirdProperty());

    }



    @FXML
    public void OnAdd() {
        try {
            URL url = App.class.getClassLoader().getResource("save.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);

            SaveLibraryController controller = fxmlLoader.getController();
            controller.setRoot(rootTreeItem);
            controller.setLibrary(null);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Add");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnEdit() {
        try {
            final int index = vocabularyTreeTableView.getSelectionModel().getSelectedIndex();
            URL url = App.class.getClassLoader().getResource("save.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);

            SaveLibraryController controller = fxmlLoader.getController();
            controller.setRoot(rootTreeItem);
            controller.setLibrary(rootTreeItem.getChildren().get(index).getValue());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Edit");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            AlertGenerator.showAlert(AlertType.WARNING, "Edit", "No word selected", "Please, select a word in the table");
        }
    }

    @FXML
    public void OnDelete() {
        try {
            final int index = vocabularyTreeTableView.getSelectionModel().getSelectedIndex();
            rootTreeItem.getChildren().remove(index);
        } catch (IndexOutOfBoundsException e) {
            AlertGenerator.showAlert(AlertType.WARNING, "Delete", "No word selected", "Please, select a word in the table");
        }
    }


}



