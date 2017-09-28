package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterpreterTest {

    @Test
    public void example1() {
        Interpreter interpreter = new Interpreter();
        int[] ram = new int[]{299, 492, 495, 399, 492, 495, 399, 283, 279, 689, 78, 100, 0, 0, 0};

        for (int s : ram) {
            interpreter.initRam(s);
        }

        interpreter.run();
        assertEquals(16, interpreter.getCounter());
    }
}