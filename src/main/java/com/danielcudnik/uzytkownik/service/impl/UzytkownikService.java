package com.danielcudnik.uzytkownik.service.impl;


import com.danielcudnik.punkty.ob.PunktyOB;
import com.danielcudnik.punkty.repository.IPunktyRepository;
import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.utils.converters.UzytkownikConverter;
import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import com.danielcudnik.uzytkownik.dto.UzytkownikLogowanieDTO;
import com.danielcudnik.uzytkownik.ob.UzytkownikOB;
import com.danielcudnik.uzytkownik.repository.IUzytkownikRepository;
import com.danielcudnik.uzytkownik.service.IUzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
@Service
@Transactional
public class UzytkownikService implements IUzytkownikService {

    @Autowired
    IUzytkownikRepository iUzytkownikRepository;

    @Autowired
    IPunktyRepository iPunktyRepository;

    @Override
    public UzytkownikDTO znajdzUzytkownikaPoId(Long aId) throws MyServerException {
        UzytkownikOB pUzytkownikOB = iUzytkownikRepository.findOne(aId);//znajdź po ID, i zwróc instancje obiektu UzytkownikOB
        if(pUzytkownikOB == null) throw new MyServerException("Nie ma takiego uzytkownika", HttpStatus.NOT_FOUND,new HttpHeaders()); //jeżeli nic nie znajdziesz, to oznacza null (wartość domyślną) to zwróc tego nulla
        return UzytkownikConverter.uzytOBdoUzytkDTO(pUzytkownikOB);//muszę przekształcić dany typ obiektu OB na DTO by potem móc go wyświetlić albo robić z nim cokolwiek innego
    }

    @Override
    public UzytkownikDTO znajdzUzytkownikaPoNicku(String aNick) throws MyServerException {
        UzytkownikOB pUzytkownikOB = iUzytkownikRepository.znajdzPoNicku(aNick);//znajdź po ID, i zwróc instancje obiektu UzytkownikOB
        if(pUzytkownikOB == null) throw new MyServerException("Nie ma takiego uzytkownika",HttpStatus.NOT_FOUND,new HttpHeaders()); //jeżeli nic nie znajdziesz, to oznacza null (wartość domyślną) to zwróc tego nulla
        return UzytkownikConverter.uzytOBdoUzytkDTO(pUzytkownikOB);//muszę przekształcić dany typ obiektu OB na DTO by potem móc go wyświetlić albo robić z nim cokolwiek innego
    }

    @Override
    public List<UzytkownikDTO> znajdzWszystkichUzytkownikow() {
        List<UzytkownikDTO> listaWynikowaUzytkownikowDTO = new ArrayList<>();//utworzenie pojemnika
        List<UzytkownikOB> listaUzytkownikowOB = iUzytkownikRepository.findAll();//zwróc mi wszystkich użytkowników
        //przepisanie moich użytkowników
        for(UzytkownikOB uzytkownik : listaUzytkownikowOB) listaWynikowaUzytkownikowDTO.add(UzytkownikConverter.uzytOBdoUzytkDTO(uzytkownik)); //zmień każdą instancję UzytkownikOB do instancji DTO

        return listaWynikowaUzytkownikowDTO;//działa
    }
    @Override
    public UzytkownikDTO zapiszUzytkownika(UzytkownikDTO aUzytkownikDTO) throws MyServerException {
        if(aUzytkownikDTO == null){
            throw new MyServerException("Brak pola uzytkownik",HttpStatus.NOT_FOUND,new HttpHeaders());
        }

        //sprawdzam czy dany rekord z OB już istnieje

        UzytkownikOB pUzytkownikOB = aUzytkownikDTO.getId() == null ? null : iUzytkownikRepository.findOne(aUzytkownikDTO.getId());
        if(pUzytkownikOB == null){//gdy nie ma takiego to zapisz
            UzytkownikOB pUzytkonikOBEmailVeryfication = aUzytkownikDTO.getNick() == null ? null : iUzytkownikRepository.znajdzPoNicku(aUzytkownikDTO.getNick());
            if(pUzytkonikOBEmailVeryfication != null) throw new MyServerException("Juz jest taki mail",HttpStatus.METHOD_NOT_ALLOWED,new HttpHeaders()); //nie można stworzyć ponieważ już jest taki eamil;
            aUzytkownikDTO = UzytkownikConverter.uzytOBdoUzytkDTO(iUzytkownikRepository.save(UzytkownikConverter.uzytDTOdoUzytkOB(aUzytkownikDTO)));//zapisuje
            pUzytkownikOB = UzytkownikConverter.uzytDTOdoUzytkOB(aUzytkownikDTO);//stwórz instancje do przypisania do dziennika
            //przepisz wiadomo

            //tworzenia pięciu standardowych dzienników planów

            return aUzytkownikDTO;
        }
        //edytuj istniejącego
        pUzytkownikOB.setNick(aUzytkownikDTO.getNick());
        pUzytkownikOB.setHaslo(aUzytkownikDTO.getHaslo());

        return UzytkownikConverter.uzytOBdoUzytkDTO(iUzytkownikRepository.save(pUzytkownikOB));
    }
    @Override
    public UzytkownikDTO logowanieUzytkownika(UzytkownikLogowanieDTO aUserDTO) throws MyServerException{
        UzytkownikOB user = aUserDTO.getNick() == null ? null : iUzytkownikRepository.znajdzPoNicku(aUserDTO.getNick());
        if(user == null) throw new MyServerException("User not found",HttpStatus.NOT_FOUND, new HttpHeaders());
        if(aUserDTO.getHaslo() == null) throw new  MyServerException("Password not found",HttpStatus.NOT_FOUND,new HttpHeaders());
        if(user.getHaslo().hashCode() != aUserDTO.getHaslo().hashCode()) throw new MyServerException("Password dont match",HttpStatus.METHOD_NOT_ALLOWED,new HttpHeaders());
        return UzytkownikConverter.uzytOBdoUzytkDTO(user);
    }
    @Override
    public void usunUzytkownika(String aNick) throws  MyServerException{
        UzytkownikOB uzytkownikOB = iUzytkownikRepository.znajdzPoNicku(aNick);
        if(uzytkownikOB == null) throw new MyServerException("User with this id not exists",HttpStatus.NOT_FOUND,new HttpHeaders());
        List<PunktyOB> punktyUzytkownika = iPunktyRepository.znajdzPunktyPoUzytkowniku(uzytkownikOB.getId());
        if (!punktyUzytkownika.isEmpty())
            for (PunktyOB punkty:punktyUzytkownika) {
               iPunktyRepository.delete(punkty.getId());
            }
        iUzytkownikRepository.delete(uzytkownikOB.getId());
    }


}
