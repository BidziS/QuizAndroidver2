package com.danielcudnik.punkty.api;


import com.danielcudnik.punkty.dto.PunktyDTO;
import com.danielcudnik.punkty.service.IPunktyService;
import com.danielcudnik.utils.MyServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
@RestController
@RequestMapping(value = "/quizAndroid/punkty")
public class PunktyController {
    @Autowired
    IPunktyService serwisPunkty;


    @RequestMapping(value = "pobierzPoUzytkowniku/{uzytkownicy.id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PunktyDTO>> znajdzPunktyPoUzytkowniku(@PathVariable("uzytkownicy.id") Long aIdUzytkownika) {
        return new ResponseEntity<>(serwisPunkty.znajdzPunktyPoUzytkowniku(aIdUzytkownika), HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoTrybir/{tryb.id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PunktyDTO>> znajdzPunktyPoTrybie(@PathVariable("tryb.id") Long aIdTrybu) {
        return new ResponseEntity<>(serwisPunkty.znajdzPunktyPoTrybie(aIdTrybu), HttpStatus.OK);
    }

    @RequestMapping(value = "/zapiszPunkty",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<PunktyDTO> zapiszPunkty(@RequestBody PunktyDTO aPunktyDTO){
        try {
            return new ResponseEntity<>(serwisPunkty.zapiszPunkty(aPunktyDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "usunPoId/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> usunDziennikPlanow(@PathVariable("id")Long aId){
        serwisPunkty.usunPunkty(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
