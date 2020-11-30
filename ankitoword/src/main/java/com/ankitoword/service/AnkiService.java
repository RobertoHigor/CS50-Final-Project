package com.ankitoword.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AnkiService {
    
    @Autowired
    private WebClient ankiWebClient;

    public String getAllDecks() {
        String actionJson = "{\n" +
                                "\"action\":\"deckNamesAndIds\","+
                                "\"version\": 6\n"+
                            "}";        
                                
        String response = this.ankiWebClient.post().uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(actionJson))
            .retrieve()
            .bodyToMono(String.class).block();
        
        System.out.println(response);
        
        return response;
    }
}
