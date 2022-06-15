package br.edu.ifsp.gru.application.data.service;

import br.edu.ifsp.gru.application.data.entity.Contas;
import br.edu.ifsp.gru.application.data.entity.Tipo;
import br.edu.ifsp.gru.application.data.repository.ContasRepository;
import br.edu.ifsp.gru.application.data.repository.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final ContasRepository contasRepository;
    private final TipoRepository tipoRepository;

    public CrmService(ContasRepository contasRepository,
                      TipoRepository tipoRepository) {

        this.contasRepository = contasRepository;
        this.tipoRepository = tipoRepository;
    }

    public List<Contas> buscaTodasContas(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return contasRepository.findAll();
        } else {
            return contasRepository.busca(filterText);
        }
    }

    public long countContas(){
        return contasRepository.count();
    }

    public void deletarConta(Contas conta){
        contasRepository.delete(conta);
    }
    
    public double somaSaldo() {
    	
    	return contasRepository.soma();
    }
    

    public void salvarConta(Contas conta){
        if(conta == null){
            System.err.println("Conta retornou null");
            return;
        }

        contasRepository.save(conta);
    }

    public List<Tipo> buscaTodosStatus(){
        return tipoRepository.findAll();
    }
}
