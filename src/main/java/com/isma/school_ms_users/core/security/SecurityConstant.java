package com.isma.school_ms_users.core.security;


public interface SecurityConstant {
    String SECRET = "schools@isma.net";
    long EXPIRATION_TIME = 864000000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
