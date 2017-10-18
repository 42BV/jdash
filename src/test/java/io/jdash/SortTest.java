package io.jdash;

import io.jdash.domain.MyBean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {
    
    @Test
    public void testSort() {
        List<Integer> values = Arrays.asList(3, 1, 2);
        List<Integer> sorted = J.sort(values);
        Assert.assertEquals(Arrays.asList(1, 2, 3), sorted);
    }

    @Test
    public void testSortByFunction() {
        MyBean a = new MyBean("a");
        MyBean b = new MyBean("b");
        MyBean c = new MyBean("c");
        
        List<MyBean> sorted = J.sort(J.asList(c, a, b), MyBean::getName);
        Assert.assertEquals(J.asList(a, b, c), sorted);
    }

}
