package com.danielcudnik.pytania.service;


import com.danielcudnik.pytania.dto.PytaniaDTO;
import com.danielcudnik.pytania.dto.PytaniaZapiszDTO;
import com.danielcudnik.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public interface IPytaniaService {
    List<PytaniaDTO> znajdzPytaniaPoKategorii(Long aIdTryb);
    List<PytaniaDTO> znajdzPytaniaPoNazwieKategorii(String nazwaKategorii);
    PytaniaDTO zapiszPytania(PytaniaDTO aPytaniaDTO) throws MyServerException;
    PytaniaDTO zapiszPytania2(PytaniaZapiszDTO aPytaniaDTO) throws MyServerException;
    void usunPytania(Long aId);
    List<PytaniaDTO> losujPytaniaPoKategorii(Long aIdTryb);
//    PytaniaDTO zmienPytanie(PytaniaDTO aPytaniaDTO) throws MyServerException;
}
