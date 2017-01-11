package com.danielcudnik.odpowiedzi.ob;



import com.danielcudnik.base.OB.BaseOB;
import com.danielcudnik.odpowiedzi.EPoprawna;
import com.danielcudnik.pytania.ob.PytaniaOB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Entity
@Table(name = "odpowiedzi")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_ODPOWIEDZI_ID")
public class OdpowiedziOB extends BaseOB {
    private String odpowiedz;
    private boolean poprawna;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PYTANIA_ID", referencedColumnName = "ID")
    @NotNull
    private PytaniaOB pytania;

    public OdpowiedziOB(){}

    public OdpowiedziOB(String odpowiedz, boolean poprawna, PytaniaOB pytania) {
        super();
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

    public PytaniaOB getPytania() {
        return pytania;
    }

    public void setPytania(PytaniaOB pytania) {
        this.pytania = pytania;
    }
}
