package com.danielcudnik.kategorie.api;


import com.danielcudnik.kategorie.dto.KategorieDTO;
import com.danielcudnik.kategorie.service.IKategorieService;
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
@RequestMapping(value = "/quizAndroid/kategorie")
public class KategorieController {
    @Autowired
    IKategorieService serwisKategorie;

    @RequestMapping(value = "pobierzPoId/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<KategorieDTO> znajdzKategoriePoId(@PathVariable("id") Long aId){
        try{
            return new ResponseEntity<>(serwisKategorie.znajdzKategoriePoId(aId), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/pobierzWszystkie",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<KategorieDTO>> znajdzWszystkieKategoriey(){
        return new ResponseEntity<>(serwisKategorie.znajdzWszystkieKategorie(),HttpStatus.OK);
    }
    @RequestMapping(value = "pobierzPoNazwie/{nazwaKategorii}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<KategorieDTO> znajdzKategoriePoNazwie(@PathVariable("nazwaKategorii") String aNazwa){
        try{
            return new ResponseEntity<>(serwisKategorie.znajdzKategoriePoNazwie(aNazwa), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
    @RequestMapping(value = "/zapiszKategorie",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<KategorieDTO> zapiszTryb(@RequestBody KategorieDTO aKategorieDTO){
        try {
            return new ResponseEntity<>(serwisKategorie.zapiszKategorie(aKategorieDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
}
