package com.isma.school_ms_users.core.security;

import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.services.IServices.IAccountUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Qualifier("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IAccountUserService accountUserService;

    public UserDetailsServiceImpl(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    /***
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountUser accountUser = null;
        try {
            accountUser = accountUserService.getUserByUsername(username);
        } catch (NoDataFoundException e) {
            e.printStackTrace();
        }
        if( accountUser == null )
            throw new UsernameNotFoundException("invalid account");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        accountUser.getAccountRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(accountUser.getUsername(), accountUser.getPassword(),authorities);
    }
}
