package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitmapTest {

    private final Bitmap bmp = new Bitmap();

    @Test
    public void create() {
        bmp.create(2, 3);
        assertBitmap(
                "OO\n" +
                "OO\n" +
                "OO"
        );
    }

    @Test
    public void label() {
        bmp.create(2, 3);
        bmp.label(1, 2, 'R');
        assertBitmap(
                "OO\n" +
                "OO\n" +
                "OR"
        );
    }

    @Test
    public void horizLine() {
        bmp.create(10, 3);
        bmp.hLine(2, 8, 1, '-');
        assertBitmap(
                "OOOOOOOOOO\n" +
                "OO-------O\n" +
                "OOOOOOOOOO"
        );
    }

    @Test
    public void vertLine() {
        bmp.create(5, 4);
        bmp.vLine(2, 0, 3, '|');
        assertBitmap(
                "OO|OO\n" +
                "OO|OO\n" +
                "OO|OO\n" +
                "OO|OO"
        );
    }

    @Test
    public void rectangle() {
        bmp.create(20, 18);
        bmp.rect(3, 4, 17, 15, '#');
        assertBitmap(
                "OOOOOOOOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOOOOOOOO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOO###############OO\n" +
                "OOOOOOOOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOOOOOOOO"
        );
    }

    @Test
    public void example1() {
        bmp.create(5, 6);
        bmp.label(2, 3, 'A');
        bmp.fill(3, 3, 'J');
        bmp.vLine(2, 3, 4, 'W');
        bmp.hLine(3, 4, 2, 'Z');
        assertBitmap(
                "JJJJJ\n" +
                "JJZZJ\n" +
                "JWJJJ\n" +
                "JWJJJ\n" +
                "JJJJJ\n" +
                "JJJJJ"
        );
    }

    private void assertBitmap(String expected) {
        assertEquals(expected, bmp.toString());
    }
}