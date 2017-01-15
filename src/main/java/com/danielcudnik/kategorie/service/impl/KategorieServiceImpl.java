package com.danielcudnik.kategorie.service.impl;

import com.danielcudnik.kategorie.dto.KategorieDTO;
import com.danielcudnik.kategorie.ob.KategorieOB;
import com.danielcudnik.kategorie.repository.IKategorieRepository;
import com.danielcudnik.kategorie.service.IKategorieService;
import com.danielcudnik.odpowiedzi.ob.OdpowiedziOB;
import com.danielcudnik.odpowiedzi.repository.IOdpowiedziRepository;
import com.danielcudnik.pytania.ob.PytaniaOB;
import com.danielcudnik.pytania.repository.IPytaniaRepository;
import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.utils.converters.KategorieConventer;
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
public class KategorieServiceImpl implements IKategorieService{
    @Autowired
    IKategorieRepository iKategorieRepository;

    @Autowired
    IPytaniaRepository iPytaniaRepository;

    @Autowired
    IOdpowiedziRepository iOdpowiedziRepository;

    @Override
    public KategorieDTO znajdzKategoriePoNazwie(String aNazwaKategorii) throws  MyServerException {
        KategorieOB pKategorieOB = iKategorieRepository.znajdzPoNazwieKategorii(aNazwaKategorii);
        if(pKategorieOB == null) throw new MyServerException("Nie ma takiej kategorii", HttpStatus.NOT_FOUND,new HttpHeaders());
        return KategorieConventer.kategorieOBdokategorieDTO(pKategorieOB);
    }
    @Override
    public List<KategorieDTO> znajdzWszystkieKategorie() {
        List<KategorieDTO> listaWynikowaKategorieDTO = new ArrayList<>();
        List<KategorieOB> listaKategorieOB = iKategorieRepository.findAll();
        for(KategorieOB kategorieOB : listaKategorieOB) listaWynikowaKategorieDTO.add(KategorieConventer.kategorieOBdokategorieDTO(kategorieOB));

        return listaWynikowaKategorieDTO;
    }
    @Override
    public KategorieDTO znajdzKategoriePoId(Long aId) throws MyServerException {
        KategorieOB pKategorieOB = iKategorieRepository.findOne(aId);
        if(pKategorieOB == null) throw new MyServerException("Nie ma takiej kategorii", HttpStatus.NOT_FOUND,new HttpHeaders());
        return KategorieConventer.kategorieOBdokategorieDTO(pKategorieOB);
    }
    @Override
    public KategorieDTO zapiszKategorie(KategorieDTO aKategorieDTO) throws MyServerException {
        if(aKategorieDTO == null){
            throw new MyServerException("Brak pola kategorie",HttpStatus.NOT_FOUND,new HttpHeaders());
        }
        KategorieOB pKategorieOB = aKategorieDTO.getId() == null ? null : iKategorieRepository.findOne(aKategorieDTO.getId());
        if(pKategorieOB == null){//gdy nie ma takiego to zapisz
            KategorieOB pKategorieOBNameVeryfication = aKategorieDTO.getNazwa() == null ? null : iKategorieRepository.znajdzPoNazwieKategorii(aKategorieDTO.getNazwa());
            if(pKategorieOBNameVeryfication != null) throw new MyServerException("Juz jest taki nazwa trybu",HttpStatus.METHOD_NOT_ALLOWED,new HttpHeaders()); //nie można stworzyć ponieważ już jest taki eamil;
            aKategorieDTO = KategorieConventer.kategorieOBdokategorieDTO(iKategorieRepository.save(KategorieConventer.kategorieDTOdoKategorieOB(aKategorieDTO)));//zapisuje
            pKategorieOB = KategorieConventer.kategorieDTOdoKategorieOB(aKategorieDTO);//stwórz instancje do przypisania do dziennika

            return aKategorieDTO;
        }
        //edytuj istniejącego
        pKategorieOB.setNazwa(aKategorieDTO.getNazwa());

        return KategorieConventer.kategorieOBdokategorieDTO(iKategorieRepository.save(pKategorieOB));
    }
    @Override
    public void usunKategorie(String aNazwaKategorii) throws  MyServerException{
        KategorieOB kategorieOB = iKategorieRepository.znajdzPoNazwieKategorii(aNazwaKategorii);
        if(kategorieOB == null) throw new MyServerException("Nie znaleziono kategorii",HttpStatus.NOT_FOUND,new HttpHeaders());
        List<PytaniaOB> pytania = iPytaniaRepository.znajdzPytaniaPoKategorii(kategorieOB.getId());
        if (!pytania.isEmpty())
            for (PytaniaOB pytanie:pytania) {
                List<OdpowiedziOB> odpowiedziOB = iOdpowiedziRepository.znajdzPunktyPoPytaniu(pytanie.getId());
                for (OdpowiedziOB odpowiedz:odpowiedziOB) {
                    iOdpowiedziRepository.delete(odpowiedz.getId());
                }
                iPytaniaRepository.delete(pytanie.getId());
            }
        iKategorieRepository.delete(kategorieOB.getId());
    }
}
