package io.jdash;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ToListTest {
    
    @Test
    public void testToList() {
        List<String> values = J.asList("a", "b", "c");
        Assert.assertEquals("a", values.get(0));
        Assert.assertEquals("b", values.get(1));
        Assert.assertEquals("c", values.get(2));
    }
    
}
