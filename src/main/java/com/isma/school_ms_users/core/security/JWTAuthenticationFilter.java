package com.isma.school_ms_users.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.isma.school_ms_users.core.exceptions.NoDataFoundException;
import com.isma.school_ms_users.data.dto.AccountUserDTO;
import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.data.repositories.AccountRepository;
import com.isma.school_ms_users.services.IServices.IAccountUserService;
import com.isma.school_ms_users.services.ImplServices.IAccountUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /***
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            AccountUser accountUser = new ObjectMapper().readValue(request.getInputStream(), AccountUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountUser.getUsername(),
                            accountUser.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * Here we will generate the jwt to send to front end
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        authResult.getAuthorities().forEach(a -> {
            roles.add(a.getAuthority());
        });
        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityConstant.SECRET));
        System.out.println(SecurityConstant.TOKEN_PREFIX + jwt);

        response.addHeader(SecurityConstant.HEADER_STRING,jwt);
      /*  try {
            Gson gson = new Gson();
            AccountUserDTO accountUserDTO=accountUserService.findUserByUsername(user.getUsername());
            String userJSON = gson.toJson(accountUserDTO);
            response.getWriter().write(userJSON);
        } catch (NoDataFoundException e) {
            e.printStackTrace();
        }*/
    }
}
