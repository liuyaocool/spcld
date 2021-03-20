package com.liuyao.spcld.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserAuth implements AuthenticationProvider {

    @Autowired
    MyUserDeatilsService userDeatilsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        // 限制重试次数

        System.out.println("校验账号");

        // 表单提交上来的
        String username = authentication.getPrincipal().toString();
        String pwd = authentication.getCredentials().toString();

        //校验
        UserDetails userDetails = userDeatilsService.loadUserByUsername(username);
        if (new BCryptPasswordEncoder().matches(pwd, userDetails.getPassword())) {
            System.out.println("密码通过 ");
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username, userDetails.getPassword(), userDetails.getAuthorities());
            // jwt 签名


            return auth;
        }
        throw new BadCredentialsException("用户明或密码错误。");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
