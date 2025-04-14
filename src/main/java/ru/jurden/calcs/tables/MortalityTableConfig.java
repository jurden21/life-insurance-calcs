package ru.jurden.calcs.tables;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.models.MortalityTable;

@Slf4j
@Component
public class MortalityTableConfig {

    @Bean
    public MortalityTable maleMortalityTable() {
        log.info("Create Male Mortality Table");
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        return factory.loadTable(MortalityTableType.MALE);
    }

    @Bean
    public MortalityTable femaleMortalityTable() {
        log.info("Create Female Mortality Table");
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        return factory.loadTable(MortalityTableType.FEMALE);
    }

    @Bean
    public MortalityTable mixedMortalityTable() {
        log.info("Create Mixed Mortality Table");
        MortalityTableParser parser = new MortalityTableParser();
        MortalityTableFactory factory = new MortalityTableFactory(parser);
        return factory.loadTable(MortalityTableType.MIXED);
    }

}
