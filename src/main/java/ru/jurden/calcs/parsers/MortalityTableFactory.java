package ru.jurden.calcs.parsers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.models.MortalityTable;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class MortalityTableFactory {

    private final MortalityTableParser parser;

    public MortalityTable loadTable(MortalityTableType tableType) {
        String fileName = tableType.getResourceName();
        try (InputStream xmlStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (Objects.isNull(xmlStream)) {
                log.error("XML file {} not found", fileName);
            }
            return parser.parse(xmlStream, tableType);
        } catch (Exception e) {
            log.error("Stream from {} not created", fileName);
        }
        return null;
    }
}