package com.pogostacker.dao;

import com.pogostacker.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PokemonDaoImpl implements PokemonDao {

    @Override
    public Map<String, Pokemon> getPokemon() {
        Map<String, Pokemon> pokemonList = new HashMap<>();




        return pokemonList;
    }
}
