package com.danielcudnik.uzytkownik.api;


import com.danielcudnik.utils.MyServerException;
import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import com.danielcudnik.uzytkownik.dto.UzytkownikLogowanieDTO;
import com.danielcudnik.uzytkownik.service.IUzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
@CrossOrigin
@SpringBootApplication
@RestController
@RequestMapping(value = "/quizAndroid/uzytkownicy")
public class UzytkownikController extends SpringBootServletInitializer implements WebApplicationInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UzytkownikController.class);
    }

    @Autowired
    IUzytkownikService serwisUzytkownika;

    @RequestMapping(value = "/pobierzPoId/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> znajdzUzytkownikaPoId(@PathVariable("id") Long aId){
        try{
            return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikaPoId(aId), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/pobierzWszystkich",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzWszystkich(){
        return new ResponseEntity<>(serwisUzytkownika.znajdzWszystkichUzytkownikow(),HttpStatus.OK);
    }
    @RequestMapping(value = "/pobierzPoNickuId/{nick}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> znajdzUzytkownikaPoNicku(@PathVariable("nick") String aNick){
        try{
            return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikaPoNicku(aNick), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
    @RequestMapping(value = "/zapiszUzytkownika",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> zapiszUzytkownika(@RequestBody UzytkownikDTO aUzytkownikDTO){
        try {
            return new ResponseEntity<>(serwisUzytkownika.zapiszUzytkownika(aUzytkownikDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value="/uzytkownikLogowanie",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> logowanieUzytkownika(@RequestBody UzytkownikLogowanieDTO aUserDTO){
        try{
            return new ResponseEntity<>(serwisUzytkownika.logowanieUzytkownika(aUserDTO),HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value="/usunPoNicku/{nick}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> deleteUser(@PathVariable("nick") String aNick){
        try {
            serwisUzytkownika.usunUzytkownika(aNick);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
}
