package model;


/**
 * <p>Type of Mortality Table</p>
 * <p>Тип таблицы смертности</p>
 */
public enum MortalityTableType {

    MALE("male"),
    FEMALE("female"),
    MIXED("mixed");

    private final String node;

    MortalityTableType(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }

}
