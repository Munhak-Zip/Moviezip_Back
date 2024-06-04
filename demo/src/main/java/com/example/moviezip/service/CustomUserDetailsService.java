package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.domain.CustomUserDetails;
import com.example.moviezip.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//Authentication 객체를 만들어줌
//시큐리티 설정에서 loginProcessingUrl("/login");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 loadUserByUsername함수가 실행
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final MybatisUserDao mybatisUserDao;

    public CustomUserDetailsService(MybatisUserDao mybatisUserDao) {
        this.mybatisUserDao = mybatisUserDao;
    }

    @Override
    public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username:"+ username);
        User user = mybatisUserDao.findByUserId(username);
        System.out.println(user);

        if (user != null) {
            System.out.println("로그인한 사용자 " + user.getUserId() + user.getPassword());
            return new CustomUserDetails(user);
        } else{
            System.out.println("로그인 실패");
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
