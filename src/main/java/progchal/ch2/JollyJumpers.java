package progchal.ch2;

class JollyJumpers {

    static boolean isJollyJumper(int[] seq) {
        var vals = new boolean[seq.length];

        for (var i = 1; i < seq.length; i++) {
            var d = seq[i] - seq[i - 1];
            var abs = Math.abs(d);
            if (abs >= 0 && abs < vals.length) {
                vals[abs] = true;
            }
        }

        for (var i = 1; i < vals.length; i++) {
            if (!vals[i]) {
                return false;
            }
        }

        return true;
    }
}
