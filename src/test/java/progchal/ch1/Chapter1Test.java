package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static progchal.ch1.Chapter1.maxCycleLength;

public class Chapter1Test {

    @Test
    public void testMaxCycleLength() {
        assertEquals(20, maxCycleLength(1, 10));
        assertEquals(125, maxCycleLength(100, 200));
        assertEquals(89, maxCycleLength(201, 210));
        assertEquals(174, maxCycleLength(900, 1000));
    }
}