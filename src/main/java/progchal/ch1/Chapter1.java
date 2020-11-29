package progchal.ch1;

public class Chapter1 {

    public static void main(String[] args) {
        var expenses = new Expenses(3);
        expenses.addCost(5.00);
        expenses.addCost(6.00);
        expenses.addCost(7.00);
        System.out.println(expenses.compute());
    }

    /**
     * 1.6.1
     */
    static int maxCycleLength(int i, int j) {
        var max = 0;

        for (var k = i; k <= j; k++) {
            max = Math.max(max, cycleLength(k));
        }

        return max;
    }

    private static int cycleLength(int n) {
        var i = n;
        var count = 1;

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
