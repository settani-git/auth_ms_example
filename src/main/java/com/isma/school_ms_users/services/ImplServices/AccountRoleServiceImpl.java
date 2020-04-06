package com.isma.school_ms_users.services.ImplServices;

import com.isma.school_ms_users.core.converters.IConverters.IConverter;
import com.isma.school_ms_users.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_users.core.exceptions.FailedToSaveDataException;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.entities.AccountRole;
import com.isma.school_ms_users.data.repositories.AccountRoleRepository;
import com.isma.school_ms_users.services.IServices.IAccountRoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@CrossOrigin("*")
public class AccountRoleServiceImpl implements IAccountRoleService {
    private final AccountRoleRepository accountRoleRepository;
    private final IConverter<AccountRole, AccountRoleDTO> accountRoleConverter;

    public AccountRoleServiceImpl(AccountRoleRepository accountRoleRepository, @Qualifier("AccountRoleConverter") IConverter<AccountRole, AccountRoleDTO> accountRoleConverter) {
        this.accountRoleRepository = accountRoleRepository;
        this.accountRoleConverter = accountRoleConverter;
    }

    @Override
    public AccountRoleDTO getRoleByName(String name) throws NoDataFoundException {
        AccountRole accountRole=accountRoleRepository.findAccountRoleByName(name);
        if(accountRole==null){
            throw new NoDataFoundException("AccountRole identified"+name+" by not exist");
        }
        return accountRoleConverter.convertToDto(accountRole);
    }

    @Override
    public AccountRoleDTO getRoleById(Long idRole) throws NoDataFoundException {
        AccountRole accountRole=accountRoleRepository.getOne(idRole);
        if(accountRole==null){
            throw new NoDataFoundException("AccountRole identified"+idRole+" by not exist");
        }
        return accountRoleConverter.convertToDto(accountRole);
    }

    @Override
    public List<AccountRoleDTO> getAllRoles() throws NoDataFoundException {
        List<AccountRole> roles=accountRoleRepository.findAll();
        if (roles==null)
            throw new NoDataFoundException("No Role was not found");
        return accountRoleConverter.convertListToListDto(roles);
    }

    @Override
    public AccountRoleDTO addRole(AccountRole role) throws DataAlreadyUsed, FailedToSaveDataException {
        AccountRole accountRole=accountRoleRepository.save(role);
        if(accountRole==null)
            throw new FailedToSaveDataException("AccountRole does not saved");
        return accountRoleConverter.convertToDto(role);
    }

    @Override
    public AccountRoleDTO updateRole(long idRole,AccountRole role) throws NoDataFoundException {
        AccountRole accRole=accountRoleRepository.getOne(idRole);
        if(accRole==null)
            throw new NoDataFoundException("No AccountRole was identified by "+idRole);
        role.setId(idRole);
        accRole=accountRoleRepository.save(role);
        return accountRoleConverter.convertToDto(accRole);
    }

    @Override
    public long deleteRole(Long idRole) throws NoDataFoundException {
        if(accountRoleRepository.getOne(idRole)==null)
            throw new NoDataFoundException("No Role was identified by "+idRole);
        try {
            accountRoleRepository.deleteById(idRole);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return idRole;
    }
}
