package com.ankitoword.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ankitoword.Helpers.JsonHelper;
import com.ankitoword.model.Anki.Action;
import com.ankitoword.model.Anki.Anki;
import com.ankitoword.model.Anki.Fields;
import com.ankitoword.model.Anki.Note;
import com.ankitoword.model.Anki.Params;
import com.ankitoword.model.MerriamWebster.AppShortDef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AnkiService {
    
    //TODO: Receber resposta do POST diretamente como Object, ao invés de String JSON

    @Autowired
    private WebClient ankiWebClient;

    public List<String> getAllDecks(String ankiUrl) {      
        Action action = new Action("deckNames", 6);                       
                                
        Mono<String> monoAnki= this.ankiWebClient.post().uri(ankiUrl)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(action)
            .retrieve().bodyToMono(String.class);

        //https://stackoverflow.com/questions/62915284/spring-webclient-not-processing-json-content
        String jsonValue = monoAnki.block();

        System.out.println("Cartões retornados: "+jsonValue);        

        // Getting result as a List<String>
        Anki ankiObject = JsonHelper.toObject(jsonValue, Anki.class);
        List<String> decks = ankiObject.getResult();
        decks.sort(Comparator.naturalOrder());
    
        return decks;
    }

	public Anki save(String ankiUrl, AppShortDef tempPalavra, String deck) {
        String frontText = tempPalavra.getHw() + " " + tempPalavra.getFl();
        System.out.println("Front Text"+ frontText+"\n\n");

        // Criando o objeto ACtion
        Fields fields = new Fields(frontText, tempPalavra.getDef()[0]);

        // Adicionando a tag
        List<String> tags = new ArrayList<>();
        tags.add(tempPalavra.getFl());

        Note note = new Note(deck, "Basic", fields, tags);
        Params params = new Params(note);
        Action action = new Action("addNote", 6, params);

        System.out.println("Objeto Action: " + action.toString());

        // Enviando o Json para o Anki
        Mono<String> monoAnki = this.ankiWebClient.put().uri(ankiUrl)
            .accept(MediaType.APPLICATION_JSON).bodyValue(action)
            .retrieve().bodyToMono(String.class);
        
        String jsonValue = monoAnki.block();
        System.out.println("Cartão adicionado: " +jsonValue);

        // Convertendo a resposta para um objeto Anki
        Anki ankiObject = JsonHelper.toObject(jsonValue, Anki.class);
        return ankiObject;
    }
}
