package TypeChart.TypeChartApp;

import Type.Type;
import TypeChart.TypeChartSetting.TypeChartSettingSingleton;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    TypeChartAppSingleton appData;
    TypeChartSettingSingleton settingData;

    Controller() {
        appData = TypeChartAppSingleton.getInstance();
        settingData = TypeChartSettingSingleton.getInstance();
    }

    // -------------- Fills ComboBox ----------------------
    public void setComboBox(ComboBox<Type> comboBox) {
        comboBox.setItems(FXCollections.observableArrayList(
                new Type(""),
                new Type("Normal"),
                new Type("Fire"),
                new Type("Water"),
                new Type("Grass"),
                new Type("Electric"),
                new Type("Ice"),
                new Type("Fighting"),
                new Type("Poison"),
                new Type("Ground"),
                new Type("Flying"),
                new Type("Psychic"),
                new Type("Bug"),
                new Type("Rock"),
                new Type("Ghost"),
                new Type("Dragon"),
                new Type("Dark"),
                new Type("Steel"),
                new Type("Fairy")
        ));
    }

    // -------------- Update Center Frame ----------------------
    public void updateCenterLayout() {
        appData.getTypeweaknessbox().getChildren().clear(); // remove all the elements
        HashMap<String, Double> typelist = createDLL();

        ArrayList<String> immune = new ArrayList<>();
        ArrayList<String> stronglyResist = new ArrayList<>();
        ArrayList<String> resist = new ArrayList<>();
        ArrayList<String> normalDamage = new ArrayList<>();
        ArrayList<String> weak = new ArrayList<>();
        ArrayList<String> veryWEak = new ArrayList<>();

        if (typelist != null) {
            for (Map.Entry<String, Double> typePair : typelist.entrySet()) {
                double value = typePair.getValue();
                String name = typePair.getKey().toLowerCase();

                switch (Double.toString(value)) {
                    case "0.0" -> immune.add(name);
                    case "0.25" -> stronglyResist.add(name);
                    case "0.5" -> resist.add(name);
                    case "1.0" -> normalDamage.add(name);
                    case "2.0" -> weak.add(name);
                    case "4.0" -> veryWEak.add(name);
                }
            }

            if (immune.size() > 0) {
                updateTypeTextLabel("Immune to:", "1");
                updateTypePanel(immune);
            }
            if (stronglyResist.size() > 0) {
                updateTypeTextLabel("Strongly Resists to:", "2");
                updateTypePanel(stronglyResist);
            }
            if (resist.size() > 0) {
                updateTypeTextLabel("Resists to:", "3");
                updateTypePanel(resist);
            }
            if (normalDamage.size() > 0) {
                updateTypeTextLabel("Normal Damage:", "4");
                updateTypePanel(normalDamage);
            }
            if (weak.size() > 0) {
                updateTypeTextLabel("Weak to:", "5");
                updateTypePanel(weak);
            }
            if (veryWEak.size() > 0) {
                updateTypeTextLabel("Very Weak to:", "6");
                updateTypePanel(veryWEak);
            }
        }
    }

    public void updateTypeTextLabel(String text, String color) {
        Label labelText = new Label(text);
        labelText.setId("label-text");

        String labelColor = "";
        switch (color) {
            case "1" -> labelColor = appData.getImmuneColor();
            case "2" -> labelColor = appData.getStronglyResistsColor();
            case "3" -> labelColor = appData.getResistsColor();
            case "4" -> labelColor = appData.getNormalDamageColor();
            case "5" -> labelColor = appData.getWeakColor();
            case "6" -> labelColor = appData.getVeryWeakColor();
        }

        labelText.setStyle("-fx-background-color: rgba(" + appData.getHeaderBackgroundColor() + "," + appData.getHeaderOpacity() + ");\n" +
                "-fx-text-fill: rgb(" + labelColor + ");\n");

        // Event Handler -> something happened
        labelText.onInputMethodTextChangedProperty();

        appData.getTypeweaknessbox().getChildren().add(labelText);
    }

    public void updateTypePanel(ArrayList<String> typelist) {
        FlowPane imgContainer = new FlowPane();
        imgContainer.setHgap(10);
        imgContainer.setVgap(5);

        for (String typeName : typelist) {
            // create image
            System.out.println(getClass().getResource("/image/type/" + typeName + ".png"));
            Image image = new Image(String.valueOf(getClass().getResource("/image/type/" + typeName + ".png")));
            // set imageView
            ImageView imageView = new ImageView(image);
            // set fit height and width of the image view
            imageView.setFitHeight(20);
            imageView.setFitWidth(80);
            // add Type to FlowPane
            imgContainer.getChildren().add(imageView);
        }
        imgContainer.setId("type-background");
        imgContainer.setStyle("-fx-background-color: rgba(" + appData.getTypeBorderColor() + "," + appData.getTypeBorderOpacity() + ");");
        appData.getTypeweaknessbox().getChildren().add(imgContainer);
    }

    // -------------- make an Array ----------------------
    public HashMap<String, Double> createDLL() {
        if (appData.getType1() != null && appData.getType1().getTypeweakness().size() > 0) {
            HashMap<String, Double> resultHashMap = new HashMap<String, Double>();

            // both types are selected and are not the same type
            if (appData.getType2() != null && appData.getType2().getTypeweakness().size() > 0 && (!appData.getType1().getName().equals(appData.getType2().getName()))) {
                HashMap<String, Double> type1 = appData.getType1().getTypeweakness(); // get HashMap with the Type Values
                HashMap<String, Double> type2 = appData.getType2().getTypeweakness(); // get HashMap with the Type Values

                // calculate the value and puts the into "resultHashMap"
                for (Map.Entry<String, Double> type1Pair : type1.entrySet()) {
                    double type2Value = type2.get(type1Pair.getKey());
                    resultHashMap.put(type1Pair.getKey(), type2Value * type1Pair.getValue());
                }
                return resultHashMap;
            }
            // only the first type or the same thing is selected
            else return appData.getType1().getTypeweakness();
        }
        return null;
    }
}
