package com.danielcudnik.uzytkownik.dto;


import com.danielcudnik.base.DTO.BaseDTO;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bidzis on 10/29/2016.
 */
@ApiModel
public class UzytkownikDTO extends BaseDTO implements Serializable {
    private String nick;
    private String haslo;

    public UzytkownikDTO() {
    }

    public UzytkownikDTO(Long id, Date techDate, String nick, String haslo){
        super(id, techDate);
        this.nick = nick;
        this.haslo = haslo;
    }

    public UzytkownikDTO(String nick, String haslo){
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
