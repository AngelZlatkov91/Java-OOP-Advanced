package football.core;

public interface Controller {
    String addField(String fieldType, String fieldName);
    String deliverySupplement(String type);
    String supplementForField(String fieldName, String supplementType);
    String addPlayer(String fieldName, String playerGender, String playerName, String nationality, int strength);
    String dragPlayer(String fieldName);
    public String calculateStrength(String fieldName);
    String getStatistics();



}
