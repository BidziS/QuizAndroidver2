package com.danielcudnik.odpowiedzi.dto;

import com.danielcudnik.base.DTO.BaseDTO;
import com.danielcudnik.odpowiedzi.EPoprawna;

/**
 * Created by Bidzis on 1/10/2017.
 */
public class OdpowiedziZapiszDTO extends BaseDTO{
    private String odpowiedz;
    private boolean poprawna;
    private Long pytania;

    public OdpowiedziZapiszDTO(){

    }

    public OdpowiedziZapiszDTO(String odpowiedz, boolean poprawna, Long pytania) {
        this.odpowiedz = odpowiedz;
        this.poprawna = poprawna;
        this.pytania = pytania;
    }

    public String getOdpowiedz() {
        return odpowiedz;
    }

    public void setOdpowiedz(String odpowiedz) {
        this.odpowiedz = odpowiedz;
    }

    public boolean getPoprawna() {
        return poprawna;
    }

    public void setPoprawna(boolean poprawna) {
        this.poprawna = poprawna;
    }

    public Long getPytania() {
        return pytania;
    }

    public void setPytania(Long pytania) {
        this.pytania = pytania;
    }
}
