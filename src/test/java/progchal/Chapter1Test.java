package progchal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static progchal.Chapter1.maxCycleLength;

public class Chapter1Test {

    @Test
    public void testMaxCycleLength() {
        assertEquals(20, maxCycleLength(1, 10));
        assertEquals(125, maxCycleLength(100, 200));
        assertEquals(89, maxCycleLength(201, 210));
        assertEquals(174, maxCycleLength(900, 1000));
    }

    @Test
    public void minesweeper1() {
        Chapter1.Minesweeper ms = new Chapter1.Minesweeper(4, 4);
        ms.putMineAt(0, 0);
        ms.putMineAt(2, 1);
        assertEquals(
                "*100\n" +
                "2210\n" +
                "1*10\n" +
                "1110",
                ms.toString()
        );
    }

    @Test
    public void minesweeper2() {
        Chapter1.Minesweeper ms = new Chapter1.Minesweeper(3, 5);
        ms.putMineAt(0, 0);
        ms.putMineAt(0, 1);
        ms.putMineAt(2, 1);
        assertEquals(
                "**100\n" +
                "33200\n" +
                "1*100",
                ms.toString()
        );
    }
}