package be.vdab.TerrariumWeb.forms;

import org.hibernate.validator.constraints.Range;

public class TerrariumSizeForm {
    @Range(min = 6, max = 20) private final Integer sizeTerrarium;

    public TerrariumSizeForm(Integer sizeTerrarium) {
        this.sizeTerrarium = sizeTerrarium;
    }

    public Integer getSizeTerrarium() {
        return sizeTerrarium;
    }
}
