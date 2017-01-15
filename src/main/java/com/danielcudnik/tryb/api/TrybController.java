package com.danielcudnik.tryb.api;


import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.tryb.service.ITrybService;
import com.danielcudnik.utils.MyServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bidzis on 11/10/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/quizAndroid/tryby")
public class TrybController {
    @Autowired
    ITrybService serwisTrybu;

    @RequestMapping(value = "pobierzPoId/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TrybDTO> znajdzTrybPoId(@PathVariable("id") Long aId){
        try{
            return new ResponseEntity<>(serwisTrybu.znajdzTrybPoId(aId), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
    @RequestMapping(value = "/pobierzWszystkie",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TrybDTO>> znajdzWszystkieTryby(){
        return new ResponseEntity<>(serwisTrybu.znajdzWszystkieTryby(),HttpStatus.OK);
    }
    @RequestMapping(value = "pobierzPoNazwie/{nazwaTrybu}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TrybDTO> znajdzTrybPoNazwie(@PathVariable("nazwaTrybu") String aNazwa){
        try{
            return new ResponseEntity<>(serwisTrybu.znajdzTrybPoNazwie(aNazwa), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
    @RequestMapping(value = "/zapiszTryb",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<TrybDTO> zapiszTryb(@RequestBody TrybDTO aTrybDTO){
        try {
            return new ResponseEntity<>(serwisTrybu.zapiszTryb(aTrybDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }
}
