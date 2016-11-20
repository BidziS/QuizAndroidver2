package com.danielcudnik.kategorie.dto;

import com.danielcudnik.base.DTO.BaseDTO;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Created by Bidzis on 11/12/2016.
 */
@ApiModel
public class KategorieDTO extends BaseDTO{
    private String nazwa;

    public KategorieDTO(){

    }
    public KategorieDTO(Long id, Date techDate, String nazwa){
        super(id, techDate);
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
