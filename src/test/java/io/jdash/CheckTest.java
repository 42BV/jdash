package io.jdash;

import org.junit.Test;

public class CheckTest {
    
    @Test
    public void testCheckNotNull() {
        J.checkNotNull("value", "Value should not be null");
    }
    
    @Test(expected = NullPointerException.class)
    public void testCheckNull() {
        J.checkNotNull(null, "Value should not be null");
    }

    @Test
    public void testCheckArgument() {
        J.checkArgument("value" != null, "Value should not be null");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentFail() {
        J.checkArgument("value" == null, "Value should be null");
    }
    
    @Test
    public void testCheckState() {
        J.checkState("value" != null, "Value should not be null");
    }
    
    @Test(expected = IllegalStateException.class)
    public void testStateFail() {
        J.checkState("value" == null, "Value should be null");
    }

}
