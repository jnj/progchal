package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LcdTest {

    @Test
    public void test1() {
        Lcd lcd = new Lcd(312466, 6);
        assertEquals(
                " ------            ------            ------   ------ \n" +
                "       |        |        | |      | |        |       \n" +
                "       |        |        | |      | |        |       \n" +
                "       |        |        | |      | |        |       \n" +
                "       |        |        | |      | |        |       \n" +
                "       |        |        | |      | |        |       \n" +
                "       |        |        | |      | |        |       \n" +
                " ------            ------   ------   ------   ------ \n" +
                "       |        | |               | |      | |      |\n" +
                "       |        | |               | |      | |      |\n" +
                "       |        | |               | |      | |      |\n" +
                "       |        | |               | |      | |      |\n" +
                "       |        | |               | |      | |      |\n" +
                "       |        | |               | |      | |      |\n" +
                " ------            ------            ------   ------ \n",
                lcd.toString()
        );
    }

}