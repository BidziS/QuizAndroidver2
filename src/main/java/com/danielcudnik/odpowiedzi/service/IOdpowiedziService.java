package com.danielcudnik.odpowiedzi.service;



import com.danielcudnik.odpowiedzi.EPoprawna;
import com.danielcudnik.odpowiedzi.dto.OdpowiedziDTO;
import com.danielcudnik.odpowiedzi.dto.OdpowiedziZapiszDTO;
import com.danielcudnik.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
public interface IOdpowiedziService {
    List<OdpowiedziDTO> znajdzOdpowiedziPoPytaniu(Long aIdPytania);
    List<OdpowiedziDTO> znajdzOdpowiedziPoPoprawnosci(boolean poprawna);
    OdpowiedziDTO zapiszOdpowiedz(OdpowiedziDTO aOdpowiedziDTO) throws MyServerException;
    OdpowiedziDTO zapiszOdpowiedz2(OdpowiedziZapiszDTO aOdpowiedziDTO) throws MyServerException;
    OdpowiedziDTO edytujOdpowiedz(OdpowiedziZapiszDTO aOdpowiedziZapiszDTO) throws MyServerException;
    void usunOdpowiedzi(Long aId);
}
