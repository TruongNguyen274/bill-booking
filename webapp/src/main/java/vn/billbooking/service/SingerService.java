package vn.billbooking.service;

import vn.billbooking.model.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findAllByActive();

    Singer findById(long id);

    Singer save(Singer singer);

}
