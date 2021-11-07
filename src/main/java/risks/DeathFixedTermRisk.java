package risks;

import model.*;

/**
 * Fixed term death risk - death within certain period
 */
public class DeathFixedTermRisk {

    final private InterestRate interestRate;
    final private MortalityTable mortalityTable;

    public DeathFixedTermRisk(InterestRate interestRate, MortalityTable mortalityTable) {
        this.interestRate = interestRate;
        this.mortalityTable = mortalityTable;
    }

    /**
     * Calculation of Risk
     * @param age - age
     * @param period - period
     * @return Value of Risk
     */
    public double Calc(int age, int period) {

        double value = 0.0;

        /* Calculation using Function class
           function = new Function(interestRate, mortalityTable);
           value = (interestRate.getI() / interestRate.getB()) * (function.getMx(age) - function.getMx(age + period)) / function.getDx(age);
         */

        for (int i = age; i < age + period; i++)
            value += Math.pow(interestRate.getV(), i + 1) * mortalityTable.getDx(i);
        value /= mortalityTable.getLx(age) * Math.pow(interestRate.getV(), age);
        value *= interestRate.getI() / interestRate.getB();

        return value;

    }

}
