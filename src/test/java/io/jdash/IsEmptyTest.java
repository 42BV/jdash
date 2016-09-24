package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class IsEmptyTest {

    @Test
    public void testNull() {
        Assert.assertTrue(J.isEmpty(null));
    }
    
    @Test
    public void testEmptyIterable() {
        Assert.assertTrue(J.isEmpty(J.asList()));
    }
    
    @Test
    public void testIterable() {
        Assert.assertFalse(J.isEmpty(J.asList(1, 2, 3)));
    }

    @Test
    public void testEmptyString() {
        Assert.assertTrue(J.isEmpty(""));
    }
    
    @Test
    public void testString() {
        Assert.assertFalse(J.isEmpty("value"));
    }
    
    @Test
    public void testOther() {
        Assert.assertFalse(J.isEmpty(1));
    }

}
