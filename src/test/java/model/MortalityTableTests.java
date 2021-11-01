package model;

import org.junit.Assert;
import org.junit.Test;

public class MortalityTableTests {

    private static final double DELTA = 1e-10;

    @Test
    public void getType() {
        MortalityTableType expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MALE);
        expected = MortalityTableType.MALE;
        actual = mortality.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMinAge() {
        int expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.FEMALE);
        expected = 0;
        actual = mortality.getMinAge();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMaxAge() {
        int expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MIXED);
        expected = 100;
        actual = mortality.getMaxAge();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLx() {
        int expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MALE);
        expected = 97021;
        actual = mortality.getLx(15);
        Assert.assertEquals(expected, actual);
        expected = 94224;
        actual = mortality.getLx(25);
        Assert.assertEquals(expected, actual);
        expected = 89351;
        actual = mortality.getLx(35);
        Assert.assertEquals(expected, actual);
        expected = 81391;
        actual = mortality.getLx(45);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDx() {
        int expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MALE);
        expected = 115;
        actual = mortality.getDx(15);
        Assert.assertEquals(expected, actual);
        expected = 409;
        actual = mortality.getDx(25);
        Assert.assertEquals(expected, actual);
        expected = 620;
        actual = mortality.getDx(35);
        Assert.assertEquals(expected, actual);
        expected = 1068;
        actual = mortality.getDx(45);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPnx() {
        double expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MALE);
        expected = 0.9487533626;
        actual = mortality.getPnx(15, 15);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.8855917790;
        actual = mortality.getPnx(15, 25);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.7128920888;
        actual = mortality.getPnx(45, 15);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.4372105023;
        actual = mortality.getPnx(45, 25);
        Assert.assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getQnx() {
        double expected, actual;
        MortalityTable mortality = new MortalityTable(MortalityTableType.MALE);
        expected = 0.0512466373;
        actual = mortality.getQnx(15, 15);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.1144082209;
        actual = mortality.getQnx(15, 25);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.2871079111;
        actual = mortality.getQnx(45, 15);
        Assert.assertEquals(expected, actual, DELTA);
        expected = 0.5627894976;
        actual = mortality.getQnx(45, 25);
        Assert.assertEquals(expected, actual, DELTA);
    }

}