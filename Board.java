public class Board {

    private CardSlot[][] grid = new CardSlot[5][10];

    public Board() {
        for (int k=0; k < 10; k++) {
            for (int l=0; l < 5; l++) {
                grid[l][k] = new CardSlot();
            }
        }
    }

    public CardSlot[][] getGrid(boolean hosting) {
        if (hosting) {
            return grid;
        }
        else {
            CardSlot[][] seenAsClient = new CardSlot[5][10];
            for (int i=0; i < 5; i++) {
                for (int j=0; j < 10; j++) {
                    seenAsClient[i][j] = grid[5 - i - 1][10 - j - 1];
                }
            }
            return seenAsClient;
        }
    }
}
