package progchal.ch1;

/**
 * 1.6.2
 */
class Minesweeper {
    private static final int MINE = -1;
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

        for (var i = row - 1; i <= row + 1; i++) {
            for (var j = col - 1; j <= col + 1; j++) {
                if (i != row || j != col) {
                    if (i >= 0 && i < h && j >= 0 && j < w) {
                        var index = i * w + j;
                        if (field[index] != MINE) {
                            field[index]++;
                        }
                    }
                }
            }
        }
    }

    private char getCountAt(int row, int col) {
        var count = field[row * w + col];
        return count == MINE ? '*' : Character.forDigit(count, 10);
    }

    @Override
    public String toString() {
        var b = new StringBuilder();

        for (var i = 0; i < h; i++) {
            for (var j = 0; j < w; j++) {
                b.append(getCountAt(i, j));
            }
            if (i < h - 1) {
                b.append("\n");
            }
        }

        return b.toString();
    }
}
