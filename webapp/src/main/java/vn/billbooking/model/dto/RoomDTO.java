package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {

    private long id;
    private KaraokeDTO karaoke;
    private String name;
    private String description;
    private String roomType;
    private String regularPrice;
    private String salePrice;
    private boolean status;
    private long karaokeId;
    private long ownerId;
}
