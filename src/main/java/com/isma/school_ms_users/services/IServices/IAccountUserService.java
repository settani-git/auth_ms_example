package com.isma.school_ms_users.services.IServices;

import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.dto.AccountUserDTO;
import com.isma.school_ms_users.data.entities.AccountUser;

import java.util.List;

public interface IAccountUserService {
    public AccountUserDTO saveUser(AccountUser accountUser, String confirmPassword) throws NoDataFoundException;
    public AccountUser getUserByUsername(String username) throws NoDataFoundException;
    public AccountUserDTO findUserByUsername(String username) throws NoDataFoundException;
    public AccountUserDTO getUserByCode(String code) throws NoDataFoundException;
    public AccountUserDTO updateUser(String code,AccountUser newAccountUser) throws NoDataFoundException;
    public List<AccountUserDTO> getAllAccounts() throws NoDataFoundException;
    public String deleteUser(String code) throws NoDataFoundException;
    public AccountRoleDTO addRoleToUser(String code, String role) throws NoDataFoundException;
    public void removeRoleFromUser(String code, String role) throws NoDataFoundException;
}
