package be.vdab.TerrariumWeb.forms;

import org.hibernate.validator.constraints.Range;

import java.lang.reflect.Field;

public class UserVariablesForm {
    @Range(min = 6, max = 20) private final Integer sizeTerrarium;
    @Range(min = 1, max = 5) private final Integer numCarnivores;
    @Range(min = 1, max = 5) private final Integer numHerbivores;
    @Range(min = 1, max = 5) private final Integer numOmnivores;
    @Range(min = 1, max = 5) private final Integer numPlants;

    public UserVariablesForm(Integer sizeTerrarium, Integer numCarnivores, Integer numHerbivores, Integer numOmnivores, Integer numPlants) {
        this.sizeTerrarium = sizeTerrarium;
        this.numCarnivores = numCarnivores;
        this.numHerbivores = numHerbivores;
        this.numOmnivores = numOmnivores;
        this.numPlants = numPlants;
    }

    public Integer getSizeTerrarium() {
        return sizeTerrarium;
    }

    public Integer getNumCarnivores() {
        return numCarnivores;
    }

    public Integer getNumHerbivores() {
        return numHerbivores;
    }

    public Integer getNumOmnivores() {
        return numOmnivores;
    }

    public Integer getNumPlants() {
        return numPlants;
    }

    public boolean containsNoNullVariables() throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.get(this) == null) {
                return false;
            }
        }
        return true;
    }
}