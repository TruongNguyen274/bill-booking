package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class KaraokeFileDTO {

    private long id;
    private MultipartFile[] files;

}
