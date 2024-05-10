package vn.billbooking.service;

import vn.billbooking.model.entity.Gallery;

import java.util.List;

public interface GalleryService {

    List<Gallery> findAll();

    Gallery findById(long id);

    List<Gallery> findByRandom(int limit, String type, boolean status);

    Gallery save(Gallery gallery);

    Gallery findByName(String name);

}
