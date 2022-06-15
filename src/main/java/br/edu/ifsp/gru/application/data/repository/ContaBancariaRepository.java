package br.edu.ifsp.gru.application.data.repository;

import br.edu.ifsp.gru.application.data.entity.ContaBancaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    //Faz a busca a partir de uma palavra parecida no banco de dados com o que o usuário digitou no campo de pesquisa
    @Query("select c from ContaBancaria c " +
        "where lower(c.contaBancaria) like lower (concat('%', :nomeConta, '%'))")
    List<ContaBancaria> busca(@Param("nomeConta") String nomeConta);
    
    //Faz a soma do saldo de todas as contas cadastradas pelo usuário
    @Query(value = "SELECT sum(saldo) FROM ContaBancaria")
    public double somaSaldo();
    
    

}
