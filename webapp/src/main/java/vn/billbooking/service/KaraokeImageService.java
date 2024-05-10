package vn.billbooking.service;

import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.KaraokeImage;

import java.util.List;

public interface KaraokeImageService {

    List<KaraokeImage> findAll();

    KaraokeImage findById(long id);

    List<KaraokeImage> findByKaraoke(Karaoke karaoke);

    KaraokeImage save(KaraokeImage karaokeImage);

    Boolean delete(long id);

    List<KaraokeImage> saveAll(long id, List<FileDTO> fileDTOList);

}
