package com.danielcudnik.pytania.api;


import com.danielcudnik.pytania.dto.PytaniaDTO;
import com.danielcudnik.pytania.dto.PytaniaZapiszDTO;
import com.danielcudnik.pytania.service.IPytaniaService;
import com.danielcudnik.utils.MyServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/quizAndroid/pytania")
public class PytaniaController {

    @Autowired
    IPytaniaService serwisPytania;

    @RequestMapping(value = "pobierzPoKategorii/{kategorie.id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PytaniaDTO>> znajdzPytaniaPoKategorii(@PathVariable("kategorie.id") Long aIdKategorii) {
        return new ResponseEntity<>(serwisPytania.znajdzPytaniaPoKategorii(aIdKategorii), HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoNazwieKategorii/{kategorie.nazwa}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PytaniaDTO>> znajdzPytaniaPoNazwieKategorii(@PathVariable("kategorie.nazwa") String aNazwaKategorii) {
        return new ResponseEntity<>(serwisPytania.znajdzPytaniaPoNazwieKategorii(aNazwaKategorii), HttpStatus.OK);
    }

    @RequestMapping(value = "/zapiszPytanie",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<PytaniaDTO> zapiszPytanie(@RequestBody PytaniaDTO aPunktyDTO){
        try {
            return new ResponseEntity<>(serwisPytania.zapiszPytania(aPunktyDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/zapiszPytanie2",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<PytaniaDTO> zapiszPytanie2(@RequestBody PytaniaZapiszDTO aPunktyDTO){
        try {
            return new ResponseEntity<>(serwisPytania.zapiszPytania2(aPunktyDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "usunPoId/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> usunPytanie(@PathVariable("id")Long aId){
        serwisPytania.usunPytania(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "losujPoKategorii/{kategorie.id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PytaniaDTO>> losujPytaniaPoKategorii(@PathVariable("kategorie.id") Long aIdKategorii) {
        return new ResponseEntity<>(serwisPytania.losujPytaniaPoKategorii(aIdKategorii), HttpStatus.OK);
    }
}
