package com.pogostacker.dao;

import com.pogostacker.model.Pokemon;

import java.sql.Connection;
import java.util.Map;

public interface PokemonDao {
    Pokemon getPokemon(String pokemonName);

}
