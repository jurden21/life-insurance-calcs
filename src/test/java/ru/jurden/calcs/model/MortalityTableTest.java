package ru.jurden.calcs.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.table.MortalityTableFactory;
import ru.jurden.calcs.table.MortalityTableParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MortalityTableTest {

    @Test
    void checkMaleMortalityTable() {
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        MortalityTable table = factory.loadTable(MortalityTableType.MALE);
        assertNotNull(table);
        assertEquals(101, table.getMaxAge());
        for (int i = table.getMinAge(); i < table.getMaxAge(); i++) {
            assertEquals(table.getLx(i + 1), table.getLx(i) - table.getDx(i));
        }

    }

    @Test
    void checkFemaleMortalityTable() {
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        MortalityTable table = factory.loadTable(MortalityTableType.FEMALE);
        assertNotNull(table);
        assertEquals(101, table.getMaxAge());
        for (int i = table.getMinAge(); i < table.getMaxAge(); i++) {
            assertEquals(table.getLx(i + 1), table.getLx(i) - table.getDx(i));
        }
    }

    @Test
    void checkMixedMortalityTable() {
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        MortalityTable table = factory.loadTable(MortalityTableType.MIXED);
        assertNotNull(table);
        assertEquals(101, table.getMaxAge());
        for (int i = table.getMinAge(); i < table.getMaxAge(); i++) {
            assertEquals(table.getLx(i + 1), table.getLx(i) - table.getDx(i));
        }
    }
}
