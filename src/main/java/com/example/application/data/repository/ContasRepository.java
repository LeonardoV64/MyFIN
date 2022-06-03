package com.example.application.data.repository;

import com.example.application.data.entity.Contas;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasRepository extends JpaRepository<Contas, UUID> {

}
