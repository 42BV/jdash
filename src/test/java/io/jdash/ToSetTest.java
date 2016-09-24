package io.jdash;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ToSetTest {
    
    @Test
    public void testToList() {
        Set<String> values = J.asSet("a", "b", "c");
        Assert.assertTrue(values.contains("a"));
        Assert.assertTrue(values.contains("b"));
        Assert.assertTrue(values.contains("c"));
    }
    
}
