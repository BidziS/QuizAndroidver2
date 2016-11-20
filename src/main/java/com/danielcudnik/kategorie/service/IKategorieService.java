package com.danielcudnik.kategorie.service;

import com.danielcudnik.kategorie.dto.KategorieDTO;
import com.danielcudnik.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public interface IKategorieService {
    KategorieDTO znajdzKategoriePoId(Long aId) throws MyServerException;
    List<KategorieDTO> znajdzWszystkieKategorie();
    KategorieDTO znajdzKategoriePoNazwie(String aNazwaKategorii) throws  MyServerException;
    KategorieDTO zapiszKategorie(KategorieDTO aKategorieDTO) throws MyServerException ;
}
