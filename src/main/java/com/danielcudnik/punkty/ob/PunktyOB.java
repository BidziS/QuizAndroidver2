package com.danielcudnik.punkty.ob;



import com.danielcudnik.base.OB.BaseOB;
import com.danielcudnik.tryb.ob.TrybOB;
import com.danielcudnik.uzytkownik.ob.UzytkownikOB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Bidzis on 11/9/2016.
 */
@Entity
@Table(name = "punkty")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_PUNKTY_ID")
public class PunktyOB extends BaseOB {
    private Long punkty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRYB_ID", referencedColumnName = "ID")
    @NotNull
    private
    TrybOB tryb;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="UZYTKOWNICY_ID",referencedColumnName = "ID")
    @NotNull
    private
    UzytkownikOB uzytkownicy;

    public  PunktyOB(){}
    public PunktyOB(TrybOB tryb) {
        this.tryb = tryb;
    }

    public PunktyOB(Long punkty, TrybOB tryb, UzytkownikOB uzytkownicy) {
        super();
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

    public TrybOB getTryb() {
        return tryb;
    }

    public void setTryb(TrybOB tryb) {
        this.tryb = tryb;
    }

    public UzytkownikOB getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(UzytkownikOB uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }
}
