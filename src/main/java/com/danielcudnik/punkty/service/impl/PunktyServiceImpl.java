package com.danielcudnik.punkty.service.impl;


import com.danielcudnik.punkty.dto.PunktyDTO;
import com.danielcudnik.punkty.dto.PunktyZapiszDTO;
import com.danielcudnik.punkty.ob.PunktyOB;
import com.danielcudnik.punkty.repository.IPunktyRepository;
import com.danielcudnik.punkty.service.IPunktyService;
import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.tryb.ob.TrybOB;
import com.danielcudnik.tryb.repository.ITrybRepository;
import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.utils.converters.PunktyConventer;
import com.danielcudnik.utils.converters.TrybConventer;
import com.danielcudnik.utils.converters.UzytkownikConverter;
import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import com.danielcudnik.uzytkownik.ob.UzytkownikOB;
import com.danielcudnik.uzytkownik.repository.IUzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bidzis on 11/11/2016.
 */
@Service
@Transactional
public class PunktyServiceImpl implements IPunktyService {

    @Autowired
    IPunktyRepository iPunktyRepository;

    @Autowired
    IUzytkownikRepository iUzytkownikRepository;

    @Autowired
    ITrybRepository iTrybRepository;

    @Override
    public List<PunktyDTO> znajdzPunktyPoTrybie(Long aIdTryb){
        List<PunktyDTO> listaWynikowaPunktyDTO = new ArrayList<>();
        List<PunktyOB> listaPunktyOB = iPunktyRepository.znajdzPunktyPoTrybie(aIdTryb);
        for(PunktyOB punktyOB : listaPunktyOB)
            listaWynikowaPunktyDTO.add(PunktyConventer.punktyOBdoPunktyDTO(punktyOB));
        Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));//musiałem podnieść do java 1.8
        return listaWynikowaPunktyDTO;
    }
    @Override
    public List<PunktyDTO> znajdzPunktyPoUzytkownikuITrybie(Long aNick, Long aTryb) {
        List<PunktyOB> punkty = iPunktyRepository.znajdzPunktyPoUzytkownikuITrybie(aNick,aTryb);
        List<PunktyDTO> listaWynikowaPunktyDTO = PunktyConventer.listPunktyOBdoPunktyDTO(punkty);
        Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));//musiałem podnieść do java 1.8
        return listaWynikowaPunktyDTO;
    }

    @Override
    public List<PunktyZapiszDTO> znajdzPunktyPoTrybie2(Long aIdTryb){
        List<PunktyZapiszDTO> listaWynikowaPunktyDTO = new ArrayList<>();
        List<PunktyOB> listaPunktyOB = iPunktyRepository.znajdzPunktyPoTrybie(aIdTryb);
        for(PunktyOB punktyOB : listaPunktyOB)
            listaWynikowaPunktyDTO.add(PunktyConventer.punktyOBdoPunktyZapiszDTO(punktyOB));
        Collections.sort(listaWynikowaPunktyDTO, (PunktyZapiszDTO a, PunktyZapiszDTO b) -> b.getPunkty().compareTo(a.getPunkty()));//musiałem podnieść do java 1.8
        return listaWynikowaPunktyDTO;
    }

    @Override
    public List<PunktyDTO> znajdzPunktyPoUzytkowniku(Long aIdUzytkownik){
        List<PunktyDTO> listaWynikowaPunktyDTO = new ArrayList<>();
        List<PunktyOB> listaPunktyOB = iPunktyRepository.znajdzPunktyPoUzytkowniku(aIdUzytkownik);
        for(PunktyOB punktyOB : listaPunktyOB)
            listaWynikowaPunktyDTO.add(PunktyConventer.punktyOBdoPunktyDTO(punktyOB));
        Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));//musiałem podnieść do java 1.8
        return listaWynikowaPunktyDTO;

    }
    @Override
    public PunktyDTO zapiszPunkty(PunktyDTO aPunktyDTO) throws MyServerException{
        UzytkownikDTO pUzytkownikDTO = aPunktyDTO.getUzytkownicy();
        if (pUzytkownikDTO == null)  throw new MyServerException("Nie znaleziono pola uzytkownika",HttpStatus.NOT_FOUND,new HttpHeaders());
        UzytkownikOB pUzytkownikOB = pUzytkownikDTO.getId() == null ? null :
                iUzytkownikRepository.findOne(pUzytkownikDTO.getId());
        if(pUzytkownikOB == null)  throw new MyServerException("Nie znaleziono uzytkownika",HttpStatus.NOT_FOUND,new HttpHeaders()); //gdy nie istnieje użytkownik nie ma sensu przechodzić dalej!

        TrybDTO pTrybDTO = aPunktyDTO.getTryb();
        if (pTrybDTO  == null)  throw new MyServerException("Nie znaleziono pola trybu",HttpStatus.NOT_FOUND,new HttpHeaders());
        TrybOB pTrybOB = pTrybDTO.getId() == null ? null :
                iTrybRepository.findOne(pTrybDTO.getId());
        if(pTrybOB == null)  throw new MyServerException("Nie znaleziono trybu",HttpStatus.NOT_FOUND,new HttpHeaders());

        PunktyOB pPunktyOB = aPunktyDTO.getId() == null ? null :
                iPunktyRepository.findOne(aPunktyDTO.getId());
        if(pPunktyOB == null) {//gdy nie ma takiego dziennika planów
            aPunktyDTO.setTechDate(aPunktyDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
            aPunktyDTO.setPunkty(aPunktyDTO.getPunkty()); //zmieniam dane!
            aPunktyDTO.setTryb(pTrybDTO);
            aPunktyDTO.setUzytkownicy(pUzytkownikDTO);
            pPunktyOB = PunktyConventer.punktyDTOdoPunktyOB(aPunktyDTO);
        }
        pPunktyOB.setTechDate(aPunktyDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
        pPunktyOB.setPunkty(aPunktyDTO.getPunkty()); //zmieniam dane!
        pPunktyOB.setTryb(pTrybOB);
        pPunktyOB.setUzytkownicy(pUzytkownikOB);
        return PunktyConventer.punktyOBdoPunktyDTO(iPunktyRepository.save(pPunktyOB));
    }

    public PunktyDTO zapiszPunkty2(PunktyZapiszDTO aPunktyDTO) throws MyServerException{
        Long pUzytkownikDTO = aPunktyDTO.getUzytkownikid();
        if (iUzytkownikRepository.findOne(pUzytkownikDTO) == null)  throw new MyServerException("Nie znaleziono pola uzytkownika",HttpStatus.NOT_FOUND,new HttpHeaders());
        UzytkownikOB pUzytkownikOB = iUzytkownikRepository.findOne(pUzytkownikDTO);
        if(pUzytkownikOB == null)  throw new MyServerException("Nie znaleziono uzytkownika",HttpStatus.NOT_FOUND,new HttpHeaders()); //gdy nie istnieje użytkownik nie ma sensu przechodzić dalej!

        Long pTrybDTO = aPunktyDTO.getTrybid();
        if (iTrybRepository.findOne(pTrybDTO)  == null)  throw new MyServerException("Nie znaleziono pola trybu",HttpStatus.NOT_FOUND,new HttpHeaders());
        TrybOB pTrybOB = iTrybRepository.findOne(pTrybDTO);
        if(pTrybOB == null)  throw new MyServerException("Nie znaleziono trybu",HttpStatus.NOT_FOUND,new HttpHeaders());

        PunktyDTO bPunktyDTO = new PunktyDTO();
        PunktyOB pPunktyOB = aPunktyDTO.getId() == null ? null :
                iPunktyRepository.findOne(aPunktyDTO.getId());
        if(pPunktyOB == null) {
            bPunktyDTO.setTechDate(aPunktyDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
            bPunktyDTO.setPunkty(aPunktyDTO.getPunkty()); //zmieniam dane!
            bPunktyDTO.setTryb(TrybConventer.trybOBdotrybDTO(pTrybOB));
            bPunktyDTO.setUzytkownicy(UzytkownikConverter.uzytOBdoUzytkDTO(pUzytkownikOB));
            pPunktyOB = PunktyConventer.punktyDTOdoPunktyOB(bPunktyDTO);
            return PunktyConventer.punktyOBdoPunktyDTO(iPunktyRepository.save(pPunktyOB));
        }
        pPunktyOB.setTechDate(aPunktyDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
        pPunktyOB.setPunkty(aPunktyDTO.getPunkty()); //zmieniam dane!
        pPunktyOB.setTryb(pTrybOB);
        pPunktyOB.setUzytkownicy(pUzytkownikOB);
        return PunktyConventer.punktyOBdoPunktyDTO(iPunktyRepository.save(pPunktyOB));
    }
    @Override
    public void usunPunkty(Long aId){
        iPunktyRepository.delete(aId);
    }

}
