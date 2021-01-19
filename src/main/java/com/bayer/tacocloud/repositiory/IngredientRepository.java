package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
