package be.vdab.TerrariumWeb.controllers;

import be.vdab.TerrariumWeb.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/terrarium")
public class TerrariumController {

    ArrayList<ArrayList<Organism>> organismRows;

    public TerrariumController() {
        this.organismRows = getOrganismRows();
    }

    @GetMapping("/reset")
    ModelAndView showReset()

    {
        Terrarium.INSTANCE.reset();
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", getOrganismRows());
        return modelAndView;
    }

    @GetMapping("/getTerrarium")
    ModelAndView getTerrarium(){
//        Terrarium.INSTANCE.setTerrarium(sizeTerrarium, numCarnivores,numHerbivores,numPlants,numOmnivores);
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", getOrganismRows());
        return modelAndView;
    }

    @GetMapping("/nextDay")
    ModelAndView showNext(){
            GameController.INSTANCE.spawnPlants();
            GameController.INSTANCE.activateOrganisms();
            organismRows = getOrganismRows();
            ModelAndView modelAndView = new ModelAndView("terrarium");
            modelAndView.addObject("organismRows", organismRows);
            return modelAndView;
    }

    private ArrayList<ArrayList<Organism>> getOrganismRows(){
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
                        organismRow.add(organism);
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
}
