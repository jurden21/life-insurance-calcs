package ru.jurden.calcs.enums;

import lombok.Getter;

/**
 * <p>Type of Mortality Table</p>
 * <p>Тип таблицы смертности</p>
 */
@Getter
public enum MortalityTableType {

    MALE("male-mortality-table.xml"),
    FEMALE("female-mortality-table.xml"),
    MIXED("mixed-mortality-table.xml"),
    ;

    private final String resourceName;

    MortalityTableType(String resourceName) {
        this.resourceName = resourceName;
    }

    public static MortalityTableType getByName(String name) {
        for (MortalityTableType mortalityTableType : MortalityTableType.values()) {
            if (mortalityTableType.name().equalsIgnoreCase(name)) {
                return mortalityTableType;
            }
        }
        return null;
    }
}
