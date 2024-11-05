package com.example.furbo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furbo.entidades.Team;

public interface TeamRepository extends JpaRepository<Team, String> {
}
