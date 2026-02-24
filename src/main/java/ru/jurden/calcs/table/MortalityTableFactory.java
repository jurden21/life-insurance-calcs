package ru.jurden.calcs.table;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.model.MortalityTable;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MortalityTableFactory {

    private final MortalityTableParser parser;

    public MortalityTable loadTable(MortalityTableType tableType) {
        String fileName = tableType.getResourceName();

        try (InputStream xmlStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (Objects.isNull(xmlStream)) {
                log.error("XML file {} not found", fileName);
                return null;
            }
            return parser.parse(xmlStream, tableType);
        } catch (Exception e) {
            log.error("Error loading table from {}: {}", fileName, e.getMessage());
            return null;
        }
    }
}