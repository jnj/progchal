package progchal.ch1;

/**
 * 1.6.6
 */
class Interpreter {
    private int[] ram = new int[1000];
    private int[] reg = new int[10];
    private boolean running;
    private int ramIndex;
    private int counter;

    void initRam(int i) {
        ram[ramIndex++] = i;
    }

    int getCounter() {
        return counter;
    }

    void run() {
        counter = 0;
        ramIndex = 0;
        running = true;

        while (running) {
            int op = ram[ramIndex];
            decode(op);
        }
    }

    private void decode(int op) {
        counter++;
        int msd = d1(op);
        int d = d2(op);
        switch (msd) {
            case 0:
                if (reg[d3(op)] != 0) {
                    ramIndex = reg[d];
                } else {
                    ramIndex++;
                }
                break;
            case 1:
                halt();
                break;
            case 2:
                reg[d] = d3(op);
                ramIndex++;
                break;
            case 3:
                reg[d] += d3(op);
                reg[d] %= 1000;
                ramIndex++;
                break;
            case 4:
                reg[d] *= d3(op);
                reg[d] %= 1000;
                ramIndex++;
                break;
            case 5:
                reg[d] = reg[d3(op)];
                ramIndex++;
                break;
            case 6:
                reg[d] += reg[d3(op)];
                reg[d] %= 1000;
                ramIndex++;
                break;
            case 7:
                reg[d] *= reg[d3(op)];
                reg[d] %= 1000;
                ramIndex++;
                break;
            case 8:
                reg[d] = ram[reg[d3(op)]];
                ramIndex++;
                break;
            case 9:
                ram[reg[d3(op)]] = reg[d];
                ramIndex++;
                break;
            default:
        }
    }

    private static int d1(int i) {
        return i / 100;
    }

    private static int d2(int i) {
        return (i % 100) / 10;
    }

    private static int d3(int i) {
        return i % 10;
    }

    private void halt() {
        running = false;
    }
}
