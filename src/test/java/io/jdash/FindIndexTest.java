package io.jdash;

import io.jdash.domain.MyBean;

import org.junit.Assert;
import org.junit.Test;

public class FindIndexTest {
    
    @Test
    public void testFindIndex() {
        MyBean bean = new MyBean();
        Assert.assertEquals(0, J.findIndex(J.asList(bean), bean));
    }

}
