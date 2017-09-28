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
                "RO\n" +
                "OO"
        );
    }

    @Test
    public void horizLine() {
        bmp.create(10, 3);
        bmp.hLine(2, 8, 2, '-');
        assertBitmap(
                "OOOOOOOOOO\n" +
                "O-------OO\n" +
                "OOOOOOOOOO"
        );
    }

    @Test
    public void vertLine() {
        bmp.create(5, 4);
        bmp.vLine(2, 1, 3, '|');
        assertBitmap(
                "O|OOO\n" +
                "O|OOO\n" +
                "O|OOO\n" +
                "OOOOO"
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
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OO###############OOO\n" +
                "OOOOOOOOOOOOOOOOOOOO\n" +
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

    @Test
    public void unreachableRegion() {
        bmp.create(10, 10);
        bmp.vLine(3, 1, 10, '|');
        bmp.hLine(1, 2, 3, '-');
        bmp.fill(5, 2, 'X');
        assertBitmap(
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "--|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX\n" +
                "OO|XXXXXXX");
    }

    private void assertBitmap(String expected) {
        assertEquals(expected, bmp.toString());
    }
}