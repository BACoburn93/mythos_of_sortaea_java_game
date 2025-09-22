package actors.attributes;

public abstract class Attribute {
    private AttributeTypes name;
    private int value;

    public Attribute(AttributeTypes name, int value) {
        this.name = name;
        this.value = value;
    }

    public AttributeTypes getName() {
        return name;
    }

    public void setName(AttributeTypes name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}

