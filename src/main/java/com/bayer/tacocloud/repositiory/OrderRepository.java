package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
