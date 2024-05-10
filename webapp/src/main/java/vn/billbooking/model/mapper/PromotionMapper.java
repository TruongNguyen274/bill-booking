package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.PromotionDTO;
import vn.billbooking.model.entity.Promotion;

import java.util.List;

public interface PromotionMapper {

    // Map Entity to DTO
    PromotionDTO toDTO(Promotion promotion);

    List<PromotionDTO> toListDTO(List<Promotion> promotions);

    // Map DTO to Entity
    Promotion toEntity(PromotionDTO promotionDTO);

}
