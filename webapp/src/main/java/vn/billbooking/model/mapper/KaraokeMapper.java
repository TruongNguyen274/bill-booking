package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;

public interface KaraokeMapper {

    //Map Entity to DTO
    KaraokeDTO toDTO(Karaoke karaoke);

    List<KaraokeDTO> toListDTO(List<Karaoke> karaokes);

    //Map DTO to Entity
    Karaoke toEntity(KaraokeDTO karaokeDTO);


}
