package com.ankitoword.model.MerriamWebster;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Meta {
    private String id;
    
    @JsonProperty(value="app-shortdef")
    private AppShortDef appShortDef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppShortDef getAppShortDef() {
        return appShortDef;
    }

    public void setAppShortDef(Object object) {         
        if (object instanceof ArrayList){
            System.out.println("Objeto inválido: "+object);
        } else {
            System.out.println("Atribuindo Valor");
            ObjectMapper mapper = new ObjectMapper();
            AppShortDef appShortDef = mapper.convertValue(object, AppShortDef.class); 
            this.appShortDef = appShortDef;
        }
       
    } 
}
