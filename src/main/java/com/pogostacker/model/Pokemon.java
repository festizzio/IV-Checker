package com.pogostacker.model;

import java.util.*;

public class Pokemon {

    private final int baseAttack;
    private final int baseDefense;
    private final int baseStamina;

    private String name;

    // CP stands for Combat Power, an arbitrary calculation by Niantic to gauge the
    // overall ability of the Pokemon in battle.
    private int CP;
    private String ivValuesPerCp;
    private final List<Integer> possibleCPValues;
    private final Map<Integer, List<IVValues>> mapOfIvValues;
    private final List<IVValues> ivList = calculateListOfIVs();

    public Pokemon(String name, int baseAttack, int baseDefense, int baseStamina) {

        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseStamina = baseStamina;
        possibleCPValues = new ArrayList<>();
        mapOfIvValues = new HashMap<>();
        calculatePossibleCPValues();
        possibleCPValues.sort(Comparator.naturalOrder());
    }

    // Does this make sense here? Feel like it would fit better in DataSource.
    // IV floor for research tasks is 10/10/10, and these values don't change between Pokemon.
    // No reason to call it every time you instantiate a new Pokemon object.
    private List<IVValues> calculateListOfIVs() {
        List<IVValues> ivValues = new ArrayList<>();
        for(int attackIV = 10; attackIV <= 15; attackIV++) {
            for (int defenseIV = 10; defenseIV <= 15; defenseIV++) {
                for (int staminaIV = 10; staminaIV <= 15; staminaIV++) {
                    ivValues.add(new IVValues(attackIV, defenseIV, staminaIV));
                }
            }
        }
        return ivValues;
    }

    private void calculatePossibleCPValues() {
        int CP;
        List<IVValues> listOfIvValues;
        for(IVValues currentIVs : ivList) {

            CP = calculateCP(currentIVs.getAttackIV(), currentIVs.getDefenseIV(), currentIVs.getStaminaIV());
            listOfIvValues = new ArrayList<>();

            if(!mapOfIvValues.isEmpty()) {
                if(mapOfIvValues.containsKey(CP)) {
                    listOfIvValues = mapOfIvValues.get(CP);
                }
            }

            listOfIvValues.add(currentIVs);

            listOfIvValues.sort(Comparator.comparingDouble(IVValues::getIvPercentage));
            mapOfIvValues.put(CP, listOfIvValues);
        }
        fillPossibleCPValueList();
    }

    private int calculateCP(int attackIV, int defenseIV, int staminaIV) {
        // Based on information found on gamepress.gg
        return (int) ((baseAttack + attackIV) * Math.pow((baseDefense + defenseIV), 0.5) *
                Math.pow((baseStamina + staminaIV), 0.5) * Math.pow(0.51739395, 2)) / 10;
    }

    private void fillPossibleCPValueList(){
        possibleCPValues.addAll(mapOfIvValues.keySet());
        possibleCPValues.sort(Comparator.naturalOrder());
    }

    public void calculateIvPercentagePerCP(int CP) {
        StringBuilder sb = new StringBuilder();
        List<IVValues> valuesPerCp;

        if(!mapOfIvValues.containsKey(CP)){
            this.ivValuesPerCp = "Invalid CP value for this Pokemon";
        } else {
            valuesPerCp = mapOfIvValues.get(CP);
            sb.append(name);
            sb.append("'s IV percentages for the CP ");
            sb.append(CP);
            sb.append(" are ");
            sb.append(valuesPerCp.get(0).getIvPercentage());
            sb.append("% - ");
            sb.append(valuesPerCp.get(valuesPerCp.size() - 1).getIvPercentage());
            sb.append("%");
            this.ivValuesPerCp = sb.toString();
        }
    }

    public int getCP() {
        return CP;
    }
    public void setCP(int CP) {
        this.CP = CP;
    }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPossibleCPValues() {
        return possibleCPValues;
    }

    public String getIVValues() {
        return this.ivValuesPerCp;
    }
}
