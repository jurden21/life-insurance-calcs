package model;

/**
 * Коммутационные функции - специальные функции, введенные для упрощения записи актуарных формул и снижения
 * трудоемкости расчетов, проводимых вручную. Функции теряют смысл при автоматизированных расчетах. Тут приведены для
 * истории и общего понимания. Эти посчитанные заранее округленные значения подставлялись в функции для ускорения
 * расчетов.
 */
public class Function {

    private final InterestRate interestRate;
    private final MortalityTable mortalityTable;

    public Function(InterestRate interestRate, MortalityTable mortalityTable) {
        this.interestRate = interestRate;
        this.mortalityTable = mortalityTable;
    }

    /**
     * Коммутационная функция D(x) = l(x) * v^x
     * @param x - age
     * @return value D(x)
     */
    public double getDx(int x) {
        return mortalityTable.getLx(x) * Math.pow(interestRate.getV(), x);
    }

    /**
     * Коммутационная функция N(x) = D(x) + D(x+1) + ... + D(w)
     * @param x - age
     * @return value N(x)
     */
    public double getNx(int x) {
        double value = 0.0;
        for (int i = x; i < mortalityTable.getMaxAge() + 1; i++)
            value += getDx(i);
        return value;
    }

    /**
     * Коммутационная функция S(x) = N(x) + N(x+1) + ... + N(w) = SUM (t-x+1) * D(t), t=x..w
     * @param x - age
     * @return value S(x)
     */
    public double getSx(int x) {
        double value = 0.0;
        for (int i = x; i < mortalityTable.getMaxAge() + 1; i++)
            // value += getNx(i);
            value += (i - x + 1) * getDx(i);
        return value;
    }

    /**
     * Коммутационная функция C(x) = d(x) * v^(x+1)
     * @param x - age
     * @return value C(x)
     */
    public double getCx(int x) {
        return mortalityTable.getDx(x) * Math.pow(interestRate.getV(), x + 1);
    }

    /**
     * Коммутационная функция M(x) = C(x) + C(x+1) + ... + C(w)
     * @param x - age
     * @return value M(x)
     */
    public double getMx(int x) {
        double value = 0.0;
        for (int i = x; i < mortalityTable.getMaxAge() + 1; i++)
            value += getCx(i);
        return value;
    }

    /**
     * Коммутационная функция R(x) = M(x) + M(x+1) + ... + M(w) = SUM (t-x+1) * C(t), t=x..w
     * @param x - age
     * @return value R(x)
     */
    public double getRx(int x) {
        double value = 0.0;
        for (int i = x; i < mortalityTable.getMaxAge() + 1; i++)
            // value += getMx(i);
            value += (i - x + 1) * getCx(i);
        return value;
    }

}
