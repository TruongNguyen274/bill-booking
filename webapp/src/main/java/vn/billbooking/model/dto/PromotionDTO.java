package vn.billbooking.model.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.billbooking.model.entity.Karaoke;

@Getter
@Setter
public class PromotionDTO {

    // Promotion
    private long id;
    private String code;
    private String discount;
    private String name;
    private String description;
    private String detail;
    private String startDate;
    private String endDate;
    private String avatar;
    private boolean status;

    // Karaoke
    private Karaoke owner;
    private long ownerId;

    // photo
    private MultipartFile avatarMul;

    // custom
    private String address;

}
