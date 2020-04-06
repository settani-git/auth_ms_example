package com.isma.school_ms_users.core.setup;

import com.isma.school_ms_users.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_users.core.exceptions.FailedToSaveDataException;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.entities.AccountRole;
import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.data.entities.Address;
import com.isma.school_ms_users.data.entities.Contact;
import com.isma.school_ms_users.services.IServices.IAccountRoleService;
import com.isma.school_ms_users.services.IServices.IAccountUserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Initializer {
    private static IAccountUserService iAccountUserService;
    private static IAccountRoleService iAccountRoleService;

    public Initializer(IAccountUserService iAccountUserService, IAccountRoleService iAccountRoleService) {
        Initializer.iAccountUserService = iAccountUserService;
        Initializer.iAccountRoleService = iAccountRoleService;
    }

    public static void initData() throws DataAlreadyUsed, FailedToSaveDataException, NoDataFoundException {
        AccountRole role1 = new AccountRole("SUPER-ADMIN");
        AccountRole role2 = new AccountRole("ADMIN");
        AccountRole role3 = new AccountRole("AGENT");
        iAccountRoleService.addRole(role1);
        iAccountRoleService.addRole(role2);
        iAccountRoleService.addRole(role3);

        List<AccountRole> roles1 = new ArrayList<>();
        roles1.add(role1);
        List<AccountRole> roles2 = new ArrayList<>();
        roles2.add(role2);
        List<AccountRole> roles3 = new ArrayList<>();
        roles3.add(role3);

        Address address = new Address("hello", "hello", "hello", "hello");
        Contact contact1 = new Contact("hello1", "hello11");
        Contact contact2 = new Contact("hello2", "hello22");
        Contact contact3 = new Contact("hello3", "hello33");
        AccountUser acc1 = new AccountUser("EE2019", "root", "lhou", "ouarhou",
                "root", new Date(), address, contact1);
        acc1.setAccountRoles(roles1);
        AccountUser acc2 = new AccountUser("EE2020", "admin", "abdo", "settani",
                "admin", new Date(), address, contact2);
        acc2.setAccountRoles(roles2);
        AccountUser acc3 = new AccountUser("EE2021", "agent", "youssef", "farid",
                "agent", new Date(), address, contact3);
        acc2.setAccountRoles(roles3);
        iAccountUserService.saveUser(acc1, "root");
        iAccountUserService.saveUser(acc2, "admin");
        iAccountUserService.saveUser(acc3, "agent");
    }
}
