public class RobinRex extends Animal {
// deze class is nog nergens anders geintegreerd
private char sex;

    public RobinRex(Location location, int lifeForce) {
        super(location, lifeForce);
        this.sex = 'M';
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

                    hasInteracted = false;
                }
                //fight it if Carnivore
                else if(organism.getClass() = Carnivore.class){
                    if (!(procreate((Carnivore) organism)){// dit klopt toch he ?

                    }
                    hasInteracted = true;
                }
            }
        }
        if(!hasInteracted){
            this.move();
            return false;
        }
        return true;
    }




    private boolean procreate(Carnivore mate){
        boolean mated = false;
        if (mate.getSex() == this.getSex()){
            //do nothing
            mated = false;
        } else {
            Terrarium.INSTANCE.addNewTrump();
            mated = true;
        }
        return mated;



    }