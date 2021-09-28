package com.pogostacker.service;

import com.pogostacker.model.Pokemon;

import java.util.List;

public interface PokemonService {
    // What do we need...Calculae IV percentages based on CP and name,
    //
    List<Integer> getCPValues(String pokemonToCheck);
    void calculateIv(int CP, String pokemonToCheck);
    String getIVValues();
    void loadPokemon();
}
