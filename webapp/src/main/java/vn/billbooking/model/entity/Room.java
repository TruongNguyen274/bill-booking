package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "karaoke_id")
	private Karaoke karaoke;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "room_type")
	private String roomType;

	@Column(name = "regular_price")
	private Double regularPrice;

	@Column(name = "sale_price")
	private String salePrice;

	@Column(name = "status")
	private boolean status;

}
