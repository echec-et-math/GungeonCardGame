public class CardSlot {

    boolean owner = true; // true means player 1

    Card content = null;

    public CardSlot() {}

    public CardSlot(Card content) {
        this.content = content;
    }

	public boolean belongsToP1() {
		return owner;
	}

	public boolean isEmpty() {
		return content == null;
	}
}
