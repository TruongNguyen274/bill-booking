package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.repository.PromotionRepository;
import vn.billbooking.service.PromotionService;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findById(long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public Promotion findByName(String name) {
        return promotionRepository.findByName(name).orElse(null);
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public List<Promotion> findByOwner(Karaoke owner) {
        return promotionRepository.findByOwner(owner);
    }

    @Override
    public List<Promotion> findAllByActive() {
        return promotionRepository.findByStatusIsTrue();
    }

    @Override
    public List<Promotion> findByLocation(int limit, long locationId) {
        return promotionRepository.findByLocation(limit, locationId);
    }

    @Override
    public List<Promotion> findByRandom(int limit) {
        return promotionRepository.findByRandom(limit);
    }

    @Override
    public List<Promotion> findByLocation(long locationId) {
        return promotionRepository.findByLocation(locationId);
    }

    @Override
    public List<Promotion> findByLocationIds(String[] ids) {
        return promotionRepository.findByLocationIds(ids);
    }

    @Override
    public int countByLocation(long locationId) {
        return promotionRepository.countByLocation(locationId);
    }

}
