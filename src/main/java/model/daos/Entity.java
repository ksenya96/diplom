package model.daos;

/**
 * Created by acer on 11.08.2016.
 */
public enum Entity {
    THEMES("ThemesEntity"),
    THEORY("TheoryEntity");

    private String tableName;

    Entity(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
