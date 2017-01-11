package com.danielcudnik.uzytkownik.service;


import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import com.danielcudnik.uzytkownik.dto.UzytkownikLogowanieDTO;

import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
public interface IUzytkownikService {
    UzytkownikDTO znajdzUzytkownikaPoId(Long aId) throws MyServerException;
    UzytkownikDTO znajdzUzytkownikaPoNicku(String aNick) throws MyServerException;
    List<UzytkownikDTO> znajdzWszystkichUzytkownikow();
    UzytkownikDTO zapiszUzytkownika(UzytkownikDTO aUzytkownikDTO) throws MyServerException ;

    UzytkownikDTO logowanieUzytkownika(UzytkownikLogowanieDTO aUserDTO) throws MyServerException;

    void usunUzytkownika(String aNick) throws  MyServerException;
}
