package io.jdash;

import io.jdash.domain.MyBean;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FilterTest {
    
    @Test
    public void testFilter() {
        MyBean a = new MyBean("a");
        MyBean b = new MyBean("b");
        MyBean c = new MyBean("c");
        
        List<MyBean> filtered = J.filter(J.asList(a, b, c), (x) -> x.getName().equals("b"));
        Assert.assertEquals(J.asList(b), filtered);
    }

}
