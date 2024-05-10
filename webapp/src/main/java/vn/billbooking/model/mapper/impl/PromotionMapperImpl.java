package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import vn.billbooking.model.dto.PromotionDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.model.mapper.PromotionMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.PromotionService;
import vn.billbooking.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public PromotionDTO toDTO(Promotion promotion) {
        if (promotion == null) {
            return null;
        }

        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setCode(promotion.getCode());
        promotionDTO.setDiscount(promotion.getDiscount());
        promotionDTO.setName(promotion.getName());
        promotionDTO.setDescription(promotion.getDescription());
        promotionDTO.setDetail(promotion.getDetail());
        if (promotion.getStartDate() != null){
            promotionDTO.setStartDate(dateUtil.convertDateToString(promotion.getStartDate(),"yyyy-MM-dd"));
        }
        if (promotion.getEndDate() != null){
            promotionDTO.setEndDate(dateUtil.convertDateToString(promotion.getEndDate(),"yyyy-MM-dd"));
        }
        promotionDTO.setAvatar(promotion.getAvatar());
        promotionDTO.setStatus(promotion.isStatus());

        // owner
        promotionDTO.setAddress(promotion.getOwner().getAddress());
        promotionDTO.setOwner(promotion.getOwner());
        promotionDTO.setOwnerId(promotion.getOwner().getId());

        return promotionDTO;
    }

    @Override
    public List<PromotionDTO> toListDTO(List<Promotion> promotions) {
        if(promotions == null) {return null;}

        List<PromotionDTO> list = new ArrayList<>(promotions.size());

        promotions.forEach(promotion -> {
            list.add(toDTO(promotion));
        });

        return list;
    }

    @Override
    public Promotion toEntity(PromotionDTO promotionDTO) {
        if(promotionDTO == null) {return null;}

        Promotion promotion = promotionService.findById(promotionDTO.getId());

        if(promotion == null) {
            promotion = new Promotion();
        }

        promotion.setCode(promotionDTO.getCode());
        promotion.setName(promotionDTO.getName().trim());
        promotion.setDiscount(promotionDTO.getDiscount());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setDetail(promotionDTO.getDetail());
        if (!StringUtils.isEmpty(promotionDTO.getStartDate())){
            promotion.setStartDate(dateUtil.convertStringToDate(promotionDTO.getStartDate(),"yyyy-MM-dd"));
        }
        if (!StringUtils.isEmpty(promotionDTO.getEndDate())){
            promotion.setEndDate(dateUtil.convertStringToDate(promotionDTO.getEndDate(),"yyyy-MM-dd"));
        }
        promotion.setStatus(promotionDTO.isStatus());

        // Karaoke
        if (promotionDTO.getOwnerId() != 0) {
            Karaoke karaoke = karaokeService.findById(promotionDTO.getOwnerId());
            promotion.setOwner(karaoke);
            promotion.setLocation(karaoke.getLocation());
        }

        if (promotionDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(promotionDTO.getAvatarMul().getOriginalFilename())) {
            promotion.setAvatar(promotionDTO.getAvatar());
        }

        return promotion;
    }
}
