package io.jdash;

import org.junit.Assert;
import org.junit.Test;

public class DateTest {
    
    @Test
    public void testToday() {
        Assert.assertNotNull(J.today());
    }

    @Test
    public void testNow() {
        Assert.assertNotNull(J.now());
    }

}
