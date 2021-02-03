package com.example.finaltest;

import com.example.finaltest.model.Paket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Paket, String> {

}
