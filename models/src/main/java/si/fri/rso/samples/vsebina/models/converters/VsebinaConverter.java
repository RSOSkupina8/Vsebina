package si.fri.rso.samples.vsebina.models.converters;

import si.fri.rso.samples.vsebina.lib.Vsebina;
import si.fri.rso.samples.vsebina.models.entities.VsebinaEntity;

public class VsebinaConverter {

    public static Vsebina toDto(VsebinaEntity entity) {

        Vsebina dto = new Vsebina();
        dto.setVsebinaId(entity.getId());
        dto.setKosaricaId(entity.getKosaricaId());
        dto.setArtikelId(entity.getArtikelId());

        return dto;

    }

    public static VsebinaEntity toEntity(Vsebina dto) {

        VsebinaEntity entity = new VsebinaEntity();
        entity.setKosaricaId(dto.getKosaricaId());
        entity.setArtikelId(dto.getArtikelId());

        return entity;

    }

}
