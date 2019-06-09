package be.vdab.TerrariumWeb.forms;

import org.hibernate.validator.constraints.Range;

public class TerrariumSizeForm {
    private final Integer sizeTerrarium;

    public TerrariumSizeForm(Integer sizeTerrarium) {
        this.sizeTerrarium = sizeTerrarium;
    }

    public Integer getSizeTerrarium() {
        return sizeTerrarium;
    }
}
