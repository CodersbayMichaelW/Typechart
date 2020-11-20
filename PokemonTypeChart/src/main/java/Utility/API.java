package Utility;

import TypeChart.TypeChartSetting.TypeChartSettingSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;

public class API {
    Connection connection;
    Utilities utility;
    File path;

    public API() {
        connection = null;
        utility = new Utilities();

//        try {
//            System.out.println("This is my PATH: " + this.getClass().getResource("./"));
//            System.out.println("This is my PATH: " + this.getClass().getClassLoader().getResource("Database"));
//            System.out.println("This is my PATH: " + new File(String.valueOf(getClass())).toPath());
//        } catch (Exception e) {
//            System.out.println(e);
//        }


        URL url = getClass().getResource("/Database");
        path = new File(String.valueOf(url));
    }

    public void connectToDB() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        // generate path
        String pathString = path.toString();
        pathString = pathString.replace("file:\\", "");
//        pathString = pathString.replace("jar:", "");

        connection = DriverManager.getConnection("jdbc:derby:" + pathString);
    }

    public HashMap<String, String> readCurrentTypeSettings() {
        try {
            connectToDB();
            // make query
            String query = "SELECT * FROM activesetting " +
                    "INNER JOIN typechartsettings ON activeSettingName = typechartsettings.typechartSettingName";
            // prepare query
            PreparedStatement getData = connection.prepareStatement(query);
            // execute query
            ResultSet rs = getData.executeQuery();
            // get the first row
            rs.next();

            // create HashMap to store data
            HashMap<String, String> typeSettings = new HashMap<>();
            typeSettings.put("activeSettingName", rs.getString("activeSettingName"));
            typeSettings.put("backgroundImage", rs.getString("backgroundImage"));
            typeSettings.put("backgroundColor", rs.getString("backgroundColor"));
            typeSettings.put("backgroundOpacity", rs.getString("backgroundOpacity"));
            typeSettings.put("headerBackgroundColor", rs.getString("headerBackgroundColor"));
            typeSettings.put("headerOpacity", rs.getString("headerOpacity"));
            typeSettings.put("typeBorderOpacity", rs.getString("typeBorderOpacity"));
            typeSettings.put("typeBorderColor", rs.getString("typeBorderColor"));
            typeSettings.put("immuneColor", rs.getString("immuneColor"));
            typeSettings.put("stronglyResistsColor", rs.getString("stronglyResistsColor"));
            typeSettings.put("resistsColor", rs.getString("resistsColor"));
            typeSettings.put("normalDamageColor", rs.getString("normalDamageColor"));
            typeSettings.put("weakColor", rs.getString("weakColor"));
            typeSettings.put("veryWeakColor", rs.getString("veryWeakColor"));
            typeSettings.put("usePicture", rs.getString("usePicture"));

            connection.close();
            return typeSettings;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }

        return null;
    }

    public ObservableList<String> getAllTypeSettingsName() {
        try {
            connectToDB();
            // make query
            String query = "SELECT typechartSettingName FROM typechartsettings ORDER BY typechartsettings.typechartSettingName ASC";

            // prepare query
            PreparedStatement getData = connection.prepareStatement(query);
            // execute query
            ResultSet rs = getData.executeQuery();

            // create HashMap to store data
            ObservableList<String> typeSettings = FXCollections.observableArrayList();
            while (rs.next()) {
                typeSettings.add(rs.getString("typechartSettingName"));
            }

            connection.close();
            return typeSettings;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }

        return null;
    }

    public void updateCurrentTypeSettings(String newVal) {
        try {
            // ------------------ change activesetting ------------------
            connectToDB();
            // make query
            String query = "UPDATE activesetting " +
                    "SET activeSettingName = ?";

            // prepare query
            PreparedStatement setData = connection.prepareStatement(query);
            setData.setString(1, newVal);
            // execute query
            setData.executeUpdate();
            // ------------------ change typechartsettings ------------------
            query = "UPDATE typechartsettings " +
                    "SET backgroundImage = ?, backgroundColor = ?, backgroundOpacity = ?, " +
                    "headerBackgroundColor = ?, headerOpacity = ?, " +
                    "typeBorderColor = ?, typeBorderOpacity = ?, " +
                    "immuneColor = ?, stronglyResistsColor = ?, resistsColor = ?, " +
                    "normalDamageColor = ?, weakColor = ?, veryWeakColor = ?, " +
                    "usePicture = ? " +
                    "WHERE typechartSettingName = ?";

            // prepare query
            setData = connection.prepareStatement(query);

            TypeChartSettingSingleton data = TypeChartSettingSingleton.getInstance();
            // Background
            setData.setString(1, TypeChartSettingSingleton.getInstance().getBackgroundImageContainer().getValue()); // Image
            setData.setString(2, utility.makeColorStringForDB(data.getBackgroundColorPicker())); // Color
            setData.setString(3, utility.makeColorOpacityString(data.getBackgroundColorPicker())); // Opacity

            // Header
            setData.setString(4, utility.makeColorStringForDB(data.getHeaderColorPicker())); // Color
            setData.setString(5, utility.makeColorOpacityString(data.getHeaderColorPicker())); // Opacity

            // Border
            setData.setString(6, utility.makeColorStringForDB(data.getBorderColorPicker())); // Color
            setData.setString(7, utility.makeColorOpacityString(data.getBorderColorPicker())); // Opacity
            // -------- Type --------
            // Immune
            setData.setString(8, utility.makeColorStringForDB(data.getImmuneColorPicker()));

            // StronglyResists
            setData.setString(9, utility.makeColorStringForDB(data.getStronglyResistsColorPicker()));

            // Resists
            setData.setString(10, utility.makeColorStringForDB(data.getResistsColorPicker()));

            // NormalDamage
            setData.setString(11, utility.makeColorStringForDB(data.getNormalDamageColorPicker()));

            // Weak
            setData.setString(12, utility.makeColorStringForDB(data.getWeakColorPicker()));

            // VeryWeak
            setData.setString(13, utility.makeColorStringForDB(data.getVeryWeakColorPicker()));

            // Use Picture
            String isSelected = TypeChartSettingSingleton.getInstance().getBackgroundUseImage().isSelected() ? "1" : "0";
            setData.setString(14, isSelected); // CheckBox

            // where it should be stored
            setData.setString(15, newVal);

            // execute query
            setData.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public HashMap<String, String> readSelectedTypeSettings(String newVal) {
        try {
            connectToDB();
            // make query
            String query = "SELECT * FROM typechartsettings " +
                    "WHERE typechartSettingName = ?";

            // prepare query
            PreparedStatement getData = connection.prepareStatement(query);
            getData.setString(1, newVal);
            // execute query
            ResultSet rs = getData.executeQuery();
            // get the first row
            rs.next();

            // create HashMap to store data
            HashMap<String, String> typeSettings = new HashMap<>();
            typeSettings.put("typechartSettingName", newVal);
            typeSettings.put("backgroundImage", rs.getString("backgroundImage"));
            typeSettings.put("backgroundColor", rs.getString("backgroundColor"));
            typeSettings.put("backgroundOpacity", rs.getString("backgroundOpacity"));
            typeSettings.put("headerBackgroundColor", rs.getString("headerBackgroundColor"));
            typeSettings.put("headerOpacity", rs.getString("headerOpacity"));
            typeSettings.put("typeBorderOpacity", rs.getString("typeBorderOpacity"));
            typeSettings.put("typeBorderColor", rs.getString("typeBorderColor"));
            typeSettings.put("immuneColor", rs.getString("immuneColor"));
            typeSettings.put("stronglyResistsColor", rs.getString("stronglyResistsColor"));
            typeSettings.put("resistsColor", rs.getString("resistsColor"));
            typeSettings.put("normalDamageColor", rs.getString("normalDamageColor"));
            typeSettings.put("weakColor", rs.getString("weakColor"));
            typeSettings.put("veryWeakColor", rs.getString("veryWeakColor"));
            typeSettings.put("usePicture", rs.getString("usePicture"));

            connection.close();
            return typeSettings;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }

        return null;
    }

    public boolean addSetting(String name, String template) {
        try {
            // ------------- get Template Data -------------
            HashMap<String, String> templateData = readSelectedTypeSettings(template);
            // ---------------------------------------------
            connectToDB();
            // make query
            // "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";
            String query = "INSERT INTO typechartsettings " +
                    "(typechartSettingName, backgroundImage, backgroundColor, backgroundOpacity, " +
                    "headerBackgroundColor, headerOpacity, typeBorderOpacity, typeBorderColor, " +
                    "immuneColor, stronglyResistsColor, resistsColor, normalDamageColor, " +
                    "weakColor, veryWeakColor, usePicture) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // prepare query
            PreparedStatement getData = connection.prepareStatement(query);
            getData.setString(1, name);
            getData.setString(2, templateData.get("backgroundImage"));
            getData.setString(3, templateData.get("backgroundColor"));
            getData.setString(4, templateData.get("backgroundOpacity"));
            getData.setString(5, templateData.get("headerBackgroundColor"));
            getData.setString(6, templateData.get("headerOpacity"));
            getData.setString(7, templateData.get("typeBorderOpacity"));
            getData.setString(8, templateData.get("typeBorderColor"));
            getData.setString(9, templateData.get("immuneColor"));
            getData.setString(10, templateData.get("stronglyResistsColor"));
            getData.setString(11, templateData.get("resistsColor"));
            getData.setString(12, templateData.get("normalDamageColor"));
            getData.setString(13, templateData.get("weakColor"));
            getData.setString(14, templateData.get("veryWeakColor"));
            getData.setString(15, templateData.get("usePicture"));
            // execute query
            getData.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteSetting(String value) {
        try {
            connectToDB();

            // make query
            String query = "DELETE FROM typechartsettings " +
                    "WHERE typechartSettingName = ?";

            // prepare query
            PreparedStatement getData = connection.prepareStatement(query);
            getData.setString(1, value);
            // execute query
            getData.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


}
