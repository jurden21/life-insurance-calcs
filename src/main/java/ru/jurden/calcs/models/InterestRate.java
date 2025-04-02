package ru.jurden.calcs.models;

/**
 * <p>Annual effective interest rate.</p>
 * <p>Эффективная процентная ставка. Определяет размер дохода, получаемого в конце года при инвестировании единичной
 * денежной суммы на один год. При инвестировании единичной суммы с эффективной процентной ставкой i через год будет
 * получена сумма, равная (1 + i).</p>
 */
public class InterestRate {

    private final double value;

    /**
     * <p>Initiating interest rate.</p>
     * <p>Инициализация процентной ставки.</p>
     * @param percent percent value
     */
    public InterestRate(double percent) {
        this.value = percent / 100;
    }

    /**
     * <p>Annual effective interest rate.</p>
     * <p>Эффективная процентная ставка. Определяет размер дохода, получаемого в конце года при инвестировании единичной
     * денежной суммы на один год. При инвестировании единичной суммы с эффективной процентной ставкой i через год будет
     * получена сумма, равная (1 + i).</p>
     * @return Value of i
     */
    public double getI() {
        return value;
    }

    /**
     * <p>Nominal interest rate convertible m times a year, and is numerically equal to m times the effective rate of
     * interest over one m-th of a year.</p>
     * <p>Номинальная годовая процентная ставка - совокупный размер дохода, получаемого за год при инвестировании
     * единичной денежной суммы с начислением процентов через равные промежутки времени m раз в течение года по формуле
     * сложных процентов с годовой процентной ставкой i.</p>
     * @param m number of interest conversions, or compounding times, per year:<br/>
     * m = 1 - annual compounding or converting interest every year<br/>
     * m = 2 - semi-annual compounding or converting interest every six months<br/>
     * m = 4 - quarterly compounding or converting interest every three months<br/>
     * m = 12 - monthly compounding or converting interest every month
     * @return Value of i<sup>(m)</sup>
     */
    public double getIm(int m) {
        return m * (Math.pow(1 + value, 1.0 / m) - 1);
    }

    /**
     * <p>Сила процента (сила роста) характеризует размер дохода, полученного при непрерывном реинвестировании единичной
     * денежной суммы за бесконечно малый промежуток времени. Сила процента (сила роста) может быть постоянной или
     * изменяющейся во времени, она используется, как правило, для учета сложных закономерностей процесса накопления
     * дохода на вложенный капитал.</p>
     * @return Value of b
     */
    public double getB() {
        return Math.log(1 + value);
    }

    /**
     * <p>Present value factor or discount factor.</p>
     * <p>Дисконтирующий множитель показывает, какая сумма должна быть инвестирована в начале года с эффективной
     * процентной ставкой i для получения в конце года денежной суммы в размере 1.</p>
     * @return Value of v
     */
    public double getV() {
        return 1 / (1 + value);
    }

    /**
     * <p>Annual effective discount rate.</p>
     * <p>Эффективная ставка дисконтирования представляет собой годовой доход, полученный при инвестировании суммы v с
     * эффективной процентной ставкой i с целью получения в конце года единичной денежной суммы.</p>
     * @return Value of d
     */
    public double getD() {
        return value / (1 + value);
    }

    /**
     * <p>Nominal rate of discount convertible m times a year.</p>
     * <p>Номинальная ставка дисконтирования характеризует размер дохода, полученного при реинвестировании единичной
     * денежной суммы m раз в году.</p>
     * @param m number of interest conversions, or compounding times, per year:<br/>
     * m = 1 - annual compounding or converting interest every year<br/>
     * m = 2 - semi-annual compounding or converting interest every six months<br/>
     * m = 4 - quarterly compounding or converting interest every three months<br/>
     * m = 12 - monthly compounding or converting interest every month<br/>
     * @return Value of d<sup>(m)</sup>
     */
    public double getDm(int m) {
        return m * (1 - Math.pow(1 - getD(), 1.0 / m));
    }
}
