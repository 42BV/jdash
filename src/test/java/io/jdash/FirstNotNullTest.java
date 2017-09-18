package io.jdash;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FirstNotNullTest {

    @Test
    public void testFirstNotNullVarArg() {
        Assert.assertEquals("a", J.findNotNull(null, "a", "b", null, "c"));
    }
    
    @Test
    public void testFirstNotNullIterable() {
        List<String> values = J.asList(null, "a", "b", null, "c");
        Assert.assertEquals("a", J.findNotNull(values));
    }

    @Test(expected = NullPointerException.class)
    public void testFirstNotNullNone() {
        J.findNotNull(null, null, null);
    }

}
