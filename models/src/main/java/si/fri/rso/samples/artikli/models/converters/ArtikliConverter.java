package si.fri.rso.samples.artikli.models.converters;

import si.fri.rso.samples.artikli.lib.Artikli;
import si.fri.rso.samples.artikli.models.entities.ArtikliEntity;

public class ArtikliConverter {

    public static Artikli toDto(ArtikliEntity entity) {

        Artikli dto = new Artikli();
        dto.setArtikelId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setName(entity.getName());
        dto.setProduct(entity.getProduct());
        dto.setPrice(entity.getPrice());
        dto.setGross_weight(entity.getGross_weight());
        dto.setUri(entity.getUri());

        return dto;

    }

    public static ArtikliEntity toEntity(Artikli dto) {

        ArtikliEntity entity = new ArtikliEntity();
        entity.setCreated(dto.getCreated());
        entity.setName(dto.getName());
        entity.setProduct(dto.getProduct());
        entity.setPrice(dto.getPrice());
        entity.setGross_weight(dto.getGross_weight());
        entity.setUri(dto.getUri());

        return entity;

    }

}
