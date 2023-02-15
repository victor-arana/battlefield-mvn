package no.data;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a grid for a Tic-Tac-Toe game. It models the grid
 * using the Integer values 0 for O, 1 for X and null for an
 * empty cell.
 */
class Grid {

    // Model the grid as two-dimensional array
    Integer[][] grid;
    private GridState state;

    // Initialize a 3x3 grid as default
    Grid(){
        this(3,3);
    }

    Grid(int rows, int columns) {
        if (rows <= 0 && columns <= 0) throw new IllegalArgumentException();
        grid = new Integer[rows][columns];
    }

    // Checks if a player has won by making a line of moves in a row.
    private boolean checkRows(Integer n, Integer[][] grid) {
        boolean nWinsByRow = false;
        for (int i = 0; i < grid.length; i++) {
            nWinsByRow = checkRow(i, n, grid);
            if (nWinsByRow) break;
        }
        return nWinsByRow;
    }

    // Checks if a player has won by making a line of moves in a column.
    private boolean checkColumns(Integer n, Integer[][] grid) {
        boolean nWinsByColumn = false;
        for (int j = 0; j < grid[0].length; j++) {
            nWinsByColumn = testColumn(j, n, grid);
            if (nWinsByColumn) break;
        }
        return nWinsByColumn;
    }

    void analizeState() {
        boolean xWinsByDiagonal = testDiagonal(1,grid);
        boolean oWinsByDiagonal = testDiagonal(0,grid);

        boolean xWinsByRow = checkRows(1, grid);
        boolean oWinsByRow = checkRows(0, grid);

        boolean xWinsByColumn = checkColumns(1, grid);
        boolean oWinsByColumn = checkColumns(0, grid);

        boolean xWins = xWinsByDiagonal || xWinsByRow || xWinsByColumn;
        boolean oWins = oWinsByDiagonal || oWinsByRow || oWinsByColumn;
        boolean gameIsNotFinished = !xWins && !oWins && gridHasEmptyCells(grid);
        boolean draw = !xWins && !oWins && !gridHasEmptyCells(grid);
        boolean impossible = (xWins && oWins) || thereIsABigDifference() ;

        if (impossible){
            this.state = GridState.IMPOSSIBLE;
        } else if (xWins && !oWins && !gameIsNotFinished && !draw && !impossible) {
            this.state = GridState.X_WINS;
        } else if (!xWins && oWins && !gameIsNotFinished && !draw && !impossible) {
            this.state = GridState.O_WINS;
        } else if (gameIsNotFinished) {
            this.state = GridState.GAME_NOT_FINISHED;
        } else if (draw) {
            this.state = GridState.DRAW;
        }

    }




    private boolean thereIsABigDifference(){
        return Math.abs(count(0) - count(1)) >= 2;
    }

    private int count(Integer n) {
        int count = 0;
        for(Integer[] row: grid) {
            for(Integer slot: row) {
                if (slot != null && slot.equals(n)) {
                    count++;
                }
            }
        }
        return count;
    }

    boolean gridHasEmptyCells(Integer[][] grid){
        boolean gridHasEmptyCells = false;
        for (Integer[] row: grid) {
            for(Integer cell: row){
                if(cell == null) {
                    gridHasEmptyCells = true;
                    break;
                }
            }
        }
        return gridHasEmptyCells;
    }

    boolean testDiagonal(Integer i, Integer[][] a) {
        boolean backSlashIsFull  = a[0][0] != null && a[1][1] != null && a[2][2] != null;
        boolean backSlashWins = backSlashIsFull
                && (a[0][0].equals(i) && a[0][0].equals(a[1][1]) && a[0][0].equals(a[2][2]));

        boolean forwardSlashIsFull  = a[0][2] != null && a[1][1] != null && a[1][1] != null;
        boolean forwardSlashWins = forwardSlashIsFull
                && (a[0][2].equals(i) && a[0][2].equals(a[1][1]) && a[0][2].equals(a[1][1]));
        return backSlashWins || forwardSlashWins;

    }

    boolean testColumn(int c, Integer i, Integer[][] a) {
        boolean isFull = a[0][c] != null && a[1][c] != null && a[2][c] != null;
        return isFull
                && (a[0][c].equals(i) && a[1][c].equals(i) && a[2][c].equals(i));
    }

    boolean checkRow(int row, Integer i, Integer[][] a) {
        boolean rowIsFull = a[row][0] != null && a[row][1] != null && a[row][2] != null;
        return rowIsFull
                && (a[row][0].equals(i) && a[row][1].equals(i) && a[row][2].equals(i));
    }

    void readState() {
        Scanner scanner = new Scanner(System.in);
        String state = scanner.nextLine();
        grid = parseState(state);
    }

    Character[][] unflatMatrix(int m, int n, char[] a){
        Character[][] output = new Character[m][n];
        int k = 0;
        int i = 0;
        while (i < m) {
            int j = 0;
            while (j < n) {
                output[i][j] = a[k];
                k++;
                j++;
            }
            i++;
        }
        return output;
    }


    Integer[][] parseState(String state) {
        Character[][] stateMatrix  = unflatMatrix(3, 3, state.toCharArray());
        this.grid = parseStateMatrix(stateMatrix);
        return parseStateMatrix(stateMatrix);
    }

    Integer[][] parseStateMatrix(Character[][] stateMatrix) {
        int m = stateMatrix.length;
        int n = stateMatrix[0].length;

        Integer[][] result = new Integer[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n;j++) {
                result[i][j] = parseSlot(stateMatrix[i][j]);
            }
        }
        return result;
    }

    Integer parseSlot(Character character) {
        Integer value = 0;
        switch (character) {
            case 'O':
                value = 0;
                break;
            case 'X':
                value = 1;
                break;
            case '_':
            default:
                value = null;
        }
        return value;
    }

    void autoFill() {
        var random = new Random();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
    }

    void printState() {
        String state = "";
        switch(getState()) {
            case X_WINS:
                state = "X wins";
                break;
            case O_WINS:
                state = "O wins";
                break;
            case DRAW:
                state = "Draw";
                break;
            case GAME_NOT_FINISHED:
                state = "Game not finished";
                break;
            case IMPOSSIBLE:
                state = "Impossible";
                break;
        }
        System.out.println(state);
    }

    void printGrid(){
        System.out.println("---------");
        for(int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < grid[i].length; j++) {
                String character = null;
                if (grid[i][j] == null) {
                    character = "_";
                } else if ((int) grid[i][j] == 0) {
                    character = "O";
                } else if ((int) grid[i][j] == 1) {
                    character = "X";
                } else {
                    character = "_";
                }
                System.out.print(character + " ");
            }
            System.out.printf("| %n");
        }
        System.out.println("---------");
    }

    public GridState getState() {
        return this.state;
    }

    public void readMoves() {
        boolean validMove = false;
        while(!validMove) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                validMove = isInputMoveValid(input) && isMoveValid(input);
                if(validMove) {
                    makeMove(input);
                    printGrid();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void makeMove(String input) {
        String[] coordinates = input.split(" ");
        int i = Integer.parseInt(coordinates[0]) - 1;
        int j = Integer.parseInt(coordinates[1]) - 1;
        this.grid[i][j] = 1;
    }

    boolean isMoveValid(String input) {
        String[] coordinates = input.split(" ");
        int i = Integer.parseInt(coordinates[0]) - 1;
        int j = Integer.parseInt(coordinates[1]) - 1;
        // Test valid cell
        boolean validCell = false;
        boolean validRow = i >= 0 && i < grid.length;
        boolean validColumn = j >= 0 && j < grid[0].length;
        if (validRow && validColumn) {
            validCell = true;
        } else {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        }
        // Check for an occupied cell
        boolean isCellOccupied = grid[i][j] != null;
        if (isCellOccupied) {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        return validCell && !isCellOccupied;
    }

    public boolean isInputMoveValid(String inputMove) {
        String[] coordinates = inputMove.split(" ");
        try {
            Integer.parseInt(coordinates[0]);
            Integer.parseInt(coordinates[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("You should enter numbers!");
        }
        return true;
    }
}
