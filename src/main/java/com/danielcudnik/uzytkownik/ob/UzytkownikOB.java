package com.danielcudnik.uzytkownik.ob;


import com.danielcudnik.base.OB.BaseOB;

import javax.persistence.*;

/**
 * Created by Bidzis on 10/29/2016.
 */
@Entity
@Table(name = "uzytkownicy")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_UZYTKOWNIK_ID")
public class UzytkownikOB extends BaseOB {
    @Column(unique = true)
    private String nick;
    private String haslo;


    public UzytkownikOB() {

    }

    public UzytkownikOB(String nick, String haslo){
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
