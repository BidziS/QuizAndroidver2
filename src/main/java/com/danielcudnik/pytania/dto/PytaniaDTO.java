package com.danielcudnik.pytania.dto;


import com.danielcudnik.base.DTO.BaseDTO;
import com.danielcudnik.kategorie.dto.KategorieDTO;

import java.util.Date;

/**
 * Created by Bidzis on 11/12/2016.
 */
public class PytaniaDTO extends BaseDTO {
    private String pytanie;
    private KategorieDTO kategorieDTO;

    public PytaniaDTO(){

    }

    public PytaniaDTO(Long id, Date techDate, String pytanie, KategorieDTO kategorieDTO){
        super(id, techDate);
        this.pytanie = pytanie;
        this.kategorieDTO = kategorieDTO;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public KategorieDTO getKategorieDTO() {
        return kategorieDTO;
    }

    public void setKategorieDTO(KategorieDTO kategorieDTO) {
        this.kategorieDTO = kategorieDTO;
    }
}
