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

