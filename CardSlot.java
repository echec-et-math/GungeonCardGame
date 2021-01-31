public class CardSlot {

    Mob content = null;

    public CardSlot() {}

    public CardSlot(Mob content) {
        this.content = content;
    }

	public boolean belongsTo(Player p) {

		return content != null && content.getOwner().getId() == p.getId();
	}

	public boolean isEmpty() {
		return content == null;
	}
}
