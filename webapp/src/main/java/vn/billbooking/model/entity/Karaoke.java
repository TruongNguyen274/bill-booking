package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "karaoke")
public class Karaoke extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @OneToMany(mappedBy = "karaoke", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "detail")
    private String detail;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "regular_price")
    private String regularPrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "voucher")
    private String voucher;

    @Column(name = "status")
    private boolean status;

    @Column(name = "total_comment")
    private int totalComment;

    @Column(name = "total_rating")
    private double totalRating;

    @Column(name = "total_rating1")
    private int totalRating1;

    @Column(name = "total_rating2")
    private int totalRating2;

    @Column(name = "total_rating3")
    private int totalRating3;

    @Column(name = "total_rating4")
    private int totalRating4;

    @Column(name = "total_rating5")
    private int totalRating5;

}
