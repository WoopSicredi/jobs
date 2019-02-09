package br.com.sicred.votacao_pauta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

/**
 * @author valverde.thiago
 */
@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class VotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotacaoApplication.class, args);
    }

}
