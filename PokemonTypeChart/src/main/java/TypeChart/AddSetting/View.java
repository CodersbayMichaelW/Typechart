package TypeChart.AddSetting;

import Utility.API;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    public View() { }

    public void openAddView() {
        Stage stage = new Stage();
        stage.setTitle("Delete Settings");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed

        // contains all the elements
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

        // Title Text
        Label titleText = new Label("Create new Setting");
        titleText.setFont(new Font("Verdana", 25));

        // name of setting
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER);
        Label nameText = new Label("Name:");
        // CSS
        TextField nameField = new TextField();
        nameField.setStyle("-fx-width: 80px;\n");
        nameField.setAlignment(Pos.CENTER);
        // add to Box
        nameBox.getChildren().addAll(nameText, nameField);

        // chose Template
        HBox templateBox = new HBox(10);
        templateBox.setAlignment(Pos.CENTER);
        Label templateText = new Label("Chose Template:");
        ComboBox<String> templateCB = new ComboBox<String>();
        // fills ComboBox with SettingsName that are in the Database
        API api = new API();
        ObservableList<String> savedTypeSettings = api.getAllTypeSettingsName();
        templateCB.setItems(savedTypeSettings);
        templateCB.setValue("Default");
        // add To Box
        templateBox.getChildren().addAll(templateText, templateCB);

        // create Setting
        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            boolean result = false;
            if (nameField.getText().length() >= 1) result = api.addSetting(nameField.getText(), templateCB.getValue());
            if (result) stage.close();
        });

        // add all elements
        container.getChildren().addAll(titleText, nameBox,templateBox, createButton);

        // create Scene and set Scene
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }
}
