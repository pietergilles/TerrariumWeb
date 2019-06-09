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

    @GetMapping
    ModelAndView showIndexPage(TerrariumSizeForm terrariumSizeForm) {
        return new ModelAndView("index", "terrariumSizeForm", terrariumSizeForm);
    }

    @PostMapping("userVariablesForm")
    String submitTerrariumSizeForm(@Valid TerrariumSizeForm terrariumSizeForm) {
        Terrarium.INSTANCE.setSize(terrariumSizeForm.getSizeTerrarium());
        return "redirect:/userVariablesForm";
    }
}
