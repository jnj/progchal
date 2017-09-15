package progchal;

public class Chapter1 {

    public static void main(String[] args) {
        System.out.println(cycleLength(22));
    }

    /**
     * 1.6.1
     */
    public static int maxCycleLength(int i, int j) {
        int max = 0;

        for (int k = i; k <= j; k++) {
            max = Math.max(max, cycleLength(k));
        }

        return max;
    }

    private static int cycleLength(int n) {
        int i = n;
        int count = 1;

        do {
            count++;
            if ((i & 1) != 0) {
                i = 3 * i + 1;
            } else {
                i >>= 1;
            }
        } while (i != 1);

        return count;
    }
}
