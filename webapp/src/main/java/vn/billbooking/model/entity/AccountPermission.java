package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "account_permission")
public class AccountPermission extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="account_id")
    public Account account;

    @ManyToOne
    @JoinColumn(name="permission_id")
    public Permission permission;

    @Column(name = "status")
    private boolean status;

}
