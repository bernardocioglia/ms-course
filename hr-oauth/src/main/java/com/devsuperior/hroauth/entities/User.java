package com.devsuperior.hroauth.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return User.serialVersionUID;
	}

	private Long id;
	private String name;
	private String email;
	private String password;

	Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(final String email) {
		this.email = email;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getEmail() {
		return this.email;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
