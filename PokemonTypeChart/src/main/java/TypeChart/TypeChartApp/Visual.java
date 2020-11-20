package TypeChart.TypeChartApp;

import Type.Type;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Visual {
    TypeChartAppSingleton appData;
    Controller controller;

    public Visual() {
        appData = TypeChartAppSingleton.getInstance();
        controller = new Controller();
    }

    // -------------- Set Scene Layout ----------------------
    public void setSceneLayout(Stage stage) {
        stage.setTitle("TypeChart");
        stage.setScene(appData.getScene()); // add the Scene to the Stage

        try {
//            appData.getScene().getStylesheets().addAll(this.getClass().getResource("../../CSS/style.css").toExternalForm());
            appData.getScene().getStylesheets().add("CSS/style.css");
        }
        catch (Exception e) {
            System.out.println("Can't find the CSS-File: " + e);
        }

        stage.show(); // show me the Stage on the screen
    }

    // -------------- Create Scene Layout ----------------------
    public void createSceneLayout(int width, int height) {
        appData.setWidth(width);
        appData.setHeight(height);

        TypeChart.TypeChartSetting.Controller a = new TypeChart.TypeChartSetting.Controller();
        a.setCurrentTypechartSettings();

        createTopLayout();
        createCenterLayout();
        putLayoutTogether();
    }

    // -------------- puts Layout together ----------------------
    private void putLayoutTogether() {
        // add the Layouts together to a Scene Layout
        BorderPane.setAlignment(appData.getComboboxes(), Pos.TOP_CENTER); // set HBox to top BorderPane Layout
        BorderPane.setAlignment(appData.getTypeweaknessbox(), Pos.CENTER);
        appData.setRoot(new BorderPane());

        // Top -> ComboBox to chose Type
        appData.getComboboxes().setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50), CornerRadii.EMPTY, Insets.EMPTY)));
        appData.getComboboxes().setId("border-bottom");
        appData.getRoot().setTop(appData.getComboboxes());

        // Center -> Typechart
        if (appData.isUsePicture()) {
            appData.setCenterLayout(new StackPane()); // Stackpane that holds the background image and the Type chart
            appData.setBackgroundImageContainer(new HBox());// make a Box to set the background-image
            appData.getBackgroundImageContainer().setId("background-image"); // set background

            appData.getBackgroundImageContainer().setStyle("-fx-background-image: url(/image/background/" + appData.getBackgroundImage() + ");\n" +
                    "-fx-opacity: " + appData.getBackgroundOpacity() + ";\n");

            appData.getCenterLayout().getChildren().addAll(appData.getBackgroundImageContainer(), appData.getTypeweaknessbox()); // add both Boxes together | Type comes before Image
            appData.getRoot().setCenter(appData.getCenterLayout());
        } else {
            appData.setCenterLayout(new StackPane());
            appData.setBackgroundImageContainer(new HBox());
            appData.getBackgroundImageContainer().setStyle("-fx-background-color: rgba(" + appData.getBackgroundColor() + "," + appData.getBackgroundOpacity() + ");");
            appData.getCenterLayout().getChildren().addAll(appData.getBackgroundImageContainer(), appData.getTypeweaknessbox()); // add both Boxes together | Type comes before Image
            appData.getRoot().setCenter(appData.getCenterLayout());
        }


        appData.getRoot().setPrefSize(appData.getWidth(), appData.getHeight()); // set size of window
        appData.setScene(new Scene(appData.getRoot())); // create Scene and add Root Layout to the Scene
    }

    // -------------- Create Top Layout ----------------------
    private void createTopLayout() {
        appData.setComboboxes(new HBox()); // HBox layout

        // create combobox and add objects to it
        ComboBox<Type> comboBox1 = new ComboBox<>();
        ComboBox<Type> comboBox2 = new ComboBox<>();
        controller.setComboBox(comboBox1);
        controller.setComboBox(comboBox2);

        // when a change in combobox happens -> do something
        comboBox1.valueProperty().addListener((obs, oldVal, newVal) -> {
            appData.setType1(newVal);
            controller.updateCenterLayout();
        });

        // when a change in combobox happens -> do something
        comboBox2.valueProperty().addListener((obs, oldVal, newVal) -> {
            appData.setType2(newVal);
            controller.updateCenterLayout();
        });

        // create Setting button
        TypeChart.TypeChartSetting.Visual a = new TypeChart.TypeChartSetting.Visual();
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> a.openTypeChartSettingMenu());

        // Auto-sizing spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        // Auto-sizing spacer
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);


        // translate comboBox move some px to the right
        int translateX = 50;
        comboBox1.setTranslateX(translateX);
        comboBox2.setTranslateX(translateX);

        // add elements to the Layout
        appData.getComboboxes().getChildren().addAll(spacer, comboBox1, comboBox2, spacer2, settingsButton);

        // combobox Layout
        appData.getComboboxes().setAlignment(Pos.BASELINE_CENTER); // center combobox
        appData.getComboboxes().setPadding(new Insets(20, 20, 20, 0));  // top, right, bottom, left
        appData.getComboboxes().setSpacing(20); // add spacing between comboboxes
    }

    // -------------- Create Center Layout ----------------------
    private void createCenterLayout() {
        appData.setTypeweaknessbox(new VBox()); // VBox Layout
        appData.getTypeweaknessbox().setSpacing(5);

        appData.getTypeweaknessbox().setPadding(new Insets(20, 0, 0, 20)); // top, right, bottom, left
    }
}
