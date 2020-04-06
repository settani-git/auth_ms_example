package com.isma.school_ms_users.data.converters.ImplConverters;


import com.isma.school_ms_users.core.converters.IConverters.IConverter;
import com.isma.school_ms_users.data.dto.AccountUserDTO;
import com.isma.school_ms_users.data.entities.AccountUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("AccountUserConverter")
public class ImplAccountUserConverter implements IConverter<AccountUser, AccountUserDTO> {

    private final ModelMapper modelMapper;

    public ImplAccountUserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountUserDTO convertToDto(AccountUser accountUser) {
        return new AccountUserDTO(accountUser);
    }

    @Override
    public List<AccountUserDTO> convertListToListDto(List<AccountUser> accountUsers) {
        List<AccountUserDTO> accountUserDtos= new ArrayList<>();
        for (AccountUser accountUser:accountUsers) {
            accountUserDtos.add(new AccountUserDTO(accountUser));
        }
        return accountUserDtos;
    }

    @Override
    public AccountUser convertToEntity(AccountUserDTO accountUserDto) {
        return modelMapper.map(accountUserDto,AccountUser.class);
    }

    @Override
    public List<AccountUser> convertListDtoToListEntity(List<AccountUserDTO> accountUserDtos) {
        List<AccountUser> accountUsers=new ArrayList<>();
        AccountUser accountUser;
        for (AccountUserDTO accountUserDto:accountUserDtos){
            accountUser=modelMapper.map(accountUserDto,AccountUser.class);
            accountUsers.add(accountUser);
        }
        return accountUsers;
    }
}
