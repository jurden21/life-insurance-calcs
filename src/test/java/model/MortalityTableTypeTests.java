package model;

import org.junit.Assert;
import org.junit.Test;

public class MortalityTableTypeTests {

    @Test
    public void getNode() {
        String expected, actual;
        MortalityTableType type = MortalityTableType.MALE;
        expected = "male";
        actual = type.getNode();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void values() {
        MortalityTableType[] expected, actual;
        expected = MortalityTableType.values();
        actual = new MortalityTableType[] {MortalityTableType.MALE, MortalityTableType.FEMALE, MortalityTableType.MIXED};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void valueOf() {
        MortalityTableType expected, actual;
        expected = MortalityTableType.FEMALE;
        actual = MortalityTableType.valueOf("FEMALE");
        Assert.assertEquals(expected, actual);
    }

}
