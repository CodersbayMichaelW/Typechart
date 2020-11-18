package TypeChart.TypeChartSetting;

import TypeChart.TypeChartApp.TypeChartAppSingleton;
import Utility.API;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Controller {
    TypeChartAppSingleton appData;
    TypeChartSettingSingleton settingsData;

    public Controller() {
        appData = TypeChartAppSingleton.getInstance();
        settingsData = TypeChartSettingSingleton.getInstance();
    }

    // -------------- Read Data from Database ----------------------
    public void setCurrentTypechartSettings() {
        API api = new API();
        HashMap<String, String> resultSettings = api.readCurrentTypeSettings();

        if (resultSettings != null) {
            // Active-Setting-Name
            appData.setActiveSettingName(resultSettings.get("activeSettingName"));
            // background
            appData.setBackgroundImage(resultSettings.get("backgroundImage"));
            appData.setBackgroundColor(resultSettings.get("backgroundColor"));
            appData.setBackgroundOpacity(resultSettings.get("backgroundOpacity"));
            appData.setUsePicture(resultSettings.get("usePicture").equals("1")); // 1 = true | 0 = false
            // header
            appData.setHeaderBackgroundColor(resultSettings.get("headerBackgroundColor"));
            appData.setHeaderOpacity(resultSettings.get("headerOpacity"));
            // type-border
            appData.setTypeBorderColor(resultSettings.get("typeBorderColor"));
            appData.setTypeBorderOpacity(resultSettings.get("typeBorderOpacity"));
            // type-color
            appData.setImmuneColor(resultSettings.get("immuneColor"));
            appData.setStronglyResistsColor(resultSettings.get("stronglyResistsColor"));
            appData.setResistsColor(resultSettings.get("resistsColor"));
            appData.setNormalDamageColor(resultSettings.get("normalDamageColor"));
            appData.setWeakColor(resultSettings.get("weakColor"));
            appData.setVeryWeakColor(resultSettings.get("veryWeakColor"));
        }
    }

    public void setTypechartSettings(HashMap<String, String> resultSettings) {
        if (resultSettings != null) {
            // Active-Setting-Name
            appData.setActiveSettingName(resultSettings.get("typechartSettingName"));
            // background
            appData.setBackgroundImage(resultSettings.get("backgroundImage"));
            appData.setBackgroundColor(resultSettings.get("backgroundColor"));
            appData.setBackgroundOpacity(resultSettings.get("backgroundOpacity"));
            appData.setUsePicture(resultSettings.get("usePicture").equals("1")); // 1 = true | 0 = false
            // header
            appData.setHeaderBackgroundColor(resultSettings.get("headerBackgroundColor"));
            appData.setHeaderOpacity(resultSettings.get("headerOpacity"));
            // type-border
            appData.setTypeBorderColor(resultSettings.get("typeBorderColor"));
            appData.setTypeBorderOpacity(resultSettings.get("typeBorderOpacity"));
            // type-color
            appData.setImmuneColor(resultSettings.get("immuneColor"));
            appData.setStronglyResistsColor(resultSettings.get("stronglyResistsColor"));
            appData.setResistsColor(resultSettings.get("resistsColor"));
            appData.setNormalDamageColor(resultSettings.get("normalDamageColor"));
            appData.setWeakColor(resultSettings.get("weakColor"));
            appData.setVeryWeakColor(resultSettings.get("veryWeakColor"));
        }
    }

    public void updateTypeChartValuesFromDB(String newVal) {
        API api = new API();
        try {
            HashMap<String, String> result = api.readSelectedTypeSettings(newVal); // read the values from DB
            setTypechartSettings(result); // sets the values local
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // set values when a saved setting is chosen
    public void saveTypeChartValues(String newVal) {
        API api = new API();
        try {
            api.updateCurrentTypeSettings(newVal); // saves the current setting into the current "activesetting"
            setCurrentTypechartSettings();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ------- ColorPicker -------
    public void updateSettingsValue() {
        // Background
        settingsData.getBackgroundColorPicker().setValue(makeColorOutOfString(appData.getBackgroundColor(), appData.getBackgroundOpacity()));
        settingsData.getBackgroundImageContainer().getSelectionModel().select(appData.getBackgroundImage());
        settingsData.getBackgroundOpacityTextField().setText(appData.getBackgroundOpacity());
        // Header
        settingsData.getHeaderColorPicker().setValue(makeColorOutOfString(appData.getHeaderBackgroundColor(), appData.getHeaderOpacity()));
        settingsData.getHeaderOpacityTextField().setText(appData.getHeaderOpacity());
        // Border
        settingsData.getBorderColorPicker().setValue(makeColorOutOfString(appData.getTypeBorderColor(), appData.getTypeBorderOpacity()));
        settingsData.getBorderOpacityTextField().setText(appData.getTypeBorderOpacity());
        // Type
        settingsData.getImmuneColorPicker().setValue(makeColorOutOfString(appData.getImmuneColor()));
        settingsData.getStronglyResistsColorPicker().setValue(makeColorOutOfString(appData.getStronglyResistsColor()));
        settingsData.getResistsColorPicker().setValue(makeColorOutOfString(appData.getResistsColor()));
        settingsData.getNormalDamageColorPicker().setValue(makeColorOutOfString(appData.getNormalDamageColor()));
        settingsData.getWeakColorPicker().setValue(makeColorOutOfString(appData.getWeakColor()));
        settingsData.getVeryWeakColorPicker().setValue(makeColorOutOfString(appData.getVeryWeakColor()));

    }

    private Color makeColorOutOfString(String color, String opacity) {
        color = color.replace(" ", "");
        String[] colors = color.split(",");
        return Color.rgb(Integer.parseInt(colors[0]),
                Integer.parseInt(colors[1]),
                Integer.parseInt(colors[2]),
                Double.parseDouble(opacity));
    }

    private Color makeColorOutOfString(String color) {
        color = color.replace(" ", "");
        String[] colors = color.split(",");
        return Color.rgb(Integer.parseInt(colors[0]),
                Integer.parseInt(colors[1]),
                Integer.parseInt(colors[2]));
    }

    // action when saveButton is pressed
    public void saveButtonIsPressed() {
        if (settingsData.getBackgroundImageContainer().getValue() != null) {
            saveTypeChartValues(settingsData.getSavedSettings().getValue());
            settingsData.getMenustage().close();
        }
    }


    // -------------- Background Image or Color update --------------
    public void updateBackgroundIfImageOrColorIsSelected(boolean newVal) {
        // Center -> Typechart
        if (newVal) {
            appData.getBackgroundImageContainer().setStyle("-fx-background-image: url(./image/background/" + appData.getBackgroundImage() + ");\n" +
                    "-fx-opacity: " + appData.getBackgroundOpacity() + ";\n");
        } else {
            appData.getBackgroundImageContainer().setStyle("-fx-background-color: rgba(" + appData.getBackgroundColor() + "," + appData.getBackgroundOpacity() + ");");
        }
    }

    // -------------- ColorPicker update --------------
    // Background
    public void updateBackgroundColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);
        double opacity = Math.round(cp.getValue().getOpacity() * 100.0) / 100.0;

        appData.setBackgroundColor(red + ", " + green + ", " + blue);
        appData.setBackgroundOpacity("" + opacity);

        settingsData.getBackgroundOpacityTextField().setText("" + opacity);
        updateBackgroundIfImageOrColorIsSelected(settingsData.getBackgroundUseImage().isSelected());
    }

    // Header
    public void updateHeaderColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);
        double opacity = Math.round(cp.getValue().getOpacity() * 100.0) / 100.0;

        appData.setHeaderBackgroundColor(red + ", " + green + ", " + blue);
        appData.setHeaderOpacity("" + opacity);

        settingsData.getHeaderOpacityTextField().setText("" + opacity);

        // get all headers and set the styling
        for (int i = 0; i < appData.getTypeweaknessbox().getChildren().size(); i++) {
            if (i % 2 == 0) {
                Node node = appData.getTypeweaknessbox().getChildren().get(i);
                node.setStyle(node.getStyle() + ";\n" + "-fx-background-color: rgba(" + appData.getHeaderBackgroundColor() + "," + appData.getHeaderOpacity() + ");\n");
            }
        }
    }

    // Border
    public void updateBorderColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);
        double opacity = Math.round(cp.getValue().getOpacity() * 100.0) / 100.0;

        appData.setTypeBorderColor(red + ", " + green + ", " + blue);
        appData.setTypeBorderOpacity("" + opacity);

        settingsData.getBorderOpacityTextField().setText("" + opacity);

        // get all headers and set the styling
        for (int i = 0; i < appData.getTypeweaknessbox().getChildren().size(); i++) {
            if (i % 2 != 0) {
                Node node = appData.getTypeweaknessbox().getChildren().get(i);
                node.setStyle(node.getStyle() + ";\n" + "-fx-background-color: rgba(" + appData.getTypeBorderColor() + "," + appData.getTypeBorderOpacity() + ");\n");
            }
        }
    }

    // -------------- TextField update --------------
    public void updateBackgroundColorOpacityWhenTextFieldIsUsed(String newVal, ColorPicker cp) {
        appData.setBackgroundOpacity(newVal);
        Color c = cp.getValue();
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        cp.setValue(Color.rgb(red, green, blue, Double.parseDouble(newVal)));
        updateBackgroundColorWhenColorPickerIsUsed(cp);
    }

    public void updateHeaderColorOpacityWhenTextFieldIsUsed(String newVal, ColorPicker cp) {
        appData.setHeaderOpacity(newVal);
        Color c = cp.getValue();
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        cp.setValue(Color.rgb(red, green, blue, Double.parseDouble(newVal)));
        updateHeaderColorWhenColorPickerIsUsed(cp);
    }

    public void updateBorderColorOpacityWhenTextFieldIsUsed(String newVal, ColorPicker cp) {
        appData.setTypeBorderOpacity(newVal);
        Color c = cp.getValue();
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        cp.setValue(Color.rgb(red, green, blue, Double.parseDouble(newVal)));
        updateBorderColorWhenColorPickerIsUsed(cp);
    }

    // --- Types ---
    // Immune
    public void updateImmuneColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setImmuneColor(red + ", " + green + ", " + blue);

        // set Immune Color
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            // cast to label
            if (((Label) node).getText().equals("Immune to:")) {
                node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getImmuneColor() + ");\n");
            }
            break;
        }
    }

    // StronglyResists
    public void updateStronglyResistsColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setStronglyResistsColor(red + ", " + green + ", " + blue);

        // set Immune Color
        int index = 0;
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            if (index % 2 == 0) {
                if (((Label) node).getText().equals("Strongly Resists to:")) {
                    node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getStronglyResistsColor() + ");\n");
                    break;
                }
            }
            index++;
        }
    }

    // Resists
    public void updateResistsColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setResistsColor(red + ", " + green + ", " + blue);

        // set Immune Color
        int index = 0;
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            if (index % 2 == 0) {
                if (((Label) node).getText().equals("Resists to:")) {
                    node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getResistsColor() + ");\n");
                    break;
                }
            }
            index++;
        }
    }

    // NormalDamage
    public void updateNormalDamageColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setNormalDamageColor(red + ", " + green + ", " + blue);

        // set Immune Color
        int index = 0;
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            if (index % 2 == 0) {
                if (((Label) node).getText().equals("Normal Damage:")) {
                    node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getNormalDamageColor() + ");\n");
                    break;
                }
            }
            index++;
        }
    }

    // Weak
    public void updateWeakColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setWeakColor(red + ", " + green + ", " + blue);

        // set Immune Color
        int index = 0;
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            if (index % 2 == 0) {
                if (((Label) node).getText().equals("Weak to:")) {
                    node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getWeakColor() + ");\n");
                    break;
                }
            }
            index++;
        }
    }

    // VeryWeak
    public void updateVeryWeakColorWhenColorPickerIsUsed(ColorPicker cp) {
        int red = (int) (cp.getValue().getRed() * 255);
        int green = (int) (cp.getValue().getGreen() * 255);
        int blue = (int) (cp.getValue().getBlue() * 255);

        appData.setVeryWeakColor(red + ", " + green + ", " + blue);

        // set Immune Color
        int index = 0;
        for (Node node : appData.getTypeweaknessbox().getChildren()) {
            if (index % 2 == 0) {
                if (((Label) node).getText().equals("Very Weak to:")) {
                    node.setStyle(node.getStyle() + ";\n" + "-fx-text-fill: rgb(" + appData.getVeryWeakColor() + ");\n");
                    break;
                }
            }
            index++;
        }
    }

}
