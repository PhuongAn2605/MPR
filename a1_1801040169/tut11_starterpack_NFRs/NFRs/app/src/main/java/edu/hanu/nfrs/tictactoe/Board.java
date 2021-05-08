package edu.hanu.nfrs.tictactoe;

public class Board {
    private Symbol[] cells;

    public Board() {
        this.cells = new Symbol[9];
    }

    public Symbol get(int x) {
        return this.cells[x];
    }

    /**
     * @requires 0 <= x <= 8
     */
    public boolean set(Symbol symbol, int x) {
        if (cells[x] == null) {
            cells[x] = symbol;
            return true;
        }

        return false;
    }

    public boolean hasBlank() {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == null) {
                return true;
            }
        }

        return false;
    }

    public boolean hasWinner() {
        int[][] conditions = {
                {0,1,2}, {3,4,5}, {6,7,8}, // row
                {0,3,6}, {1,4,5}, {2,5,8}, // column
                {0,4,8}, {6,4,2}           // diagonal
        };

        for (int[] condition : conditions) {
            Symbol cell1 = cells[condition[0]];
            Symbol cell2 = cells[condition[1]];
            Symbol cell3 = cells[condition[2]];

            if (cell1 != null && cell1 == cell2 && cell2 == cell3) {
                return true;
            }
        }

        return false;
    }
}
