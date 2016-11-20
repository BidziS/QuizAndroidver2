package com.danielcudnik.tryb.dto;

import com.danielcudnik.base.DTO.BaseDTO;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Created by Bidzis on 11/9/2016.
 */
@ApiModel
public class TrybDTO extends BaseDTO{
    private String nazwaTrybu;

    public TrybDTO(){

    }
    public TrybDTO(Long id, Date techDate, String nazwaTrybu){
        super(id,techDate);
        this.nazwaTrybu = nazwaTrybu;
    }
    public String getNazwaTrybu() {
        return nazwaTrybu;
    }

    public void setNazwaTrybu(String nazwaTrybu) {
        this.nazwaTrybu = nazwaTrybu;
    }
}
