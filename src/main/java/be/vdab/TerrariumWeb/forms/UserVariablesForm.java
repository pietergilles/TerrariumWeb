package be.vdab.TerrariumWeb.forms;

import java.lang.reflect.Field;

public class UserVariablesForm {
    private final Integer numCarnivores;
    private final Integer numHerbivores;
    private final Integer numOmnivores;
    private final Integer numPlants;
    private final Integer numPlantsPerDay;

    public UserVariablesForm(Integer numCarnivores, Integer numHerbivores, Integer numOmnivores, Integer numPlants, Integer numPlantsPerDay) {
        this.numCarnivores = numCarnivores;
        this.numHerbivores = numHerbivores;
        this.numOmnivores = numOmnivores;
        this.numPlants = numPlants;
        this.numPlantsPerDay = numPlantsPerDay;
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

    public Integer getNumPlantsPerDay() {
        return numPlantsPerDay;
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