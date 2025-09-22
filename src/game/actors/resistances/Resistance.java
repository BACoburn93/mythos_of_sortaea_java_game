package actors.resistances;

import abilities.damages.DamageTypes;

public abstract class Resistance {
    private DamageTypes name;
    private int value;

    public Resistance(DamageTypes name, int value) {
        this.name = name;
        this.value = value;
    }

    public DamageTypes getName() {
        return name;
    }

    public void setName(DamageTypes name) {
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
