package be.vdab.TerrariumWeb.controllers;

import be.vdab.TerrariumWeb.domain.Terrarium;
import be.vdab.TerrariumWeb.forms.UserVariablesForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class OrganismsController {
    @GetMapping("/userVariablesForm")
    ModelAndView showUserVariablesForm(UserVariablesForm userVariablesForm) {
        return new ModelAndView("userVariablesForm", "userVariablesForm", userVariablesForm);
    }

    @PostMapping("/terrarium")
    ModelAndView submitUserVariablesForm(@Valid UserVariablesForm userVariablesForm, Errors errors) throws IllegalAccessException {
        if (userVariablesForm.containsNoNullVariables()) {
            Terrarium.INSTANCE.setNumbersOfOrganisms(
                    userVariablesForm.getNumCarnivores(),
                    userVariablesForm.getNumHerbivores(),
                    userVariablesForm.getNumPlants(),
                    userVariablesForm.getNumOmnivores());
            return new ModelAndView("redirect:/terrarium/getTerrarium");
        }
        return new ModelAndView("redirect:/terrarium");
    }
}