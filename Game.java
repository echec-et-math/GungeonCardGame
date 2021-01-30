public class Game {
    private Player host;
    private Player client;

    public Player getHost() {
        return host;
    }

    public Player getClient() {
        return client;
    }

    public void setHost(Player p) {
        host = p;
    }

    public void setClient(Player p) {
        client = p;
    }

    private Board board = new Board();

    public Board getBoard() {
        return board;
    }
}
