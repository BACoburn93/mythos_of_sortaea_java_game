package items;

public abstract class Item {
    private String name;
    private double value;
    private int quantity;
    private int actionCost;

    public Item(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.actionCost = 1;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
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

