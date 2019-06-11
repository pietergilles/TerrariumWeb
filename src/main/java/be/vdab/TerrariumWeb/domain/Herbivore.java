package be.vdab.TerrariumWeb.domain;

import java.util.List;
import java.util.Optional;

public class Herbivore extends Animal {

    public Herbivore(Location location, int lifeForce) {
        super(location, lifeForce);
    }

    @Override
    public boolean interactWithEnvironment() {
        Organism foundOrganism;
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location organismToMyRight = new Location(getLocation().getX() + 1, getLocation().getY());
        Location organismToMyLeft = new Location(getLocation().getX() - 1, getLocation().getY());
        Location organismToMyNorth = new Location(getLocation().getX(), getLocation().getY() - 1);
        Location organismToMySouth = new Location(getLocation().getX() + 1, getLocation().getY() + 1);
//        for (Organism organism : organisms) {
//            if (organism.getLocation().equals(organismToMyRight) || organism.getLocation().equals(organismToMyLeft) ||organism.getLocation().equals(organismToMySouth) ||organism.getLocation().equals(organismToMyNorth)) {
//                neighbourOrganism = organism;
//            }
//        }
        if (this.getLifeForce() > this.getNextHpLevelForPowerup()) {
            Optional<Organism> neighbourOrganism = organisms.stream().filter(organism ->
                    (organism.getLocation().equals(organismToMyRight)
                            || organism.getLocation().equals(organismToMyLeft)
                            || organism.getLocation().equals(organismToMySouth)
                            || organism.getLocation().equals(organismToMyNorth))).findFirst();
           foundOrganism = neighbourOrganism.orElse(null);
//            System.out.println("if   neighbourOrganism.get()"+neighbourOrganism.get());
        } else {
            Optional<Organism> neighbourOrganism = organisms.stream().filter(organism ->
                    (organism.getLocation().equals(organismToMyRight))).findFirst();
//            System.out.println("else neighbourOrganism.get()"+neighbourOrganism.get());
            foundOrganism = neighbourOrganism.orElse(null);
        }

        if (foundOrganism == null) {
            this.move();
            this.hasNotEaten();
            System.out.println("Herbivore has not eaten, it now has " + this.getLifeForce() + " HP");
            return false;

        } else if (foundOrganism.getClass().equals(Herbivore.class)) {
            if (((Animal) foundOrganism).getSex() != this.getSex()) {
                procreate();
                if (this.getLifeForce() > (this.getNextHpLevelForPowerup() + 10)) {
                    procreate();
                }
                System.out.println("Herbivores have procreated");
            }
            return true;
        } else if (foundOrganism.getClass().equals(Plant.class)) {
            this.setLifeForce(this.getLifeForce() + foundOrganism.getLifeForce());
            this.getTerrarium().remove(foundOrganism);
            return true;

        } else {
//            System.out.println("Something wrong.");
            return false;
        }
    }

    private void procreate() {
        Terrarium.INSTANCE.addNewHerbivore();
    }
}
