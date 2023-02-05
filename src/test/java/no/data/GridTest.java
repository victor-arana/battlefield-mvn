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
}