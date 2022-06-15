package br.edu.ifsp.gru.application.data.service;

import br.edu.ifsp.gru.application.data.entity.ContaBancaria;
import br.edu.ifsp.gru.application.data.entity.TipoConta;
import br.edu.ifsp.gru.application.data.repository.ContaBancariaRepository;
import br.edu.ifsp.gru.application.data.repository.TipoContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final TipoContaRepository tipoContaRepository;

    //Cria uma conexão com os repositories de contas bancárias e tipos de contas
    public CrmService(ContaBancariaRepository contaBancariaRepository,
                      TipoContaRepository tipoContaRepository) {

        this.contaBancariaRepository = contaBancariaRepository;
        this.tipoContaRepository = tipoContaRepository;
    }

    //Retorna uma lista com todas as contas cadastradas pelo usuário
    public List<ContaBancaria> buscaTodasContasBancarias(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return contaBancariaRepository.findAll();
        } else {
            return contaBancariaRepository.busca(filterText);
        }
    }

    public long countContas(){
        return contaBancariaRepository.count();
    }

    public void deletarConta(ContaBancaria conta){
        contaBancariaRepository.delete(conta);
    }

    //Função que soma todos os saldos de todas as contas cadastradas pelo usuário
    public double somaSaldo() {
    	
    	return contaBancariaRepository.somaSaldo();
    }
    
    //Exception para o cadastro de contas
    public void salvarConta(ContaBancaria conta){
        if(conta == null){
            System.err.println("Conta retornou null");
            return;
        }

        contaBancariaRepository.save(conta);
    }

    //Retorna todos os tipos de contas cadastradas no banco de dados
    public List<TipoConta> buscaTodosTipos(){
        return tipoContaRepository.findAll();
    }
}
