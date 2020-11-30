package com.ankitoword.service;

import java.util.ArrayList;
import java.util.List;

import com.ankitoword.model.Dicionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Flux;

@Service
public class WordService {
    @Autowired
    private WebClient webClient;
    
    @Value("${merriam.key}")
    private String APIKey;

    public List<Dicionario> getWords(String palavra) {
        
        // OMono permite tratar o retorno quando a requisição finalizar sem bloquear o método.
        // Um teste possível é checar se o tamanho da lista é >= 0
        List<Dicionario> dicionarios = new ArrayList<>();
        try {
            Flux<Dicionario> fluxDicionario = this.webClient
            .get()
            .uri(builder -> builder.path("/"+palavra).queryParam("key", APIKey).build())
            .retrieve() //Retorna o response-spec
            .bodyToFlux(Dicionario.class);
            // Em vez do retrieve(), existe o exchange() que retorna os cabeçalhos e permite tratar erros.
            
            dicionarios = fluxDicionario.collectList().block();
        } catch (DecodingException e) {
            System.out.println("Ocorreu um erro: "+e);
            //return null;
        } catch (WebClientResponseException exc) {
            //TODO: importar SLF4J
            //log.error("Error Response Code: {} Response Body: {}", exc.getRawStatusCode(), exc.getResponseBodyAsString());
        }

        /* Por ser um método non-blocking, diferente de RestTemplate, o restante
        * do código continua a ser executado mesmo antes da requisição terminar.
        * Deve-se tomar cuidado pois é possível retoranr o método antes da requsição.
        */
        return dicionarios;
    }
}
