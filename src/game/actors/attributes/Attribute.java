package actors.attributes;

public abstract class Attribute {
    private AttributeTypes name;
    private double value;

    public Attribute(AttributeTypes name, double value) {
        this.name = name;
        this.value = value;
    }

    public AttributeTypes getName() {
        return name;
    }

    public void setName(AttributeTypes name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + (int) value;
    }
}

