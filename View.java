import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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

    private BoardCardGraphicModel[][] modelGrid = new BoardCardGraphicModel[5][10];

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
                BoardCardGraphicModel cm = new BoardCardGraphicModel(xpos, ypos, grid[i][j], i, j);
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
        ArrayList<Card> playerHand = getViewUser().getAttributes().getHand();
        for (int i=0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            HandCardGraphicModel unknownCard = new HandCardGraphicModel(c, i);
            playerInfo.add(unknownCard);
        }
        JLabel deck_icon_player = new JLabel();
        deck_icon_player.setIcon(new ImageIcon("icons/DeckCount.png"));
        deck_icon_player.setBounds(1600, 20, 60, 60);
        playerInfo.add(deck_icon_player);
        JLabel deck_amount_player = new JLabel(Integer.toString(getViewUser().getAttributes().getAmountOfCardsLeft()));
        deck_amount_player.setForeground(new Color(255,255,255));
        deck_amount_player.setBounds(1700, 0, 100, 100);
        playerInfo.add(deck_amount_player);

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
        JLabel deck_icon_enemy = new JLabel();
        deck_icon_enemy.setIcon(new ImageIcon("icons/DeckCount.png"));
        deck_icon_enemy.setBounds(1600, 20, 60, 60);
        enemyInfo.add(deck_icon_enemy);
        JLabel deck_amount_enemy = new JLabel(Integer.toString(getViewUser().getAttributes().getAmountOfCardsLeft()));
        deck_amount_enemy.setForeground(new Color(0,0,0));
        deck_amount_enemy.setBounds(1700, 0, 100, 100);
        enemyInfo.add(deck_amount_enemy);

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

        for (BoardCardGraphicModel[] l : modelGrid) {
            for (BoardCardGraphicModel cm : l) {
                cm.refresh(grid);
            }
        }

        ArrayList<Card> playerHand = getViewUser().getAttributes().getHand();
        for (int i=0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            HandCardGraphicModel unknownCard = new HandCardGraphicModel(c, i);
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

    private class HandCardGraphicModel extends JPanel implements MouseInputListener {

        private static final long serialVersionUID = 1L;

        public HandCardGraphicModel(Card c, int index) {
            setLayout(null);
            setBounds(20 + index*130, 20, 125, 250);
            setBackground(new Color(0,0,0));

            if (c instanceof Mob) {

                Mob m = (Mob) c;

                JLabel title = new JLabel(m.name + " (" + m.cost + ")");
                title.setBounds(5, 0, 100, 25);
                title.setForeground(new Color(255,255,255));
                add(title);

                JLabel HP = new JLabel("HP : "+m.HP+"/"+m.maxHP);
                HP.setBounds(2, 25, 125, 25);
                HP.setForeground(new Color(0,255,0));

                add(HP);

                JLabel dmg = new JLabel("ATK : " + m.damage);
                dmg.setBounds(2, 50, 125, 25);
                dmg.setForeground(new Color(255,0,0));
                add(dmg);

                JLabel range = new JLabel("Range : " + m.range);
                range.setBounds(2, 75, 125, 25);
                range.setForeground(new Color(0,0,255));
                add(range);

                JLabel speed = new JLabel("SPD : " + m.speed);
                speed.setBounds(2, 100, 125, 25);
                speed.setForeground(new Color(255,255,0));
                add(speed);
            }

        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseDragged(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseMoved(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }
        
    }

    private class BoardCardGraphicModel extends JPanel implements MouseInputListener {

        private final int gridRelativeX;
        private final int gridRelativeY;

        private static final int width = 65;
        private static final int height = 90;

        public BoardCardGraphicModel(int xpos, int ypos, CardSlot c, int gridRelativeX, int gridRelativeY) {
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
