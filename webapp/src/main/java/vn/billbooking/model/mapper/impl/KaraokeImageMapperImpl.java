package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.dto.KaraokeImageDTO;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.KaraokeImage;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.KaraokeImageMapper;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.KaraokeImageService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;

import java.util.ArrayList;
import java.util.List;

@Component
public class KaraokeImageMapperImpl implements KaraokeImageMapper {

    @Autowired
    KaraokeService karaokeService;
    @Autowired
    KaraokeImageService karaokeImageService;

    @Override
    public KaraokeImageDTO toDTO(KaraokeImage karaokeImage) {
        if (karaokeImage == null) {
            return null;
        }
        KaraokeImageDTO karaokeImageDTO = new KaraokeImageDTO();
        karaokeImageDTO.setId(karaokeImage.getId());
        karaokeImageDTO.setAvatar(karaokeImage.getAvatar());

        return karaokeImageDTO;
    }

    @Override
    public List<KaraokeImageDTO> toListDTO(List<KaraokeImage> karaokeImages) {
        if (karaokeImages == null) {
            return null;
        }

        List<KaraokeImageDTO> list = new ArrayList<>(karaokeImages.size());
        for (KaraokeImage karaokeImage : karaokeImages) {
            KaraokeImageDTO karaokeImageDTO = toDTO(karaokeImage);
            if (karaokeImageDTO != null) {
                list.add(karaokeImageDTO);
            }
        }
        return list;
    }

    @Override
    public KaraokeImage toEntity(KaraokeImageDTO karaokeImageDTO) {
        KaraokeImage karaokeImage = karaokeImageService.findById(karaokeImageDTO.getId());
        if (karaokeImage == null) {
            karaokeImage = new KaraokeImage();
        }

        return karaokeImage;
    }

}
