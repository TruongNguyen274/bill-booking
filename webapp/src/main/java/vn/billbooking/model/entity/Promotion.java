package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion extends BaseEntity {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Karaoke owner;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "code")
	private String code;

	@Column(name = "discount")
	private String discount;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "detail")
	private String detail;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "status")
	private boolean status;

}
