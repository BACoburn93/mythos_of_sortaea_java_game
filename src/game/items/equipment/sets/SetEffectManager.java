package items.equipment.sets;

import characters.Character;
import characters.managers.SetManager;
import characters.managers.AttributeSetBonusApplier;


public class SetEffectManager {
    static {
        // Ensure applier is installed once at class load / game startup.
        SetManager.getInstance().setApplier(new AttributeSetBonusApplier());
    }

    public static void updateCharacterSets(Character c) {
        SetManager.getInstance().reconcile(c);
    }
}