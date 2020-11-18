package TypeChart.TypeChartSetting;

import TypeChart.TypeChartApp.TypeChartAppSingleton;
import Utility.API;
import Utility.Utilities;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Visual {
    TypeChartAppSingleton appData;
    TypeChartSettingSingleton settingsData;
    Controller controller;
    Utilities utility;

    public Visual() {
        appData = TypeChartAppSingleton.getInstance();
        settingsData = TypeChartSettingSingleton.getInstance();
        controller = new Controller();
        utility = new Utilities();
    }

    // -------------- TypeChart Setting Menu ----------------------
    public void openTypeChartSettingMenu() {
        if (!settingsData.getMenustage().isShowing()) {
            // Set menuStage Properties
            settingsData.getMenustage().setTitle("TypeChart Settings");
            settingsData.getMenustage().setWidth(500);
            settingsData.getMenustage().setHeight(500);
//        menuStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed

            // Design of Menu
            settingsData.setSettingsContainer(new VBox(10));

            // choose saved Settings
            createSavedSetting();

            // create Scene
            Scene settingMenuScene = new Scene(settingsData.getSettingsContainer());
            settingsData.getMenustage().setScene(settingMenuScene);

            settingsData.getMenustage().show();
        }
    }


    // ---------- load Setting ----------
    private void createSavedSetting() {
        settingsData.setSavedSetting(new HBox());
        Label tempText = new Label("Select: ");

        ComboBox<String> tempCB = new ComboBox<String>(); // savedSettings
        tempCB.setPrefWidth(150);

        // fills ComboBox with SettingsName that are in the Database
        API api = new API();
        ObservableList<String> savedTypeSettings = api.getAllTypeSettingsName();
        tempCB.setItems(savedTypeSettings);

        // set variable into the Singleton class
        settingsData.setSavedSettings(tempCB);

        // when a change in ComboBox happens -> do something
        tempCB.valueProperty().addListener((obs, oldVal, newVal) -> {

            settingsData.getSettingsContainer().getChildren().removeAll(settingsData.getBackgroundContainer(), settingsData.getHeaderContainer(), settingsData.getBorderContainer(), settingsData.getTypeContainer(), settingsData.getSaveCancelContainer());

            if (oldVal != null && !oldVal.equals(newVal)) controller.updateTypeChartValuesFromDB(newVal); // Update Values

            createSettings(); // Visual
        });

        // add
        Button addButton = new Button("+");
        TypeChart.AddSetting.View addView = new TypeChart.AddSetting.View();
        addButton.setOnAction(e -> addView.openAddView());

        // delete
        Button deleteButton = new Button("-");
        TypeChart.DelSetting.View delView = new TypeChart.DelSetting.View();
        deleteButton.setOnAction(e -> delView.openDelView());


        // set CSS-Margin
        setCSSSavedSetting(settingsData.getSavedSetting());
        setVerticalAlignmentToCenter(settingsData.getSavedSetting());

        settingsData.getSavedSetting().setAlignment(Pos.CENTER);
        settingsData.getSavedSetting().getChildren().addAll(tempText, settingsData.getSavedSettings(), addButton, deleteButton);
        settingsData.getSettingsContainer().getChildren().add(settingsData.getSavedSetting());

        // ---------------------------- select the active SettingsName ----------------------------
        tempCB.getSelectionModel().select(appData.getActiveSettingName());
    }

    private void createSettings() {
        // Background
        HBox backgroundContainer = createBackgroundSettings();

        // Header
        HBox headerContainer = createHeaderSettings();

        // Border
        HBox borderContainer = createBorderSettings();

        // Type
        VBox typeContainer = createTypeSettings();

        // Save and Cancel
        HBox saveCancelContainer = createSaveCancel();

        // add HBox to VBox
        settingsData.getSettingsContainer().getChildren().addAll(backgroundContainer, headerContainer, borderContainer,
                typeContainer, saveCancelContainer);

        // set Colors in ColorPicker / have to after the ColorPicker is Indianized;
        controller.updateSettingsValue();
    }

    // ---------- Background ----------
    private HBox createBackgroundSettings() {
        HBox backgroundContainer = new HBox();

        Label backgroundText = new Label("Background:");

        // Checkbox
        CheckBox backgroundUseImage = new CheckBox("Use Image");
        backgroundContainer.getChildren().addAll(backgroundText, backgroundUseImage); // add Text and Checkbox to BContainer
        // when a change in Checkbox happens -> do something
        backgroundUseImage.selectedProperty().addListener((obs, oldVal, newVal) -> {
            controller.updateBackgroundIfImageOrColorIsSelected(newVal);
        });

        // ComboBox
        ComboBox<String> backgroundColorComboBox = new ComboBox<String>();
        // when a change in comboBox happens -> do something
        backgroundColorComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            appData.setBackgroundImage(newVal);
            controller.updateBackgroundIfImageOrColorIsSelected(appData.isUsePicture());
        });

        // ColorPicker
        ColorPicker backgroundColorContainer = new ColorPicker();
        // when a change in ColorPicker -> do something
        backgroundColorContainer.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateBackgroundColorWhenColorPickerIsUsed(backgroundColorContainer));

        // Opacity
        Label opacityText = new Label("opacity:");
        TextField opacityTextField = new TextField();
        // when a change in opacityTextField -> change opacity Text and ColorPicker
        opacityTextField.textProperty().addListener((observable, oldVal, newVal) -> controller.updateBackgroundColorOpacityWhenTextFieldIsUsed(newVal, backgroundColorContainer));

        // Set CSS-Margin
        setCSSBackgroundSettings(backgroundUseImage, backgroundColorComboBox, backgroundColorContainer, opacityText, opacityTextField);
        setVerticalAlignmentToCenter(backgroundContainer);

        // set variable into the Singleton class
        settingsData.setBackgroundUseImage(backgroundUseImage);
        settingsData.setBackgroundImageContainer(backgroundColorComboBox);
        settingsData.setBackgroundColorPicker(backgroundColorContainer);
        settingsData.setBackgroundOpacityTextField(opacityTextField);

        // add EventListener
        backgroundUseImage.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
                    // remove the Box before
                    backgroundContainer.getChildren().removeAll(backgroundColorComboBox, backgroundColorContainer, opacityText, opacityTextField);

                    // checkbox is selected -> use Picture
                    if (backgroundUseImage.isSelected()) {
                        appData.setUsePicture(true);
                        backgroundColorComboBox.setItems(FXCollections.observableArrayList(
                                utility.readAllImagesInBackgroundFolder()
                        ));
                        backgroundContainer.getChildren().add(backgroundColorComboBox);
                    } else {
                        appData.setUsePicture(false);
                        backgroundContainer.getChildren().add(backgroundColorContainer);
                    }
                    // Opacity
                    backgroundContainer.getChildren().add(opacityText);
                    backgroundContainer.getChildren().add(opacityTextField);
                });

        // Checkbox parameter when opening the window
        backgroundUseImage.setSelected(appData.isUsePicture());

        // checkBox is not checked -> therefore it doesn't do the Event
        if (!appData.isUsePicture()) {
            // ColorPicker
            backgroundContainer.getChildren().add(backgroundColorContainer);
            // Opacity
            backgroundContainer.getChildren().add(opacityText);
            backgroundContainer.getChildren().add(opacityTextField);
        }

        settingsData.setBackgroundContainer(backgroundContainer);
        return settingsData.getBackgroundContainer();
    }

    // ---------- Header / Border ----------
    private HBox createHeaderSettings() {
        HBox borderContainer = new HBox();

        Label lText = new Label("Header" + ":");

        ColorPicker cColor = new ColorPicker();
        cColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateHeaderColorWhenColorPickerIsUsed(cColor));


        Label opacityText = new Label("opacity:");
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldVal, newVal) -> controller.updateHeaderColorOpacityWhenTextFieldIsUsed(newVal, cColor));


        // set CSS
        setCSSHeaderBorder(lText, cColor, textField);
        setVerticalAlignmentToCenter(borderContainer);

        // set variable into the Singleton class
        settingsData.setHeaderColorPicker(cColor);
        settingsData.setHeaderOpacityTextField(textField);

        borderContainer.getChildren().addAll(lText, settingsData.getHeaderColorPicker(), opacityText, settingsData.getHeaderOpacityTextField());

        settingsData.setHeaderContainer(borderContainer);
        return settingsData.getHeaderContainer();
    }

    private HBox createBorderSettings() {
        HBox borderContainer = new HBox();

        Label lText = new Label("Border" + ":");

        ColorPicker cColor = new ColorPicker();
        cColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateBorderColorWhenColorPickerIsUsed(cColor));


        Label opacityText = new Label("opacity:");
        TextField textField = new TextField();
        textField.textProperty().addListener((observable, oldVal, newVal) -> controller.updateBorderColorOpacityWhenTextFieldIsUsed(newVal, cColor));


        // set CSS
        setCSSHeaderBorder(lText, cColor, textField);
        setVerticalAlignmentToCenter(borderContainer);

        // set variable into the Singleton class
        settingsData.setBorderColorPicker(cColor);
        settingsData.setBorderOpacityTextField(textField);

        borderContainer.getChildren().addAll(lText, settingsData.getBorderColorPicker(), opacityText, settingsData.getBorderOpacityTextField());

        settingsData.setBorderContainer(borderContainer);
        return settingsData.getBorderContainer();
    }

    // ---------- Types ----------
    public VBox createTypeSettings() {
        VBox typeContainer = new VBox(5);
        typeContainer.setAlignment(Pos.CENTER);

        // Type Header
        Label typeHeader = new Label("Types");
        typeHeader.setFont(new Font(30));

        // Immune and StronglyResists
        HBox tempContainer = createImmuneStronglyResistsSettings();

        // Immune and StronglyResists
        HBox tempContainer2 = createResistsNormalDamageSettings();

        // Immune and StronglyResists
        HBox tempContainer3 = createWeakVeryWeakSettings();

        typeContainer.getChildren().addAll(typeHeader, tempContainer, tempContainer2, tempContainer3);

        settingsData.setTypeContainer(typeContainer);
        return settingsData.getTypeContainer();
    }

    private HBox createImmuneStronglyResistsSettings() {
        HBox tempContainer = new HBox();

        Label tempText1 = new Label("Immune:");
        ColorPicker immuneColor = new ColorPicker();
        immuneColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateImmuneColorWhenColorPickerIsUsed(immuneColor));

        Label tempText2 = new Label("Str.Resists:");
        ColorPicker stronglyResistsColor = new ColorPicker();
        stronglyResistsColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateStronglyResistsColorWhenColorPickerIsUsed(stronglyResistsColor));

        // set CSS
        setCSSType(tempText1, immuneColor);
        setCSSType(tempText2, stronglyResistsColor);
        setVerticalAlignmentToCenter(tempContainer);

        // set variable into the Singleton class
        settingsData.setImmuneColorPicker(immuneColor);
        settingsData.setStronglyResistsColorPicker(stronglyResistsColor);

        tempContainer.getChildren().addAll(tempText1, settingsData.getImmuneColorPicker(), tempText2, settingsData.getStronglyResistsColorPicker());
        return tempContainer;
    }

    private HBox createResistsNormalDamageSettings() {
        HBox tempContainer = new HBox();

        Label tempText1 = new Label("Resists:");
        ColorPicker resistsColor = new ColorPicker();
        resistsColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateResistsColorWhenColorPickerIsUsed(resistsColor));


        Label tempText2 = new Label("N.Damage:");
        ColorPicker normalDamageColor = new ColorPicker();
        normalDamageColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateNormalDamageColorWhenColorPickerIsUsed(normalDamageColor));


        // set CSS
        setCSSType(tempText1, resistsColor);
        setCSSType(tempText2, normalDamageColor);
        setVerticalAlignmentToCenter(tempContainer);

        // set variable into the Singleton class
        settingsData.setResistsColorPicker(resistsColor);
        settingsData.setNormalDamageColorPicker(normalDamageColor);

        tempContainer.getChildren().addAll(tempText1, settingsData.getResistsColorPicker(), tempText2, settingsData.getNormalDamageColorPicker());
        return tempContainer;
    }

    private HBox createWeakVeryWeakSettings() {
        HBox tempContainer = new HBox();

        Label tempText1 = new Label("Weak:");
        ColorPicker weakColor = new ColorPicker();
        weakColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateWeakColorWhenColorPickerIsUsed(weakColor));


        Label tempText2 = new Label("VeryWeak:");
        ColorPicker veryWeakColor = new ColorPicker();
        veryWeakColor.valueProperty().addListener((observable, oldColor, newColor) -> controller.updateVeryWeakColorWhenColorPickerIsUsed(veryWeakColor));


        // set CSS
        setCSSType(tempText1, weakColor);
        setCSSType(tempText2, veryWeakColor);
        setVerticalAlignmentToCenter(tempContainer);

        // set variable into the Singleton class
        settingsData.setWeakColorPicker(weakColor);
        settingsData.setVeryWeakColorPicker(veryWeakColor);

        tempContainer.getChildren().addAll(tempText1, settingsData.getWeakColorPicker(), tempText2, settingsData.getVeryWeakColorPicker());
        return tempContainer;
    }

    // ---------- Save / Cancel ----------
    private HBox createSaveCancel() {
        HBox tempBox = new HBox(20);

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // set CSS
        setCSSSaveCancel(saveButton, cancelButton);

        // set variable into the Singleton class
        settingsData.setSaveButton(saveButton);
        settingsData.setCancelButton(cancelButton);

        // when saveButton is clicked -> do something
        saveButton.setOnAction(e -> controller.saveButtonIsPressed());

        // when cancelButton is clicked -> do something
        cancelButton.setOnAction(e -> settingsData.getMenustage().close());

        tempBox.setAlignment(Pos.CENTER);
        tempBox.getChildren().addAll(settingsData.getSaveButton(), settingsData.getCancelButton());

        settingsData.setSaveCancelContainer(tempBox);
        return settingsData.getSaveCancelContainer();
    }

    // ---------- CSS - Margin - Padding ----------
    private void setCSSSavedSetting(HBox hbox) {
        hbox.setPadding(new Insets(10, 0, 10, 0));
    }

    private void setCSSBackgroundSettings(CheckBox usePic, ComboBox<String> cb, ColorPicker cp, Label opacityLabel, TextField textField) {
        cb.setStyle("-fx-pref-width: 150;");
        cp.setStyle("-fx-pref-width: 150;");

        textField.setStyle("-fx-width: 80px;\n");
        textField.setMinSize(40.0, Control.USE_PREF_SIZE);
        textField.setMaxSize(40.0, Control.USE_PREF_SIZE);
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setAlignment(Pos.CENTER_RIGHT);

        // Set Margin
        HBox.setMargin(usePic, new Insets(0, 20, 0, 20));
        HBox.setMargin(cb, new Insets(0, 20, 0, 5));
        HBox.setMargin(cp, new Insets(0, 20, 0, 5));
        HBox.setMargin(opacityLabel, new Insets(0, 5, 0, 5));
    }

    private void setCSSHeaderBorder(Label lText, ColorPicker cColor, TextField textField) {
        lText.setMinSize(80.0, Control.USE_PREF_SIZE);
        lText.setMaxSize(80.0, Control.USE_PREF_SIZE);

        cColor.setMinSize(100.0, Control.USE_PREF_SIZE);
        cColor.setMaxSize(100.0, Control.USE_PREF_SIZE);
        HBox.setMargin(cColor, new Insets(0, 50, 0, 0));

        textField.setStyle("-fx-width: 80px;\n");
        textField.setMinSize(40.0, Control.USE_PREF_SIZE);
        textField.setMaxSize(40.0, Control.USE_PREF_SIZE);
        textField.setAlignment(Pos.CENTER_RIGHT);
    }

    private void setCSSType(Label lText, ColorPicker cColor) {
        lText.setMinSize(65.0, Control.USE_PREF_SIZE);
        lText.setMaxSize(65.0, Control.USE_PREF_SIZE);

        cColor.setMinSize(100.0, Control.USE_PREF_SIZE);
        cColor.setMaxSize(100.0, Control.USE_PREF_SIZE);
        HBox.setMargin(cColor, new Insets(0, 60, 0, 0));
    }

    private void setCSSSaveCancel(Button saveBtn, Button cancelBtn) {
        HBox.setMargin(saveBtn, new Insets(20, 0, 0, 0));
        HBox.setMargin(cancelBtn, new Insets(20, 0, 0, 0));
    }

    // Set Vertical Alignment of a Box to the Center
    private void setVerticalAlignmentToCenter(HBox box) {
        box.setAlignment(Pos.CENTER_LEFT);
    }
}
