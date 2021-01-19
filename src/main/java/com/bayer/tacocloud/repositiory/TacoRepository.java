package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
