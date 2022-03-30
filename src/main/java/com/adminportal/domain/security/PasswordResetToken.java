package com.adminportal.domain.security;

import com.adminportal.domain.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name="user_id")
	private User user;
	
	private Date expiryDate;
	public PasswordResetToken(final String token, final User user) {
		super ();
		
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate();
	}
	
	private Date calculateExpiryDate() {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, PasswordResetToken.EXPIRATION);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(final String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		PasswordResetToken that = (PasswordResetToken) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
