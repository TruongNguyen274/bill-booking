package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SingerDTO {

    private long id;
    private String avatar;
    private String name;
    private String description;
    private String detail;
    private boolean status;

    // custom
    private MultipartFile avatarMul;

}
