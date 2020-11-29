package progchal.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Bitmap {
    private char[] img;
    private int w;
    private int h;

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        var moreInput = true;

        while (moreInput) {
            try {
                String line = reader.readLine();
                moreInput = bitmap.command(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean command(String cmd) {
        String[] parts = cmd.split(" ");
        switch (parts[0]) {
            case "X":
                return false;
            case "I":
                create(parseInt(parts[1]), parseInt(parts[2]));
            case "C":
                clear();
            case "L":
                label(parseInt(parts[1]), parseInt(parts[2]), parts[3].charAt(0));
            case "V":
                vLine(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parts[4].charAt(0));
            case "H":
                hLine(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parts[4].charAt(0));
            case "K":
                rect(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parseInt(parts[4]), parts[5].charAt(0));
            case "F":
                fill(parseInt(parts[1]), parseInt(parts[2]), parts[3].charAt(0));
            case "S":
                write(parts[1], System.out);
            default:
                return true;
        }
    }

    private void write(String name, PrintStream ps) {
        ps.println(name + ".bmp");
        ps.println(toString());
    }

    void fill(int xx, int yy, char c) {
        int x = xx - 1;
        int y = yy - 1;
        Boolean[] region = new Boolean[w * h];
        int index = y * w + x;
        char regionColor = img[index];

        Set<Pixel> seen = new HashSet<>();
        Queue<Pixel> queue = new LinkedList<>();
        queue.add(new Pixel(x, y));

        while (!queue.isEmpty()) {
            Pixel p = queue.remove();
            seen.add(p);
            if (p.color() == regionColor) {
                region[p.index()] = true;
                for (Pixel n : p.neighbors()) {
                    if (!seen.contains(n)) {
                        queue.add(n);
                    }
                }
            }
        }

        for (int i = 0; i < region.length; i++) {
            if (Boolean.TRUE == region[i]) {
                img[i] = c;
            }
        }
    }

    private boolean validColumn(int i) {
        return i >= 0 && i < w;
    }

    private boolean validRow(int i) {
        return i >= 0 && i < h;
    }

    void rect(int x1, int y1, int x2, int y2, char c) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                label(i, j, c);
            }
        }
    }

    void hLine(int x1, int x2, int y, char c) {
        int xStart = x1 - 1;
        int xStop = x2 - 1;
        int r = (y - 1) * w;
        for (int i = xStart; i <= xStop; i++) {
            img[r + i] = c;
        }
    }

    void vLine(int x, int y1, int y2, char c) {
        for (int i = y1; i <= y2; i++) {
            label(x, i, c);
        }
    }

    void label(int x, int y, char c) {
        img[(y - 1) * w + (x - 1)] = c;
    }

    void create(int m, int n) {
        w = m;
        h = n;
        img = new char[w * h];
        Arrays.fill(img, 'O');
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                buf.append(img[r * w + c]);
            }
            if (r < h - 1) {
                buf.append('\n');
            }
        }

        return buf.toString();
    }

    private void clear() {
        Arrays.fill(img, 'O');
    }

    class Pixel {
        private final int x;
        private final int y;

        Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }

        char color() {
            return img[index()];
        }

        int index() {
            return y * w + x;
        }

        boolean valid() {
            return validColumn(x) && validRow(y);
        }

        boolean notEqual(int x, int y) {
            return this.x != x || this.y != y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Pixel)) {
                return false;
            } else {
                Pixel that = (Pixel) o;
                return x == that.x && y == that.y;
            }
        }

        @Override
        public String toString() {
            return "Pixel{" +
                   "x=" + x +
                   ", y=" + y +
                   '}';
        }

        @Override
        public int hashCode() {
            int x = 17;
            x = 31 * x + this.x;
            x = 31 * x + this.y;
            return x;
        }

        Iterable<Pixel> neighbors() {
            return new EdgeNeighbors(this);
        }
    }

    class EdgeNeighbors implements Iterable<Pixel> {
        private final Iterable<Pixel> neighbors;

        EdgeNeighbors(Pixel p) {
            this(p.x, p.y);
        }

        EdgeNeighbors(int x, int y) {
            List<Pixel> neighbors = new ArrayList<>();
            addIfValid(x - 1, y, neighbors, x, y);
            addIfValid(x + 1, y, neighbors, x, y);
            addIfValid(x, y - 1, neighbors, x, y);
            addIfValid(x, y + 1, neighbors, x, y);
            this.neighbors = neighbors;
        }

        private void addIfValid(int x1, int y1, List<Pixel> neighbors, int x, int y) {
            Pixel e = new Pixel(x1, y1);
            if (e.valid() && e.notEqual(x, y)) {
                neighbors.add(e);
            }
        }

        @Override
        public Iterator<Pixel> iterator() {
            return neighbors.iterator();
        }
    }
}
