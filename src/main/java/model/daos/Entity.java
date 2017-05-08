package model.daos;

/**
 * Created by acer on 11.08.2016.
 */
public enum Entity {
    THEMES("Theme"),
    THEORY("Theory"),
    TASKS("Task"),
    ACTIONS("Action"),
    PARENTS("Parent"),
    PUPILS("Pupil"),
    SCHOOLS("School"),
    USERS("User"),
    TEACHERS("Teacher");

    private String tableName;

    Entity(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
