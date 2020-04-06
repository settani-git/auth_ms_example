package com.isma.school_ms_users.services.IServices;

import com.isma.school_ms_users.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_users.core.exceptions.FailedToSaveDataException;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.entities.AccountRole;

import java.util.List;

public interface IAccountRoleService {
    AccountRoleDTO getRoleByName(String name) throws NoDataFoundException;

    AccountRoleDTO getRoleById(Long id) throws NoDataFoundException;

    List<AccountRoleDTO> getAllRoles() throws NoDataFoundException;

    AccountRoleDTO addRole(AccountRole role) throws DataAlreadyUsed, FailedToSaveDataException;

    AccountRoleDTO updateRole(long idRole, AccountRole role) throws NoDataFoundException;

    long deleteRole(Long idRole) throws NoDataFoundException;
}
