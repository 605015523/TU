package com.tu.action;

import java.util.HashMap;
import java.util.Map;

public class Role {
	public static final Role ROLE_GROUP_LEADER = new Role(1, "Group leader");
	public static final Role ROLE_ACCOUNTING = new Role(2, "Accounting");
	public static final Role ROLE_NORMAL_MEMBER = new Role(3, "Normal member");
	public static final Role ROLE_IT_ADMIN = new Role(4, "IT Admin");
	public static final Map<Integer, Role> ROLES;
	static {
		ROLES = new HashMap<Integer, Role>();
		ROLES.put(ROLE_GROUP_LEADER.getId(), ROLE_GROUP_LEADER);
		ROLES.put(ROLE_ACCOUNTING.getId(), ROLE_ACCOUNTING);
		ROLES.put(ROLE_NORMAL_MEMBER.getId(), ROLE_NORMAL_MEMBER);
		ROLES.put(ROLE_IT_ADMIN.getId(), ROLE_IT_ADMIN);
	}
	
	private Integer id;
	private String name;
	
	public Role(Integer id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
