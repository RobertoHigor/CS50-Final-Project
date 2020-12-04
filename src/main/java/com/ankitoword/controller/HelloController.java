package com.ankitoword.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.ankitoword.model.MerriamWebster.AppShortDef;
import com.ankitoword.service.AnkiService;
import com.ankitoword.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("ankiUrl")
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
        HttpSession session,
        Model theModel) {

        // Checar se a página foi acessada após definir o endereço do Anki
        if (session.getAttribute("ankiUrl") == null){
            theModel.addAttribute("error", "Anki URL not set");
            return "index";
        // Checar se foi passado algum valor de busca
        }else if (palavra == null){
            return "palavras-card";
        } 

        //Receber dado de sessão
        System.out.println("=== Session Data ===");
        String ankiUrl = session.getAttribute("ankiUrl").toString();
        System.out.println(ankiUrl);

        

        List<AppShortDef> palavras;
        List<String> decks;
        try
        {
            palavras = wordService.getWords(palavra);
            decks = ankiService.getAllDecks(ankiUrl);
        } catch (DecodingException exc) {
            System.out.println(exc);
            return "palavras-card";
        }    
       
        // Adicionar as palavras da lista (contendo palavra, tipo e definição)
        theModel.addAttribute("palavras", palavras);
        theModel.addAttribute("decks", decks);
        
        return "palavras-card";    
    }

    @PostMapping("/save")
    public String listAllDecks(
        @RequestParam(value = "deck", required = true) String deck, 
        @RequestParam(value = "back", required = true) String[] def,
        @RequestParam(value = "hw") String hw,
        @RequestParam(value = "fl") String fl,
        HttpSession session){

        System.out.println("Word type: " + fl);
        System.out.println("Word name: " + hw);
        System.out.println("Deck Selected" + deck);

        AppShortDef appShortDef = new AppShortDef(hw, fl, def);
        String ankiUrl = session.getAttribute("ankiUrl").toString();
        ankiService.save(ankiUrl, appShortDef, deck);    
       
        return "redirect:/palavras";
    }

    @PostMapping("/anki")
    public String setAnkiUrl(@RequestParam("ankiUrl") String ankiUrl,
        HttpSession session,
        Model theModel) {
        session.setAttribute("ankiUrl", ankiUrl);
        return "redirect:/palavras";
    }
}
