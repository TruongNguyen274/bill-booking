package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.GalleryDTO;
import vn.billbooking.model.entity.Gallery;
import vn.billbooking.model.mapper.GalleryMapper;
import vn.billbooking.service.GalleryService;
import java.util.ArrayList;
import java.util.List;


@Component
public class GalleryMapperImpl implements GalleryMapper {

    @Autowired
    GalleryService galleryService;

    @Override
    public GalleryDTO toDTO(Gallery gallery) {
        if (gallery == null) {
            return null;
        }

        GalleryDTO galleryDTO = new GalleryDTO();
        galleryDTO.setId(gallery.getId());
        galleryDTO.setImage(gallery.getImage());
        galleryDTO.setLink(gallery.getLink());
        galleryDTO.setType(gallery.getType());
        galleryDTO.setTitle(gallery.getTitle());
        galleryDTO.setStatus(gallery.isStatus());

        return galleryDTO;
    }

    @Override
    public List<GalleryDTO> toListDTO(List<Gallery> galleries) {
        if (galleries == null){
            return null;
        }
        List<GalleryDTO> list = new ArrayList<>(galleries.size());
        for (Gallery gallery : galleries){
            list.add(toDTO(gallery));
        }
        return list;
    }

    @Override
    public Gallery toEntity(GalleryDTO galleryDTO) {
        Gallery gallery = galleryService.findById(galleryDTO.getId());
        if (gallery == null){
            gallery = new Gallery();
        }
        gallery.setLink(galleryDTO.getLink());
        gallery.setType(galleryDTO.getType());
        gallery.setTitle(galleryDTO.getTitle());
        gallery.setStatus(galleryDTO.isStatus());

        return gallery;
    }

}
