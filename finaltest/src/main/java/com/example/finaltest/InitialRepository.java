package com.example.finaltest;

import com.example.finaltest.model.Initial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialRepository extends CrudRepository<Initial, String> {
    Initial findByUuid(String id);
}
