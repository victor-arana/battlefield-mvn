package no.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void x_should_win_by_row() {
        var grid = new Grid();
        grid.parseGame("XXXOO__O_");
        grid.analizeState();
        assertEquals(GridState.X_WINS,grid.getState());
    }

    @Test
    public void x_should_win_by_column() {
        var grid = new Grid();
        grid.parseGame("X_XOOXO_X");
        grid.analizeState();
        assertEquals(GridState.X_WINS, grid.getState());
    }

    @Test
    public void x_should_win_by_diagonal() {
        var grid = new Grid();
        grid.parseGame("XOXOXOXXO");
        grid.analizeState();
        assertEquals(GridState.X_WINS,grid.getState());

    }

    @Test
    public void o_should_win_by_row(){
        var grid = new Grid();
        grid.parseGame("X__OOOX_X");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void o_should_win_by_column(){
        var grid = new Grid();
        grid.parseGame("XOOOXOXXO");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void o_should_win_by_diagonal() {
        var grid = new Grid();
        grid.parseGame("X_OXO_O_X");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void state_should_be_draw() {
        var grid = new Grid();
        grid.parseGame("XOXOOXXXO");
        grid.analizeState();
        assertEquals(GridState.DRAW, grid.getState());
    }

    @Test
    public void state_should_be_not_finished() {
        var grid = new Grid();
        grid.parseGame("XO_OOX_X_");
        grid.analizeState();
        assertEquals(GridState.GAME_NOT_FINISHED, grid.getState());
    }

    @Test
    public void state_should_be_impossible_01() {
        var grid = new Grid();
        grid.parseGame("XO_XO_XOX");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

    @Test
    public void state_should_be_impossible_02() {
        var grid = new Grid();
        grid.parseGame("_OOOO_X_X");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

    @Test
    public void state_should_be_impossible_03() {
        var grid = new Grid();
        grid.parseGame("_O_X__X_X");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOccupiedCellMove() {
        var grid = new Grid();
        grid.parseGame("_XXOO_OX_");
        String input = "3 1";
        try {
            grid.isMoveValid(input);
        } catch (Exception e) {
            assertEquals("This cell is occupied! Choose another one!", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRowAndColumn() {
        var grid = new Grid();
        grid.parseGame("_XXOO_OX_");
        String input = "4 1";
        try {
            grid.isMoveValid(input);
        } catch (Exception e) {
            assertEquals("Coordinates should be from 1 to 3!", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        var grid = new Grid();
        grid.parseGame("_XXOO_OX_");
        String inputMove = "one one";
        try {
            grid.isInputValid(inputMove);
        } catch (Exception e) {
            assertEquals("You should enter numbers!", e.getMessage());
            throw e;
        }
    }
}