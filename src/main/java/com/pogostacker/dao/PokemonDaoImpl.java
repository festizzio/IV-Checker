package com.pogostacker.dao;

import com.pogostacker.model.Pokemon;
import com.pogostacker.util.SQLiteQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.pogostacker.util.SQLiteQueries.*;

@Slf4j
@Repository
public class PokemonDaoImpl implements PokemonDao {
    private Connection conn;

    @PostConstruct
    public void init() {
        try {
            conn = DriverManager.getConnection(SQLiteQueries.CONNECTION_STRING);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon getPokemon(String pokemonName) {
        Pokemon selectedPokemon = null;
        try(PreparedStatement selectStatement = conn.prepareStatement(SELECT_POKEMON)) {
            selectStatement.setString(INDEX_POKEMON_NAME, pokemonName);
            log.info("Prepared statement in getPokemon inside Dao");
            try(ResultSet resultSet = selectStatement.executeQuery()) {
                log.info("Created ResultSet inside Dao");
                if(resultSet.next()) {
                    int baseAttack = resultSet.getInt(INDEX_BASE_ATTACK);
                    int baseDefense = resultSet.getInt(INDEX_BASE_DEFENSE);
                    int baseStamina = resultSet.getInt(INDEX_BASE_STAMINA);
                    selectedPokemon = new Pokemon(pokemonName, baseAttack, baseDefense, baseStamina);
                    log.info("Created new Pokemon inside Dao");
                }
            }
        } catch(SQLException e) {
            log.info("Error accessing database: {}", e.getMessage());
            log.info("selectedPokemon = {}", selectedPokemon);
        }
        return selectedPokemon;
    }
}
