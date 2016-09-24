package io.jdash;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CountTest {
    
    @Test
    public void testCount() {
        List<String> values = J.asList("aa", "ab", "bc");
        Assert.assertEquals(2, J.count(values, value -> value.startsWith("a")));
    }

}
