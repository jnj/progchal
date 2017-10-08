package progchal.ch2;

class JollyJumpers {

    static boolean isJollyJumper(int[] seq) {
        boolean[] vals = new boolean[seq.length];

        for (int i = 1; i < seq.length; i++) {
            int d = seq[i] - seq[i - 1];
            int abs = Math.abs(d);
            if (abs >= 0 && abs < vals.length) {
                vals[abs] = true;
            }
        }

        for (int i = 1; i < vals.length; i++) {
            if (!vals[i]) {
                return false;
            }
        }

        return true;
    }
}
