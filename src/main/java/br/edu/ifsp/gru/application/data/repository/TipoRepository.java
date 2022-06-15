package br.edu.ifsp.gru.application.data.repository;

import br.edu.ifsp.gru.application.data.entity.Tipo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, UUID> {

}
