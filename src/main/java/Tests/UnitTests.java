package Tests;

import decathlon.Deca100M;
import heptathlon.Hep200M;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UnitTests
{
    @Test
    public void test100MUnderMinValue()
    {
        Deca100M deca100M = new Deca100M();
        double expected = 4;
        double actual = deca100M.getMinBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }

    @Test
    public void test100MMinValue()
    {
        Deca100M deca100m = new Deca100M();
        double expected = 5;
        double actual = deca100m.getMinBoundaryValue();
        double delta = 0.001;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void test100MAboveMaxValue()
    {
        Deca100M deca100M = new Deca100M();
        double expected = 21;
        double actual = deca100M.getMaxBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }

    @Test
    public void Test100MAtNegativeValue()
    {
        Deca100M deca100M = new Deca100M();
        double expected = -1;
        double actual = deca100M.getMinBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }

    @Test
    public void TestHep200MUnderMinValue()
    {
        Hep200M hep200M = new Hep200M();
        double expected = 19;
        double actual = hep200M.getMinBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }

    @Test
    public void testHep200MMinValue()
    {
        Hep200M hep200M = new Hep200M();
        double expected = 20;
        double actual = hep200M.getMinBoundaryValue();
        double delta = 0.001;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void test200MAboveMaxValue()
    {
        Hep200M hep200M = new Hep200M();
        double expected = 101;
        double actual = hep200M.getMaxBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }

    @Test
    public void test200MAtNegativeValue()
    {
        Hep200M hep200M = new Hep200M();
        double expected = -1;
        double actual = hep200M.getMinBoundaryValue();
        double delta = 0.001;
        assertNotEquals(expected, actual, delta);
    }
}