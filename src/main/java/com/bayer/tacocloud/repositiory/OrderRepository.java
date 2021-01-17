package com.bayer.tacocloud.repositiory;

import com.bayer.tacocloud.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
