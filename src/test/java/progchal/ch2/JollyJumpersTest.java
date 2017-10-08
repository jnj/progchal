package progchal.ch2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JollyJumpersTest {

    @Test
    public void is() {
        assertTrue(JollyJumpers.isJollyJumper(new int[]{1, 4, 2, 3}));
    }


    @Test
    public void isNot() {
        assertFalse(JollyJumpers.isJollyJumper(new int[]{1, 4, 2, -1, 6}));
    }
}