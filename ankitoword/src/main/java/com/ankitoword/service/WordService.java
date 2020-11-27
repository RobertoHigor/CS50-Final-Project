package com.ankitoword.service;

import java.util.ArrayList;
import java.util.List;

import com.ankitoword.entity.Dicionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class WordService {
    @Autowired
    private WebClient webClient;
    
    @Value("${merriam.key}")
    private String APIKey;

    public List<Dicionario> getWords(String palavra) {
        
        // OMono permite tratar o retorno quando a requisição finalizar sem bloquear o método.
   
        Flux<Dicionario> fluxDicionario = this.webClient
            .get()
            .uri(builder -> builder.path("/"+palavra).queryParam("key", APIKey).build())
            .retrieve() //Retorna o response-spec
            .bodyToFlux(Dicionario.class);
            // Em vez do retrieve(), existe o exchange() que retorna os cabeçalhos e permite tratar erros.

        List<Dicionario> dicionarios = new ArrayList<>();
        dicionarios = fluxDicionario.collectList().block(); // Esperar a 
        

        /* Por ser um método non-blocking, diferente de RestTemplate, o restante
        * do código continua a ser executado mesmo antes da requisição terminar.
        * Deve-se tomar cuidado pois é possível retoranr o método antes da requsição.
        */
        return dicionarios;
    }
}
