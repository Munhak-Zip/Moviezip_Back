package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface LoginMapper {
    User userLogin(@Param("userId") String userId, @Param("password") String password);
}

