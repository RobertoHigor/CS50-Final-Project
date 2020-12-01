package com.ankitoword.model.MerriamWebster;

import org.springframework.stereotype.Component;

@Component
public class AppShortDef {
    public String hw;
    public String fl;
    public String[] def;
    
    public String getHw() {
        //String[] split = hw.split(":");
        //return split[0];
        return this.hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String[] getDef() {
        return def;
    }

    public void setDef(String[] def) {
        this.def = def;
    }

    
}
