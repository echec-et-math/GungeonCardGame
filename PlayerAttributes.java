import java.util.ArrayList;

public class PlayerAttributes {
    private int keys;
    public int getKeys() {
        return keys;
    }

    private int money;
    public int getMoney() {
        return money;
    }

    private int activeSlots;
    public int getAmountOfActiveSlots() {
        return activeSlots;
    }

    private int cardsLeftInDeck;
    public int getAmountOfCardsLeft() {
        return cardsLeftInDeck;
    }

    private ArrayList<Card> hand;
    public ArrayList<Card> getHand() {
        return hand;
    }

    private ArrayList<Chest> chests;
    public ArrayList<Chest> getChests() {
        return chests;
    }
}
