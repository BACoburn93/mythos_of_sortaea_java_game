package items;

public abstract class Item {
    private String name;
    private int goldValue;
    private int quantity;
    private int actionCost;

    public Item(String name, int goldValue, int quantity) {
        this.name = name;
        this.goldValue = goldValue;
        this.quantity = quantity;
        this.actionCost = 1;
    }

    public String getName() {
        return name;
    }

    public int getGoldValue() {
        return goldValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void incrementQuantity() {
        quantity++;
    }

    public int getActionCost() {
        return actionCost;
    }

    @Override
    public String toString() {
        return name;
    }
}

