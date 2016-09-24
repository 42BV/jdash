package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class UpperCaseTest {
    
    @Test
    public void testUpperCase() {
        Assert.assertEquals("VALUE", J.upperCase("value"));
    }
    
    @Test
    public void testUpperCaseNull() {
        Assert.assertEquals("", J.upperCase(null));
    }

}
