package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Ingredient;
import com.bayer.tacocloud.model.Taco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Repository
public class JDBCTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    public JDBCTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco){
        log.info("Saving taco design");
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        log.info("Saving taco ingredients");
        for (Ingredient ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco){
        log.info("Getting timestamp for taco creation");
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO Taco(name, createdAt) values(?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf
                        .newPreparedStatementCreator(
                                Arrays.asList(taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbc.update("INSERT INTO Taco_Ingredients(taco, ingredient)" + " values(?, ?)",
                tacoId, ingredient.getId());
    }

}
