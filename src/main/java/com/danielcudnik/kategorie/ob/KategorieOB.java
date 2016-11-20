package com.danielcudnik.kategorie.ob;

import com.danielcudnik.base.OB.BaseOB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Entity
@Table(name = "kategorie")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_KATEGORIA_ID")
public class KategorieOB extends BaseOB {
    @Column(unique = true)
    private String nazwa;

    public KategorieOB(){

    }
    public KategorieOB(String nazwa){
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
