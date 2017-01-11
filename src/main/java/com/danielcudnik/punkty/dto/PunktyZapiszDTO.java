package com.danielcudnik.punkty.dto;

import com.danielcudnik.base.DTO.BaseDTO;

import java.util.Date;

/**
 * Created by Bidzis on 11/28/2016.
 */
public class PunktyZapiszDTO extends BaseDTO {
    private Long punkty;
    private Long trybid;
    private Long uzytkownikid;


    public PunktyZapiszDTO() {
    }

    public PunktyZapiszDTO(Long punkty, Long trybid, Long uzytkownikid) {
        this.punkty = punkty;
        this.trybid = trybid;
        this.uzytkownikid = uzytkownikid;
    }

    public PunktyZapiszDTO(Long id, Date techDate, Long punkty, Long trybid, Long uzytkownikid) {
        super(id, techDate);
        this.punkty = punkty;
        this.trybid = trybid;
        this.uzytkownikid = uzytkownikid;

    }

    public Long getPunkty() {
        return punkty;
    }

    public void setPunkty(Long punkty) {
        this.punkty = punkty;
    }

    public Long getTrybid() {
        return trybid;
    }

    public void setTrybid(Long trybid) {
        this.trybid = trybid;
    }

    public Long getUzytkownikid() {
        return uzytkownikid;
    }

    public void setUzytkownikid(Long uzytkownikid) {
        this.uzytkownikid = uzytkownikid;
    }
}
