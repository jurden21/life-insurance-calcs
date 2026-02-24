package ru.jurden.calcs.table;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jurden.calcs.enums.MortalityTableType;
import ru.jurden.calcs.model.MortalityTable;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MortalityTableConfig {

    private final MortalityTableFactory factory;

    @Bean
    public MortalityTable maleMortalityTable() {
        log.info("Create Male Mortality Table");
        return factory.loadTable(MortalityTableType.MALE);
    }

    @Bean
    public MortalityTable femaleMortalityTable() {
        log.info("Create Female Mortality Table");
        return factory.loadTable(MortalityTableType.FEMALE);
    }

    @Bean
    public MortalityTable mixedMortalityTable() {
        log.info("Create Mixed Mortality Table");
        return factory.loadTable(MortalityTableType.MIXED);
    }

}
