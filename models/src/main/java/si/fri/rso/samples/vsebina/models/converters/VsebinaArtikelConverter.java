package si.fri.rso.samples.vsebina.models.converters;

import si.fri.rso.samples.vsebina.lib.Artikel;
import si.fri.rso.samples.vsebina.lib.Vsebina;
import si.fri.rso.samples.vsebina.lib.VsebinaArtikel;

public class VsebinaArtikelConverter {

    public static VsebinaArtikel toDto (Artikel artikel, Vsebina vsebina){
        VsebinaArtikel va = new VsebinaArtikel();
        va.setArtikelId(artikel.getArtikelId());
        va.setVsebinaId(vsebina.getVsebinaId());
        va.setKosaricaId(vsebina.getKosaricaId());
        va.setName(artikel.getName());
        va.setPrice(artikel.getPrice());
        va.setStore(artikel.getStore());
        return va;
    }
}
