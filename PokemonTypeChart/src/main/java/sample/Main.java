package sample;

// Stage and Scene
import TypeChart.TypeChartApp.*;
import javafx.stage.Stage;
// Other Things
import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        int width = 860;
        int height = 700;

        Visual mainScene = new Visual();

        mainScene.createSceneLayout(width, height);

        mainScene.setSceneLayout(stage);
    }
}

// binding function of ColorPicker and TextField
// cColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateHeaderColorWhenColorPickerIsUsed(cColor));
// textField.textProperty().addListener((observable, oldVal, newVal) -> controller.updateHeaderColorOpacityWhenTextFieldIsUsed(newVal, cColor));

// edit Setting
// todo -> maybe you should be able to change the name of the saved setting
// close menu = reset
// todo -> close settingsMenu should load data from DB = reset
