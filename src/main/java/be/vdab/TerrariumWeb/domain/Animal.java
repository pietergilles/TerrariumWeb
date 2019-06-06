package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends Organism {

    public Animal(Location location, int lifeForce) {
        super(location, lifeForce);
    }

    public boolean move() {

        int currentX = this.getLocation().getX();
        int currentY = this.getLocation().getY();

        List<Location> emptyLocations = Terrarium.INSTANCE.getEmptyLocations();
        List<Location> possibleLocation = new ArrayList<>();
        possibleLocation.add(new Location(currentX +1, currentY));
        possibleLocation.add(new Location(currentX -1, currentY));
        possibleLocation.add(new Location(currentX , currentY +1));
        possibleLocation.add(new Location(currentX , currentY -1));
        for (Location location : possibleLocation) {
            if(location.getX() > 5) location.setX(0);
            if(location.getX() < 0) location.setX(5);
            if(location.getY() > 5) location.setY(0);
            if(location.getY() < 0) location.setY(5);
        }
        List<Location> removeList = new ArrayList<>();
        for (Location location : possibleLocation) {
            if(!emptyLocations.contains(location)) {
                removeList.add(location);
            }
        }
        possibleLocation.removeAll(removeList);
        if (possibleLocation.isEmpty()) return false;
        int randomNumber = (int) Math.ceil(Math.random() * possibleLocation.size())-1;
        this.setLocation(possibleLocation.get(randomNumber));
        return true;
    }

    public abstract boolean interactWithEnvironment();

    public Terrarium getTerrarium() {
        return Terrarium.INSTANCE;
    }
}
