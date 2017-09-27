package progchal.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Bitmap {
    private char[] img;
    private int w;
    private int h;

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String line = reader.readLine();
                bitmap.command(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void command(String cmd) {
        String[] parts = cmd.split(" ");
        switch (parts[0]) {
            case "I":
                create(parseInt(parts[1]), parseInt(parts[2]));
                break;
            case "C":
                clear();
                break;
            case "L":
                label(parseInt(parts[1]), parseInt(parts[2]), parts[3].charAt(0));
                break;
            case "V":
                vLine(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parts[4].charAt(0));
                break;
            case "H":
                hLine(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parts[4].charAt(0));
                break;
            case "K":
                rect(parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]), parseInt(parts[4]), parts[5].charAt(0));
                break;
            case "F":
                fill(parseInt(parts[1]), parseInt(parts[2]), parts[3].charAt(0));
                break;
            case "S":
                write(parts[1]);
                break;
            case "X":
                System.exit(0);
            default:
        }
    }

    private void write(String name) {
        System.out.println(name + ".bmp");
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                System.out.print(img[r * w + c]);
            }
            System.out.print('\n');
        }
    }

    private void fill(int x, int y, char c) {
        Boolean[] region = new Boolean[w * h];
        int index = y * w + x;
        char regionColor = img[index];
        region[index] = Boolean.TRUE;
        updateNeighborsRegion(region, regionColor, new EdgeNeighbors(x, y));

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                index = i * w + j;
                if (region[index] == null) {
                    findRegion(j, i, index, region, regionColor);
                }
            }
        }

        for (int i = 0; i < region.length; i++) {
            Boolean b = region[i];
            if (Boolean.TRUE == b) {
                img[i] = c;
            }
        }
    }

    /**
     * Determines the region of pixel (x, y).
     */
    private void findRegion(int x, int y, int z, Boolean[] region, char regionColor) {
        if (region[z] != null) {
            // status already known.
            return;
        } else if (img[z] != regionColor) {
            region[z] = false;
            return;
        }

        EdgeNeighbors neighbors = new EdgeNeighbors(x, y);
        // determine status of neighboring pixels.
        boolean some = false;
        boolean none = true;
        boolean unkn = true;

        for (Pixel n : neighbors) {
            none = none && region[n.index()] == Boolean.FALSE;
            unkn = unkn && region[n.index()] == null;

            if (region[n.index()] == Boolean.TRUE) {
                some = true;
                unkn = false;
                none = false;
                break;
            }
        }

        if (none) {
            region[z] = false;
        } else if (some) {
            region[z] = true;
            updateNeighborsRegion(region, img[z], neighbors);
        } else if (unkn) {
            for (Pixel neighbor : neighbors) {
                for (Pixel nNeighbor : new EdgeNeighbors(neighbor)) {
                    if (nNeighbor.notEqual(x, y)) {
                        Boolean status = region[nNeighbor.index()];

                        if (status == Boolean.TRUE) {
                            region[z] = img[z] == regionColor;
                            updateNeighborsRegion(region, img[z], neighbors);
                            return;
                        } else if (status == null) {
                            findRegion(nNeighbor.x, nNeighbor.y, nNeighbor.index(), region, regionColor);

                            if (region[nNeighbor.index()] == Boolean.TRUE) {
                                region[z] = true;
                                updateNeighborsRegion(region, img[z], neighbors);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateNeighborsRegion(Boolean[] region, char c, EdgeNeighbors neighbors) {
        for (Pixel n : neighbors) {
            int i = n.index();
            if (null == region[i]) {
                region[i] = c == n.color();
            }
        }
    }

    private boolean validColumn(int i) {
        return i >= 0 && i < w;
    }

    private boolean validRow(int i) {
        return i >= 0 && i < h;
    }

    private void rect(int x1, int y1, int x2, int y2, char c) {
        int start = y1 * w + x1;
        int stop = y2 * w + x2;
        for (int i = start; i <= stop; i++) {
            img[i] = c;
        }
    }

    private void hLine(int x1, int x2, int y, char c) {
        int startX = Math.min(x1, x2);
        int stopX = Math.max(x1, x2);
        int r = y * w;
        for (int i = startX; i <= stopX; i++) {
            img[r + i] = c;
        }
    }

    private void vLine(int x, int y1, int y2, char c) {
        int startY = Math.min(y1, y2);
        int stopY = Math.max(y1, y2);

        for (int y = startY; y <= stopY; y++) {
            label(x, y, c);
        }
    }

    private void label(int x, int y, char c) {
        img[y * w + x] = c;
    }

    private void create(int m, int n) {
        w = m;
        h = n;
        img = new char[w * h];
        Arrays.fill(img, 'O');
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
            } else if (o == null || !(o instanceof Pixel)) {
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
