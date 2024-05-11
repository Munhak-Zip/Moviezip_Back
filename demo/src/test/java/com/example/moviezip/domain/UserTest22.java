package com.example.moviezip.domain;

import com.example.moviezip.dao.ReviewDao;
import com.example.moviezip.dao.UserDao;
import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.service.ReviewImpl;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class) // JUnit 5에서 Spring 테스트와의 통합을 위해 사용
public class UserTest22 {

    @Autowired
    UserDao userDao;
    @Autowired
    private MybatisUserDao mybatisUserDao;

    @Test
    public void testUser() throws Exception{
        System.out.println("테스트");

        User user = userDao.getUserById(3L);

        TestCase.assertNotNull(user);
        System.out.println("유저 현재 비번:"+ user.getId()+ user.getPassword());

        mybatisUserDao.updatePassword(user.getId(),"newPass");

        User user1 = userDao.getUserById(3L);

        System.out.println("변경된 유저비번:"+ user1.getPassword());

        //비번 확인
        String check = "newPass";
        if (check.equals(user1.getPassword()))
            System.out.println("맞았다!");
        else
            System.out.println("틀렸다!");


    }
}
