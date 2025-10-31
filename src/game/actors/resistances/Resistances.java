package actors.resistances;

import abilities.damages.DamageTypes;

public class Resistances {
    private BludgeoningResistance bludgeoning;
    private PiercingResistance piercing;
    private SlashingResistance slashing;
    private EarthResistance earth;
    private FireResistance fire;
    private IceResistance ice;
    private LightningResistance lightning;
    private VenomResistance venom;
    private WaterResistance water;
    private WindResistance wind;
    private DarknessResistance darkness;
    private LightResistance light;

    public Resistances(BludgeoningResistance bludgeoning, PiercingResistance piercing, SlashingResistance slashing,
                       EarthResistance earth, FireResistance fire, IceResistance ice,
                       LightningResistance lightning, VenomResistance venom, WaterResistance water,
                       WindResistance wind, DarknessResistance darkness, LightResistance light) {
        this.bludgeoning = bludgeoning;
        this.piercing = piercing;
        this.slashing = slashing;
        this.earth = earth;
        this.fire = fire;
        this.ice = ice;
        this.lightning = lightning;
        this.venom = venom;
        this.water = water;
        this.wind = wind;
        this.darkness = darkness;
        this.light = light;
    }

    public Resistances(double bludgeoning, double piercing, double slashing, double earth, double fire, double ice, double lightning,
    double venom, double water, double wind, double darkness, double light) {
        this.bludgeoning = new BludgeoningResistance(bludgeoning);
        this.piercing = new PiercingResistance(piercing);
        this.slashing = new SlashingResistance(slashing);
        this.earth = new EarthResistance(earth);
        this.fire = new FireResistance(fire);
        this.ice = new IceResistance(ice);
        this.lightning = new LightningResistance(lightning);
        this.venom = new VenomResistance(venom);
        this.water = new WaterResistance(water);
        this.wind = new WindResistance(wind);
        this.darkness = new DarknessResistance(darkness);
        this.light = new LightResistance(light);
    }

    public Resistances() {
        this(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0);
    }

    public Double getResistance(DamageTypes type) {
        return switch (type) {
            case BLUDGEONING -> bludgeoning.getValue();
            case PIERCING -> piercing.getValue();
            case SLASHING -> slashing.getValue();
            case EARTH -> earth.getValue();
            case FIRE -> fire.getValue();
            case ICE -> ice.getValue();
            case LIGHTNING -> lightning.getValue();
            case VENOM -> venom.getValue();
            case WATER -> water.getValue();
            case WIND -> wind.getValue();
            case DARKNESS -> darkness.getValue();
            case LIGHT -> light.getValue();
            default -> throw new IllegalArgumentException("Unknown damage type: " + type);
        };
    }

    public BludgeoningResistance getBludgeoning() {
        return bludgeoning;
    }

    public PiercingResistance getPiercing() {
        return piercing;
    }

    public SlashingResistance getSlashing() {
        return slashing;
    }

    public EarthResistance getEarth() {
        return earth;
    }

    public FireResistance getFire() {
        return fire;
    }

    public IceResistance getIce() {
        return ice;
    }

    public LightningResistance getLightning() {
        return lightning;
    }

    public VenomResistance getVenom() {
        return venom;
    }

    public WaterResistance getWater() {
        return water;
    }

    public WindResistance getWind() {
        return wind;
    }

    public DarknessResistance getDarkness() {
        return darkness;
    }

    public LightResistance getLight() {
        return light;
    }

    public void add(Resistances other) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() + other.bludgeoning.getValue());
        this.piercing.setValue(this.piercing.getValue() + other.piercing.getValue());
        this.slashing.setValue(this.slashing.getValue() + other.slashing.getValue());
        this.earth.setValue(this.earth.getValue() + other.earth.getValue());
        this.fire.setValue(this.fire.getValue() + other.fire.getValue());
        this.ice.setValue(this.ice.getValue() + other.ice.getValue());
        this.lightning.setValue(this.lightning.getValue() + other.lightning.getValue());
        this.venom.setValue(this.venom.getValue() + other.venom.getValue());
        this.water.setValue(this.water.getValue() + other.water.getValue());
        this.wind.setValue(this.wind.getValue() + other.wind.getValue());
        this.darkness.setValue(this.darkness.getValue() + other.darkness.getValue());
        this.light.setValue(this.light.getValue() + other.light.getValue());
    }

    // add scalar to every resistance
    public void add(double scalar) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() + scalar);
        this.piercing.setValue(this.piercing.getValue() + scalar);
        this.slashing.setValue(this.slashing.getValue() + scalar);
        this.earth.setValue(this.earth.getValue() + scalar);
        this.fire.setValue(this.fire.getValue() + scalar);
        this.ice.setValue(this.ice.getValue() + scalar);
        this.lightning.setValue(this.lightning.getValue() + scalar);
        this.venom.setValue(this.venom.getValue() + scalar);
        this.water.setValue(this.water.getValue() + scalar);
        this.wind.setValue(this.wind.getValue() + scalar);
        this.darkness.setValue(this.darkness.getValue() + scalar);
        this.light.setValue(this.light.getValue() + scalar);
    }

    // Add individual resistances by a scalar
    public void addBludgeoning(double scalar) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() + scalar);
    }
    public void addPiercing(double scalar) {
        this.piercing.setValue(this.piercing.getValue() + scalar);
    }
    public void addSlashing(double scalar) {
        this.slashing.setValue(this.slashing.getValue() + scalar);
    }
    public void addEarth(double scalar) {
        this.earth.setValue(this.earth.getValue() + scalar);
    }
    public void addFire(double scalar) {
        this.fire.setValue(this.fire.getValue() + scalar);
    }
    public void addIce(double scalar) {
        this.ice.setValue(this.ice.getValue() + scalar);
    }
    public void addLightning(double scalar) {
        this.lightning.setValue(this.lightning.getValue() + scalar);
    }
    public void addVenom(double scalar) {
        this.venom.setValue(this.venom.getValue() + scalar);
    }
    public void addWater(double scalar) {
        this.water.setValue(this.water.getValue() + scalar);
    }
    public void addWind(double scalar) {
        this.wind.setValue(this.wind.getValue() + scalar);
    }
    public void addDarkness(double scalar) {
        this.darkness.setValue(this.darkness.getValue() + scalar);
    }
    public void addLight(double scalar) {
        this.light.setValue(this.light.getValue() + scalar);
    }

    public void subtract(Resistances other) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() - other.bludgeoning.getValue());
        this.piercing.setValue(this.piercing.getValue() - other.piercing.getValue());
        this.slashing.setValue(this.slashing.getValue() - other.slashing.getValue());
        this.earth.setValue(this.earth.getValue() - other.earth.getValue());
        this.fire.setValue(this.fire.getValue() - other.fire.getValue());
        this.ice.setValue(this.ice.getValue() - other.ice.getValue());
        this.lightning.setValue(this.lightning.getValue() - other.lightning.getValue());
        this.venom.setValue(this.venom.getValue() - other.venom.getValue());
        this.water.setValue(this.water.getValue() - other.water.getValue());
        this.wind.setValue(this.wind.getValue() - other.wind.getValue());
        this.darkness.setValue(this.darkness.getValue() - other.darkness.getValue());
        this.light.setValue(this.light.getValue() - other.light.getValue());
    }

    // subtract scalar from every resistance
    public void subtract(double scalar) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() - scalar);
        this.piercing.setValue(this.piercing.getValue() - scalar);
        this.slashing.setValue(this.slashing.getValue() - scalar);
        this.earth.setValue(this.earth.getValue() - scalar);
        this.fire.setValue(this.fire.getValue() - scalar);
        this.ice.setValue(this.ice.getValue() - scalar);
        this.lightning.setValue(this.lightning.getValue() - scalar);
        this.venom.setValue(this.venom.getValue() - scalar);
        this.water.setValue(this.water.getValue() - scalar);
        this.wind.setValue(this.wind.getValue() - scalar);
        this.darkness.setValue(this.darkness.getValue() - scalar);
        this.light.setValue(this.light.getValue() - scalar);
    }

    // Subtract individual resistances by a scalar
    public void subtractBludgeoning(double scalar) {
        this.bludgeoning.setValue(this.bludgeoning.getValue() - scalar);
    }
    public void subtractPiercing(double scalar) {
        this.piercing.setValue(this.piercing.getValue() - scalar);
    }
    public void subtractSlashing(double scalar) {
        this.slashing.setValue(this.slashing.getValue() - scalar);
    }
    public void subtractEarth(double scalar) {
        this.earth.setValue(this.earth.getValue() - scalar);
    }
    public void subtractFire(double scalar) {
        this.fire.setValue(this.fire.getValue() - scalar);
    }
    public void subtractIce(double scalar) {
        this.ice.setValue(this.ice.getValue() - scalar);
    }
    public void subtractLightning(double scalar) {
        this.lightning.setValue(this.lightning.getValue() - scalar);
    }
    public void subtractVenom(double scalar) {
        this.venom.setValue(this.venom.getValue() - scalar);
    }
    public void subtractWater(double scalar) {
        this.water.setValue(this.water.getValue() - scalar);
    }
    public void subtractWind(double scalar) {
        this.wind.setValue(this.wind.getValue() - scalar);
    }
    public void subtractDarkness(double scalar) {
        this.darkness.setValue(this.darkness.getValue() - scalar);
    }
    public void subtractLight(double scalar) {
        this.light.setValue(this.light.getValue() - scalar);
    }


    // divide every resistance by a scalar
    public void divideAll(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        bludgeoning.setValue(bludgeoning.getValue() / divisor);
        piercing.setValue(piercing.getValue() / divisor);
        slashing.setValue(slashing.getValue() / divisor);
        earth.setValue(earth.getValue() / divisor);
        fire.setValue(fire.getValue() / divisor);
        ice.setValue(ice.getValue() / divisor);
        lightning.setValue(lightning.getValue() / divisor);
        venom.setValue(venom.getValue() / divisor);
        water.setValue(water.getValue() / divisor);
        wind.setValue(wind.getValue() / divisor);
        darkness.setValue(darkness.getValue() / divisor);
        light.setValue(light.getValue() / divisor);
    }

    // element-wise divide by another Resistances; if other element is zero, skip that element
    public void divide(Resistances other) {
        if (other == null) return;
        if (other.bludgeoning.getValue() != 0.0) this.bludgeoning.setValue(this.bludgeoning.getValue() / other.bludgeoning.getValue());
        if (other.piercing.getValue() != 0.0) this.piercing.setValue(this.piercing.getValue() / other.piercing.getValue());
        if (other.slashing.getValue() != 0.0) this.slashing.setValue(this.slashing.getValue() / other.slashing.getValue());
        if (other.earth.getValue() != 0.0) this.earth.setValue(this.earth.getValue() / other.earth.getValue());
        if (other.fire.getValue() != 0.0) this.fire.setValue(this.fire.getValue() / other.fire.getValue());
        if (other.ice.getValue() != 0.0) this.ice.setValue(this.ice.getValue() / other.ice.getValue());
        if (other.lightning.getValue() != 0.0) this.lightning.setValue(this.lightning.getValue() / other.lightning.getValue());
        if (other.venom.getValue() != 0.0) this.venom.setValue(this.venom.getValue() / other.venom.getValue());
        if (other.water.getValue() != 0.0) this.water.setValue(this.water.getValue() / other.water.getValue());
        if (other.wind.getValue() != 0.0) this.wind.setValue(this.wind.getValue() / other.wind.getValue());
        if (other.darkness.getValue() != 0.0) this.darkness.setValue(this.darkness.getValue() / other.darkness.getValue());
        if (other.light.getValue() != 0.0) this.light.setValue(this.light.getValue() / other.light.getValue());
    }

    // didvide individual resistances by a scalar
    public void divideBludgeoning(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        bludgeoning.setValue(bludgeoning.getValue() / divisor);
    }
    public void dividePiercing(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        piercing.setValue(piercing.getValue() / divisor);
    }
    public void divideSlashing(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        slashing.setValue(slashing.getValue() / divisor);
    }
    public void divideEarth(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        earth.setValue(earth.getValue() / divisor);
    }
    public void divideFire(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        fire.setValue(fire.getValue() / divisor);
    }
    public void divideIce(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        ice.setValue(ice.getValue() / divisor);
    }
    public void divideLightning(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        lightning.setValue(lightning.getValue() / divisor);
    }
    public void divideVenom(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        venom.setValue(venom.getValue() / divisor);
    }
    public void divideWater(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        water.setValue(water.getValue() / divisor);
    }
    public void divideWind(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        wind.setValue(wind.getValue() / divisor);
    }
    public void divideDarkness(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        darkness.setValue(darkness.getValue() / divisor);
    }
    public void divideLight(double divisor) {
        if (divisor == 0.0) throw new IllegalArgumentException("divisor must not be zero");
        light.setValue(light.getValue() / divisor);
    }


    public void multiplyAll(double factor) {
        bludgeoning.setValue(bludgeoning.getValue() * factor);
        piercing.setValue(piercing.getValue() * factor);
        slashing.setValue(slashing.getValue() * factor);
        earth.setValue(earth.getValue() * factor);
        fire.setValue(fire.getValue() * factor);
        ice.setValue(ice.getValue() * factor);
        lightning.setValue(lightning.getValue() * factor);
        venom.setValue(venom.getValue() * factor);
        water.setValue(water.getValue() * factor);
        wind.setValue(wind.getValue() * factor);
        darkness.setValue(darkness.getValue() * factor);
        light.setValue(light.getValue() * factor);
    }

    // multiply individual resistances by a factor
    public void multiplyBludgeoning(double factor) {
        bludgeoning.setValue(bludgeoning.getValue() * factor);
    }
    public void multiplyPiercing(double factor) {
        piercing.setValue(piercing.getValue() * factor);
    }
    public void multiplySlashing(double factor) {
        slashing.setValue(slashing.getValue() * factor);
    }
    public void multiplyEarth(double factor) {
        earth.setValue(earth.getValue() * factor);
    }
    public void multiplyFire(double factor) {
        fire.setValue(fire.getValue() * factor);
    }
    public void multiplyIce(double factor) {
        ice.setValue(ice.getValue() * factor);
    }
    public void multiplyLightning(double factor) {
        lightning.setValue(lightning.getValue() * factor);
    }
    public void multiplyVenom(double factor) {
        venom.setValue(venom.getValue() * factor);
    }
    public void multiplyWater(double factor) {
        water.setValue(water.getValue() * factor);
    }
    public void multiplyWind(double factor) {
        wind.setValue(wind.getValue() * factor);
    }
    public void multiplyDarkness(double factor) {
        darkness.setValue(darkness.getValue() * factor);
    }
    public void multiplyLight(double factor) {
        light.setValue(light.getValue() * factor);
    }

    @Override
    public String toString() {
        return bludgeoning + ", " + piercing + ", " + slashing + ", " +
                earth + ", " + fire + ", " + ice + ", " + lightning + ", " +
                venom + ", " + water + ", " + wind + ", " + darkness + ", " + light;
    }
}

