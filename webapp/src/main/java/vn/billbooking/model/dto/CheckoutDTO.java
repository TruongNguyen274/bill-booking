package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDTO {

    private String fullName;
    private String email;
    private String phone;
    private String totalPeople;
    private String roomType;
    private String timeOrder;
    private String note;

    private long accountId;
    private long ownerId;

}
