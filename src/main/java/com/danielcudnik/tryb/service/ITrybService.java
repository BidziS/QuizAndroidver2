package com.danielcudnik.tryb.service;

import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/10/2016.
 */
public interface ITrybService {
    TrybDTO znajdzTrybPoId(Long aId) throws MyServerException;
    List<TrybDTO> znajdzWszystkieTryby();
    TrybDTO znajdzTrybPoNazwie(String aNazwaTrybu) throws  MyServerException;
    TrybDTO zapiszTryb(TrybDTO aTrybDTO) throws MyServerException ;
}
