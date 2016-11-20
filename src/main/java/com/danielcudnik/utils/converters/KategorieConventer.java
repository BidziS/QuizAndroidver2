package com.danielcudnik.utils.converters;



import com.danielcudnik.kategorie.dto.KategorieDTO;
import com.danielcudnik.kategorie.ob.KategorieOB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public class KategorieConventer {
    public static KategorieDTO kategorieOBdokategorieDTO(KategorieOB aKategorieOB){
        if(aKategorieOB ==null) return null;
        return new KategorieDTO(aKategorieOB.getId(),aKategorieOB.getTechDate(),aKategorieOB.getNazwa());
    }
    public static KategorieOB kategorieDTOdoKategorieOB(KategorieDTO aKategorieDTO){
        if(aKategorieDTO == null) return null;
        KategorieOB pKategorieOB = new KategorieOB(aKategorieDTO.getNazwa());
        pKategorieOB.setId(aKategorieDTO.getId());
        pKategorieOB.setTechDate(aKategorieDTO.getTechDate());
        return pKategorieOB;
    }
    public static List<KategorieDTO> listKategorieOBdoKategorieDTO(List<KategorieOB> aListaKategorieOB)
    {
        List<KategorieDTO> pListaKategorieDTO = new ArrayList<>();
        for(KategorieOB tryb : aListaKategorieOB)
        {
            pListaKategorieDTO.add(kategorieOBdokategorieDTO(tryb));
        }
        return pListaKategorieDTO;
    }

    public static List<KategorieOB> listKategorieDTOdoKategorieOB(List<KategorieDTO> aListaKategorieDTO){
        List<KategorieOB> pListaKategorieOB = new ArrayList<>();
        for(KategorieDTO kategorieDTO : aListaKategorieDTO){
            pListaKategorieOB.add(kategorieDTOdoKategorieOB(kategorieDTO));
        }
        return pListaKategorieOB;
    }
}
