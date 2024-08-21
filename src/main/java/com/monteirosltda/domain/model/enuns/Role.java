package com.monteirosltda.domain.model.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_CREATE;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_DELETE;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_READ;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_UPDATE;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_LOGGER;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_CREATE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_DELETE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_READ;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_UPDATE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_LOGGER;
import static com.monteirosltda.domain.model.enuns.Permission.SON_CHECK_PAY;
import static com.monteirosltda.domain.model.enuns.Permission.SON_CHECK_TASK;
import static com.monteirosltda.domain.model.enuns.Permission.SON_READ_TASK;
import static com.monteirosltda.domain.model.enuns.Permission.SON_SAC_PAY;
import static com.monteirosltda.domain.model.enuns.Permission.SON_LOGGER;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  MANAGER_READ,
                  MANAGER_UPDATE,
                  MANAGER_DELETE,
                  MANAGER_CREATE,
                  ADMIN_LOGGER
          )
  ),
  MANAGER(
          Set.of(
                  MANAGER_READ,
                  MANAGER_UPDATE,
                  MANAGER_DELETE,
                  MANAGER_CREATE,
                  MANAGER_LOGGER
          )
  ),
SON(
        Set.of(
                SON_CHECK_PAY,
                SON_CHECK_TASK,
                SON_READ_TASK,
                SON_SAC_PAY,
                SON_LOGGER
        )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
