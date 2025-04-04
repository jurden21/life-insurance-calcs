package ru.jurden.calcs.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>Type of Mortality Table</p>
 * <p>Тип таблицы смертности</p>
 */
@Getter
@RequiredArgsConstructor
public enum MortalityTableType {

    MALE("male-mortality-table.xml"),
    FEMALE("female-mortality-table.xml"),
    MIXED("mixed-mortality-table.xml"),
    ;

    private final String resourceName;
}
