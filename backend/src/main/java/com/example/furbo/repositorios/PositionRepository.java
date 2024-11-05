package com.example.furbo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.furbo.entidades.Position;

public interface PositionRepository extends JpaRepository<Position, String> {
}
