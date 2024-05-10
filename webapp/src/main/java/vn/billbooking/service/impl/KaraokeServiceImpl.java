package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.repository.KaraokeRepository;
import vn.billbooking.service.KaraokeService;

import java.util.HashMap;
import java.util.List;

@Service
public class KaraokeServiceImpl implements KaraokeService {

    @Autowired
    private KaraokeRepository karaokeRepository;

    @Override
    public List<Karaoke> findAll() {
        return karaokeRepository.findAll();
    }

    @Override
    public Karaoke findById(long id) {
        return karaokeRepository.findById(id).orElse(null);
    }

    @Override
    public Karaoke findByName(String name) {
        return karaokeRepository.findByName(name).orElse(null);
    }

    @Override
    public Karaoke save(Karaoke karaoke) {
        return karaokeRepository.save(karaoke);
    }

    @Override
    public List<Karaoke> findByRandom(int limit) {
        return karaokeRepository.findByRandom(limit);
    }

    @Override
    public List<Karaoke> findByLocation(int limit, long locationId) {
        return karaokeRepository.findByLocation(limit, locationId);
    }

    @Override
    public List<Karaoke> findByLocation(long locationId) {
        return karaokeRepository.findByLocation(locationId);
    }

    @Override
    public List<Karaoke> findByPoint(String[] pointIds) {
        return karaokeRepository.findByPoint(pointIds);
    }

    @Override
    public List<Karaoke> findByAccount(Account account) {
        return karaokeRepository.findByAccount(account);
    }

    @Override
    public int countByPoint(long pointId) {
        return karaokeRepository.countByPoint(pointId);
    }

    @Override
    public List<Karaoke> searchByKeyword(String keyword) {
        return karaokeRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Karaoke> searchByKeywordAndLocation(String keyword, long locationId) {
        return karaokeRepository.searchByKeywordAndLocation(keyword, locationId);
    }

}
