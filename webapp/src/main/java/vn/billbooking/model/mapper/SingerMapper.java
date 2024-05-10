package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.SingerDTO;
import vn.billbooking.model.entity.Singer;

import java.util.List;

public interface SingerMapper {

    // Map Entity to DTO
    SingerDTO toDTO(Singer singer);

    List<SingerDTO> toListDTO(List<Singer> singers);

    // Map DTO to Entity
    Singer toEntity(SingerDTO singerDTO);

}
