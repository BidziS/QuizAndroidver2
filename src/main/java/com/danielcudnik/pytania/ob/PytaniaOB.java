package com.danielcudnik.pytania.ob;



import com.danielcudnik.base.OB.BaseOB;
import com.danielcudnik.kategorie.ob.KategorieOB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Entity
@Table(name = "pytania")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_PYTANIA_ID")
public class PytaniaOB extends BaseOB {
    private String pytanie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KATEGORIE_ID", referencedColumnName = "ID")
    @NotNull
    private KategorieOB kategorie;



    public PytaniaOB(){

    }

    public PytaniaOB(String pytanie, KategorieOB kategorie){
        this.pytanie = pytanie;
        this.kategorie = kategorie;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public KategorieOB getKategorie() {
        return kategorie;
    }

    public void setKategorie(KategorieOB kategorie) {
        this.kategorie = kategorie;
    }
}
