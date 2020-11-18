package Type;

import java.util.HashMap;

public class Type {
    HashMap<String, Double> typeweakness = new HashMap<String, Double>();
    private final String name;

    public Type(String type) {
        name = type;

        switch (type) {
            case "Normal" -> setStats(1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1);
            case "Fire" -> setStats(1, 0.5, 2, 0.5, 1, 0.5, 1, 1, 2, 1, 1, 0.5, 2, 1, 1, 1, 0.5, 0.5);
            case "Water" -> setStats(1, 0.5, 0.5, 2, 2, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1);
            case "Grass" -> setStats(1, 2, 0.5, 0.5, 0.5, 2, 1, 2, 0.5, 2, 1, 2, 1, 1, 1, 1, 1, 1);
            case "Electric" -> setStats(1, 1, 1, 1, 0.5, 1, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 0.5, 1);
            case "Ice" -> setStats(1, 2, 1, 1, 1, 0.5, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1);
            case "Fighting" -> setStats(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0.5, 0.5, 1, 1, 0.5, 1, 2);
            case "Poison" -> setStats(1, 1, 1, 0.5, 1, 1, 0.5, 0.5, 2, 1, 2, 0.5, 1, 1, 1, 1, 1, 0.5);
            case "Ground" -> setStats(1, 1, 2, 2, 0, 2, 1, 0.5, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 1);
            case "Flying" -> setStats(1, 1, 1, 0.5, 2, 2, 0.5, 1, 0, 1, 1, 0.5, 2, 1, 1, 1, 1, 1);
            case "Psychic" -> setStats(1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 0.5, 2, 1, 2, 1, 2, 1, 1);
            case "Bug" -> setStats(1, 2, 1, 0.5, 1, 1, 0.5, 1, 0.5, 2, 1, 1, 2, 1, 1, 1, 1, 1);
            case "Rock" -> setStats(0.5, 0.5, 2, 2, 1, 1, 2, 0.5, 2, 0.5, 1, 1, 1, 1, 1, 1, 2, 1);
            case "Ghost" -> setStats(0, 1, 1, 1, 1, 1, 0, 0.5, 1, 1, 1, 0.5, 1, 2, 1, 2, 1, 1);
            case "Dragon" -> setStats(1, 0.5, 0.5, 0.5, 0.5, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2);
            case "Dark" -> setStats(1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 2, 1, 0.5, 1, 0.5, 1, 2);
            case "Steel" -> setStats(0.5, 2, 1, 0.5, 1, 0.5, 2, 0, 2, 0.5, 0.5, 0.5, 0.5, 1, 0.5, 1, 0.5, 0.5);
            case "Fairy" -> setStats(1, 1, 1, 1, 1, 1, 0.5, 2, 1, 1, 1, 0.5, 1, 1, 0, 0.5, 2, 1);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Double> getTypeweakness() {
        return typeweakness;
    }

    private void setStats(double normal, double fire, double water, double grass, double electric, double ice, double fighting, double poison, double ground,
                          double flying, double psychic, double bug, double rock, double ghost, double dragon, double dark, double steel, double fairy) {
        typeweakness.put("Normal", normal);
        typeweakness.put("Fire", fire);
        typeweakness.put("Water", water);
        typeweakness.put("Grass", grass);
        typeweakness.put("Electric", electric);
        typeweakness.put("Ice", ice);
        typeweakness.put("Fighting", fighting);
        typeweakness.put("Poison", poison);
        typeweakness.put("Ground", ground);
        typeweakness.put("Flying", flying);
        typeweakness.put("Psychic", psychic);
        typeweakness.put("Bug", bug);
        typeweakness.put("Rock", rock);
        typeweakness.put("Ghost", ghost);
        typeweakness.put("Dragon", dragon);
        typeweakness.put("Dark", dark);
        typeweakness.put("Steel", steel);
        typeweakness.put("Fairy", fairy);
    }

}
