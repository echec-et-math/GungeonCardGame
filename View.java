import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;

    private Game game;

    private final boolean hosting;

    public boolean isViewUserHosting() {
        return hosting;
    }

    private final Player player;

    public Player getViewUser() {
        return player;
    }

    private final Player opponent;

    public Player getOpponent() {
        return opponent;
    }

    private JPanel gameBoard = new JPanel();

    private JPanel gameWindow = new JPanel();

    private JPanel gameMenu = new JPanel();

    private JPanel infoTab = new JPanel();

    private CardGraphicModel[][] modelGrid = new CardGraphicModel[5][10];

    private JPanel playerInfo = new JPanel();
    private JPanel enemyInfo = new JPanel();

    public View(Game game, boolean hosting, Player player, Player opponent) { // TODO : bad implementation here, can be improved with only game and ID
        this.game = game;
        this.hosting = hosting;
        this.player = player;
        this.opponent = opponent;
        setSize(1800, 1200);
        setTitle("GUNGEON CARD GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        gameWindow.setLayout(null);
        gameWindow.setBackground(new Color(255,255,255)); // useless

        gameBoard.setLayout(null);
        gameBoard.setBounds(540, 280, 668, 458);
        gameBoard.setBackground(new Color(255,255,255));

        int xpos = 0;
        int ypos = 0;
        CardSlot[][] grid = game.getBoard().getGrid(hosting);

        for (int i=0; i < 5; i++) {
            for (int j=0; j < 10; j++) {
                CardGraphicModel cm = new CardGraphicModel(xpos, ypos, grid[i][j], i, j);
                gameBoard.add(cm);
                cm.addMouseListener(cm);
                cm.addMouseMotionListener(cm);
                xpos+=67;
            }
            xpos = 0;
            ypos+=92;
        }

        playerInfo.setLayout(null);
        playerInfo.setBounds(0, 738, 1800, 462);
        playerInfo.setBackground(new Color(0,0,255));
        int playerNextX = 20;
        for (Card c : getViewUser().getAttributes().getHand()) {
            JPanel unknownCard = new JPanel();
            unknownCard.setLayout(null);
            unknownCard.setBounds(playerNextX, 20, 125, 250);
            JLabel title = new JLabel("Random Card");
            //Font font = new Font() // TODO : change font style and size
            title.setBounds(5, 0, 100, 25);
            title.setForeground(new Color(255,255,255));
            unknownCard.add(title);
            unknownCard.setBackground(new Color(0,0,0));
            playerNextX += 130;
            playerInfo.add(unknownCard);
        }

        enemyInfo.setLayout(null);
        enemyInfo.setBounds(0, 0, 1800, 280);
        enemyInfo.setBackground(new Color(255,0,0));
        int enemyNextX = 20;
        for (int i=0; i < getOpponent().getAttributes().getHand().size(); i++) {
            JPanel unknownCard = new JPanel();
            unknownCard.setLayout(null);
            unknownCard.setBounds(enemyNextX, 5, 125, 250);
            unknownCard.setBackground(new Color(0,0,0));
            enemyNextX += 130;
            enemyInfo.add(unknownCard);
        }

        infoTab.setBounds(1208, 280, 592, 458);
        infoTab.setBackground(new Color(128,128,128));

        gameMenu.setBounds(0, 280, 540, 458);
        gameMenu.setBackground(new Color(0,255,0));


        gameWindow.add(gameBoard);
        gameWindow.add(playerInfo);
        gameWindow.add(enemyInfo);
        gameWindow.add(infoTab);
        gameWindow.add(gameMenu);

        getContentPane().add(gameWindow);
        setVisible(true);

    }

    public void refresh() {

        CardSlot[][] grid = game.getBoard().getGrid(hosting);

        for (CardGraphicModel[] l : modelGrid) {
            for (CardGraphicModel cm : l) {
                cm.refresh(grid);
            }
        }

        int playerNextX = 20;
        for (Card c : getViewUser().getAttributes().getHand()) {
            JPanel unknownCard = new JPanel();
            unknownCard.setLayout(null);
            unknownCard.setBounds(playerNextX, 20, 125, 250);
            JLabel title = new JLabel("Random Card");
            title.setBounds(5, 0, 100, 25);
            title.setForeground(new Color(255,255,255));
            unknownCard.add(title);
            unknownCard.setBackground(new Color(0,0,0));
            playerNextX += 130;
            playerInfo.add(unknownCard);
        }

        int enemyNextX = 20;
        for (int i=0; i < getOpponent().getAttributes().getHand().size(); i++) {
            JPanel unknownCard = new JPanel();
            unknownCard.setLayout(null);
            unknownCard.setBounds(enemyNextX, 5, 125, 250);
            unknownCard.setBackground(new Color(0,0,0));
            enemyNextX += 130;
            enemyInfo.add(unknownCard);
        }

    }

    private void setCardInfo(int x, int y) {
        CardSlot target = game.getBoard().getGrid(hosting)[x][y];
        if (!target.isEmpty()) {
            // TODO : create info tab design
        }
    }

    private void clearCardInfo() {
        for (Component c : infoTab.getComponents()) {
            c.setVisible(false); // TODO : test this
        }
        infoTab.setBackground(new Color(128,128,128));
    }

    private class CardGraphicModel extends JPanel implements MouseInputListener {

        private final int gridRelativeX;
        private final int gridRelativeY;

        private static final int width = 65;
        private static final int height = 90;

        public CardGraphicModel(int xpos, int ypos, CardSlot c, int gridRelativeX, int gridRelativeY) {
            this.gridRelativeX = gridRelativeX;
            this.gridRelativeY = gridRelativeY;
            setBounds(xpos, ypos, width, height);
            if (c.isEmpty()) {
                setBackground(new Color(0,0,0));
            }
            else if (c.belongsTo(getViewUser())) {
                setBackground(new Color(0,255,0));
            }
            else {
                setBackground(new Color(255,0,0));
            }
        }

        protected void refresh(CardSlot[][] grid) {
            CardSlot c = grid[gridRelativeY][gridRelativeX];
            if (c.isEmpty()) {
                setBackground(new Color(0,0,0));
            }
            else if (c.belongsTo(getViewUser())) {
                setBackground(new Color(0,255,0));
            }
            else {
                setBackground(new Color(255,0,0));
            }
        }

        private static final long serialVersionUID = 1L;

        @Override
        public void mouseClicked(MouseEvent arg0) {
           System.out.println(String.format("Click in %d,%d", gridRelativeX, gridRelativeY)); // TODO : TESTING PURPOSES

        }

        public void mouseEntered(MouseEvent arg0) {
            setCardInfo(gridRelativeX, gridRelativeY);

        }

        public void mouseExited(MouseEvent arg0) {
            clearCardInfo();
        }

        public void mousePressed(MouseEvent arg0) {
        }

        public void mouseReleased(MouseEvent arg0) {
        }

        public void mouseDragged(MouseEvent arg0) {
        }

        public void mouseMoved(MouseEvent arg0) {
        }

    }
    
}
