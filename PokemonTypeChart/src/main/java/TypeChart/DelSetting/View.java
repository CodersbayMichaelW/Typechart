package TypeChart.DelSetting;

import Utility.API;
import Utility.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {
    Utilities utility;

    public View() {
        utility = new Utilities();
    }

    public void openDelView() {
        Stage stage = new Stage();
        stage.setTitle("Add Setting");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed

        // contains all the elements
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

        // Title Text
        Label titleText = new Label("Delete Setting");
        titleText.setFont(new Font("Verdana", 25));

        // chose Setting
        HBox templateBox = new HBox(10);
        templateBox.setAlignment(Pos.CENTER);
        Label templateText = new Label("Chose Setting:");
        ComboBox<String> templateCB = new ComboBox<String>();
        // fills ComboBox with SettingsName that are in the Database
        API api = new API();
        ObservableList<String> savedTypeSettings = api.getAllTypeSettingsName();
        savedTypeSettings.remove("Default");
        templateCB.setValue(savedTypeSettings.get(0));
        templateCB.setItems(savedTypeSettings);
        // add To Box
        templateBox.getChildren().addAll(templateText, templateCB);

        // delete Setting
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            boolean result = api.deleteSetting(templateCB.getValue());
            if (result) stage.close();
        });

        // add all elements
        container.getChildren().addAll(titleText,templateBox, deleteButton);

        // create Scene and set Scene
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }
}
