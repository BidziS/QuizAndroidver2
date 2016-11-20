package com.danielcudnik.punkty.dto;


import com.danielcudnik.base.DTO.BaseDTO;
import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Bidzis on 11/9/2016.
 */
@ApiModel
public class PunktyDTO extends BaseDTO {
    private Long punkty;
    TrybDTO tryb;
    UzytkownikDTO uzytkownicy;

    public PunktyDTO(){

    }
    public PunktyDTO(Long id, Date techDate, Long punkty, TrybDTO tryb, UzytkownikDTO uzytkownicy) {
        super(id, techDate);
        this.punkty = punkty;
        this.tryb = tryb;
        this.uzytkownicy = uzytkownicy;
    }
    public Long getPunkty() {
        return punkty;
    }

    public void setPunkty(Long punkty) {
        this.punkty = punkty;
    }

    public TrybDTO getTryb() {
        return tryb;
    }

    public void setTryb(TrybDTO tryb) {
        this.tryb = tryb;
    }

    public UzytkownikDTO getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(UzytkownikDTO uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }
}
