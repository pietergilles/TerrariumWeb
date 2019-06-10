package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public enum GameController {

    INSTANCE;
    ArrayList< ArrayList<ArrayList<ArrayList<Organism>>>> days;
    long numberOfDays;

    public void spawnPlants(){
        List<Location> emptyLocations = Terrarium.INSTANCE.getEmptyLocations();
        if (emptyLocations.size() > 0) {
            int randomNumber = (int) Math.ceil(Math.random() * emptyLocations.size())-1;
            Plant plant = new Plant(emptyLocations.get(randomNumber), 1); //one lifeforce
            Terrarium.INSTANCE.addOrganism(plant);
        }
    }
    public void activateOrganisms(){
        //let Carnivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Carnivore){
                ((Carnivore) organism).interactWithEnvironment();
            }
        }
        //then let omnivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Omnivore){
                ((Omnivore) organism).interactWithEnvironment();
            }
        }
        //then let Herbivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Herbivore){
                ((Herbivore) organism).interactWithEnvironment();
            }
        }
    }
    public void printTerrarium(){
        //not very efficient but temporary, size of Terrarium should be variable
        System.out.println(" - - - - - - - - - - - - - - ");
        for(int y=0;y<=5;y++){
            System.out.print("|\t");
            for(int x=0;x<=5;x++){

                //create new Location for every point on grid
                Location location = new Location(x, y);
                //check if there's an organism on this location and what kind
                boolean organismFound = false;
                for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
                    if(organism.getLocation().equals(location)){
                        organismFound = true;
                        if(organism instanceof Plant){
                            System.out.print("P\t");
                        }
                        else if(organism instanceof Herbivore){
                            System.out.print("H\t");
                        }
                        else if(organism instanceof Carnivore){
                            System.out.print("C\t");
                        }
                    }
                }
                //if not print "."
                if(!organismFound){
                    System.out.print(".\t");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" - - - - - - - - - - - - - -");
    }

    public ArrayList<ArrayList<Organism>> getNextState(){
        ArrayList<Organism> organisms = (ArrayList) Terrarium.INSTANCE.getAllOrganisms();
        ArrayList<ArrayList<Organism>> organismRows = new ArrayList<ArrayList<Organism>>();
        long size = Terrarium.INSTANCE.getSize();
        for(int y=0; y<size; y++){
            ArrayList<Organism> organismRow = new ArrayList<>();
            for(int x=0; x<size; x++){
                boolean organismFound = false;
                for(Organism organism : organisms){
                    Location location = new Location(x, y);
                    if(organism.getLocation().equals(location)){
                        organismRow.add(clone(organism));
                        organismFound = true;
                    }

                }
                if(!organismFound){
                    organismRow.add(new Plant(new Location(x, y), 0));
                }
            }
            organismRows.add(organismRow);

        }
        return organismRows;
    }

    public ArrayList<ArrayList<ArrayList<Organism>>> getNextDay(){
        ArrayList<ArrayList<ArrayList<Organism>>> states = new ArrayList<>();
        //spawn shrub
        GameController.INSTANCE.spawnPlants();
        states.add(getNextState());
        //let Carnivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Carnivore){
                ((Carnivore) organism).interactWithEnvironment();
                states.add(getNextState());
            }
        }
        //then let omnivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Omnivore){
                ((Omnivore) organism).interactWithEnvironment();
                states.add(getNextState());
            }
        }
        //then let Herbivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Herbivore){
                ((Herbivore) organism).interactWithEnvironment();
                states.add(getNextState());
            }
        }
        return states;
    }

    public ArrayList< ArrayList<ArrayList<ArrayList<Organism>>>> generateAllDays(){
        days = new ArrayList<>();
        while(Terrarium.INSTANCE.getEmptyLocations().size() > 0 && days.size()<200){
            days.add(getNextDay());
        }
        System.out.println("Number of days: " + days.size());
        numberOfDays = days.size();
        return days;
    }

    public ArrayList<ArrayList<Organism>> getStateFromDay(int day, int state){
        if(day-1>=days.size() || state-1>= days.get(day-1).size() || day-1<0 || state -1 <0){
            return null;
        }
        return days.get(day-1).get(state-1);
    }

    public long getNumberOfDays()
    {
        return numberOfDays;
    }

    public long getNumberOfStatesInDay(int day){
        return days.get(day-1).size();
    }

    private Organism clone(Organism organism){
        if(organism instanceof Plant){
            return new Plant(organism.getLocation(), organism.getLifeForce());
        }
        else if(organism instanceof Carnivore){
            return new Carnivore(organism.getLocation(), organism.getLifeForce());
        }
        else if(organism instanceof Herbivore){
            return new Herbivore(organism.getLocation(), organism.getLifeForce());
        }
        else if(organism instanceof Omnivore){
            return new Omnivore(organism.getLocation(), organism.getLifeForce());
        }
        else{
            return new Plant(organism.getLocation(), 0);
        }

    }
}
