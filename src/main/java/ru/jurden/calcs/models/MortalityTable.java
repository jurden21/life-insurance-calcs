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

    public int getMinAge() {
        return lx.keySet().stream().min(Integer::compareTo).orElseThrow();
    }

    public int getMaxAge() {
        return lx.keySet().stream().max(Integer::compareTo).orElseThrow();
    }

    /**
     * <p>l<sub>x</sub> - показатель таблицы смертности, характеризующий число лиц из наблюдаемой совокупности,
     * доживших до возраста x лет. Значения l приводятся в таблице смертности при целых x (x = 0, 1, 2,.. w,
     * где w - предельный возраст таблицы смертности).</p>
     */
    public int getLx(int x) {
        return lx.getOrDefault(x, 0);
    }

    /**
     * <p>d<sub>x</sub> = l<sub>x</sub> - l<sub>x+1</sub> - показатель таблицы смертности, характеризующий
     * число лиц, умерших в возрасте от x лет до возраста x+1 год.</p>
     */
    public int getDx(int x) {
        return dx.getOrDefault(x, 0);
    }

    /**
     * <p><sub>n</sub>p<sub>x</sub> = l<sub>x+n</sub> / l<sub>x</sub> - вероятность для лица в возрасте x лет
     * дожить до возраста x+n лет.</p>
     */
    public double getPnx(int x, int n) {
        return 1.0 * getLx(x + n) / getLx(x);
    }

    /**
     * <p><sub>n</sub>q<sub>x</sub> = (l<sub>x</sub> - l<sub>x+n</sub>) / l<sub>x</sub> - вероятность для лица
     * в возрасте x лет умереть в течение предстоящих n лет.</p>
     */
    public double getQnx(int x, int n) {
        return 1.0 * (getLx(x) - getLx(x + n)) / getLx(x);
    }
}