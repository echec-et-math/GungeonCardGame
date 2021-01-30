public class Board {

    private CardSlot[][] grid = new CardSlot[5][10];

    public Board() {
        for (int k=0; k < 10; k++) {
            for (int l=0; l < 5; l++) {
                grid[l][k] = new CardSlot();
            }
        }
    }

    public CardSlot[][] getGrid() {
        return grid;
    }
}
