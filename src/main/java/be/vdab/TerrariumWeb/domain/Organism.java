package be.vdab.TerrariumWeb.domain;

public abstract class Organism {
    private Location location;
    private int lifeForce;

    public Organism(Location location, int lifeForce) {
        if(location == null || !(lifeForce>=0)){
            throw new IllegalArgumentException();
        }
        this.location = location;
        this.lifeForce = lifeForce;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLifeForce() {
        return lifeForce;
    }

    public void setLifeForce(int lifeForce) {
        this.lifeForce = lifeForce;
    }
}
