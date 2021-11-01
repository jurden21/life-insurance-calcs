package model;

import org.junit.Assert;
import org.junit.Test;

public class FunctionTests {

    private static final double INTEREST_RATE = 0.05;
    private static final double DELTA5 = 1e-5;
    private static final double DELTA10 = 1e-10;

    @Test
    public void getDx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 46765.92532;
        actual = function.getDx(15);
        Assert.assertEquals(expected, actual, DELTA5);
        expected = 17121.05454;
        actual = function.getDx(35);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  5758.54915;
        actual = function.getDx(55);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  1240.42411;
        actual = function.getDx(75);
        Assert.assertEquals(expected, actual, DELTA5);
    }

    @Test
    public void getNx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 897393.46284;
        actual = function.getNx(15);
        Assert.assertEquals(expected, actual, DELTA5);
        expected = 291216.60366;
        actual = function.getNx(35);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  74114.67634;
        actual = function.getNx(55);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =   8841.69700;
        actual = function.getNx(75);
        Assert.assertEquals(expected, actual, DELTA5);
    }

    @Test
    public void getSx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 15337699.64161;
        actual = function.getSx(15);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  4142859.68035;
        actual = function.getSx(35);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =   762059.14292;
        actual = function.getSx(55);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =    51635.82990;
        actual = function.getSx(75);
        Assert.assertEquals(expected, actual, DELTA5);
    }

    @Test
    public void getCx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 33.9002526273;
        actual = function.getCx(15);
        Assert.assertEquals(expected, actual, DELTA10);
        expected = 41.4377795091;
        actual = function.getCx(35);
        Assert.assertEquals(expected, actual, DELTA10);
        expected = 65.3981275294;
        actual = function.getCx(55);
        Assert.assertEquals(expected, actual, DELTA10);
        expected = 70.1912376358;
        actual = function.getCx(75);
        Assert.assertEquals(expected, actual, DELTA10);
    }

    @Test
    public void getMx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 4032.90328;
        actual = function.getMx(15);
        Assert.assertEquals(expected, actual, DELTA5);
        expected = 3253.59723;
        actual = function.getMx(35);
        Assert.assertEquals(expected, actual, DELTA5);
        expected = 2229.27884;
        actual = function.getMx(55);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  819.39092;
        actual = function.getMx(75);
        Assert.assertEquals(expected, actual, DELTA5);
    }

    @Test
    public void getRx() {
        double expected, actual;
        Function function = new Function(new InterestRate(INTEREST_RATE), new MortalityTable(MortalityTableType.MIXED));
        expected = 167026.81323;
        actual = function.getRx(15);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  93937.57126;
        actual = function.getRx(35);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =  37826.14573;
        actual = function.getRx(55);
        Assert.assertEquals(expected, actual, DELTA5);
        expected =   6382.84796;
        actual = function.getRx(75);
        Assert.assertEquals(expected, actual, DELTA5);
    }

}