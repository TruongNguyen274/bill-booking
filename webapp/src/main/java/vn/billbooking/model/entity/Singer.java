package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "singer")
public class Singer extends BaseEntity {

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "detail")
	private String detail;

	@Column(name = "status")
	private boolean status;

}
