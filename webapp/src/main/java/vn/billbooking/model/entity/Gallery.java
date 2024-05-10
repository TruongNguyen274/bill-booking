package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "gallery")
public class Gallery extends BaseEntity {

	@Column(name = "type")
	private String type;

	@Column(name = "image")
	private String image;

	@Column(name = "link")
	private String link;

	@Column(name = "title")
	private String title;

	@Column(name = "status")
	private boolean status;

}
