package com.ankitoword.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Meta {
    private String id;
    
    @JsonProperty(value="app-shortdef")
    //@JsonInclude(Include.NON_EMPTY)
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
