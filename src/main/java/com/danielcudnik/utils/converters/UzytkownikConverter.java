package com.danielcudnik.utils.converters;



import com.danielcudnik.uzytkownik.dto.UzytkownikDTO;
import com.danielcudnik.uzytkownik.ob.UzytkownikOB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
public class UzytkownikConverter {
    public static UzytkownikDTO uzytOBdoUzytkDTO(UzytkownikOB aUzytkownikOB){
        if(aUzytkownikOB ==null) return null;
        return new UzytkownikDTO(aUzytkownikOB.getId(),aUzytkownikOB.getTechDate(),aUzytkownikOB.getNick(),aUzytkownikOB.getHaslo());
    }

    public static UzytkownikOB uzytDTOdoUzytkOB(UzytkownikDTO aUzytkownikDTO){
        if(aUzytkownikDTO == null) return null;
        UzytkownikOB pUzytkownikOB = new UzytkownikOB(aUzytkownikDTO.getNick(),aUzytkownikDTO.getHaslo());
        pUzytkownikOB.setId(aUzytkownikDTO.getId());
        pUzytkownikOB.setTechDate(aUzytkownikDTO.getTechDate());
        return pUzytkownikOB;
    }

    public static List<UzytkownikDTO> listUzytkOBdoUzytkDTO(List<UzytkownikOB> aListaUzytkownikowOB)
    {
        List<UzytkownikDTO> pListaUzytkownikowDTO = new ArrayList<>();
        for(UzytkownikOB uzytkownik : aListaUzytkownikowOB)
        {
            pListaUzytkownikowDTO.add(uzytOBdoUzytkDTO(uzytkownik));
        }
        return pListaUzytkownikowDTO;
    }

    public static List<UzytkownikOB> listUzytkDTOdoUzytkOB(List<UzytkownikDTO> aListaUzytkownikowDTO){
        List<UzytkownikOB> pListaUzytkownikowOB = new ArrayList<>();
        for(UzytkownikDTO uzytkownikDTO : aListaUzytkownikowDTO){
            pListaUzytkownikowOB.add(uzytDTOdoUzytkOB(uzytkownikDTO));
        }
        return pListaUzytkownikowOB;
    }
}
