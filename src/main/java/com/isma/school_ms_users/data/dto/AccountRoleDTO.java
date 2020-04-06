package com.isma.school_ms_users.data.dto;

import com.isma.school_ms_users.data.entities.AccountRole;

public class AccountRoleDTO {
    private Long id;
    private String name;

    public AccountRoleDTO() {

    }

    public AccountRoleDTO(AccountRole accountRole) {
        this.id=accountRole.getId();
        this.name =accountRole.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
