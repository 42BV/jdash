package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class TrimTest {
    
    @Test
    public void testTrim() {
        Assert.assertEquals("value", J.trim("  value "));
    }
    
    @Test
    public void testTrimNull() {
        Assert.assertEquals("", J.trim(null));
    }

}
