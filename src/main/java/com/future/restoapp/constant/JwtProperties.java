package com.future.restoapp.constant;

public interface JwtProperties {

    String SECRET = "R35st0_4Pp_15_s0_gO0D!";

//    Long EXPIRATION_TIME = 864_000_000; // 10 days
    Long EXPIRATION_TIME = 864_000_000 * 1000L; // 10 * 1000 days

    String TOKEN_PREFIX = "Bearer ";

    String HEADER_STRING = "Authorization";

}
