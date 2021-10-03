package com.pogostacker.service;

import com.pogostacker.dao.PokemonDao;
import com.pogostacker.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {
    Pokemon newPokemon;
    PokemonDao pokemonDao;

    @Autowired
    public PokemonServiceImpl(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    @Override
    public void loadPokemon() {

    }

    @Override
    public List<Integer> getCPValues(String pokemonToCheck) {
        return pokemonDao.getPokemon(pokemonToCheck).getPossibleCPValues();
    }

    @Override
    public void calculateIv(int CP, String pokemonToCheck) {
        newPokemon = pokemonDao.getPokemon(pokemonToCheck);
        if(newPokemon != null) {
            newPokemon.calculateIvPercentagePerCP(CP);
        }
    }

    public String getIVValues() {
        return newPokemon.getIVValues();
    }
}
