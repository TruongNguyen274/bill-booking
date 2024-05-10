package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PointService;

import java.util.ArrayList;
import java.util.List;

@Component
public class KaraokeMapperImpl implements KaraokeMapper {

    @Autowired
    KaraokeService karaokeService;

    @Autowired
    AccountService accountService;

    @Autowired
    PointService pointService;

    @Autowired
    LocationService locationService;

    @Override
    public KaraokeDTO toDTO(Karaoke karaoke) {
        if (karaoke == null) {
            return null;
        }

        KaraokeDTO karaokeDTO = new KaraokeDTO();
        karaokeDTO.setId(karaoke.getId());
        karaokeDTO.setName(karaoke.getName());
        karaokeDTO.setAddress(karaoke.getAddress());
        karaokeDTO.setPhone(karaoke.getPhone());
        karaokeDTO.setStatus(karaoke.isStatus());
        karaokeDTO.setDescription(karaoke.getDescription());
        karaokeDTO.setDetail(karaoke.getDetail());
        karaokeDTO.setAvatar(karaoke.getAvatar());
        karaokeDTO.setRegularPrice(karaoke.getRegularPrice());
        karaokeDTO.setSalePrice(karaoke.getSalePrice());
        if (karaoke.getVoucher() != null && !karaoke.getVoucher().equalsIgnoreCase("")) {
            karaokeDTO.setVoucher(karaoke.getVoucher());
        } else {
            karaokeDTO.setVoucher("HOT");
        }
        karaokeDTO.setTotalComment(karaoke.getTotalComment());
        karaokeDTO.setTotalRating(karaoke.getTotalRating());
        karaokeDTO.setTotalRating1(karaoke.getTotalRating1());
        karaokeDTO.setTotalRating2(karaoke.getTotalRating2());
        karaokeDTO.setTotalRating3(karaoke.getTotalRating3());
        karaokeDTO.setTotalRating4(karaoke.getTotalRating4());
        karaokeDTO.setTotalRating5(karaoke.getTotalRating5());

        // custom
        LocationDTO locationDTO = new LocationDTO();
        Location location = karaoke.getLocation();
        if (location != null) {
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
            karaokeDTO.setLocationDTO(locationDTO);
            karaokeDTO.setLocationId(location.getId());
        }

        AccountDTO accountDTO = new AccountDTO();
        Account account = karaoke.getAccount();
        if (account != null) {
            accountDTO.setId(account.getId());
            accountDTO.setFullName(account.getFullName());
            accountDTO.setUsername(account.getUsername());
            accountDTO.setEmail(account.getEmail());
            karaokeDTO.setAccountDTO(accountDTO);
            karaokeDTO.setAccountId(account.getId());
        }

        PointDTO pointDTO = new PointDTO();
        Point point =  karaoke.getPoint();
        if (point != null){
            pointDTO.setId(point.getId());
            pointDTO.setName(point.getName());
            karaokeDTO.setPointDTO(pointDTO);
            karaokeDTO.setPointId(point.getId());
        }

        return karaokeDTO;
    }

    @Override
    public List<KaraokeDTO> toListDTO(List<Karaoke> karaokes) {
        if (karaokes == null) {
            return null;
        }

        List<KaraokeDTO> list = new ArrayList<>(karaokes.size());
        for (Karaoke karaoke : karaokes) {
            KaraokeDTO karaokeDTO = toDTO(karaoke);
            if (karaokeDTO != null) {
                list.add(karaokeDTO);
            }
        }
        return list;
    }

    @Override
    public Karaoke toEntity(KaraokeDTO karaokeDTO) {
        Karaoke karaoke = karaokeService.findById(karaokeDTO.getId());
        if (karaoke == null) {
            karaoke = new Karaoke();
        }
        karaoke.setName(karaokeDTO.getName().trim());
        karaoke.setAddress(karaokeDTO.getAddress());
        karaoke.setRegularPrice(karaokeDTO.getRegularPrice());
        karaoke.setSalePrice(karaokeDTO.getSalePrice());
        karaoke.setVoucher(karaokeDTO.getVoucher());
        karaoke.setPhone(karaokeDTO.getPhone());
        karaoke.setStatus(karaokeDTO.isStatus());
        karaoke.setAccount(accountService.findById(karaokeDTO.getAccountId()));
        karaoke.setLocation(locationService.findById(karaokeDTO.getLocationId()));
        karaoke.setPoint(pointService.findById(karaokeDTO.getPointId()));
        karaoke.setDescription(karaokeDTO.getDescription());
        karaoke.setDetail(karaokeDTO.getDetail());
        return karaoke;
    }

}
