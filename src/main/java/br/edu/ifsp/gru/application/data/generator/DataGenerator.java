package br.edu.ifsp.gru.application.data.generator;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.edu.ifsp.gru.application.data.entity.ContaBancaria;
import br.edu.ifsp.gru.application.data.entity.TipoConta;
import br.edu.ifsp.gru.application.data.repository.ContaBancariaRepository;
import br.edu.ifsp.gru.application.data.repository.TipoContaRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

//Gera 5 contas bancárias para teste quando o usuário entra pela primeira vez
@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(ContaBancariaRepository contaBancariaRepository,
                                      TipoContaRepository tipoContaRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (contaBancariaRepository.count() != 0L) {
                logger.info("Usando um Banco de Dados Existente!");
                return;
            }
            int seed = 123;

            logger.info("Gerando informações demonstrativas...");

            List<TipoConta> tipoContas = tipoContaRepository
                    .saveAll(Stream.of("Corrente", "Poupança")
                            .map(TipoConta::new).collect(Collectors.toList()));

            logger.info("... gerando 5 ContaBancaria aleatorias...");
            ExampleDataGenerator<ContaBancaria> contaBancariaGenerator = new ExampleDataGenerator<>(ContaBancaria.class,
                    LocalDateTime.now());
            contaBancariaGenerator.setData(ContaBancaria::setConta, DataType.COMPANY_NAME);
            contaBancariaGenerator.setData(ContaBancaria::setSaldo, DataType.PRICE);

            Random r = new Random(seed);
            List<ContaBancaria> conta = contaBancariaGenerator.create(5, seed).stream().peek(contact -> {
                contact.setStatus(tipoContas.get(r.nextInt(tipoContas.size())));
            }).collect(Collectors.toList());

            contaBancariaRepository.saveAll(conta);

            logger.info("ContaBancaria geradas");
        };
    }

}
