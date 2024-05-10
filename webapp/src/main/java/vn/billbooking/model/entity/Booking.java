package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Karaoke owner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "progress")
    private String progress;

    @Column(name = "roomType")
    private String roomType;

    @Column(name = "timeOrder")
    private Date timeOrder;

    @Column(name = "bill")
    private String bill;

    @Column(name = "total_people")
    private String totalPeople;

    @Column(name = "totalBill")
    private double totalBill;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private boolean status;

    @Column(name = "is_confirm")
    private int isConfirm;

}
