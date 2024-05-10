package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointDTO {

    private long id;
    private LocationDTO location;
    private String name;
    private String description;
    private boolean status;

    private long locationId;
    private int count;

}
