package be.vdab.TerrariumWeb.domain;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore(Location location, int lifeForce) {
        super(location, lifeForce);
    }

    @Override
    public boolean interactWithEnvironment() {
        Organism neighbourOrganism = null;
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location organismToMyRight = new Location(getLocation().getX() + 1, getLocation().getY());
        if(organismToMyRight.getX()>=Terrarium.INSTANCE.getSize()){
            organismToMyRight.setX(0);
        }
        for (Organism organism : organisms) {
            if (organism.getLocation().equals(organismToMyRight)) {
                neighbourOrganism = organism;
            }
        }
        if  (neighbourOrganism == null) {
            this.move();
            this.hasNotEaten();
            System.out.println("Herbivore has not eaten, it now has " + this.getLifeForce() + " HP");
            return false;

        } else if (neighbourOrganism.getClass().equals(Herbivore.class)) {
            if(((Animal) neighbourOrganism).getSex() != this.getSex()){
                procreate();
                System.out.println("Herbivores have procreated");
            }
            return true;
        } else if (neighbourOrganism.getClass().equals(Plant.class)) {
                this.setLifeForce(this.getLifeForce() + neighbourOrganism.getLifeForce());
                this.getTerrarium().remove(neighbourOrganism);
                return true;

        } else {
//            System.out.println("Something wrong.");
            return false;
        }
    }

    private void procreate(){
        Terrarium.INSTANCE.addNewHerbivore();
    }
}
