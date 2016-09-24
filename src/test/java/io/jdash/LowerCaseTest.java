package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class LowerCaseTest {
    
    @Test
    public void testLowerCase() {
        Assert.assertEquals("value", J.lowerCase("VALUE"));
    }
    
    @Test
    public void testLowerCaseNull() {
        Assert.assertEquals("", J.lowerCase(null));
    }

}
