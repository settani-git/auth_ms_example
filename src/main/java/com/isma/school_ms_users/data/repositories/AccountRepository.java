package com.isma.school_ms_users.data.repositories;

import com.isma.school_ms_users.data.entities.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountUser, String> {
   public AccountUser findAccountUserByUsername(String username);
   public AccountUser findAccountUserByContact_Email(String email);
}
