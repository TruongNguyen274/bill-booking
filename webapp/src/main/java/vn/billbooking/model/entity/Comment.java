package vn.billbooking.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Karaoke owner;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "rate")
	private Integer rate;

	@Column(name = "reply")
	private String reply;

	@Column(name = "status")
	private boolean status;

	@Column(name = "progress")
	private String progress;

}
