package com.ankitoword.controller;

import java.util.List;

import com.ankitoword.entity.Dicionario;
import com.ankitoword.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
   
@RestController
public class HelloController {

    @Autowired
    private WordService wordService;

    @GetMapping("/hello")
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
    }
}
