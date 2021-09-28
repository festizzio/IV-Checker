package com.pogostacker.service;

import com.pogostacker.dao.PokemonDao;
import com.pogostacker.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PokemonServiceImpl implements PokemonService {
    private Map<String, Pokemon> pokemon;
    Pokemon newPokemon;
    PokemonDao pokemonDao;

    @Autowired
    public PokemonServiceImpl(PokemonDao pokemonDao) {
//        this.pokemonDao = pokemonDao;
//        pokemon = pokemonDao.getPokemon();
        pokemon = new HashMap<>();
        pokemon.put("bulbasaur", new Pokemon("bulbasaur", 118, 111, 128));
        pokemon.put("ivysaur", new Pokemon("ivysaur", 151, 143, 155));
        pokemon.put("venusaur", new Pokemon("venusaur", 198,189,190));
    }

    @Override
    public void loadPokemon() {

    }

    @Override
    public List<Integer> getCPValues(String pokemonToCheck) {
        return pokemon.get(pokemonToCheck.toLowerCase()).getPossibleCPValues();
    }

    @Override
    public void calculateIv(int CP, String pokemonToCheck) {
        newPokemon = pokemon.get(pokemonToCheck.toLowerCase());
        newPokemon.calculateIvPercentagePerCP(CP);
    }

    public String getIVValues() {
        return newPokemon.getIVValues();
    }
}
