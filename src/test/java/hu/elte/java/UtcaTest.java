package hu.elte.java;


import junit.framework.Assert;
import org.junit.Test;

public class UtcaTest {
    @Test
    public void testPay() {
        Utca utca = new Utca();
        utca.pay(5, 5);
        Assert.assertEquals(false, true);
    }
}
