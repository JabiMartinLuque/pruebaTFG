package com.example.furbo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furbo.entidades.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
    
}
