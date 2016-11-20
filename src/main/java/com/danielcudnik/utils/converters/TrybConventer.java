package com.danielcudnik.utils.converters;



import com.danielcudnik.tryb.dto.TrybDTO;
import com.danielcudnik.tryb.ob.TrybOB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/10/2016.
 */
public class TrybConventer {
    public static TrybDTO trybOBdotrybDTO(TrybOB aTrybOB){
        if(aTrybOB ==null) return null;
        return new TrybDTO(aTrybOB.getId(),aTrybOB.getTechDate(),aTrybOB.getNazwaTrybu());
    }

    public static TrybOB trybDTOdoTrybOB(TrybDTO aTrybDTO){
        if(aTrybDTO == null) return null;
        TrybOB pTrybOB = new TrybOB(aTrybDTO.getNazwaTrybu());
        pTrybOB.setId(aTrybDTO.getId());
        pTrybOB.setTechDate(aTrybDTO.getTechDate());
        return pTrybOB;
    }

    public static List<TrybDTO> listTrybowOBdoTrybowDTO(List<TrybOB> aListaTrybowOB)
    {
        List<TrybDTO> pListaTrybowDTO = new ArrayList<>();
        for(TrybOB tryb : aListaTrybowOB)
        {
            pListaTrybowDTO.add(trybOBdotrybDTO(tryb));
        }
        return pListaTrybowDTO;
    }

    public static List<TrybOB> listTrybowDTOdoTrybowOB(List<TrybDTO> aListaTrybowDTO){
        List<TrybOB> pListaTrybowOB = new ArrayList<>();
        for(TrybDTO trybDTO : aListaTrybowDTO){
            pListaTrybowOB.add(trybDTOdoTrybOB(trybDTO));
        }
        return pListaTrybowOB;
    }
}
