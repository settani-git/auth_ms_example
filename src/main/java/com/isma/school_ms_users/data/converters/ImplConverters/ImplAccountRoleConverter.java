package com.isma.school_ms_users.data.converters.ImplConverters;

import com.isma.school_ms_users.core.converters.IConverters.IConverter;
import com.isma.school_ms_users.data.dto.AccountRoleDTO;
import com.isma.school_ms_users.data.entities.AccountRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("AccountRoleConverter")
public class ImplAccountRoleConverter implements IConverter<AccountRole, AccountRoleDTO> {

    private final ModelMapper modelMapper;

    public ImplAccountRoleConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountRoleDTO convertToDto(AccountRole accountRole) {
        return new AccountRoleDTO(accountRole);
    }

    @Override
    public List<AccountRoleDTO> convertListToListDto(List<AccountRole> accountRoles) {
        List<AccountRoleDTO> accountRoleDtos= new ArrayList<>();
        for (AccountRole accountRole:accountRoles) {
            accountRoleDtos.add(new AccountRoleDTO(accountRole));
        }
        return accountRoleDtos;
    }

    @Override
    public AccountRole convertToEntity(AccountRoleDTO accountRoleDto) {
        return modelMapper.map(accountRoleDto,AccountRole.class);
    }

    @Override
    public List<AccountRole> convertListDtoToListEntity(List<AccountRoleDTO> accountRoleDtos) {
        List<AccountRole> roles=new ArrayList<>();
        AccountRole role;
        for (AccountRoleDTO roleDto:accountRoleDtos){
            role=modelMapper.map(roleDto,AccountRole.class);
            roles.add(role);
        }
        return roles;
    }

}
