package com.danielcudnik.punkty.service;



import com.danielcudnik.punkty.dto.PunktyDTO;
import com.danielcudnik.punkty.dto.PunktyZapiszDTO;
import com.danielcudnik.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/11/2016.
 */
public interface IPunktyService {
    List<PunktyDTO> znajdzPunktyPoTrybie(Long aIdTryb);
    List<PunktyZapiszDTO> znajdzPunktyPoTrybie2(Long aIdTryb);
    List<PunktyDTO> znajdzPunktyPoUzytkowniku(Long aIdUzytkownik);
    List<PunktyDTO> znajdzPunktyPoUzytkownikuITrybie(Long aNick, Long aTryb);
    PunktyDTO zapiszPunkty(PunktyDTO aPunktyDTO) throws MyServerException;
    public PunktyDTO zapiszPunkty2(PunktyZapiszDTO aPunktyDTO) throws MyServerException;
    void usunPunkty(Long aId);

}
