package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AccountDTO {

    // Account
    private long id;
    private String code;
    private String avatar;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private boolean verifyEmail;
    private boolean verifyPhone;
    private boolean status;

    //Location
    private LocationDTO locationDTO;

    // Role
    private long roleId;
    private RoleDTO role;

    // Location
    private long locationId;
    private LocationDTO location;

    // photo
    private MultipartFile avatarMul;

    // custom
    private String repassword;
    private String oldPassword;
    private String newPassword;
    private String verifyNewPassword;

    @Override
    public String toString() {
        return fullName;
    }

}
