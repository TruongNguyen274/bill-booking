package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    // Account
    private long id;
    private String avatar;
    private String fullName;
    private String username;
    private String email;
    private String phone;


}
