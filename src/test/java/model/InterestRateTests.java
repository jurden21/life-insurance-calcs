package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InterestRateTests {

    private static final double INTEREST_RATE = 0.05;
    private static final double DELTA = 1e-10;
    private InterestRate interestRate;

    @Before
    public void setUp() {
        interestRate = new InterestRate(INTEREST_RATE);
    }

    @Test
    public void getI() {
        double expected, actual;
        expected = 0.05;
        actual = interestRate.getI();
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getIm() {
        double expected, actual;
        expected = 0.05;
        actual = interestRate.getIm(1);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.0490889377;
        actual = interestRate.getIm(4);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.0488894854;
        actual = interestRate.getIm(12);
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getB() {
        double expected, actual;
        expected = 0.0487901641;
        actual = interestRate.getB();
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getV() {
        double expected, actual;
        expected = 0.9523809523;
        actual = interestRate.getV();
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getD() {
        double expected, actual;
        expected = 0.0476190476;
        actual = interestRate.getD();
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getDm() {
        double expected, actual;
        expected = 0.0476190476;
        actual = interestRate.getDm(1);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.0484938103;
        actual = interestRate.getDm(4);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.0486911117;
        actual = interestRate.getDm(12);
        Assert.assertEquals(expected, actual, DELTA);
    }

}
