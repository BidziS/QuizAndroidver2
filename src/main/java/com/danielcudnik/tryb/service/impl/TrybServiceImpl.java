package com.danielcudnik.tryb.service.impl;


import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.tryb.ob.TrybOB;
import com.danielcudnik.tryb.repository.ITrybRepository;
import com.danielcudnik.tryb.service.ITrybService;

import com.danielcudnik.utils.MyServerException;

import com.danielcudnik.utils.converters.TrybConventer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/10/2016.
 */
@Service
@Transactional
public class TrybServiceImpl implements ITrybService {
    @Autowired
    ITrybRepository iTrybRepository;

    @Override
    public TrybDTO znajdzTrybPoNazwie(String aNazwaTrybu) throws MyServerException {
        TrybOB pTrybOB = iTrybRepository.znajdzPoNazwieTrybu(aNazwaTrybu);
        if(pTrybOB == null) throw new MyServerException("Nie ma takiego trybu", HttpStatus.NOT_FOUND,new HttpHeaders());
        return TrybConventer.trybOBdotrybDTO(pTrybOB);
    }
    @Override
    public List<TrybDTO> znajdzWszystkieTryby() {
        List<TrybDTO> listaWynikowaTrybowDTO = new ArrayList<>();
        List<TrybOB> listaTrybowOB = iTrybRepository.findAll();
        for(TrybOB uzytkownik : listaTrybowOB) listaWynikowaTrybowDTO.add(TrybConventer.trybOBdotrybDTO(uzytkownik));

        return listaWynikowaTrybowDTO;
    }

    public TrybDTO znajdzTrybPoId(Long aId) throws MyServerException {
        TrybOB pTrybOB = iTrybRepository.findOne(aId);
        if(pTrybOB == null) throw new MyServerException("Nie ma takiego trybu", HttpStatus.NOT_FOUND,new HttpHeaders());
        return TrybConventer.trybOBdotrybDTO(pTrybOB);
    }
    @Override
    public TrybDTO zapiszTryb(TrybDTO aTrybDTO) throws MyServerException {
        if(aTrybDTO == null){
            throw new MyServerException("Brak pola tryb",HttpStatus.NOT_FOUND,new HttpHeaders());
        }

        //sprawdzam czy dany rekord z OB już istnieje

        TrybOB pTrybOB = aTrybDTO.getId() == null ? null : iTrybRepository.findOne(aTrybDTO.getId());
        if(pTrybOB == null){//gdy nie ma takiego to zapisz
            TrybOB pTrybOBNameVeryfication = aTrybDTO.getNazwaTrybu() == null ? null : iTrybRepository.znajdzPoNazwieTrybu(aTrybDTO.getNazwaTrybu());
            if(pTrybOBNameVeryfication != null) throw new MyServerException("Juz jest taki nazwa trybu",HttpStatus.METHOD_NOT_ALLOWED,new HttpHeaders()); //nie można stworzyć ponieważ już jest taki eamil;
            aTrybDTO = TrybConventer.trybOBdotrybDTO(iTrybRepository.save(TrybConventer.trybDTOdoTrybOB(aTrybDTO)));//zapisuje
            pTrybOB = TrybConventer.trybDTOdoTrybOB(aTrybDTO);//stwórz instancje do przypisania do dziennika
            //przepisz wiadomo

            //tworzenia pięciu standardowych dzienników planów



            return aTrybDTO;
        }
        //edytuj istniejącego
        pTrybOB.setImie(aTrybDTO.getNazwaTrybu());

        return TrybConventer.trybOBdotrybDTO(iTrybRepository.save(pTrybOB));
    }

}
