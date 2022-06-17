package br.edu.ifsp.gru.application.data.repository;

import br.edu.ifsp.gru.application.data.entity.TipoConta;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoContaRepository extends JpaRepository<TipoConta, UUID> {

}
