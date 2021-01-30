public class CardSlot {

    Card content = null;

    public CardSlot() {}

    public CardSlot(Card content) {
        this.content = content;
    }

	public boolean belongsTo(Player p) {

		return content != null && content.getOwner().getId() == p.getId();
	}

	public boolean isEmpty() {
		return content == null;
	}
}
