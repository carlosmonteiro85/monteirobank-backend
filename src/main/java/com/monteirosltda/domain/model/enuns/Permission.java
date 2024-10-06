package com.monteirosltda.domain.model.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_CREATE("admin:create"), 
	ADMIN_DELETE("admin:delete"), 
	ADMIN_LOGGER("admin:logger"),
	MANAGER_READ("management:read"), 
	MANAGER_UPDATE("management:update"), 
	MANAGER_CREATE("management:create"),
	MANAGER_DELETE("management:delete"), 
	MANAGER_LOGGER("management:logger");

	@Getter
	private final String permission;
}
