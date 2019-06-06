package be.vdab.TerrariumWeb.controllers;

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

    @GetMapping("index")
    ModelAndView showHomePage(UserVariablesForm userVariablesForm) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userVariablesForm", userVariablesForm);
        return modelAndView;
    }

    @PostMapping("index")
    ModelAndView submitUserVariablesForm(@Valid UserVariablesForm userVariablesForm, Errors errors) throws IllegalAccessException {
        if (errors.hasErrors()) {
            return new ModelAndView("index");
        }
        if (userVariablesForm.containsNoNullVariables()) {
            return new ModelAndView("redirect:/terrarium/setTerrarium");
        }
        return new ModelAndView("redirect:/terrarium");
    }
}
