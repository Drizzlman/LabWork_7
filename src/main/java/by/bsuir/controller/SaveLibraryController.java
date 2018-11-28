package by.bsuir.controller;

import by.bsuir.model.Library;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.Objects;

import static javafx.scene.control.Alert.*;

public class SaveLibraryController {

    @FXML //определение графического интерфейса связанного c save.fxml
    private AnchorPane anchorPane;
    @FXML
    private TextField firstTextField;
    @FXML
    private TextField secondTextField;
    @FXML
    private TextField thirdTextField;

    private TreeItem<Library> root;//  структура в которой мы храним элементы дерева
    //private ObservableList<Library> masterData = FXCollections.observableArrayList();
    private Library library;
    public void setRoot(TreeItem<Library> root) {
        this.root = root;
    }

    public void setLibrary(Library library) {
        this.library = library;
        initSaveForm();
    }

    @FXML
    public void OnSave() {
        try {
            if (this.library == null) {
                addLibrary();
            } else {
                editLibrary();
            }
            OnCancel();
        } catch (NullPointerException | ValidationException e) {
            AlertGenerator.showAlert(AlertType.ERROR, "Save", "Save error", e.getMessage());
        }
    }

    @FXML
    public void OnCancel() {
        Stage stage = (Stage) this.anchorPane.getScene().getWindow();
        stage.close();
    }

    private void addLibrary() throws ValidationException {
        //  if (isValidFields()) {
        final Library library = new Library(
                this.firstTextField.getText(),
                this.secondTextField.getText(),
                this.thirdTextField.getText()
        );
        final TreeItem<Library> treeItem = new TreeItem<>(library);
        Objects.requireNonNull(this.root, "Property root cannot be null").getChildren().add(treeItem);
        //  } else {
        //    throw new ValidationException("Not valid some fields");
        // }
    }

    private void editLibrary() throws ValidationException {
        // if (isValidFields()) {
        final Library library = Objects.requireNonNull(this.library, "Property word cannot be null");
        library.setFirst(this.firstTextField.getText());
        library.setSecond(this.secondTextField.getText());
        library.setThird(this.thirdTextField.getText());
        // human.setThird(this.birthdayDatePicker.getText());
        // } else {
        //     throw new ValidationException("Not valid some fields");
        // }
    }


    private void initSaveForm() {
        if (library == null) {
            initFields("", "", "");
        } else {
            initFields(this.library.getFirst(), this.library.getSecond(), this.library.getThird());
        }
}

    private void initFields(String first, String second, String third) {
        this.firstTextField.setText(first);
        this.secondTextField.setText(second);
        this.thirdTextField.setText(third);
    }

}

  /*  private boolean isValidFields() {
        boolean validity = true;
        try {
            int age = Integer.parseInt(this.ageTextField.getText());
            if (age < 1) {
                validity = false;
            }

            // TODO: Change birthday validation
            if (this.birthdayDatePicker.getValue().isAfter(LocalDate.now().minusYears(age))) {
                validity = false;
            }
        } catch (NumberFormatException e) {
            validity = false;
        }
        return validity;
    }
}*/
