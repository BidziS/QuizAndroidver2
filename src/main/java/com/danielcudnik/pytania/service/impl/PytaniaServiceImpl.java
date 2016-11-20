package com.danielcudnik.pytania.service.impl;


import com.danielcudnik.kategorie.dto.KategorieDTO;
import com.danielcudnik.kategorie.ob.KategorieOB;
import com.danielcudnik.kategorie.repository.IKategorieRepository;
import com.danielcudnik.pytania.dto.PytaniaDTO;
import com.danielcudnik.pytania.ob.PytaniaOB;
import com.danielcudnik.pytania.repository.IPytaniaRepository;
import com.danielcudnik.pytania.service.IPytaniaService;
import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.utils.converters.PytaniaConventer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Service
@Transactional
public class PytaniaServiceImpl implements IPytaniaService {

    @Autowired
    IPytaniaRepository iPytaniaRepository;

    @Autowired
    IKategorieRepository iKategorieRepository;

    @Override
    public List<PytaniaDTO> znajdzPytaniaPoKategorii(Long aIdTryb){
        List<PytaniaDTO> listaWynikowaPytaniaDTO = new ArrayList<>();
        List<PytaniaOB> listaPytaniaOB = iPytaniaRepository.znajdzPunktyPoKategorii(aIdTryb);
        for(PytaniaOB punktyOB : listaPytaniaOB)
            listaWynikowaPytaniaDTO.add(PytaniaConventer.pytaniaOBdoPytaniaDTO(punktyOB));
        //Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));
        return listaWynikowaPytaniaDTO;
    }
    @Override
    public List<PytaniaDTO> znajdzPytaniaPoNazwieKategorii(String nazwaKategorii){
        List<PytaniaDTO> listaWynikowaPytaniaDTO = new ArrayList<>();
        List<PytaniaOB> listaPytaniaOB = iPytaniaRepository.znajdzPoNazwieKategorii(nazwaKategorii);
        for(PytaniaOB punktyOB : listaPytaniaOB)
            listaWynikowaPytaniaDTO.add(PytaniaConventer.pytaniaOBdoPytaniaDTO(punktyOB));
        //Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));
        return listaWynikowaPytaniaDTO;
    }
    @Override
    public PytaniaDTO zapiszPytania(PytaniaDTO aPytaniaDTO) throws MyServerException {

        KategorieDTO pKategorieDTO = aPytaniaDTO.getKategorieDTO();
        if (pKategorieDTO  == null)  throw new MyServerException("Nie znaleziono pola trybu",HttpStatus.NOT_FOUND,new HttpHeaders());
        KategorieOB pKategorieOB = pKategorieDTO.getId() == null ? null :
                iKategorieRepository.findOne(pKategorieDTO.getId());
        if(pKategorieOB == null)  throw new MyServerException("Nie znaleziono trybu",HttpStatus.NOT_FOUND,new HttpHeaders());

        PytaniaOB pPytaniaOB = aPytaniaDTO.getId() == null ? null :
                iPytaniaRepository.findOne(aPytaniaDTO.getId());
        if(pPytaniaOB == null) {//gdy nie ma takiego dziennika planów
            aPytaniaDTO.setTechDate(aPytaniaDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
            aPytaniaDTO.setPytanie(aPytaniaDTO.getPytanie()); //zmieniam dane!
            aPytaniaDTO.setKategorieDTO(pKategorieDTO);
            pPytaniaOB = PytaniaConventer.pytaniaDTOdoPytaniaOB(aPytaniaDTO);
        }
        pPytaniaOB.setTechDate(aPytaniaDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
        pPytaniaOB.setPytanie(aPytaniaDTO.getPytanie()); //zmieniam dane!
        pPytaniaOB.setKategorie(pKategorieOB);
        return PytaniaConventer.pytaniaOBdoPytaniaDTO(iPytaniaRepository.save(pPytaniaOB));
    }
    @Override
    public void usunPytania(Long aId){
        iPytaniaRepository.delete(aId);
    }
}
