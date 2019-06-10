package be.vdab.TerrariumWeb.domain;

import java.util.List;

public class Carnivore extends Animal{


    public Carnivore(Location location, int lifeForce) {
        super(location, lifeForce);
    }

    @Override
    public boolean interactWithEnvironment() {
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location toTheRight = new Location(getLocation().getX() +1, getLocation().getY());
        boolean hasInteracted = false;
        for(Organism organism : organisms){
            //check if there's an organism to the right
            if(organism.getLocation().equals(toTheRight)){
                //eat it if Herbivore
                if(organism.getClass().equals(Herbivore.class)){
                    eat((Herbivore) organism);
                    hasInteracted = true;
                }
                //fight it if Carnivore
                else if(organism.getClass().equals(Carnivore.class)){
                    if(((Animal) organism).getSex() != this.getSex()){
                        procreate();
                        System.out.println("Carnivores have procreated");
                    }
                    else{
                        fight(organism);
                        System.out.println("Carnivores fighting");
                    }
                    hasInteracted = true;
                }
                //fight it if omnivore
                else if(organism.getClass().equals(Omnivore.class)){
                        fight(organism);
                        hasInteracted = true;
                    }

                }
            }

        if(!hasInteracted){
            this.move();
            this.hasNotEaten();
            System.out.println("Carnivore has not eaten, it now has " + this.getLifeForce() + " HP");
            return false;
        }
        return true;
    }


    private void eat(Herbivore herbivore){
        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        this.getTerrarium().remove(herbivore);
    }

    private void fight(Organism enemy){
        if(enemy.getLifeForce() == this.getLifeForce()){
            //do nothing
        }
        else if(enemy.getLifeForce() > this.getLifeForce()){
            enemy.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(this);
        }
        else if(enemy.getLifeForce() < this.getLifeForce()){
            this.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(enemy);
        }
    }
    private void procreate(){
        Terrarium.INSTANCE.addNewCarnivore();
    }
}


