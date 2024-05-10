package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class KaraokeDTO {

    private long id;
    private AccountDTO accountDTO;
    private LocationDTO locationDTO;
    private PointDTO pointDTO;
    private String name;
    private String address;
    private String phone;
    private int roomNumber;
    private String description;
    private String detail;
    private String avatar;
    private String regularPrice;
    private String salePrice;
    private String voucher;
    private boolean status;

    // custom
    private MultipartFile avatarMul;
    private long accountId;
    private long locationId;
    private long pointId;

    private long totalComment;
    private double totalRating;
    private Integer totalRating1;
    private Integer totalRating2;
    private Integer totalRating3;
    private Integer totalRating4;
    private Integer totalRating5;

}
