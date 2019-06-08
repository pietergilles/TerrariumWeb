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
    ArrayList<ArrayList<ArrayList<Organism>>> states;
    int stateCounter;

    public TerrariumController() {
        this.organismRows = GameController.INSTANCE.getState();
        this.stateCounter = 0;
    }

    @GetMapping("/reset")
    ModelAndView showReset()

    {
        Terrarium.INSTANCE.reset();
        Terrarium.INSTANCE.setDay(1);
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", GameController.INSTANCE.getState());
        modelAndView.addObject("day", Terrarium.INSTANCE.getDay());
        return modelAndView;
    }

    @GetMapping("/getTerrarium")
    ModelAndView getTerrarium(){
//        Terrarium.INSTANCE.setTerrarium(sizeTerrarium, numCarnivores,numHerbivores,numPlants,numOmnivores);
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", GameController.INSTANCE.getState());
        modelAndView.addObject("day", Terrarium.INSTANCE.getDay());
        return modelAndView;
    }

    @GetMapping("/nextDay")
    ModelAndView showNextDay(){
            GameController.INSTANCE.spawnPlants();
            GameController.INSTANCE.activateOrganisms();
            organismRows = GameController.INSTANCE.getState();
            ModelAndView modelAndView = new ModelAndView("terrarium");
            modelAndView.addObject("organismRows", organismRows);
            Terrarium.INSTANCE.setDay(Terrarium.INSTANCE.getDay() + 1);
            modelAndView.addObject("day", Terrarium.INSTANCE.getDay());

            //reset list of states
            stateCounter = 0;
            states = null;

            return modelAndView;
    }

    @GetMapping("/nextState")
    ModelAndView showNextState(){
        if(states == null){
            states = GameController.INSTANCE.getListOfStates();
        }
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", states.get(stateCounter));
        modelAndView.addObject("day", Terrarium.INSTANCE.getDay());
        if(stateCounter < (states.size()-1)){
            stateCounter += 1;
//            System.out.println("State " + stateCounter + " of " + states.size());
        }
        else{
            states = null;
            stateCounter = 0;
            Terrarium.INSTANCE.setDay(Terrarium.INSTANCE.getDay() + 1);

        }

        return modelAndView;
    }




}
