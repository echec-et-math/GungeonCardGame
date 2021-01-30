public class Game {
    private Player user;
    private Player opponent;

    public Player getUser() {
        return user;
    }

    public Player getOpponent() {
        return opponent;
    }

    private Board board = new Board();

    public Board getBoard() {
        return board;
    }
}
