class CustomDice {
    static roll(diceStr) {
        const [count, sides] = diceStr.toLowerCase().split('d').map(Number);
        let total = 0;
        for (let i = 0; i < count; i++) {
            total += Math.floor(Math.random() * sides) + 1;
        }
        return total;
    }
}

class Weapon {
    constructor({ name, attackBonus = 0, damageBonus = 0, damageType = 'slashing', dice = '1d6', modifierAttr='strength' }) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.damageBonus = damageBonus;
        this.damageType = damageType;
        this.dice = dice;
        this.modifierAttr = modifierAttr;
    }

    rollDamage() {
        return CustomDice.roll(this.dice) + this.damageBonus;
    }
}

class Spell {
    constructor({ name, attackBonus = 0, damageDice = '1d6', damageType = 'force', level = 0 }) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.level = level;
    }

    rollDamage() {
        return CustomDice.roll(this.damageDice);
    }
}

class DamageType {
    constructor(type, amount) {
        this.type = type;
        this.amount = amount;
    }
}

class Character {
    constructor({
        name,
        hp,
        ac,
        weapon,
        spellCastingMod = 'intelligence',
        spells = [],
        strength = 10,
        dexterity = 10,
        constitution = 10,
        intelligence = 10,
        wisdom = 10,
        charisma = 10,
        resistances = [],
        immunities = [],
        hasAdvantage = false, 
        hasDisadvantage = false,
        damageMod = new DamageType('none', 0),
        level = 1,
        speed = 30,
        initiative = 0,
        conditions = []
    }) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.ac = ac;
        this.weapon = weapon;
        this.spells = spells;
        this.spellCastingMod = spellCastingMod;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.resistances = resistances; 
        this.immunities = immunities; 
        this.hasAdvantage = hasAdvantage;
        this.hasDisadvantage = hasDisadvantage;
        this.damageMod = damageMod;
        this.level = level;
        this.proficiencyBonus = Math.ceil(level / 4) + 1;
        this.speed = speed;
        this.initiative = initiative;
        this.conditions = conditions;
    }

    isAlive() {
        return this.hp > 0;
    }

    attack(target, customModifiers = []) {
        let attr = (this.weapon.modifierAttr || 'strength').toLowerCase();
        let statValue = this[attr] ?? this.strength;
        if (typeof statValue !== "number" || isNaN(statValue)) {
            statValue = this.strength;
        }
        let statMod = Math.floor((statValue - 10) / 2);

        let attackRoll = CustomDice.roll("1d20");
        let attackRollAdv = CustomDice.roll("1d20");
        let rawRoll = attackRoll;
        if (this.hasAdvantage) {
            rawRoll = Math.max(attackRoll, attackRollAdv);
        }
        if (this.hasDisadvantage) {
            rawRoll = Math.min(attackRoll, attackRollAdv);
        }
        let totalAttack = rawRoll + this.weapon.attackBonus + this.proficiencyBonus + statMod;

        let isCrit = rawRoll === 20;
        let isCritMiss = rawRoll === 1;

        console.log(`${this.name} rolls an attack: d20(${rawRoll}) + weaponBonus(${this.weapon.attackBonus}) + proficiency(${this.proficiencyBonus}) + statMod(${statMod}) = total(${totalAttack}) vs AC(${target.ac})`);

        let hit = totalAttack >= target.ac && !isCritMiss;

        let result = {
            attacker: this.name,
            target: target.name,
            attackRoll: rawRoll,
            totalAttack,
            hit,
            isCrit,
            isCritMiss,
            damage: []
        };

        if (isCritMiss) {
            console.log(`${this.name} critically misses!`);
            return result;
        }

        if (hit) {
            let weaponDamage = this.weapon.rollDamage();
            let weaponType = this.weapon.damageType;
            let totalDamage = weaponDamage;

            if (isCrit) {
                console.log(`${this.name} scores a critical hit!`);
                totalDamage += this.weapon.rollDamage();
            }

            result.damage.push({ type: weaponType, amount: statMod });

            if (this.damageMod && this.damageMod.amount > 0) {
                result.damage.push({ type: this.damageMod.type, amount: this.damageMod.amount });
            }

            for (const mod of customModifiers) {
                result.damage.push({ type: mod.type, amount: mod.amount });
            }

            result.damage.push({ type: weaponType, amount: totalDamage });

            let damageLog = result.damage.map(d => `${d.amount} ${d.type}`).join(', ');
            console.log(`${this.name} deals: ${damageLog}`);

            let finalDamage = 0;
            for (const dmg of result.damage) {
                if (target.immunities.includes(dmg.type)) continue;
                if (target.resistances.includes(dmg.type)) {
                    finalDamage += Math.floor(dmg.amount / 2);
                } else {
                    finalDamage += dmg.amount;
                }
            }

            target.hp -= finalDamage;
            result.finalDamage = finalDamage;
            result.hp = target.hp;
        }

        return result;
    }

    spellAttack(target, spell) {
        let attackRoll = CustomDice.roll("1d20");
        let attackRollAdv = CustomDice.roll("1d20");
        let rawRoll = attackRoll;

        let statValue = this[this.spellCastingMod] ?? this.intelligence;
        
        if (typeof statValue !== "number" || isNaN(statValue)) {
            statValue = this.intelligence;
        }

        let statMod = statValue ? Math.floor((statValue - 10) / 2) : 0;

        if (this.hasAdvantage) {
            rawRoll = Math.max(attackRoll, attackRollAdv);
        }
        if (this.hasDisadvantage) {
            rawRoll = Math.min(attackRoll, attackRollAdv);
        }
        let totalAttack = rawRoll + statMod + this.proficiencyBonus;

        let isCrit = rawRoll === 20;
        let isCritMiss = rawRoll === 1;

        console.log(`${this.name} casts ${spell.name}: d20(${rawRoll}) + spellAttackBonus(${statMod}) + proficiency(${this.proficiencyBonus}) = total(${totalAttack}) vs AC(${target.ac})`);

        let hit = totalAttack >= target.ac && !isCritMiss;

        let result = {
            caster: this.name,
            target: target.name,
            attackRoll: rawRoll,
            totalAttack,
            hit,
            isCrit,
            isCritMiss,
            damage: []
        };

        if (isCritMiss) {
            console.log(`${this.name} critically misses!`);
            return result;
        }

        if (hit) {
            let spellDamage = spell.rollDamage();
            if (isCrit) {
                console.log(`${this.name} scores a critical hit!`);
                spellDamage += spell.rollDamage();
            }
            result.damage.push(new Damage({ amount: spellDamage, type: spell.damageType }));

            let damageLog = result.damage.map(d => `${d.amount} ${d.type}`).join(', ');
            console.log(`${this.name} deals: ${damageLog}`);

            let finalDamage = 0;
            for (const dmg of result.damage) {
                if (target.immunities.includes(dmg.type)) continue;
                if (target.resistances.includes(dmg.type)) {
                    finalDamage += Math.floor(dmg.amount / 2);
                } else {
                    finalDamage += dmg.amount;
                }
            }

            target.hp -= finalDamage;
            result.finalDamage = finalDamage;
            result.hp = target.hp;
        }

        return result;
    }
}

const eldritchBlast = new Spell({
    name: "Eldritch Blast",
    attackBonus: 0,
    damageDice: "1d10",
    damageType: "force",
    level: 0
});

const fireBolt = new Spell({
    name: "Fire Bolt",
    attackBonus: 0,
    damageDice: "1d10",
    damageType: "fire",
    level: 0
});

class Damage {
    constructor({ amount, type }) {
        this.amount = amount;
        this.type = type;
    }
}

// Weapons
const pactWeapon = new Weapon({
    name: "Pact Weapon",
    attackBonus: 0,
    damageBonus: 0,
    damageType: "radiant",
    dice: "1d8",
    modifierAttr: "charisma"
});

const flameTongue = new Weapon({
    name: "Flame Tongue",
    attackBonus: 0,
    damageBonus: 0,
    damageType: "fire",
    dice: "4d6"
});

const sunBlade = new Weapon({
    name: "Sun Blade",
    attackBonus: 2,
    damageBonus: 2,
    damageType: "radiant",
    dice: "1d8"
});

const fireBoltTierOne = new Weapon({
    name: "Fire Bolt",
    attackBonus: 0,
    damageBonus: 0,
    damageType: "fire",
    dice: "1d10"
});

// Characters

// Whis: Warlock with Pact Weapon and Thunder damage modifier (Booming Blade)
const whis = new Character({
    name: "Whis",
    hp: 36,
    ac: 18,
    weapon: pactWeapon,
    spellCastingMod: 'charisma',
    strength: 8,
    dexterity: 12,
    constitution: 14,
    intelligence: 10,
    wisdom: 13,
    charisma: 19,
    resistances: [],
    immunities: [],
    hasAdvantage: false,
    damageMod: new DamageType("thunder", 4),
    level: 3,
    speed: 35
});

// Whis with Advantage using imp to grant advantage with help action each round
const whisWithImp = new Character({
    name: "Whis",
    hp: 36,
    ac: 18,
    weapon: pactWeapon,
    spellCastingMod: 'charisma',
    strength: 8,
    dexterity: 12,
    constitution: 14,
    intelligence: 10,
    wisdom: 13,
    charisma: 19,
    resistances: [],
    immunities: [],
    hasAdvantage: true,
    damageMod: new DamageType("thunder", 4),
    level: 3,
    speed: 35
});

// Bharok: Barbarian with Flame Tongue and Slashing damage modifier (Rage)
const bharok = new Character({
    name: "Bharok",
    hp: 43,
    ac: 14,
    weapon: flameTongue,
    strength: 17,
    dexterity: 13,
    constitution: 16,
    intelligence: 8,
    wisdom: 12,
    charisma: 10,
    resistances: ["bludgeoning", "piercing", "slashing"],
    immunities: [],
    hasAdvantage: false,
    damageMod: new DamageType("slashing", 2),
    level: 3,
    speed: 35
});

// Bareth: Cleric with Sun Blade
const bareth = new Character({
    name: "Bareth",
    hp: 21,
    ac: 16,
    weapon: sunBlade,
    strength: 8,
    dexterity: 10,
    constitution: 12,
    intelligence: 14,
    wisdom: 19,
    charisma: 15,
    resistances: [],
    immunities: [],
    hasAdvantage: false,
    level: 3,
    speed: 30
});

// Bareth with Firebolt for spell example
const barethWithFirebolt = new Character({
    name: "Bareth Firebolt",
    hp: 21,
    ac: 16,
    spells: [fireBolt],
    spellCastingMod: 'wisdom',
    strength: 8,
    dexterity: 10,
    constitution: 12,
    intelligence: 14,
    wisdom: 19,
    charisma: 15,
    resistances: [],
    immunities: [],
    hasAdvantage: false,
    level: 3,
    speed: 30,
    spells: [fireBolt]
});

// Rowan: Wizard with Fire Bolt
const rowan = new Character({
    name: "Rowan",
    hp: 20, 
    ac: 12,
    spells: [fireBolt],
    spellCastingMod: 'intelligence',
    strength: 8,
    dexterity: 14,
    constitution: 14,
    intelligence: 18,
    wisdom: 12,
    charisma: 10,
    resistances: [],
    immunities: [],
    hasAdvantage: false,
    level: 3,
    speed: 25
});

const simulateFight = (charA, charB) => {
    let round = 1;
    let attacker = charA;
    let defender = charB;

    while (attacker.isAlive() && defender.isAlive() && round <= 50) {
        console.log(`--- Round ${round} ---`);
        let result;
        let maxRounds = 50;

        if (attacker.spells && attacker.spells.length > maxRounds) {
            result = attacker.spellAttack(defender, attacker.spells[0]);
            if (result.hit) {
                console.log(`${result.caster} hits ${result.target} with ${attacker.spells[0].name} for ${result.finalDamage} damage! (${result.target} HP: ${result.hp})`);
            } else {
                console.log(`${result.caster} misses ${result.target} with a ${result.totalAttack} against ${defender.ac}!`);
            }
        } else {
            result = attacker.attack(defender);
            if (result.hit) {
                console.log(`${result.attacker} hits ${result.target} for ${result.finalDamage} damage! (${result.target} HP: ${result.hp})`);
            } else {
                console.log(`${result.attacker} misses ${result.target} with a ${result.totalAttack} against ${defender.ac}!`);
            }
        }

        if (!defender.isAlive()) {
            console.log(`\n${attacker.name} wins!`);
            return attacker.name;
        }
        [attacker, defender] = [defender, attacker];
        round++;
    }

    if (attacker.isAlive() && !defender.isAlive()) {
        console.log(`\n${attacker.name} wins!`);
        return attacker.name;
    } else if (!attacker.isAlive() && defender.isAlive()) {
        console.log(`\n${defender.name} wins!`);
        return defender.name;
    } else {
        console.log(`\nNo winner after ${maxRounds} rounds.`);
        return null;
    }
}

function simulateFightNTimes(charA, charB, n) {
    const results = { [charA.name]: 0, [charB.name]: 0, draws: 0 };
    for (let i = 0; i < n; i++) {
        const a = Object.assign(
            Object.create(Object.getPrototypeOf(charA)),
            JSON.parse(JSON.stringify(charA))
        );
        if (charA.weapon) {
            a.weapon = Object.assign(
                Object.create(Object.getPrototypeOf(charA.weapon)),
                JSON.parse(JSON.stringify(charA.weapon))
            );
        }
        if (charA.spells) {
            a.spells = charA.spells.map(spell =>
                Object.assign(
                    Object.create(Object.getPrototypeOf(spell)),
                    JSON.parse(JSON.stringify(spell))
                )
            );
        }

        const b = Object.assign(
            Object.create(Object.getPrototypeOf(charB)),
            JSON.parse(JSON.stringify(charB))
        );
        if (charB.weapon) {
            b.weapon = Object.assign(
                Object.create(Object.getPrototypeOf(charB.weapon)),
                JSON.parse(JSON.stringify(charB.weapon))
            );
        }
        if (charB.spells) {
            b.spells = charB.spells.map(spell =>
                Object.assign(
                    Object.create(Object.getPrototypeOf(spell)),
                    JSON.parse(JSON.stringify(spell))
                )
            );
        }

        const winner = simulateFight(a, b);
        if (winner === charA.name) results[charA.name]++;
        else if (winner === charB.name) results[charB.name]++;
        else results.draws++;
    }
    return results;
}