package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class DefaultStringTest {
    
    @Test
    public void testDefaultStringKeep() {
        Assert.assertEquals("value", J.defaultString("value"));
    }
    
    @Test
    public void testDefaultStringReplace() {
        Assert.assertEquals("", J.defaultString(null));
    }

}
