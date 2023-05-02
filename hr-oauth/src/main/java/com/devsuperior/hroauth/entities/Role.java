package com.devsuperior.hroauth.entities;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String roleName;

	public Role() {
	}

	public Role(final Long id, final String name) {
		this.id = id;
		this.roleName = name;
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
		final Role other = (Role) obj;
		if (this.roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!this.roleName.equals(other.roleName)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return this.id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.roleName == null) ? 0 : this.roleName.hashCode());
		return result;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setRoleName(final String name) {
		this.roleName = name;
	}

}
