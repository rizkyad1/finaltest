package com.example.finaltest;

import com.example.finaltest.model.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepo extends CrudRepository<ProductRedis, String> {
}
