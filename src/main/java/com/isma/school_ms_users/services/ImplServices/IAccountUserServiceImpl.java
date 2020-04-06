package com.isma.school_ms_users.services.ImplServices;

import com.isma.school_ms_users.core.converters.IConverters.IConverter;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.dto.AccountUserDTO;
import com.isma.school_ms_users.data.entities.AccountRole;
import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.data.repositories.AccountRepository;
import com.isma.school_ms_users.data.repositories.AccountRoleRepository;
import com.isma.school_ms_users.services.IServices.IAccountUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@CrossOrigin("*")
public class IAccountUserServiceImpl implements IAccountUserService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Qualifier("AccountUserConverter")
    private IConverter<AccountUser, AccountUserDTO> accountUserConverter;
    @Qualifier("AccountRoleConverter")
    private IConverter<AccountRole, AccountRoleDTO> accountRoleConverter;

    public IAccountUserServiceImpl(AccountRepository accountRepository,
                                   AccountRoleRepository accountRoleRepository,
                                   BCryptPasswordEncoder passwordEncoder,
                                   @Qualifier("AccountUserConverter") IConverter<AccountUser,
                                           AccountUserDTO> accountUserConverter,
                                   @Qualifier("AccountRoleConverter") IConverter<AccountRole, AccountRoleDTO> accountRoleConverter) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountUserConverter = accountUserConverter;
        this.accountRoleConverter = accountRoleConverter;
    }

    @Override
    public AccountUserDTO saveUser(AccountUser accountUser, String confirmedPassword) throws NoDataFoundException {
        AccountUser user = accountRepository.findAccountUserByUsername(accountUser.getUsername());
        if (!accountUser.getPassword().equals(confirmedPassword))
            throw new RuntimeException("Please confirm your password correctly!");
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        AccountUser rs = accountRepository.save(accountUser);
        addRoleToUser(accountUser.getCode(), "AGENT");
        return accountUserConverter.convertToDto(rs);
    }

    @Override
    public AccountUser getUserByUsername(String username) throws NoDataFoundException {
        AccountUser accountUser = accountRepository.findAccountUserByUsername(username);
        if (accountUser == null)
            throw new NoDataFoundException("AccountUser identify by username " + username + " Not Exit");
        return accountUser;
    }

    @Override
    public AccountUserDTO findUserByUsername(String username) throws NoDataFoundException {
        AccountUser accountUser = accountRepository.findAccountUserByUsername(username);
        if (accountUser == null)
            throw new NoDataFoundException("AccountUser identify by username " + username + " does not Exit");
        return accountUserConverter.convertToDto(accountUser);
    }

    @Override
    public AccountUserDTO getUserByCode(String code) throws NoDataFoundException {
        AccountUser accountUser = accountRepository.getOne(code);
        if (accountUser == null)
            throw new NoDataFoundException("AccountUser identify by username" + code + " Not Exit");
        return accountUserConverter.convertToDto(accountUser);
    }

    @Override
    public AccountUserDTO updateUser(String code, AccountUser newAccountUser) throws NoDataFoundException {
        AccountUser us = accountRepository.getOne(code);
        if (us == null)
            throw new NoDataFoundException("No AccountUser was identified by " + code);
        us = accountRepository.save(newAccountUser);
        return accountUserConverter.convertToDto(us);
    }

    @Override
    public List<AccountUserDTO> getAllAccounts() throws NoDataFoundException {
        List<AccountUser> accountUsers = accountRepository.findAll();
        if (accountUsers == null)
            throw new NoDataFoundException("No AccountUser was not found");
        return accountUserConverter.convertListToListDto(accountUsers);
    }

    @Override
    public String deleteUser(String code) throws NoDataFoundException {
        if (accountRepository.getOne(code) == null)
            throw new NoDataFoundException("No User was identified by " + code);
        try {
            accountRepository.deleteById(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return code;
    }

    @Override
    public AccountRoleDTO addRoleToUser(String code, String role) throws NoDataFoundException {
        AccountUser accountUser = accountRepository.getOne(code);
        AccountRole accountRole = accountRoleRepository.findAccountRoleByName(role);
        if (accountRole == null)
            throw new NoDataFoundException("No AccountRole was identified by name " + role);
        accountUser.addRoleToAccount(accountRole);
        accountRepository.save(accountUser);
        return accountRoleConverter.convertToDto(accountRole);
    }

    @Override
    public void removeRoleFromUser(String code, String role) throws NoDataFoundException {
        AccountUser accountUser = accountRepository.getOne(code);
        if (accountUser == null)
            throw new NoDataFoundException("No AccountUser was identified by" + code);
        accountUser.removeRoleToAccount(accountRoleRepository.findAccountRoleByName(role));
        accountRepository.save(accountUser);
    }
}
