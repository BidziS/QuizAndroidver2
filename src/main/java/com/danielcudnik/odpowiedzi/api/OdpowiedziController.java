package com.danielcudnik.odpowiedzi.api;


import com.danielcudnik.odpowiedzi.EPoprawna;
import com.danielcudnik.odpowiedzi.dto.OdpowiedziDTO;
import com.danielcudnik.odpowiedzi.dto.OdpowiedziZapiszDTO;
import com.danielcudnik.odpowiedzi.service.IOdpowiedziService;
import com.danielcudnik.utils.MyServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/quizAndroid/odpowiedzi")
public class OdpowiedziController {

    @Autowired
    IOdpowiedziService serwisOdpowiedzi;

    @RequestMapping(value = "pobierzPoPytaniu/{pytania.id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<OdpowiedziDTO>> znajdzPytaniaPoKategorii(@PathVariable("pytania.id") Long aIdPytania) {
        return new ResponseEntity<>(serwisOdpowiedzi.znajdzOdpowiedziPoPytaniu(aIdPytania), HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoPoprawnosci/{poprawnosc}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<OdpowiedziDTO>> znajdzOdpowiedziPoPoprawnosci(@PathVariable("poprawnosc")boolean aPoprawna){
        return new ResponseEntity<>(serwisOdpowiedzi.znajdzOdpowiedziPoPoprawnosci(aPoprawna),HttpStatus.OK);
    }

    @RequestMapping(value = "/zapiszOdpowiedz",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<OdpowiedziDTO> zapiszOdpowiedz(@RequestBody OdpowiedziDTO aOdpowiedziDTO){
        try {
            return new ResponseEntity<>(serwisOdpowiedzi.zapiszOdpowiedz(aOdpowiedziDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/zapiszOdpowiedz2",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<OdpowiedziDTO> zapiszOdpowiedz2(@RequestBody OdpowiedziZapiszDTO aOdpowiedziDTO){
        try {
            return new ResponseEntity<>(serwisOdpowiedzi.zapiszOdpowiedz2(aOdpowiedziDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/edytujOdpowiedz",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<OdpowiedziDTO> edytytujOdpowiedz(@RequestBody OdpowiedziZapiszDTO aOdpowiedziDTO){
        try {
            return new ResponseEntity<>(serwisOdpowiedzi.zapiszOdpowiedz2(aOdpowiedziDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "usunPoId/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> usunOdpowiedz(@PathVariable("id")Long aId){
        serwisOdpowiedzi.usunOdpowiedzi(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
