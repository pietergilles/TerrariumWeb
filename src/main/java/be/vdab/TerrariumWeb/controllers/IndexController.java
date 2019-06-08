package be.vdab.TerrariumWeb.controllers;

import be.vdab.TerrariumWeb.domain.Terrarium;
import be.vdab.TerrariumWeb.forms.TerrariumSizeForm;
import be.vdab.TerrariumWeb.forms.UserVariablesForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {
    private Integer sizeTerrarium;

    @GetMapping("index")
    ModelAndView showIndexPage(TerrariumSizeForm terrariumSizeForm, UserVariablesForm userVariablesForm) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("terrariumSizeForm", terrariumSizeForm);
        modelAndView.addObject("userVariablesForm", userVariablesForm);
        return modelAndView;
    }

    @PostMapping("index")
    String submitTerrariumSizeForm(@Valid TerrariumSizeForm terrariumSizeForm, Errors errors) {
        if (errors.hasErrors()) {
            return "/index";
        }
        sizeTerrarium = terrariumSizeForm.getSizeTerrarium();
        System.out.println(sizeTerrarium);
        return ("redirect:/index");
    }

    @PostMapping("terrarium")
    ModelAndView submitUserVariablesForm(@Valid UserVariablesForm userVariablesForm,
                                         Errors errors) throws IllegalAccessException {
        if (errors.hasErrors()) {
            return new ModelAndView("index");
        }
        if (userVariablesForm.containsNoNullVariables()) {
            Terrarium.INSTANCE.setTerrarium(
                    sizeTerrarium,
                    userVariablesForm.getNumCarnivores(),
                    userVariablesForm.getNumHerbivores(),
                    userVariablesForm.getNumPlants(),
                    userVariablesForm.getNumOmnivores());
            return new ModelAndView("redirect:/terrarium/getTerrarium");
        }
        return new ModelAndView("redirect:/terrarium");
    }
}
