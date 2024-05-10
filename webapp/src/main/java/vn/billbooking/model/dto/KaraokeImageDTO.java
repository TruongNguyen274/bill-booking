package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class KaraokeImageDTO {

    private long id;
    private KaraokeDTO karaokeDTO;
    private String avatar;
    // custom
    private MultipartFile avatarMul;


}
