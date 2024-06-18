package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    User userLogin(@Param("userId") String userId, @Param("password") String password);

    User getUserById(@Param("userId") String userId); //아이디로 회원 정보 가져오기

}

