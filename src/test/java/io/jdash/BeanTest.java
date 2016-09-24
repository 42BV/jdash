package io.jdash;

import io.jdash.domain.MyBean;

import org.junit.Assert;
import org.junit.Test;

public class BeanTest {
    
    @Test
    public void testInstantiate() {
        MyBean bean = J.instantiate(MyBean.class);
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean instanceof MyBean);
    }

    @Test
    public void testGet() {
        MyBean bean = new MyBean();
        bean.setName("Henk");
        
        Assert.assertEquals("Henk", J.get(bean, "name"));
    }
    
    @Test
    public void testSet() {
        MyBean bean = new MyBean();
        J.set(bean, "name", "Henk");
        
        Assert.assertEquals("Henk", bean.getName());
    }

    //
    // Wrapper
    //
    
    @Test
    public void testWrapByClass() {
        MyBean bean = J.bean(MyBean.class).instance();
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean instanceof MyBean);
    }
    
    @Test
    public void testWrapByInstance() {
        MyBean bean = new MyBean();
        MyBean result = J.wrap(bean).instance();
        Assert.assertEquals(bean, result);
    }

    @Test
    public void testWrapSetGet() {
        Assert.assertEquals("Henk", J.bean(MyBean.class).set("name", "Henk").get("name"));
    }

}
