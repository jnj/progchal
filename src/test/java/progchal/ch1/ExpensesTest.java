package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpensesTest {

    @Test
    public void expenses1() {
        Expenses e = new Expenses(3);
        e.addCost(10.00);
        e.addCost(20.00);
        e.addCost(30.00);
        assertEquals(10.00, e.compute(), 1e-9);
    }

    @Test
    public void expenses2() {
        Expenses e = new Expenses(4);
        e.addCost(15.00);
        e.addCost(15.01);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(11.99, e.compute(), 1e-9);
    }

    @Test
    public void expenses3() {
        Expenses e = new Expenses(4);
        e.addCost(15.00);
        e.addCost(15.00);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(11.99, e.compute(), 1e-9);
    }

    @Test
    public void expenses4() {
        Expenses e = new Expenses(4);
        e.addCost(15.00);
        e.addCost(15.00);
        e.addCost(3.00);
        e.addCost(3.00);
        assertEquals(12.00, e.compute(), 1e-9);
    }

    @Test
    public void expenses5() {
        Expenses e = new Expenses(4);
        e.addCost(10.00);
        e.addCost(15.01);
        e.addCost(3.00);
        e.addCost(3.01);
        assertEquals(9.50, e.compute(), 1e-9);
    }
}