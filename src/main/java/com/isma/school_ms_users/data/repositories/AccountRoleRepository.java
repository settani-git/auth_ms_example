package com.isma.school_ms_users.data.repositories;

import com.isma.school_ms_users.data.entities.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    AccountRole findAccountRoleByName(String name);
}
