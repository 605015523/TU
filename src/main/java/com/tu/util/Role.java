package com.tu.util;

public enum Role {
	LEADER(1),
	ACCOUNTER(2),
	NORMALUSER(3);
	
	private final Integer roleId;
	
	Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}
}
