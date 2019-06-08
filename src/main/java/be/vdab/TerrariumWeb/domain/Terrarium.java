package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public enum Terrarium {

	INSTANCE;
	List<Organism> grid;
	long day;
	long state;
	long size;
	long numCarnivores;
	long numHerbivores;
	long numPlants;
	long numOmnivores;


	public void addOrganism(Organism organism) {
		//Location occupiedLocation = new Location(organism.getLocation().getX(), organism.getLocation().getY());
		if (grid.size() <= size*size) {
			if (!grid.contains(organism)) {
				grid.add(organism);

			} else if (!this.getEmptyLocations().contains(organism.getLocation())) {
				throw new IllegalArgumentException(" er is al een organisme op die plek aanwezig");
			}


		} else if (grid.contains(organism)) {

			throw new IllegalArgumentException(" de index is meer dan 36 ");
		}
	}

	public void addNewHerbivore() {
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Herbivore(getEmptyLocations().get(randomNumber), 10));
		}
	}
	public void addNewCarnivore() {//sex of the carnivore is initialised in constructor
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Carnivore(getEmptyLocations().get(randomNumber), 10));
		}
	}

	Terrarium() {
		this.day = 1;
		this.state = 1;
		this.size = 6;
		this.numCarnivores = 2;
		this.numPlants = 1;
		this.numHerbivores = 3;
		grid = new ArrayList<>(36);
		//minimum capcity max capacity kan je niet instellen


		//in het geval dat het randomnummer hetzelfde is, zal er een fout zijn
		int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), 10)); //one lifeforce

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 10));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 10));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 10));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 10));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 10));
	}

	//grid heeft een fixed grote nodig nu

	public List<Location> getEmptyLocations() {


		List<Location> occupiedPlaces = new ArrayList<>();
		for(Organism organism : grid){
			occupiedPlaces.add(organism.getLocation());
		}
		List<Location> emptyPlaces = new ArrayList<>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Location location = new Location(i, j);
//				System.out.println("Location: " + i + " " + j);
				if(!occupiedPlaces.contains(location)){
					emptyPlaces.add(location);

				}
			}
		}
		return emptyPlaces;

	}

	public List<Organism> getAllOrganisms() {

		List<Organism> allOrganisms = new ArrayList<>();

		for(Organism organism : grid) {
			if (organism instanceof Organism) {
				allOrganisms.add(organism);
			}
		}
		return allOrganisms;
	}

	public void remove(Organism organism){

		grid.remove(organism);
	}

	public void reset(){
		this.day = 1;
		this.state = 1;
		grid = new ArrayList<>();

		for(int i = 1;i<=numCarnivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 10));
		}
		for(int i = 1;i<=numHerbivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 10));
		}
		for(int i = 1;i<=numPlants;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), 1));
		}

	}

	public void setTerrarium(long sizeTerrarium, long numCarnivores, long numHerbivores,
							 long numPlants, long numOmnivores){

		this.day = 1;
		this.state = 1;
		this.size = sizeTerrarium;
		this.numCarnivores = numCarnivores;
		this.numHerbivores = numHerbivores;
		this.numOmnivores = numOmnivores;
		this.numPlants = numPlants;
		reset();
	}

	public long getSize() {
		return size;
	}

	public long getDay()
	{
		return day;
	}

	public void setDay(long day)
	{
		this.day = day;
	}

	public long getState()
	{
		return state;
	}

	public void setState(long state)
	{
		this.state = state;
	}
}
