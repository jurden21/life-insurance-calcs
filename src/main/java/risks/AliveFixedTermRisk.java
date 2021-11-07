package risks;

import model.*;

/**
 *
 */
public class AliveFixedTermRisk {

    final private InterestRate interestRate;
    final private MortalityTable mortalityTable;

    public AliveFixedTermRisk(InterestRate interestRate, MortalityTable mortalityTable) {
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
           value = function.getDx(age + period) / function.getDx(age);
         */

        value = mortalityTable.getLx(age + period) * Math.pow(interestRate.getV(), period) / mortalityTable.getLx(age);

        return value;

    }

}
