public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
                Game g = new Game();
                Player host = new Player(0);
                host.drawCard();
                host.drawCard();
                host.drawCard();
                Player client = new Player(1);
                client.drawCard();
                client.drawCard();
                client.drawCard();
                client.drawCard();
                g.setHost(host);
                g.setClient(client);
                if (true) { // TODO : how to know which computer hosts ?
                    new View(g, true, host, client);
                }
                else {
                    new View(g, false, g.getClient(), g.getHost());
                }

			}
            
        });
    }
}
