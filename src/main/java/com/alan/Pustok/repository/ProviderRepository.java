package com.alan.Pustok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.Pustok.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
