package io.jdash;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JoinTest {
    
    @Test
    public void testJoin() {
        List<String> values = J.asList("a", "b", "c");
        Assert.assertEquals("a, b, c", J.join(values));
    }

}
