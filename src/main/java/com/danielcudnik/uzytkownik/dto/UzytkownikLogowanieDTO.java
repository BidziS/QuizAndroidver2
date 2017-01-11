package com.danielcudnik.uzytkownik.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by Bidzis on 11/22/2016.
 */
@ApiModel
public class UzytkownikLogowanieDTO implements Serializable {

    private String nick;
    private String haslo;

    public UzytkownikLogowanieDTO() {
    }

    public UzytkownikLogowanieDTO(String nick, String haslo) {
        this.nick = nick;
        this.haslo = haslo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
