package com.adminportal.domain.security;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role {

	@Id
	private int roleId;
	private String name;
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@ToString.Exclude
	private Set<UserRole> userRoles = new HashSet<>();


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Role role = (Role) o;
		return false;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
