package com.danielcudnik.pytania.dto;

import com.danielcudnik.base.DTO.BaseDTO;

import java.util.Date;

/**
 * Created by Bidzis on 1/10/2017.
 */
public class PytaniaZapiszDTO{
    private String pytanie;
    private Long kategorieID;

    public PytaniaZapiszDTO(){

    }

    public PytaniaZapiszDTO(String pytanie, Long kategorieID) {
        this.pytanie = pytanie;
        this.kategorieID = kategorieID;
    }


    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public Long getKategorieID() {
        return kategorieID;
    }

    public void setKategorieID(Long kategorieID) {
        this.kategorieID = kategorieID;
    }
}
