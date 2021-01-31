import java.util.ArrayList;

public class Player {
    private final int id;

    private PlayerAttributes attributes = new PlayerAttributes();
    
    public int getId() {
        return id;
    }

    public Player(int id) {
        this.id = id;
        attributes.cardsLeftInDeck = 20; // TODO : for test purposes obviously
    }

    public PlayerAttributes getAttributes() {
        return attributes;
    }

    public void drawCard() {
        attributes.hand.add(new BulletKin()); // TODO : test purposes
    }

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
    
        private ArrayList<Card> hand = new ArrayList<Card>();;
        public ArrayList<Card> getHand() {
            return hand;
        }
    
        private ArrayList<Chest> chests = new ArrayList<Chest>();;
        public ArrayList<Chest> getChests() {
            return chests;
        }
    }
}
