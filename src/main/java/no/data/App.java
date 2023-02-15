package no.data;

public class App
{
    public static void main( String[] args )
    {
        var grid = new Grid();
        grid.readGame();
        grid.analizeState();
        grid.printGrid();
        grid.readMoves();
    }
}
