package com.example.fieldpasserbe.member.utile;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * PasswordEncoder를 Bean으로 등록
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증 or 인가에 대한 설정
     */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // Post 방식으로 값을 전송할 때 token사용해야하는 보안 설정을 해제
                .authorizeRequests()
                .antMatchers("/","/api/auth/register","/api/auth/login","/api/:userid/unregister",
                             "/api/auth/logout","/api/:memberid","/api/:userid/userinfo","/api/:userid/userpwd","/emailConfirm").permitAll()
                .anyRequest().authenticated();
    }


}
