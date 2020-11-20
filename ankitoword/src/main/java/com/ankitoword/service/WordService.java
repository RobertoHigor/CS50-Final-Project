package com.ankitoword.service;

import java.util.ArrayList;
import java.util.List;

import com.ankitoword.entity.Dicionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class WordService {
    @Autowired
    private WebClient webClient;
    
    @Value("${merriam.key}")
    private String APIKey;

    public List<Dicionario> helloWorld() {
        
        //https://gturnquist-quoters.cfapps.io/api/random", 200);
        // Mono permite tratar o retorno quando a requisição finalizar sem bloquear o método.
   
        Flux<Dicionario> result = this.webClient
            .method(HttpMethod.GET)
            .uri(builder -> builder.path("/yield").queryParam("key", APIKey).build())
            .retrieve()
            .bodyToFlux(Dicionario.class);
        // exchange() ao invés de retrieve() permite tratar erros.
        // O retrieve retorna anas o response-spec

        // Escuta aresposta do mono, sendo executado na mesma hora que a requisição retornar
        result.subscribe(w -> {
            System.out.println("Fim da requisição");
        });

        List<Dicionario> palavras = new ArrayList<>();
        palavras = result.collectList().block(); // Esperar a 
        // No caso de várias requisições, ao invés de block(), existe o zip()
        /* Ex: Mono.zip(mono1, mono2).map(tuple -> { 
            tuple.getT1().setPreco(tuple.getT2().getPreço));
        });
        */
        /* Por ser um método non-blocking, diferente de RestTemplate, o restante
        * do código continua a ser executado mesmo antes da requisição terminar.
        * Deve-se tomar cuidado pois é possível retoranr o método antes da requsição.
        */
        return palavras;
    }
}
