package com.ankitoword.controller;

import java.util.ArrayList;
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

//@RestController
@Controller
public class HelloController {

    @Autowired
    private WordService wordService;

    @Autowired
    private AnkiService ankiService;

    @GetMapping("/palavras")
    public String listWords(
        @RequestParam(value = "search", required = false) String palavra, 
        Model theModel) {
        // Se a busca estiver vazia, apenas retornar o index
        if (palavra == null){
            return "index";
        }

        List<RootWebster> listaDicionarios;
        List<String> listaDecks;
        try
        {
            listaDicionarios = wordService.getWords(palavra);
            listaDecks = ankiService.getAllDecks().getResult();
        } catch (DecodingException exc) {
            System.out.println(exc);
            return "index";
        }    

        // Criar uma lista com as metas
        List<Meta> listaMetas = new ArrayList<>();
        for (RootWebster dicionario : listaDicionarios){
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
       
        // Adicionar as palavras da lista (contendo palavra, tipo e definição)
        theModel.addAttribute("palavras", palavras);
        theModel.addAttribute("decks", listaDecks);
        
        return "index";    
    }

    @PostMapping("/save")
    public String listAllDecks(
        @RequestParam(value = "deck", required = true) String deck, 
        @RequestParam(value = "back", required = true) String[] def,
        @RequestParam(value = "hw") String hw,
        @RequestParam(value = "fl") String fl) {

        System.out.println("APPSHORTDEF: " + fl);
        System.out.println("APPSHORTDEF: " + hw);
        System.out.println("@@@@@@@@@@@@@"+deck);

        AppShortDef appShortDef = new AppShortDef(hw, fl, def);
        ankiService.save(appShortDef, deck);    
       
        return "redirect:/";
    }
}
