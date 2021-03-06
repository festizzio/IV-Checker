package com.pogostacker.model;

import java.text.DecimalFormat;

public class IVValues {
    private final int attackIV;
    private final int defenseIV;
    private final int staminaIV;
    private static final DecimalFormat df2 = new DecimalFormat("##.##");
    private final double IvPercentage;

    public IVValues(int attackIV, int defenseIV, int staminaIV) {
        this.attackIV = attackIV;
        this.defenseIV = defenseIV;
        this.staminaIV = staminaIV;
        IvPercentage = Double.parseDouble(df2.format(((((double) attackIV + (double) defenseIV +
                (double) staminaIV) / 45) * 100)));
    }

    public double getIvPercentage() {
        return IvPercentage;
    }

    public int getAttackIV() {
        return attackIV;
    }

    public int getDefenseIV() {
        return defenseIV;
    }

    public int getStaminaIV() {
        return staminaIV;
    }
}
