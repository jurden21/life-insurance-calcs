package model;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Mortality table (also called a life table or actuarial table) is a table which shows, for each age, what the
 * probability is that a person of that age will die before their next birthday ("probability of death")</p>
 * <p>Таблица смертности - таблица, которая для каждого возраста показывает вероятность того, что человек этого
 * возраста умрет до своего следующего дня рождения (вероятность смерти)</p>
 */
public class MortalityTable {

    private final int MIN_AGE = 0;
    private final MortalityTableType type;
    private int maxAge = MIN_AGE - 1;
    private final Map<Integer, Integer> lx;
    private final Map<Integer, Integer> dx;

    public MortalityTable(MortalityTableType type) {
        this.type = type;
        this.lx = new HashMap<>();
        this.dx = new HashMap<>();
        loadFromXml(type);
    }

    /**
     * <p>Loading mortality table</p>
     * <p>Загрузка данных таблицы смертности</p>
     * @param type Type of Mortality table - name of node
     */
    private void loadFromXml(MortalityTableType type) {

        String MORTALITY_TABLE_FILENAME = "/mortality-tables.xml";
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {

            URI uri = Objects.requireNonNull(getClass().getResource(MORTALITY_TABLE_FILENAME)).toURI();
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(Files.newInputStream(Paths.get(uri)));

            while (reader.hasNext()) {
                if ((reader.next() == XMLEvent.START_ELEMENT) && (reader.getLocalName().equals(type.getNode()))) {
                    while (reader.hasNext()) {
                        int event = reader.next();
                        if ((event == XMLEvent.START_ELEMENT) && (reader.getLocalName().equals("data"))) {
                            int x = Integer.parseInt(reader.getAttributeValue(null, "x"));
                            lx.put(x, Integer.parseInt(reader.getAttributeValue(null, "lx")));
                            dx.put(x, Integer.parseInt(reader.getAttributeValue(null, "dx")));
                            maxAge = Math.max(maxAge, x - 1);
                        }
                        if ((event == XMLEvent.END_ELEMENT) && (reader.getLocalName().equals(type.getNode())))
                            break;
                    }
                }
            }
            reader.close();
        }
        catch (URISyntaxException | IOException | XMLStreamException | NullPointerException ex) {
            lx.clear();
            dx.clear();
            ex.printStackTrace();
        }

    }

    /**
     * <p>Type of mortality table</p>
     * <p>Тип таблицы смертности</p>
     * @return type of Life table
     */
    public MortalityTableType getType() {
        return type;
    }

    /**
     * <p>Minimum age in mortality table</p>
     * <p>Минимальный возраст в таблице смертности</p>
     * @return minimum age
     */
    public int getMinAge() {
        return MIN_AGE;
    }

    /**
     * <p>Maximum age in mortality table, or it's the limiting age of the mortality tables - w</p>
     * <p>Максимальный возраст в таблице смертности - w</p>
     * @return minimum age
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * <p>l(x) - показатель таблицы смертности, характеризующий число лиц из наблюдаемой совокупности, доживших до
     * возраста x лет. Значения Lx приводятся в таблице смертности при целых x (x = 0, 1, 2,.. w, где w - предельный
     * возраст таблицы смертности).</p>
     * <p>l(x) is the number of people alive, relative to an original cohort, at age x. As age increases the number of
     * people alive decreases.</p>
     * @param x - age
     * @return value l(x)
     */
     public int getLx(int x) {
         return lx.get(x);
     }

    /**
     * <p>d(x) = l(x) - l(x+1) - показатель таблицы смертности, характеризующий число лиц, умерших в возрасте от x
     * лет до возраста x+1 год.</p>
     * <p>d(x) is the number of people who die between age x and age x+1. d(x) may be calculated using the formula
     * d(x) = l(x) - l(x+1).</p>
     * @param x - age
     * @return value d(x)
     */
     public int getDx(int x) {
         return dx.get(x);
     }

    /**
     * <p>(n)p(x) = l(x+n) / l(x) - вероятность для лица в возрасте x лет дожить до возраста x + n лет. Если n = 1,
     * p(x) - вероятность того, что человек возраста x через год будет жив.</p>
     * <p>(n)p(x) is the probability that a life age x will survive to age x+n.</p>
     * @param x - age
     * @param n - period
     * @return value (n)p(x)
     */
    public double getPnx(int x, int n) {
        return 1.0 * getLx(x + n) / getLx(x);
    }

    /**
     * <p>(n)q(x) = (SUM [t=0;t<n] d(x+t)) / l(x) = (l(x) - l(x+n)) / l(x) - вероятность для лица в возрасте x лет
     * умереть в * течение предстоящих n лет.
     * Если n = 1, q(x) - вероятность того, что человек возраста x умрет в предстоящем году.
     * Определяем, но не считаем:
     * (n|)q(x) - вероятность того, что лицо, возраста x лет, умрет в возрасте от x + n до x + n + 1 года.
     * (n|m)q(x) - вероятность того, что лицо, возраста x лет, умрет в возрасте от x + n до x + n + m лет.</p>
     * <p>(n)q(x) is the probability of death between the ages of x and age x+n.</p>
     * @param x - age
     * @param n - period
     * @return value nQx
     */
    public double getQnx(int x, int n) {
        return 1.0 * (getLx(x) - getLx(x + n)) / getLx(x);
    }

}
