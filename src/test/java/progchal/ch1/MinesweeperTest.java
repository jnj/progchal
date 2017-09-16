package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinesweeperTest {

    @Test
    public void minesweeper1() {
        Minesweeper ms = new Minesweeper(4, 4);
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
        Minesweeper ms = new Minesweeper(3, 5);
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