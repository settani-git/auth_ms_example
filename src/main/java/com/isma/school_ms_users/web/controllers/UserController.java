package com.isma.school_ms_users.web.controllers;

import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.dto.AccountUserDTO;
import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.services.IServices.IAccountUserService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path = "/accounts")
@Api(value = "AccountUser API", description = "Operations about AccountUser")
public class UserController {
    private final IAccountUserService accountUserService;

    public UserController(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping(path = "")
    public List<AccountUserDTO> getAllAccounts() throws NoDataFoundException {
        return accountUserService.getAllAccounts();
    }

    @PostMapping(path = "")
    public AccountUserDTO addAccount(@RequestBody AccountUser accountUser, @RequestBody String confirmPassword) throws NoDataFoundException {
        return accountUserService.saveUser(accountUser, confirmPassword);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{code}")
    public AccountUserDTO updateAccount(@PathVariable String code, @RequestBody AccountUser accountUser) throws NoDataFoundException {
        return accountUserService.updateUser(code, accountUser);
    }

    @DeleteMapping(path = "/{code}")
    public String deleteAccount(@PathVariable String code) throws NoDataFoundException {
        return accountUserService.deleteUser(code);
    }

    @GetMapping(path = "account-by-code")
    public AccountUserDTO getAccountByCode(@RequestParam("code") String code) throws NoDataFoundException {
        return accountUserService.getUserByCode(code);
    }

    @GetMapping(path = "account-by-username")
    public AccountUserDTO getAccountByUsername(@RequestParam String username) throws NoDataFoundException {
        return accountUserService.findUserByUsername(username);
    }

    @DeleteMapping(value = "/role")
    public void removeRoleFromUser(@RequestParam String code, @RequestParam String role) throws NoDataFoundException {
       accountUserService.removeRoleFromUser(code, role);
    }
}
