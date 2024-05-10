package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.KaraokeImageDTO;
import vn.billbooking.model.entity.KaraokeImage;

import java.util.List;

public interface KaraokeImageMapper {

    //Map Entity to DTO
    KaraokeImageDTO toDTO(KaraokeImage karaokeImage);

    List<KaraokeImageDTO> toListDTO(List<KaraokeImage> karaokeImages);

    //Map DTO to Entity
    KaraokeImage toEntity(KaraokeImageDTO karaokeImageDTO);


}
