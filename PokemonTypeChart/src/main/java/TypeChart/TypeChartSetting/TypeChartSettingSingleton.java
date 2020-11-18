package TypeChart.TypeChartSetting;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class TypeChartSettingSingleton {
    // make it a Singleton
    private static TypeChartSettingSingleton INSTANCE;
    // New Stage
    private Stage menustage;
    // Settings
    private ComboBox<String> savedSettings;
    // Background
    private CheckBox backgroundUseImage;
    private ComboBox<String> backgroundImageContainer;
    private ColorPicker backgroundColorPicker;
    private TextField backgroundOpacityTextField;
    // Header
    private ColorPicker headerColorPicker;
    private TextField headerOpacityTextField;
    // Border
    private ColorPicker borderColorPicker;
    private TextField borderOpacityTextField;
    // TypeSettings
    private ColorPicker immuneColorPicker;
    private ColorPicker stronglyResistsColorPicker;
    private ColorPicker resistsColorPicker;
    private ColorPicker normalDamageColorPicker;
    private ColorPicker weakColorPicker;
    private ColorPicker veryWeakColorPicker;
    // Save and Cancel
    private Button saveButton;
    private Button cancelButton;

    // Containers for Pane
    private VBox settingsContainer;     // contains all
    private HBox savedSetting;          // saveSettings
    private HBox backgroundContainer;   // background
    private HBox headerContainer;       // header
    private HBox borderContainer;       // border
    private VBox typeContainer;         // Type
    private HBox saveCancelContainer;   // Save-Cancel

    TypeChartSettingSingleton() {
        menustage = new Stage();
    }

    // this is how you create this class
    public static TypeChartSettingSingleton getInstance() {
        if (INSTANCE == null) INSTANCE = new TypeChartSettingSingleton();

        return INSTANCE;
    }

    public Stage getMenustage() {
        return menustage;
    }

    public void setMenustage(Stage menustage) {
        this.menustage = menustage;
    }

    public ComboBox<String> getSavedSettings() {
        return savedSettings;
    }

    public void setSavedSettings(ComboBox<String> savedSettings) {
        this.savedSettings = savedSettings;
    }

    public CheckBox getBackgroundUseImage() {
        return backgroundUseImage;
    }

    public void setBackgroundUseImage(CheckBox backgroundUseImage) {
        this.backgroundUseImage = backgroundUseImage;
    }

    public ComboBox<String> getBackgroundImageContainer() {
        return backgroundImageContainer;
    }

    public void setBackgroundImageContainer(ComboBox<String> backgroundImageContainer) {
        this.backgroundImageContainer = backgroundImageContainer;
    }

    public ColorPicker getBackgroundColorPicker() {
        return backgroundColorPicker;
    }

    public void setBackgroundColorPicker(ColorPicker backgroundColorPicker) {
        this.backgroundColorPicker = backgroundColorPicker;
    }

    public TextField getBackgroundOpacityTextField() {
        return backgroundOpacityTextField;
    }

    public void setBackgroundOpacityTextField(TextField backgroundOpacityTextField) {
        this.backgroundOpacityTextField = backgroundOpacityTextField;
    }

    public ColorPicker getHeaderColorPicker() {
        return headerColorPicker;
    }

    public void setHeaderColorPicker(ColorPicker headerColorPicker) {
        this.headerColorPicker = headerColorPicker;
    }

    public TextField getHeaderOpacityTextField() {
        return headerOpacityTextField;
    }

    public void setHeaderOpacityTextField(TextField headerOpacityTextField) {
        this.headerOpacityTextField = headerOpacityTextField;
    }

    public ColorPicker getBorderColorPicker() {
        return borderColorPicker;
    }

    public void setBorderColorPicker(ColorPicker borderColorPicker) {
        this.borderColorPicker = borderColorPicker;
    }

    public TextField getBorderOpacityTextField() {
        return borderOpacityTextField;
    }

    public void setBorderOpacityTextField(TextField borderOpacityTextField) {
        this.borderOpacityTextField = borderOpacityTextField;
    }

    public ColorPicker getImmuneColorPicker() {
        return immuneColorPicker;
    }

    public void setImmuneColorPicker(ColorPicker immuneColorPicker) {
        this.immuneColorPicker = immuneColorPicker;
    }

    public ColorPicker getStronglyResistsColorPicker() {
        return stronglyResistsColorPicker;
    }

    public void setStronglyResistsColorPicker(ColorPicker stronglyResistsColorPicker) {
        this.stronglyResistsColorPicker = stronglyResistsColorPicker;
    }

    public ColorPicker getResistsColorPicker() {
        return resistsColorPicker;
    }

    public void setResistsColorPicker(ColorPicker resistsColorPicker) {
        this.resistsColorPicker = resistsColorPicker;
    }

    public ColorPicker getNormalDamageColorPicker() {
        return normalDamageColorPicker;
    }

    public void setNormalDamageColorPicker(ColorPicker normalDamageColorPicker) {
        this.normalDamageColorPicker = normalDamageColorPicker;
    }

    public ColorPicker getWeakColorPicker() {
        return weakColorPicker;
    }

    public void setWeakColorPicker(ColorPicker weakColorPicker) {
        this.weakColorPicker = weakColorPicker;
    }

    public ColorPicker getVeryWeakColorPicker() {
        return veryWeakColorPicker;
    }

    public void setVeryWeakColorPicker(ColorPicker veryWeakColorPicker) {
        this.veryWeakColorPicker = veryWeakColorPicker;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    public VBox getSettingsContainer() {
        return settingsContainer;
    }

    public void setSettingsContainer(VBox settingsContainer) {
        this.settingsContainer = settingsContainer;
    }

    public HBox getSavedSetting() {
        return savedSetting;
    }

    public void setSavedSetting(HBox savedSetting) {
        this.savedSetting = savedSetting;
    }

    public HBox getBackgroundContainer() {
        return backgroundContainer;
    }

    public void setBackgroundContainer(HBox backgroundContainer) {
        this.backgroundContainer = backgroundContainer;
    }

    public HBox getHeaderContainer() {
        return headerContainer;
    }

    public void setHeaderContainer(HBox headerContainer) {
        this.headerContainer = headerContainer;
    }

    public HBox getBorderContainer() {
        return borderContainer;
    }

    public void setBorderContainer(HBox borderContainer) {
        this.borderContainer = borderContainer;
    }

    public VBox getTypeContainer() {
        return typeContainer;
    }

    public void setTypeContainer(VBox typeContainer) {
        this.typeContainer = typeContainer;
    }

    public HBox getSaveCancelContainer() {
        return saveCancelContainer;
    }

    public void setSaveCancelContainer(HBox saveCancelContainer) {
        this.saveCancelContainer = saveCancelContainer;
    }
}
