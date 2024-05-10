package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostDTO {

    private long id;
    private KaraokeDTO owner;
    private String avatar;
    private String name;
    private String description;
    private String detail;
    private boolean status;
    private String progress;

    // custom
    private long ownerId;
    private MultipartFile avatarMul;

}
