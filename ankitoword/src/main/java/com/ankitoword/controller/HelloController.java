package com.ankitoword.controller;

import java.util.ArrayList;
import java.util.List;

import com.ankitoword.entity.AppShortDef;
import com.ankitoword.entity.Dicionario;
import com.ankitoword.entity.Meta;
import com.ankitoword.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
@Controller
public class HelloController {

    @Autowired
    private WordService wordService;

    /*@GetMapping("/hello")
    public String hello() {
        List<Dicionario> palavras = wordService.helloWorld();

        String word;
        String label;
        String[] definition;
        for (Dicionario palavra : palavras) {

            System.out.println(palavra.getMeta().getId());    
            System.out.println(palavra.getMeta().getAppShortdef().getDef()[0]);    
        }
        
        return palavras.get(0).getMeta().getAppShortdef().getDef()[0];       
    }*/

    @GetMapping("/palavras")
    public String listWords(@RequestParam(value = "search", required = false) String palavra, Model theModel) {
        //String palavra = "ball";
        List<Dicionario> listaDicionarios = wordService.getWords(palavra);

        //TODO: Tratar erro de palavra não encontrada
        if (palavra == null){
            return "index";
        }

        List<Meta> listaMetas = new ArrayList<>();
        for (Dicionario dicionario : listaDicionarios){
            listaMetas.add(dicionario.getMeta());
        }

        List<AppShortDef> palavras = new ArrayList<>();
        for (Meta meta : listaMetas){
            palavras.add(meta.getAppShortdef());
        }
       
        theModel.addAttribute("palavras", palavras);
        
        return "index";    
    }
}
