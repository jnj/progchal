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

    @Test
    public void expenses1() {
        Chapter1.Expenses e = new Chapter1.Expenses(3);
        e.addCost(10.00);
        e.addCost(20.00);
        e.addCost(30.00);
        assertEquals(10.00, e.compute(), 1e-9);
    }

    @Test
    public void expenses2() {
        Chapter1.Expenses e = new Chapter1.Expenses(4);
        e.addCost(15.00);
        e.addCost(15.01);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(11.99, e.compute(), 1e-9);
    }

    @Test
    public void expenses3() {
        Chapter1.Expenses e = new Chapter1.Expenses(4);
        e.addCost(15.00);
        e.addCost(15.00);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(11.99, e.compute(), 1e-9);
    }

    @Test
    public void expenses4() {
        Chapter1.Expenses e = new Chapter1.Expenses(4);
        e.addCost(15.00);
        e.addCost(15.00);
        e.addCost(3.00);
        e.addCost(3.00);
        assertEquals(12.00, e.compute(), 1e-9);
    }

    @Test
    public void expenses5() {
        Chapter1.Expenses e = new Chapter1.Expenses(4);
        e.addCost(10.00);
        e.addCost(15.01);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(9.50, e.compute(), 1e-9);
    }
}