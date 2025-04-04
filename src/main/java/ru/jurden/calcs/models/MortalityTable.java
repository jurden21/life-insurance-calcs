package ru.jurden.calcs.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.jurden.calcs.enums.MortalityTableType;

import java.util.Map;

/**
 * <p>Mortality table (also called a life table or actuarial table) is a table which shows, for each age, what the
 * probability is that a person of that age will die before their next birthday ("probability of death")</p>
 * <p>Таблица смертности - таблица, которая для каждого возраста показывает вероятность того, что человек этого
 * возраста умрет до своего следующего дня рождения (вероятность смерти)</p>
 */
@AllArgsConstructor
public class MortalityTable {
    @Getter
    private final MortalityTableType tableType;
    private final Map<Integer, Integer> lx;
    private final Map<Integer, Integer> dx;

    public int getLx(int x) {
        return lx.getOrDefault(x, 0);
    }

    public int getDx(int x) {
        return dx.getOrDefault(x, 0);
    }

    public int getMinAge() {
        return lx.keySet().stream().min(Integer::compareTo).orElseThrow();
    }

    public int getMaxAge() {
        return lx.keySet().stream().max(Integer::compareTo).orElseThrow();
    }
}