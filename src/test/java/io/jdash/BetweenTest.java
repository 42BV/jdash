package io.jdash;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class BetweenTest {
    
    private LocalDate today = J.today();
    private LocalDate yesterday = today.minusDays(1);
    private LocalDate tommorow = today.plusDays(2);

    @Test
    public void testBetween() {
        Assert.assertTrue(J.between(today, yesterday, tommorow));
    }
    
    @Test
    public void testBetweenEqual() {
        Assert.assertTrue(J.between(today, today, today));
    }
    
    @Test
    public void testBetweenNull() {
        Assert.assertTrue(J.between(today, null, null));
    }

    @Test
    public void testBetweenExpired() {
        Assert.assertFalse(J.between(yesterday, today, tommorow));
    }

    @Test
    public void testBetweenExpiredNoStart() {
        Assert.assertFalse(J.between(tommorow, null, today));
    }
    
    @Test
    public void testBetweenNotStarted() {
        Assert.assertFalse(J.between(tommorow, yesterday, today));
    }

    @Test
    public void testBetweenNotStartedNoEnd() {
        Assert.assertFalse(J.between(today, tommorow, null));
    }

}
