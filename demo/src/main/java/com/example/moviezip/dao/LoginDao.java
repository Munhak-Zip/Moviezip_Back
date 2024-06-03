package com.example.moviezip.dao;

import com.example.moviezip.domain.User;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
    User userLogin(@Param("userId") String userId, @Param("password") String password);
    User getUserById(@Param("userId") String userId); //아이디로 회원 정보 가져오기

}
