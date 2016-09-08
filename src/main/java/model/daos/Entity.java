package model.daos;

/**
 * Created by acer on 11.08.2016.
 */
public enum Entity {
    THEMES("ThemesEntity"),
    THEORY("TheoryEntity"),
    TASKS("TasksEntity"),
    ACTIONS("ActionsEntity"),
    PARENTS("ParentsEntity"),
    PUPILS("PupilsEntity"),
    SCHOOLS("SchoolsEntity"),
    USERS("UsersEntity");

    private String tableName;

    Entity(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
