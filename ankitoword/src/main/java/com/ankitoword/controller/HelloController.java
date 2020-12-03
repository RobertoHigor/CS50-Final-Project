package com.ankitoword.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ankitoword.model.MerriamWebster.AppShortDef;
import com.ankitoword.model.MerriamWebster.Meta;
import com.ankitoword.model.MerriamWebster.RootWebster;
import com.ankitoword.service.AnkiService;
import com.ankitoword.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    // Dependency Injection
    @Autowired
    private WordService wordService;
    @Autowired
    private AnkiService ankiService;

    // Endpoints

    @GetMapping("/palavras")
    public String listWords(
        @RequestParam(value = "search", required = false) String palavra, 
        Model theModel) {
        // Se a busca estiver vazia, apenas retornar o index
        if (palavra == null){
            return "index";
        }

        List<AppShortDef> palavras;
        List<String> decks;
        try
        {
            palavras = wordService.getWords(palavra);
            decks = ankiService.getAllDecks();
        } catch (DecodingException exc) {
            System.out.println(exc);
            return "index";
        }    
       
        // Adicionar as palavras da lista (contendo palavra, tipo e definição)
        theModel.addAttribute("palavras", palavras);
        theModel.addAttribute("decks", decks);
        
        return "index";    
    }

    @PostMapping("/save")
    public String listAllDecks(
        @RequestParam(value = "deck", required = true) String deck, 
        @RequestParam(value = "back", required = true) String[] def,
        @RequestParam(value = "hw") String hw,
        @RequestParam(value = "fl") String fl) {

        System.out.println("Word type: " + fl);
        System.out.println("Word name: " + hw);
        System.out.println("Deck Selected" + deck);

        AppShortDef appShortDef = new AppShortDef(hw, fl, def);
        ankiService.save(appShortDef, deck);    
       
        return "redirect:/";
    }
}
