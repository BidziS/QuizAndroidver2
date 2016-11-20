package com.danielcudnik.utils.converters;



import com.danielcudnik.pytania.dto.PytaniaDTO;
import com.danielcudnik.pytania.ob.PytaniaOB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public class PytaniaConventer {
    public static PytaniaDTO pytaniaOBdoPytaniaDTO(PytaniaOB aPytaniaOB){
        if(aPytaniaOB == null) return null;
        return new PytaniaDTO(aPytaniaOB.getId(),aPytaniaOB.getTechDate(),aPytaniaOB.getPytanie(),KategorieConventer.kategorieOBdokategorieDTO(aPytaniaOB.getKategorie()));
    }

    public static PytaniaOB pytaniaDTOdoPytaniaOB(PytaniaDTO aPytaniaDTO){
        if(aPytaniaDTO == null) return null;


        return new PytaniaOB(aPytaniaDTO.getPytanie(),KategorieConventer.kategorieDTOdoKategorieOB(aPytaniaDTO.getKategorieDTO()));
    }

    public static List<PytaniaDTO> listPytaniaOBdoPytaniaDTO(List<PytaniaOB> aListaPytaniaOB)
    {
        List<PytaniaDTO> pListaPytaniaDTO = new ArrayList<>();
        for(PytaniaOB pytaniaOB : aListaPytaniaOB)
        {
            pListaPytaniaDTO.add(pytaniaOBdoPytaniaDTO(pytaniaOB));
        }
        return pListaPytaniaDTO;
    }

    public static List<PytaniaOB> listPytaniaDTOdoPytaniaOB(List<PytaniaDTO> aListaPytaniaDTO){
        List<PytaniaOB> pListaPytaniaOB = new ArrayList<>();
        for(PytaniaDTO pytaniaDTO : aListaPytaniaDTO){
            pListaPytaniaOB.add(pytaniaDTOdoPytaniaOB(pytaniaDTO));
        }
        return pListaPytaniaOB;
    }
}
