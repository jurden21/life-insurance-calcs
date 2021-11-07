package risks;

import model.*;

/**
 * Termless death risk - death without time limit
 */
public class DeathTermlessRisk {

    final private InterestRate interestRate;
    final private MortalityTable mortalityTable;

    public DeathTermlessRisk(InterestRate interestRate, MortalityTable mortalityTable) {
        this.interestRate = interestRate;
        this.mortalityTable = mortalityTable;
    }

    /**
     * Calculation of Risk
     * @param age - age
     * @param term - term
     * @return Value of Risk
     */
    public double Calc(int age) {

        double value = 0.0;

        /* Calculation using Function class
           function = new Function(interestRate, mortalityTable);
           value = (interestRate.getI() / interestRate.getB()) * function.getMx(age) / function.getDx(age);
         */

        for (int i = age; i <= mortalityTable.getMaxAge(); i++)
            value += Math.pow(interestRate.getV(), i + 1) * mortalityTable.getDx(i);
        value /= mortalityTable.getLx(age) * Math.pow(interestRate.getV(), age);
        value *= interestRate.getI() / interestRate.getB();

        return value;

    }

}
