package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.GalleryDTO;
import vn.billbooking.model.entity.Gallery;

import java.util.List;

public interface GalleryMapper {

    //Map Entity to DTO
    GalleryDTO toDTO(Gallery gallery);

    List<GalleryDTO> toListDTO(List<Gallery> galleries);

    //Map DTO to Entity
    Gallery toEntity(GalleryDTO galleryDTO);


}
