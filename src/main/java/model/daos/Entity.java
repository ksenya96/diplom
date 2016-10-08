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
    TEACHERS("Teacher"),
    PUPILS_AND_PARENTS("PupilsAndParentsEntity"),
    PUPILS_AND_TEACHERS("PupilsAndTeachersEntity"),
    PUPILS_AND_THEMES("PupilsAndThemesEntity"),
    PUPILS_AND_TASKS("PupilsAndTasksEntity");

    private String tableName;

    Entity(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
