package vn.billbooking.service;

import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Promotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> findAll();

    Promotion findById(long id);

    Promotion findByName(String name);

    Promotion save(Promotion promotion);

    List<Promotion> findByOwner(Karaoke owner);

    List<Promotion> findAllByActive();

    List<Promotion> findByLocation(int limit, long locationId);

    List<Promotion> findByRandom(int limit);

    List<Promotion> findByLocation(long locationId);

    List<Promotion> findByLocationIds(String[] ids);

    int countByLocation(long locationId);

}
