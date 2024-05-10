package vn.billbooking.service;

import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;

import java.util.HashMap;
import java.util.List;

public interface KaraokeService {

    List<Karaoke> findAll();

    Karaoke findById(long id);

    Karaoke findByName(String name);

    Karaoke save(Karaoke karaoke);

    List<Karaoke> findByRandom(int limit);

    List<Karaoke> findByLocation(int limit, long locationId);

    List<Karaoke> findByLocation(long locationId);

    List<Karaoke> findByPoint(String[] pointIds);

    List<Karaoke> findByAccount(Account account);

    int countByPoint(long pointId);

    List<Karaoke> searchByKeyword(String keyword);

    List<Karaoke> searchByKeywordAndLocation(String keyword, long locationId);

}
