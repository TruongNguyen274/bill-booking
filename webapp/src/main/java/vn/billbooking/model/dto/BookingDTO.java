package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookingDTO {

    private long id;
    private KaraokeDTO owner;
    private AccountDTO account;
    private String code;
    private String price;
    private String discount;
    private String progress;
    private String roomType;
    private String timeInput;
    private String bill;
    private String totalBill;
    private Boolean status;
    private int isConfirm;
    private String totalPeople;
    private String timeOrder;
    private String note;

    // custom
    private long accountId;
    private long ownerId;
    private MultipartFile billMul;

}
