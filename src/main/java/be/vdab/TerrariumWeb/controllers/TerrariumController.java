package be.vdab.TerrariumWeb.controllers;

import be.vdab.TerrariumWeb.domain.GameController;
import be.vdab.TerrariumWeb.domain.Terrarium;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/terrarium")
public class TerrariumController {

//    ArrayList<ArrayList<Organism>> organismRows;
//    ArrayList<ArrayList<ArrayList<Organism>>> states;
    int dayCounter;
    int stateCounter;
    ArrayList<String> dayLog = new ArrayList<>();

    public TerrariumController() {

    }
    public void clearDayLog() {
        dayLog.clear();
    }



    @GetMapping("/getTerrarium")
    ModelAndView getTerrarium(){
        GameController.INSTANCE.generateAllDays();
        this.dayCounter = 1;
        this.stateCounter = 1;
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
        modelAndView.addObject("day", dayCounter);
        return modelAndView;
    }

    @GetMapping("/nextDay")
    ModelAndView showNextDay()
    {
        ModelAndView modelAndView = new ModelAndView("terrarium");
        stateCounter = 1;
        if (GameController.INSTANCE.getNumberOfDays() > dayCounter)
        {
            dayCounter += 1;
            modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
            modelAndView.addObject("day", dayCounter);
            return modelAndView;
        }
        else{
            modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
            modelAndView.addObject("day", dayCounter);
        }
        return modelAndView;
    }

    @GetMapping("/nextState")
    ModelAndView showNextState(){
        stateCounter +=1;
        if(GameController.INSTANCE.getNumberOfStatesInDay(dayCounter) < stateCounter){
            System.out.println("No next state, showing next day");
            if(dayCounter<GameController.INSTANCE.getNumberOfDays()){
                dayCounter+=1;
                stateCounter = 1;
            }
            else{
                System.out.println("There is no next day, showing last state of last day");
                stateCounter -=1;
            }
        }
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
        modelAndView.addObject("day", dayCounter);
        return modelAndView;
    }

    @GetMapping("/previousDay")
    ModelAndView showPreviousDay()
    {
        ModelAndView modelAndView = new ModelAndView("terrarium");
        stateCounter = 1;
        if (dayCounter>1)
        {
            dayCounter -= 1;

            modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
            modelAndView.addObject("day", dayCounter);
            return modelAndView;
        }
        else{
            modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
            modelAndView.addObject("day", dayCounter);
        }
        return modelAndView;
    }

    @GetMapping("/previousState")
    ModelAndView showPreviousState(){
        stateCounter -=1;
        if(stateCounter <1){
            System.out.println("No previous state, showing last state of previous day");
            if(dayCounter>1){
                dayCounter -=1;
                stateCounter=(int)GameController.INSTANCE.getNumberOfStatesInDay(dayCounter);
            }
            else{
                System.out.println("There is no previous day, showing first state of first day");
                dayCounter = 1;
                stateCounter = 1;
            }
        }
        ModelAndView modelAndView = new ModelAndView("terrarium");
        modelAndView.addObject("organismRows", GameController.INSTANCE.getStateFromDay(dayCounter, stateCounter));
        modelAndView.addObject("day", dayCounter);
        return modelAndView;
    }


    @GetMapping("/reset")
    ModelAndView showReset()

    {
        Terrarium.INSTANCE.reset();
        return getTerrarium();
    }




}
