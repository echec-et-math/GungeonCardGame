import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.GridLayout;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;

    private Game game;

    private JPanel gameBoard = new JPanel();

    private JPanel gameWindow = new JPanel();

    private JPanel gameMenu = new JPanel();

    private JPanel infoTab = new JPanel();

    private CardGraphicModel[][] modelGrid = new CardGraphicModel[5][10];

    private JPanel playerInfo = new JPanel();
    private JPanel enemyInfo = new JPanel();

    public View(Game game) {
        this.game = game;
        setSize(1800, 1000);
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
        CardSlot[][] grid = game.getBoard().getGrid();

        for (int i=0; i < 5; i++) {
            for (int j=0; j < 10; j++) {
                CardGraphicModel cm = new CardGraphicModel(xpos, ypos, grid[i][j], i, j);
                gameBoard.add(cm);
                xpos+=67;
            }
            xpos = 0;
            ypos+=92;
        }

        playerInfo.setBounds(0, 738, 1800, 250);
        playerInfo.setBackground(new Color(0,0,255));

        enemyInfo.setBounds(0, 0, 1800, 280);
        enemyInfo.setBackground(new Color(255,0,0));

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

        CardSlot[][] grid = game.getBoard().getGrid();

        for (CardGraphicModel[] l : modelGrid) {
            for (CardGraphicModel cm : l) {
                cm.refresh(grid);
            }
        }
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
            else if (c.belongsToP1()) {
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
            else if (c.belongsToP1()) {
                setBackground(new Color(0,255,0));
            }
            else {
                setBackground(new Color(255,0,0));
            }
        }

        private static final long serialVersionUID = 1L;

        @Override
        public void mouseClicked(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO

        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO

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
    
}
