package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.SingerDTO;
import vn.billbooking.model.entity.Singer;
import vn.billbooking.model.mapper.SingerMapper;
import vn.billbooking.service.SingerService;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingerMapperImpl implements SingerMapper {

    @Autowired
    private SingerService singerService;

    @Override
    public SingerDTO toDTO(Singer singer) {
        SingerDTO singerDTO = new SingerDTO();

        singerDTO.setId(singer.getId());
        singerDTO.setName(singer.getName());
        singerDTO.setAvatar(singer.getAvatar());
        singerDTO.setDescription(singer.getDescription());
        singerDTO.setDetail(singer.getDetail());
        singerDTO.setStatus(singer.isStatus());

        return singerDTO;
    }

    @Override
    public List<SingerDTO> toListDTO(List<Singer> singers) {
        if (singers == null){
            return null;
        }
        List<SingerDTO> list = new ArrayList<>(singers.size());
        for (Singer singer : singers) {
            list.add(toDTO(singer));
        }
        return list;
    }

    @Override
    public Singer toEntity(SingerDTO singerDTO) {
        Singer singer = singerService.findById(singerDTO.getId());

        if (singer == null){
            singer = new Singer();
        }

        singer.setName(singerDTO.getName().trim());
        singer.setDescription(singerDTO.getDescription());
        singer.setDetail(singerDTO.getDetail());
        singer.setStatus(singerDTO.isStatus());
        return singer;
    }

}
