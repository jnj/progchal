package progchal.ch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chessboard {
    private final char[][] pieces = new char[8][8];

    private int whiteKingRow;
    private int whiteKingCol;
    private int blackKingRow;
    private int blackKingCol;

    void setRow(int i, String contents) {
        char[] chars = contents.trim().toCharArray();
        assert chars.length == 8;
        System.arraycopy(chars, 0, pieces[i], 0, chars.length);
    }

    void init() {
        for (int i = 0; i < pieces.length; i++) {
            char[] piece = pieces[i];
            for (int j = 0; j < piece.length; j++) {
                char c = piece[j];
                if (c == 'k') {
                    blackKingRow = i;
                    blackKingCol = j;
                }
                if (c == 'K') {
                    whiteKingRow = i;
                    whiteKingCol = j;
                }
            }
        }
    }

    private boolean isEmpty() {
        for (char[] row : pieces) {
            for (char c : row) {
                if (c != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int gameNo = 1;

        while (true) {
            Chessboard board = readBoard();

            if (board.isEmpty()) {
                return;
            }

            System.out.println("Game #" + gameNo + ": " + board.status());
            gameNo++;
        }
    }

    boolean canPawnCheck(int ki, int kj) {
        char k = pieces[ki][kj];
        assert k == 'K' || k == 'k';
        int py = k == 'K' ? ki - 1 : ki + 1;
        return canCheck(k, 'p', py, kj - 1) || canCheck(k, 'p', py, kj + 1);
    }

    boolean canRookCheck(int ki, int kj) {
        return scanColumn(ki, kj, 'r', -1) ||
               scanColumn(ki, kj, 'r', +1) ||
               scanRow(ki, kj, 'r', -1) ||
               scanRow(ki, kj, 'r', +1);
    }

    private boolean scanColumn(int ki, int kj, char piece, int delta) {
        char king = pieces[ki][kj];
        int i = ki + delta;

        while (isValidPosition(i, kj)) {
            char p = pieces[i][kj];
            i += delta;
            if (p == '.') {
                continue;
            }
            return sameKind(piece, p) && opposing(king, p);
        }

        return false;
    }

    private boolean scanRow(int ki, int kj, char piece, int delta) {
        char king = pieces[ki][kj];
        int i = kj + delta;

        while (isValidPosition(i, kj)) {
            char p = pieces[ki][i];
            i += delta;
            if (p == '.') {
                continue;
            }
            return sameKind(piece, p) && opposing(king, p);
        }

        return false;
    }

    private boolean canCheck(char king, char piece, int i, int j) {
        if (!isValidPosition(i, j)) {
            return false;
        }
        char p = pieces[i][j];
        return sameKind(p, piece) && opposing(king, p);
    }

    private static boolean sameKind(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }

    private static boolean opposing(char a, char b) {
        return a != '.' && b != '.' && Character.getType(a) != Character.getType(b);
    }

    private boolean isValidPosition(int i, int j) {
        return i >= 0 && i < pieces.length &&
               j >= 0 && j < pieces[0].length;
    }

    private String status() {
        if (blackKingInCheck()) {
            return "black king is in check";
        } else if (whiteKingInCheck()) {
            return "white king is in check";
        } else {
            return "no king is in check";
        }
    }

    private boolean whiteKingInCheck() {
        return canPawnCheck(whiteKingRow, whiteKingCol);
    }

    private boolean blackKingInCheck() {
        return false;
    }

    private static Chessboard readBoard() {
        Chessboard board = new Chessboard();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            try {
                String line = reader.readLine();
                board.setRow(i, line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return board;
    }
}
