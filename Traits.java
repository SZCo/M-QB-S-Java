package com.quarterback.career;

import java.util.HashMap;
import java.util.Map;

public class Traits {
    public enum TraitType {
        PHYSICAL, TALENT, GOOD_MENTAL, BAD_MENTAL
    }

    public enum TraitMultiplier {
        GODLY, GREAT, GOOD, BASIC
    }

    private Map<TraitType, String> traits;
    private Map<TraitType, TraitMultiplier> multipliers;

    public Traits() {
        traits = new HashMap<>();
        multipliers = new HashMap<>();
    }

    public void addTrait(TraitType type, String trait, TraitMultiplier multiplier) {
        traits.put(type, trait);
        multipliers.put(type, multiplier);
    }

    public String getTrait(TraitType type) {
        return traits.get(type);
    }

    public TraitMultiplier getMultiplier(TraitType type) {
        return multipliers.get(type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TraitType type : TraitType.values()) {
            if (traits.containsKey(type)) {
                sb.append(type).append(": ").append(traits.get(type))
                        .append(" (").append(multipliers.get(type)).append(")\n");
            }
        }
        return sb.toString();
    }
}
