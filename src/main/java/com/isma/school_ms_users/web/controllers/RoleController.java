package com.isma.school_ms_users.web.controllers;

import com.isma.school_ms_users.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_users.core.exceptions.FailedToSaveDataException;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.entities.AccountRole;
import com.isma.school_ms_users.services.IServices.IAccountRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path = "roles")
@Api(value = "AccountRole API", description = "Operations about AccountRole")
public class RoleController {
    private final IAccountRoleService accountRoleService;

    public RoleController(IAccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    /***
     *
     * @return
     * @throws NoDataFoundException
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<AccountRoleDTO> getAllRoles() throws NoDataFoundException {
        return accountRoleService.getAllRoles();
    }

    /***
     *
     * @param id
     * @return
     * @throws NoDataFoundException
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public AccountRoleDTO getRoleById(@PathVariable Long id) throws NoDataFoundException {
        return accountRoleService.getRoleById(id);
    }

    /***
     *
     * @param name
     * @return
     * @throws NoDataFoundException
     */
    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public AccountRoleDTO getRoleByName(@PathVariable String name) throws NoDataFoundException {
        return accountRoleService.getRoleByName(name);
    }

    /***
     *
     * @param role
     * @return
     * @throws DataAlreadyUsed
     * @throws FailedToSaveDataException
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    public AccountRoleDTO addRole(AccountRole role) throws DataAlreadyUsed, FailedToSaveDataException {
        return accountRoleService.addRole(role);
    }

    /***
     *
     * @param idRole
     * @param role
     * @return
     * @throws NoDataFoundException
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public AccountRoleDTO updateRole(@PathVariable long idRole, @RequestBody AccountRole role) throws NoDataFoundException {
        return accountRoleService.updateRole(idRole, role);
    }

    /***
     *
     * @param id
     * @return
     * @throws NoDataFoundException
     */
    @RequestMapping(path = "/{id}")
    public long deleteRole(@PathVariable Long id) throws NoDataFoundException {
        return accountRoleService.deleteRole(id);
    }
}
