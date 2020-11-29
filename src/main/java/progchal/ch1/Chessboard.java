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
        var chars = contents.trim().toCharArray();
        assert chars.length == 8;
        System.arraycopy(chars, 0, pieces[i], 0, chars.length);
    }

    void init() {
        for (var i = 0; i < pieces.length; i++) {
            var piece = pieces[i];
            for (var j = 0; j < piece.length; j++) {
                var c = piece[j];
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
        for (var row : pieces) {
            for (var c : row) {
                if (c != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        var gameNo = 1;

        while (true) {
            var board = readBoard();

            if (board.isEmpty()) {
                return;
            }

            System.out.println("Game #" + gameNo + ": " + board.status());
            gameNo++;
        }
    }

    boolean canBishopCheck(int ki, int kj) {
        return scanDiag(ki, kj, 'b', -1, -1) ||
               scanDiag(ki, kj, 'b', +1, -1) ||
               scanDiag(ki, kj, 'b', +1, -1) ||
               scanDiag(ki, kj, 'b', +1, +1);
    }

    boolean canPawnCheck(int ki, int kj) {
        var king = pieces[ki][kj];
        return (canCheck(king, 'p', ki - 1, kj - 1) && king == 'K') ||
               (canCheck(king, 'p', ki - 1, kj + 1) && king == 'K') ||
               (canCheck(king, 'p', ki + 1, kj - 1) && king == 'k') ||
               (canCheck(king, 'p', ki + 1, kj + 1) && king == 'k');
    }

    boolean canRookCheck(int ki, int kj) {
        return scanColumn(ki, kj, 'r', -1) ||
               scanColumn(ki, kj, 'r', +1) ||
               scanRow(ki, kj, 'r', -1) ||
               scanRow(ki, kj, 'r', +1);
    }

    boolean canQueenCheck(int ki, int kj) {
        return scanDiag(ki, kj, 'q', -1, -1) ||
               scanDiag(ki, kj, 'q', +1, -1) ||
               scanDiag(ki, kj, 'q', +1, -1) ||
               scanDiag(ki, kj, 'q', +1, +1) ||
               scanColumn(ki, kj, 'q', -1) ||
               scanColumn(ki, kj, 'q', +1) ||
               scanRow(ki, kj, 'q', -1) ||
               scanRow(ki, kj, 'q', +1);
    }

    boolean canKingCheck(int ki, int kj) {
        var king = pieces[ki][kj];
        return canCheck(king, 'k', ki - 1, kj) ||
               canCheck(king, 'k', ki - 1, kj - 1) ||
               canCheck(king, 'k', ki - 1, kj + 1) ||
               canCheck(king, 'k', ki, kj - 1) ||
               canCheck(king, 'k', ki, kj + 1) ||
               canCheck(king, 'k', ki + 1, kj - 1) ||
               canCheck(king, 'k', ki + 1, kj + 1) ||
               canCheck(king, 'k', ki + 1, kj);
    }

    boolean canKnightCheck(int ki, int kj) {
        var king = pieces[ki][kj];
        return canCheck(king, 'n', ki - 1, kj - 2) ||
               canCheck(king, 'n', ki - 2, kj - 1) ||
               canCheck(king, 'n', ki - 2, kj + 1) ||
               canCheck(king, 'n', ki - 1, kj + 2) ||
               canCheck(king, 'n', ki + 1, kj - 2) ||
               canCheck(king, 'n', ki + 2, kj - 1) ||
               canCheck(king, 'n', ki + 2, kj + 1) ||
               canCheck(king, 'n', ki + 1, kj + 2);
    }

    private boolean scanDiag(int ki, int kj, char piece, int id, int jd) {
        var king = pieces[ki][kj];

        for (int i = ki + id, j = kj + jd; isValidPosition(i, j); i += id, j += jd) {
            var p = pieces[i][j];

            if (p == '.') {
                continue;
            }

            return canCheck(king, piece, i, j);
        }

        return false;
    }

    private boolean scanColumn(int ki, int kj, char piece, int delta) {
        var king = pieces[ki][kj];

        for (var i = ki + delta; isValidPosition(i, kj); i += delta) {
            var p = pieces[i][kj];

            if (p == '.') {
                continue;
            }

            return canCheck(king, piece, i, kj);
        }

        return false;
    }

    private boolean scanRow(int ki, int kj, char piece, int delta) {
        var king = pieces[ki][kj];

        for (var i = kj + delta; isValidPosition(i, kj); i += delta) {
            var p = pieces[ki][i];

            if (p == '.') {
                continue;
            }

            return canCheck(king, piece, ki, i);
        }

        return false;
    }

    private boolean canCheck(char king, char piece, int i, int j) {
        if (!isValidPosition(i, j)) {
            return false;
        }
        var p = pieces[i][j];
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

    String status() {
        if (blackKingInCheck()) {
            return "black king is in check";
        } else if (whiteKingInCheck()) {
            return "white king is in check";
        } else {
            return "no king is in check";
        }
    }

    private boolean kingInCheck(int kingRow, int kingCol) {
        return canPawnCheck(kingRow, kingCol) ||
               canRookCheck(kingRow, kingCol) ||
               canBishopCheck(kingRow, kingCol) ||
               canQueenCheck(kingRow, kingCol) ||
               canKingCheck(kingRow, kingCol) ||
               canKnightCheck(kingRow, kingCol);
    }

    private boolean whiteKingInCheck() {
        return kingInCheck(whiteKingRow, whiteKingCol);
    }

    private boolean blackKingInCheck() {
        return kingInCheck(blackKingRow, blackKingCol);
    }

    private static Chessboard readBoard() {
        var board = new Chessboard();
        var reader = new BufferedReader(new InputStreamReader(System.in));

        for (var i = 0; i < 8; i++) {
            try {
                var line = reader.readLine();
                board.setRow(i, line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return board;
    }
}
