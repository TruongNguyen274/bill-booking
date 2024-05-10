package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "karaoke_image")
public class KaraokeImage extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "karaoke_id")
    private Karaoke karaoke;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private boolean status;

}
