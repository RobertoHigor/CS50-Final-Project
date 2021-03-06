package com.ankitoword.service;

import java.util.ArrayList;
import java.util.List;

import com.ankitoword.model.MerriamWebster.AppShortDef;
import com.ankitoword.model.MerriamWebster.Meta;
import com.ankitoword.model.MerriamWebster.RootWebster;

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

    public List<AppShortDef> getWords(String palavra) {
        
        // O Mono permite tratar o retorno quando a requisição finalizar sem bloquear o método.
        // Um teste possível é checar se o tamanho da lista é >= 0
        List<RootWebster> dicionarios = new ArrayList<>();
        try {
            Flux<RootWebster> fluxDicionario = this.webClient
            .get()
            .uri(builder -> builder.path("/"+palavra).queryParam("key", APIKey).build())
            .retrieve() //Retorna o response-spec
            .bodyToFlux(RootWebster.class);
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
        List<AppShortDef> palavras = rootToAppShortDef(dicionarios);
        return palavras;
    }

    private List<AppShortDef> rootToAppShortDef(List<RootWebster> myDicionarios) {
         // Criar uma lista com as metas
         List<Meta> listaMetas = new ArrayList<>();
         for (RootWebster dicionario : myDicionarios){
             if (dicionario.getMeta().getAppShortDef() != null){
                 //System.out.println("Objeto adicionado");
                 listaMetas.add(dicionario.getMeta());
             } else {
                 //System.out.println("Entrou no else");
             }
         }
 
         // Pegar os dados das metas e criar uma lista de palavras do AppShortDef
         List<AppShortDef> palavras = new ArrayList<>();
         for (Meta meta : listaMetas){          
             palavras.add(meta.getAppShortDef());
         }
        
        return palavras;
    }
}
