package TypeChart.TypeChartApp;

import Type.Type;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public final class TypeChartAppSingleton {
    // make it a Singleton
    private static TypeChartAppSingleton INSTANCE;
    // Variables
    private int width;
    private int height;
    private Scene scene;
    private Type type1;
    private Type type2;
    // ----- Pane -----
    // Top
    private HBox comboboxes;
    // Background
    private BorderPane root;
    private StackPane centerLayout;
    private HBox backgroundImageContainer;
    // Center
    private VBox typeweaknessbox;

    // -- Label ---
    Label labelText;
    // ----- Data from Database -----
    // Active-Setting-Name
    private String activeSettingName;
    // background
    private String backgroundImage;
    private String backgroundColor;
    private String backgroundOpacity;
    private boolean usePicture;
    // header
    private String headerBackgroundColor;
    private String headerOpacity;
    // type-border
    private String typeBorderColor;
    private String typeBorderOpacity;
    // type-color
    private String immuneColor;
    private String stronglyResistsColor;
    private String resistsColor;
    private String normalDamageColor;
    private String weakColor;
    private String veryWeakColor;


    private TypeChartAppSingleton() {
        activeSettingName = "Default";
        // background
        backgroundImage = "typechartBackground.jpg";
        backgroundColor = "50, 50, 50";
        backgroundOpacity = "0.4";
        usePicture = true;
        // header
        headerBackgroundColor = "50, 50, 50";
        headerOpacity = "0.0";
        // type-border
        typeBorderColor = "50, 50, 50";
        typeBorderOpacity = "0.2";
        // type-color
        immuneColor = "0, 255, 255";
        stronglyResistsColor = "255, 105, 180";
        resistsColor = "255, 0, 0";
        normalDamageColor = "255, 255, 255";
        weakColor = "0, 255, 0";
        veryWeakColor = "191, 255, 0";
    }

    // this is how you create this class
    public static TypeChartAppSingleton getInstance() {
        if(INSTANCE == null) INSTANCE = new TypeChartAppSingleton();

        return INSTANCE;
    }

    // -------------- getters and setters -------------------------
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public HBox getComboboxes() {
        return comboboxes;
    }

    public void setComboboxes(HBox comboboxes) {
        this.comboboxes = comboboxes;
    }

    public VBox getTypeweaknessbox() {
        return typeweaknessbox;
    }

    public void setTypeweaknessbox(VBox typeweaknessbox) {
        this.typeweaknessbox = typeweaknessbox;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public StackPane getCenterLayout() {
        return centerLayout;
    }

    public void setCenterLayout(StackPane centerLayout) {
        this.centerLayout = centerLayout;
    }

    public HBox getBackgroundImageContainer() {
        return backgroundImageContainer;
    }

    public void setBackgroundImageContainer(HBox backgroundImageContainer) {
        this.backgroundImageContainer = backgroundImageContainer;
    }

    public String getActiveSettingName() {
        return activeSettingName;
    }

    public void setActiveSettingName(String activeSettingName) {
        this.activeSettingName = activeSettingName;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundOpacity() {
        return backgroundOpacity;
    }

    public void setBackgroundOpacity(String backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }

    public boolean isUsePicture() {
        return usePicture;
    }

    public void setUsePicture(boolean usePicture) {
        this.usePicture = usePicture;
    }

    public String getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(String headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public String getHeaderOpacity() {
        return headerOpacity;
    }

    public void setHeaderOpacity(String headerOpacity) {
        this.headerOpacity = headerOpacity;
    }

    public String getTypeBorderColor() {
        return typeBorderColor;
    }

    public void setTypeBorderColor(String typeBorderColor) {
        this.typeBorderColor = typeBorderColor;
    }

    public String getTypeBorderOpacity() {
        return typeBorderOpacity;
    }

    public void setTypeBorderOpacity(String typeBorderOpacity) {
        this.typeBorderOpacity = typeBorderOpacity;
    }

    public String getImmuneColor() {
        return immuneColor;
    }

    public void setImmuneColor(String immuneColor) {
        this.immuneColor = immuneColor;
    }

    public String getStronglyResistsColor() {
        return stronglyResistsColor;
    }

    public void setStronglyResistsColor(String stronglyResistsColor) {
        this.stronglyResistsColor = stronglyResistsColor;
    }

    public String getResistsColor() {
        return resistsColor;
    }

    public void setResistsColor(String resistsColor) {
        this.resistsColor = resistsColor;
    }

    public String getNormalDamageColor() {
        return normalDamageColor;
    }

    public void setNormalDamageColor(String normalDamageColor) {
        this.normalDamageColor = normalDamageColor;
    }

    public String getWeakColor() {
        return weakColor;
    }

    public void setWeakColor(String weakColor) {
        this.weakColor = weakColor;
    }

    public String getVeryWeakColor() {
        return veryWeakColor;
    }

    public void setVeryWeakColor(String veryWeakColor) {
        this.veryWeakColor = veryWeakColor;
    }

}
