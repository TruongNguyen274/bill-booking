package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private long id;

    // Karaoke
    private KaraokeDTO owner;
    private long ownerId;

    // Account
    private AccountDTO account;
    private long accountId;

    private Integer rate;
    private String reply;
    private boolean status;
    private String progress;
}
