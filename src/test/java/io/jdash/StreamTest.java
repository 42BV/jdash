package io.jdash;

import io.jdash.domain.MyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class StreamTest {
    
    @Test
    public void testFirst() {
        List<String> values = J.asList("a", "b", "c");
        Assert.assertEquals("a", J.find(values, value -> value != null));
    }
    
    @Test
    public void testFind() {
        List<String> values = J.asList("a", "b", "c");
        Assert.assertEquals("a", J.findOptional(values, value -> value != null).get());
    }
    
    @Test
    public void testFindNone() {
        List<String> values = J.asList("a", "b", "c");
        Assert.assertFalse(J.findOptional(values, value -> value == null).isPresent());
    }

    //
    // Wrapper
    //
    
    @Test
    public void testWrap() {
        Stream<String> stream = J.asList("a", "b", "c").stream();
        Assert.assertEquals(stream, J.wrap(stream).stream());
    }
    
    @Test
    public void testWrapAsList() {
        List<String> list = J.asList("a", "b", "c");
        Assert.assertEquals(list, J.stream(list).asList());
    }
    
    @Test
    public void testWrapAsSet() {
        Set<String> set = J.asSet("a", "b", "c");
        Assert.assertEquals(set, J.stream(set).asSet());
    }
    
    @Test
    public void testWrapperFirst() {
        Assert.assertEquals("a", J.stream("a", "b", "c").find().orElse(null));
    }
    
    @Test
    public void testWrapperValue() {
        Assert.assertEquals("a", J.stream("a", "b", "c").value());
    }
    
    @Test(expected = NullPointerException.class)
    public void testWrapperValueNull() {
        J.stream("a", "b", "c").filter(value -> value == null).value();
    }
    
    @Test
    public void testPick() {
        MyBean bean = new MyBean();
        bean.setName("Henk");
        
        Assert.assertEquals("Henk", J.stream(bean).pick("name", String.class).value());
    }

    @Test
    public void testFilterAndDo() {
        MyBean myBean = new MyBean();
        myBean.setName("Jan");

        Consumer<List<MyBean>> updateNameToLastValueInStream = (beans) -> myBean.setName(beans.get(beans.size() - 1).getName());

        List<MyBean> beans = new ArrayList<MyBean>() {{
            add(new MyBean() {{
                setName("Dirk");
            }});

            add(new MyBean() {{
                setName("Pappie");
            }});

            add(new MyBean() {{
                setName("Ankie");
            }});
        }};

        List<MyBean> filtered = J.stream(beans).filterAndDo((bean) -> !bean.getName().equals("Pappie"), updateNameToLastValueInStream).asList();
        Assert.assertEquals(2, filtered.size());
        Assert.assertEquals("Dirk", filtered.get(0).getName());
        Assert.assertEquals("Ankie", filtered.get(1).getName());

        Assert.assertEquals("Ankie", myBean.getName());
    }

}
