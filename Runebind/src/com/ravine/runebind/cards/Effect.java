package com.ravine.runebind.cards;

/*
 * Activation Types:
 * "activate" - once per turn
 * discard
 * weapon - only during combat
 *
 * Types of effects:
 * cancel incoming dmg by #   -------------------------------
 * inflict # dmg    -------------------------------
 * +/-# skill  -----------------------------------
 * increase  outgoing dmg by #  --------------------------
 * +/-# attribute        ----------------------------
 * make ranged/melee/magic attack  -----------------------
 * +/-# to roll     ----------------------------------------
 * take # fatigue, deal # dmg ---------------------------------
 * move up to # spaces   --------------------------------------
 * reroll # movement/combat/skill dice    -------------------------
 * discard health/fatigue from player or ally   ----------------------
 * + # phase you can attack     ------------------------------------
 * reduce market cost by #      ----------------------------------
 * take # fatigue/dmg for +/- bonus   --------------------------
 * take # fatigue instead of # dmg    --------------------------
 * take # dmg/fatigue to test skill/attribute X    ------------------------
 * move to any space on board
 * +/- movement dice   --------------------------------------
 * if hero takes # dmg, enemy takes # dmg  ------------------------------
 * +/- dmg for # rounds      ------------------------------------------
 *
 * Conditions:
 * before:
 * -combat
 * -rolling dice
 * -moving
 * after:
 * -movement roll
 * -combat roll
 * -successful ranged/melee/magic attack
 * during:
 * -ranged/melee/magic phase
 */

public class Effect {
    public static enum Activation {
        combat,
        roll,
        move,
        range,
        melee,
        magic,
        market
    }
    public static enum Timing {
        before,
        after,
        during
    }

    private Timing time;
    private Activation activation;

    private int costMod = 0, mindMod = 0, bodyMod = 0, spiritMod = 0, numDice = 0, movementMod = 0, dmgOutMod = 0,
            dmgInMod = 0, takeFatigue = 0, rollMod = 0, discardDmg = 0, discardFatigue = 0, phaseMod = 0, dmgIn = 0,
            numRounds = 0;
    private boolean allowRanged = false, allowMelee = false, allowMagic = false, reRollCombat = false,
            reRollMovement = false, reRollSkill = false;

    public Effect(Timing time, Activation activation) {
        this.time = time;
        this.activation = activation;
    }

    public int getCostMod() {
        return costMod;
    }

    public void setCostMod(int costMod) {
        this.costMod = costMod;
    }

    public int getMindMod() {
        return mindMod;
    }

    public void setMindMod(int mindMod) {
        this.mindMod = mindMod;
    }

    public int getBodyMod() {
        return bodyMod;
    }

    public void setBodyMod(int bodyMod) {
        this.bodyMod = bodyMod;
    }

    public int getSpiritMod() {
        return spiritMod;
    }

    public void setSpiritMod(int spiritMod) {
        this.spiritMod = spiritMod;
    }

    public int getNumDice() {
        return numDice;
    }

    public void setNumDice(int numDice) {
        this.numDice = numDice;
    }

    public int getMovementMod() {
        return movementMod;
    }

    public void setMovementMod(int movementMod) {
        this.movementMod = movementMod;
    }

    public int getDmgOutMod() {
        return dmgOutMod;
    }

    public void setDmgOutMod(int dmgOutMod) {
        this.dmgOutMod = dmgOutMod;
    }

    public int getDmgInMod() {
        return dmgInMod;
    }

    public void setDmgInMod(int dmgInMod) {
        this.dmgInMod = dmgInMod;
    }

    public int getTakeFatigue() {
        return takeFatigue;
    }

    public void setTakeFatigue(int takeFatigue) {
        this.takeFatigue = takeFatigue;
    }

    public int getRollMod() {
        return rollMod;
    }

    public void setRollMod(int rollMod) {
        this.rollMod = rollMod;
    }

    public int getDiscardDmg() {
        return discardDmg;
    }

    public void setDiscardDmg(int discardDmg) {
        this.discardDmg = discardDmg;
    }

    public int getDiscardFatigue() {
        return discardFatigue;
    }

    public void setDiscardFatigue(int discardFatigue) {
        this.discardFatigue = discardFatigue;
    }

    public int getPhaseMod() {
        return phaseMod;
    }

    public void setPhaseMod(int phaseMod) {
        this.phaseMod = phaseMod;
    }

    public int getDmgIn() {
        return dmgIn;
    }

    public void setDmgIn(int dmgIn) {
        this.dmgIn = dmgIn;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    public boolean isAllowRanged() {
        return allowRanged;
    }

    public void setAllowRanged(boolean allowRanged) {
        this.allowRanged = allowRanged;
    }

    public boolean isAllowMelee() {
        return allowMelee;
    }

    public void setAllowMelee(boolean allowMelee) {
        this.allowMelee = allowMelee;
    }

    public boolean isAllowMagic() {
        return allowMagic;
    }

    public void setAllowMagic(boolean allowMagic) {
        this.allowMagic = allowMagic;
    }

    public boolean isReRollCombat() {
        return reRollCombat;
    }

    public void setReRollCombat(boolean reRollCombat) {
        this.reRollCombat = reRollCombat;
    }

    public boolean isReRollMovement() {
        return reRollMovement;
    }

    public void setReRollMovement(boolean reRollMovement) {
        this.reRollMovement = reRollMovement;
    }

    public boolean isReRollSkill() {
        return reRollSkill;
    }

    public void setReRollSkill(boolean reRollSkill) {
        this.reRollSkill = reRollSkill;
    }



	
	
}
