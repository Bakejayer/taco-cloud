package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient Save(Ingredient ingredient);
}
