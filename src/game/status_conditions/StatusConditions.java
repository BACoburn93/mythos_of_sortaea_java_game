package status_conditions;

import java.util.List;

public class StatusConditions {
    private Bleed bleed;
    private Blind blind;
    private Burn burn;
    private Confused confused;
    private Dry dry;
    private Envenom envenom;
    private Fear fear;
    private Freeze freeze;
    private Manipulate manipulate;
    private Paralyze paralyze;
    private Poison poison;
    private Rot rot;
    private Sick sick;
    private Slow slow;
    private Stun stun;
    private Weak weak;
    private Wet wet;

    // Handle initialization of status conditions by passing in specific status condition objects
    public StatusConditions(Bleed bleed, Blind blind, Burn burn, Confused confused,
                            Dry dry, Envenom envenom, Fear fear, Freeze freeze,
                            Manipulate manipulate, Paralyze paralyze, Poison poison,
                            Rot rot, Sick sick, Slow slow, Stun stun, Weak weak, Wet wet) {
        this.bleed = bleed;
        this.blind = blind;
        this.burn = burn;
        this.confused = confused;
        this.dry = dry;
        this.envenom = envenom;
        this.fear = fear;
        this.freeze = freeze;
        this.manipulate = manipulate;
        this.paralyze = paralyze;
        this.poison = poison;
        this.rot = rot;
        this.sick = sick;
        this.slow = slow;
        this.stun = stun;
        this.weak = weak;
        this.wet = wet;
    }

    // Handle initialization of status conditions by resistance values
    public StatusConditions(int bleed, int blind, int burn, int confused,
                            int dry, int envenom, int fear, int freeze,
                            int manipulate, int paralyze, int poison,
                            int rot, int sick, int slow, int stun, int weak, int wet) {
        this.bleed = new Bleed(0, bleed);
        this.blind = new Blind(0, blind);
        this.burn = new Burn(0, burn);
        this.confused = new Confused(0, confused);
        this.dry = new Dry(0, dry);
        this.envenom = new Envenom(0, envenom);
        this.fear = new Fear(0, fear);
        this.freeze = new Freeze(0, freeze);
        this.manipulate = new Manipulate(0, manipulate);
        this.paralyze = new Paralyze(0, paralyze);
        this.poison = new Poison(0, poison);
        this.rot = new Rot(0, rot);
        this.sick = new Sick(0, sick);
        this.slow = new Slow(0, slow);
        this.stun = new Stun(0, stun);
        this.weak = new Weak(0, weak);
        this.wet = new Wet(0, wet);
    }

    // Default constructor initializing all status conditions with default values set to 0
    public StatusConditions() {
        this.bleed = new Bleed(0, 0);
        this.blind = new Blind(0, 0);
        this.burn = new Burn(0, 0);
        this.confused = new Confused(0, 0);
        this.dry = new Dry(0, 0);
        this.envenom = new Envenom(0, 0);
        this.fear = new Fear(0, 0);
        this.freeze = new Freeze(0, 0);
        this.manipulate = new Manipulate(0, 0);
        this.paralyze = new Paralyze(0, 0);
        this.poison = new Poison(0, 0);
        this.rot = new Rot(0, 0);
        this.sick = new Sick(0, 0);
        this.slow = new Slow(0, 0);
        this.stun = new Stun(0, 0);
        this.weak = new Weak(0, 0);
        this.wet = new Wet(0, 0);
    }

    public Bleed getBleed() {
        return bleed;
    }

    public Blind getBlind() {
        return blind;
    }

    public Burn getBurn() {
        return burn;
    }

    public Confused getConfused() {
        return confused;
    }

    public Dry getDry() {
        return dry;
    }

    public Envenom getEnvenom() {
        return envenom;
    }

    public Fear getFear() {
        return fear;
    }

    public Freeze getFreeze() {
        return freeze;
    }

    public Manipulate getManipulate() {
        return manipulate;
    }

    public Paralyze getParalyze() {
        return paralyze;
    }

    public Poison getPoison() {
        return poison;
    }

    public Rot getRot() {
        return rot;
    }

    public Sick getSick() {
        return sick;
    }

    public Slow getSlow() {
        return slow;
    }

    public Stun getStun() {
        return stun;
    }

    public Weak getWeak() {
        return weak;
    }

    public Wet getWet() {
        return wet;
    }

    public StatusCondition getStatus(StatusTypes type) {
        return switch (type) {
            case BLEED -> bleed;
            case BLIND -> blind;
            case BURN -> burn;
            case CONFUSED -> confused;
            case DRY -> dry;
            case ENVENOM -> envenom;
            case FEAR -> fear;
            case FREEZE -> freeze;
            case MANIPULATE -> manipulate;
            case PARALYZE -> paralyze;
            case POISON -> poison;
            case ROT -> rot;
            case SICK -> sick;
            case SLOW -> slow;
            case STUN -> stun;
            case WEAK -> weak;
            case WET -> wet;
        };
    }

    public String getStatusAffectedText(StatusTypes type) {
        return switch (type) {
            case BLEED -> " has begun bleeding.";
            case BLIND -> " has been blinded.";
            case BURN -> " is burning.";
            case CONFUSED -> " is confused.";
            case DRY -> " has been dried.";
            case ENVENOM -> " has been affected by venom.";
            case FEAR -> " is scared.";
            case FREEZE -> " is frozen.";
            case MANIPULATE -> " is being manipulated.";
            case PARALYZE -> " is paralyzed.";
            case POISON -> " is poisoned.";
            case ROT -> " has begun rotting.";
            case SICK -> " is sick.";
            case SLOW -> " has it's movements slowed.";
            case STUN -> " has been stunned.";
            case WEAK -> " is feeling weak.";
            case WET -> " has been soaked.";
        };
    }

    public List<StatusCondition> getAll() {
        return List.of(
            bleed, blind, burn, confused, dry, envenom, fear, freeze,
            manipulate, paralyze, poison, rot, sick, slow, stun, weak, wet
        );
    }

    @Override
    public String toString() {
        return bleed + ", " + blind + ", " + burn + ", " +
                confused + ", " + dry + ", " + envenom + ", " + fear + ", " +
                freeze + ", " + manipulate + ", " + paralyze + ", " + poison +
                ", " + rot + ", " + sick + ", " + slow + ", " + stun + ", " +
                weak + ", " + wet;
    }
}
