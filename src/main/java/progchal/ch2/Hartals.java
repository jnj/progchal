package progchal.ch2;

public class Hartals {

    public static void main(String[] args) {
        System.out.println(daysLost(14, 3, 4, 8));
    }

    private static int daysLost(int n, int h1, int... rest) {
        var count = 0;

        for (var i = 1; i <= n; i++) {
            var lost = isLostDay(h1, i);

            if (!lost) {
                for (var h : rest) {
                    if (isLostDay(h, i)) {
                        lost = true;
                        break;
                    }
                }
            }

            if (lost) {
                count++;
            }
        }

        return count;
    }

    private static boolean isLostDay(int h, int i) {
        if (i % h != 0) {
            return false;
        }

        var mod7 = i % 7;
        return mod7 != 6 && mod7 != 0;
    }
}
