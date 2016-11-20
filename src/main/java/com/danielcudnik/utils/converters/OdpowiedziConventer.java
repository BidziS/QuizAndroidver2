package com.danielcudnik.utils.converters;



import com.danielcudnik.odpowiedzi.dto.OdpowiedziDTO;
import com.danielcudnik.odpowiedzi.ob.OdpowiedziOB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
public class OdpowiedziConventer {
    public static OdpowiedziDTO odpowiedziOBdoOdpowiedziDTO(OdpowiedziOB aOdpowiedziOB){
        if(aOdpowiedziOB == null) return null;
        return new OdpowiedziDTO(aOdpowiedziOB.getId(),aOdpowiedziOB.getTechDate(),aOdpowiedziOB.getOdpowiedz(),aOdpowiedziOB.getPoprawna(),PytaniaConventer.pytaniaOBdoPytaniaDTO(aOdpowiedziOB.getPytania()));
    }

    public static OdpowiedziOB odpowiedziDTOdoOdpowiedziOB(OdpowiedziDTO aOdpowiedziDTO){
        if(aOdpowiedziDTO == null) return null;


        return new OdpowiedziOB(aOdpowiedziDTO.getOdpowiedz(),aOdpowiedziDTO.getPoprawna(),PytaniaConventer.pytaniaDTOdoPytaniaOB(aOdpowiedziDTO.getPytania()));
    }

    public static List<OdpowiedziDTO> listOdpowiedziOBdoOdpowiedziDTO(List<OdpowiedziOB> aListaOdpowiedziOB)
    {
        List<OdpowiedziDTO> pListaOdpowiedziDTO = new ArrayList<>();
        for(OdpowiedziOB odpowiedziOB : aListaOdpowiedziOB)
        {
            pListaOdpowiedziDTO.add(odpowiedziOBdoOdpowiedziDTO(odpowiedziOB));
        }
        return pListaOdpowiedziDTO;
    }

    public static List<OdpowiedziOB> listOdpowiedziDTOdoOdpowiedziOB(List<OdpowiedziDTO> aListaOdpowiedziDTO) {
        List<OdpowiedziOB> pListaOdpowiedziOB = new ArrayList<>();
        for (OdpowiedziDTO pytaniaDTO : aListaOdpowiedziDTO) {
            pListaOdpowiedziOB.add(odpowiedziDTOdoOdpowiedziOB(pytaniaDTO));
        }
        return pListaOdpowiedziOB;
    }
}
