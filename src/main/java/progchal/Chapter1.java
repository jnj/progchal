package progchal;

import java.util.Arrays;

public class Chapter1 {

    public static void main(String[] args) {
        Expenses expenses = new Expenses(3);
        expenses.addCost(5.00);
        expenses.addCost(6.00);
        expenses.addCost(7.00);
        System.out.println(expenses.compute());
    }

    /**
     * 1.6.1
     */
    static int maxCycleLength(int i, int j) {
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

    /**
     * 1.6.2
     */
    static class Minesweeper {
        private static int MINE = -1;
        private final int w;
        private final int h;
        private final int[] field;

        Minesweeper(int rows, int cols) {
            this.w = cols;
            this.h = rows;
            this.field = new int[rows * cols];
        }

        void putMineAt(int row, int col) {
            field[row * w + col] = MINE;

            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i != row || j != col) {
                        if (i >= 0 && i < h && j >= 0 && j < w) {
                            int index = i * w + j;
                            if (field[index] != MINE) {
                                field[index]++;
                            }
                        }
                    }
                }
            }
        }

        char getCountAt(int row, int col) {
            int count = field[row * w + col];
            return count == MINE ? '*' : Character.forDigit(count, 10);
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    b.append(getCountAt(i, j));
                }
                if (i < h - 1) {
                    b.append("\n");
                }
            }

            return b.toString();
        }
    }

    /**
     * 1.6.3
     */
    public static class Expenses {
        private final int[] costs;
        private int i = 0;

        private int[] lte;
        private int[] gt;
        private int totalExchanged;
        private int avgCents;

        Expenses(int numStudents) {
            this.costs = new int[numStudents];
        }

        void addCost(double cost) {
            costs[i++] = (int) (cost * 100);
        }

        double compute() {
            Arrays.sort(costs);
            computeAvg();
            partition();

            boolean done = false;
            totalExchanged = 0;
            boolean pennyUsed = false;

            while (!done) {
                int prevExchanged = totalExchanged;

                for (int i = lte.length - 1; i >= 0; i--) {
                    int lt = lte[i];
                    int delta = pennyUsed ? 1 : 0;
                    pennyUsed = true;

                    if (lt < avgCents + delta) {
                        int needed = avgCents - lt;
                        int taken = 0;

                        for (int j = 0; j < gt.length && taken != needed; j++) {
                            int g = gt[j];

                            if (g > avgCents + delta) {
                                int remaining = needed - taken;
                                int takeAmount = Math.min(g - avgCents, remaining);
                                gt[j] -= takeAmount;
                                lte[i] += takeAmount;
                                totalExchanged += takeAmount;
                                taken += takeAmount;
                            }
                        }
                    }
                }

                if (allMeetAvg()) {
                    unpartition();
                    moveCent();
                    done = true;
                }

                if (prevExchanged == totalExchanged) {
                    done = true;
                }
            }

            return totalExchanged / 100.0;
        }

        private void computeAvg() {
            double avg = 0;

            for (int c : costs) {
                avg += c;
            }

            avg /= costs.length;
            avgCents = (int) avg;
        }

        void unpartition() {
            System.arraycopy(lte, 0, costs, 0, lte.length);
            System.arraycopy(gt, 0, costs, lte.length, gt.length);
        }

        void moveCent() {
            int index = -1;

            for (int i = 0; i < costs.length; i++) {
                int i1 = costs[i];
                if (i1 > avgCents + 1) {
                    index = i;
                    break;
                }
            }

            if (index > -1) {
                totalExchanged++;
            }
        }

        boolean allMeetAvg() {
            for (int i1 : lte) {
                if (i1 < avgCents) {
                    return false;
                }
            }

            return true;
        }

        void partition() {
            int lteCount = 0;
            for (int cost : costs) {
                if (cost <= avgCents) {
                    lteCount++;
                }
            }

            int gtCount = costs.length - lteCount;
            lte = new int[lteCount];
            gt = new int[gtCount];
            int li = 0;
            int gi = 0;

            for (int cost : costs) {
                if (cost <= avgCents) {
                    lte[li++] = cost;
                } else {
                    gt[gi++] = cost;
                }
            }
        }
    }
}
