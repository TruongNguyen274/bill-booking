package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GalleryDTO {

    private long id;
    private String type;
    private String image;
    private String link;
    private String title;
    private boolean status;

    private MultipartFile imageMul;
}
