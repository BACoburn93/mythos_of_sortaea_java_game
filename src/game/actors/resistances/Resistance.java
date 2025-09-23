package actors.resistances;

import abilities.damages.DamageTypes;

public abstract class Resistance {
    private DamageTypes name;
    private double value;

    public Resistance(DamageTypes name, double value) {
        this.name = name;
        this.value = value;
    }

    public DamageTypes getName() {
        return name;
    }

    public void setName(DamageTypes name) {
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
        return name + ": " + value;
    }
}
