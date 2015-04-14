package hu.elte.java.emberek;

import junit.framework.Assert;
import org.junit.Test;

public class EmberTest {

    @Test(expected = NullPointerException.class)
    public void testEmber() {
        Ember ember = new Ember("Rozi", null, 0, 0, 0);
    }

    @Test
    public void testEquals() {
        Ember ember = new Ember("Rozi", "ferfi", 0, 0, 0);
        Ember ember2 = new Ember("Rozi", "ferfi", 0, 0, 0);
        Ember ember3 = new Ember("Rozi", "no", 0, 0, 0);
        Assert.assertTrue(ember.equals(ember));
        Assert.assertTrue(ember.equals(ember2));
        Assert.assertTrue(ember.equals(ember3));
    }
}
