package no.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void x_should_win_by_row() {
        var grid = new Grid();
        grid.parseState("XXXOO__O_");
        grid.analizeState();
        assertEquals(GridState.X_WINS,grid.getState());
    }

    @Test
    public void x_should_win_by_column() {
        var grid = new Grid();
        grid.parseState("X_XOOXO_X");
        grid.analizeState();
        assertEquals(GridState.X_WINS, grid.getState());
    }

    @Test
    public void x_should_win_by_diagonal() {
        var grid = new Grid();
        grid.parseState("XOXOXOXXO");
        grid.analizeState();
        assertEquals(GridState.X_WINS,grid.getState());

    }

    @Test
    public void o_should_win_by_row(){
        var grid = new Grid();
        grid.parseState("X__OOOX_X");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void o_should_win_by_column(){
        var grid = new Grid();
        grid.parseState("XOOOXOXXO");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void o_should_win_by_diagonal() {
        var grid = new Grid();
        grid.parseState("X_OXO_O_X");
        grid.analizeState();
        assertEquals(GridState.O_WINS, grid.getState());
    }

    @Test
    public void state_should_be_draw() {
        var grid = new Grid();
        grid.parseState("XOXOOXXXO");
        grid.analizeState();
        assertEquals(GridState.DRAW, grid.getState());
    }

    @Test
    public void state_should_be_not_finished() {
        var grid = new Grid();
        grid.parseState("XO_OOX_X_");
        grid.analizeState();
        assertEquals(GridState.GAME_NOT_FINISHED, grid.getState());
    }

    @Test
    public void state_should_be_impossible_01() {
        var grid = new Grid();
        grid.parseState("XO_XO_XOX");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

    @Test
    public void state_should_be_impossible_02() {
        var grid = new Grid();
        grid.parseState("_OOOO_X_X");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

    @Test
    public void state_should_be_impossible_03() {
        var grid = new Grid();
        grid.parseState("_O_X__X_X");
        grid.analizeState();
        assertEquals(GridState.IMPOSSIBLE ,grid.getState());
    }

}