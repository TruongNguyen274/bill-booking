package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.KaraokeImage;
import vn.billbooking.repository.KaraokeImageRepository;
import vn.billbooking.repository.KaraokeRepository;
import vn.billbooking.service.KaraokeImageService;
import vn.billbooking.service.KaraokeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class KaraokeImageServiceImpl implements KaraokeImageService {

    @Autowired
    private KaraokeImageRepository karaokeImageRepository;

    @Autowired
    private KaraokeService karaokeService;

    @Override
    public List<KaraokeImage> findAll() {
        return karaokeImageRepository.findAll();
    }

    @Override
    public KaraokeImage findById(long id) {
        return karaokeImageRepository.findById(id).orElse(null);
    }

    @Override
    public List<KaraokeImage> findByKaraoke(Karaoke karaoke) {
        return karaokeImageRepository.findByKaraoke(karaoke);
    }


    @Override
    public KaraokeImage save(KaraokeImage karaokeImage) {
        return karaokeImageRepository.save(karaokeImage);
    }

    @Override
    public Boolean delete(long id) {
        karaokeImageRepository.deleteKaraokeImage(id);
        return true;
    }

    @Override
    public List<KaraokeImage> saveAll(long id, List<FileDTO> fileDTOList) {
        if (fileDTOList == null) {
            return null;
        }

        Karaoke karaoke = karaokeService.findById(id);

        // mapper
        List<KaraokeImage> karaokeImageList = new ArrayList<>();
        for (FileDTO fileDTO : fileDTOList) {
            KaraokeImage karaokeImage = new KaraokeImage();
            karaokeImage.setKaraoke(karaoke);
            karaokeImage.setAvatar(fileDTO.getPath());
            karaokeImage.setStatus(true);
            karaokeImageList.add(karaokeImage);
        }

        // save
        karaokeImageRepository.saveAll(karaokeImageList);

        return karaokeImageList;
    }

}
