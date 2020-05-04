package com.apatricio.demo.repository;

import com.apatricio.demo.model.mongodb.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String>, OrderRepositoryCustom {

}
