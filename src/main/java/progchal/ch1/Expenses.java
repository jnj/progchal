package progchal.ch1;

import java.util.Arrays;

/**
 * 1.6.3
 */
class Expenses {
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

        var done = false;
        totalExchanged = 0;
        var pennyUsed = false;

        while (!done) {
            var prevExchanged = totalExchanged;

            for (var i = lte.length - 1; i >= 0; i--) {
                var lt = lte[i];
                var delta = pennyUsed ? 1 : 0;
                pennyUsed = true;

                if (lt < avgCents + delta) {
                    var needed = avgCents - lt;
                    var taken = 0;

                    for (var j = 0; j < gt.length && taken != needed; j++) {
                        var g = gt[j];

                        if (g > avgCents + delta) {
                            var remaining = needed - taken;
                            var takeAmount = Math.min(g - avgCents, remaining);
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

        for (var c : costs) {
            avg += c;
        }

        avg /= costs.length;
        avgCents = (int) avg;
    }

    private void unpartition() {
        System.arraycopy(lte, 0, costs, 0, lte.length);
        System.arraycopy(gt, 0, costs, lte.length, gt.length);
    }

    private void moveCent() {
        var index = -1;

        for (var i = 0; i < costs.length; i++) {
            var i1 = costs[i];
            if (i1 > avgCents + 1) {
                index = i;
                break;
            }
        }

        if (index > -1) {
            totalExchanged++;
        }
    }

    private boolean allMeetAvg() {
        for (var i1 : lte) {
            if (i1 < avgCents) {
                return false;
            }
        }

        return true;
    }

    private void partition() {
        var lteCount = 0;
        for (var cost : costs) {
            if (cost <= avgCents) {
                lteCount++;
            }
        }

        var gtCount = costs.length - lteCount;
        lte = new int[lteCount];
        gt = new int[gtCount];
        var li = 0;
        var gi = 0;

        for (var cost : costs) {
            if (cost <= avgCents) {
                lte[li++] = cost;
            } else {
                gt[gi++] = cost;
            }
        }
    }
}
