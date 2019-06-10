package be.vdab.TerrariumWeb.domain;

import java.util.List;
import be.vdab.TerrariumWeb.domain.Terrarium;

public class Carnivore extends Animal{

    private char sex;

    public Carnivore(Location location, int lifeForce) {
        super(location, lifeForce);
        int randomNumber = (int) (Math.ceil(Math.random() * 2);
        if (randomNumber <2){
            this.sex ='F';
        } else {
            this.sex ='M';
        }
    }



    @Override
    public boolean interactWithEnvironment() {
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location toTheRight = new Location(getLocation().getX() +1, getLocation().getY());
        if(toTheRight.getX() > 5){
            toTheRight.setX(0);
        }
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
                   if (!(procreate((Carnivore) organism)){// dit klopt toch he ?
                        fight((Carnivore) organism);
                    }
                    hasInteracted = true;
                }
            }
        }
        if(!hasInteracted){
            this.hasNotEaten();
            this.move();
            return false;
        }
        return true;
    }


    private void eat(Herbivore herbivore){
        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        this.getTerrarium().remove(herbivore);
    }

    private void fight(Carnivore enemy){
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

    public char getSex(){
        return this.sex;
    }




    private boolean procreate(Carnivore mate){
        boolean mated= false;
        if (mate.getSex() == this.getSex()){
            //do nothing
            mated = false;
        } else {
            Terrarium.INSTANCE.addNewCarnivore();
            mated = true;
        }
        return mated;

}


