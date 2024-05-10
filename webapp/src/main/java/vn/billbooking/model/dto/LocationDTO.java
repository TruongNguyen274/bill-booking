package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {

    private long id;
    private String name;
    private String description;
    private boolean status;

    private int count;

}
