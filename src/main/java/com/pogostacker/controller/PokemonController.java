package com.pogostacker.controller;

import com.pogostacker.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class PokemonController {
    PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("result")
    public String getIVs(@RequestParam int CP, @RequestParam String name, Model model) {
        pokemonService.calculateIv(CP, name);
        if(pokemonService.getIVValues() == null) {
            model.addAttribute("IVs", "Could not find IVs for that Pokemon name and/or CP value");
        } else {
            model.addAttribute("IVs", pokemonService.getIVValues());
        }
        return "/result";
    }

    @GetMapping("home")
    public String goHome() {
        return "/home";
    }

}
