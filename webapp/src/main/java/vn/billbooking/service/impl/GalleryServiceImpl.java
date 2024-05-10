package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Gallery;
import vn.billbooking.repository.GalleryRepository;
import vn.billbooking.service.GalleryService;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    @Override
    public List<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery findById(long id) {
        return galleryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Gallery> findByRandom(int limit, String type, boolean status) {
        return galleryRepository.findByRandom(limit, type, status);
    }

    @Override
    public Gallery save(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery findByName(String name) {
        return galleryRepository.findByType(name).orElse(null);
    }

}
