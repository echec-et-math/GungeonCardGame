public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
                Game g = new Game();
                new View(g);
			}
            
        });
    }
}
