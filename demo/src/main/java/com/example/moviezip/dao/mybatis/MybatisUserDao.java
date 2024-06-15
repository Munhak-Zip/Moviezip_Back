package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.UserDao;
import com.example.moviezip.dao.mybatis.mapper.UserMapper;
import com.example.moviezip.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisUserDao implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) throws DataAccessException {
        return userMapper.getUserById(id);
    }

    @Override
    public void updatePassword(Long id, String newPassword) throws DataAccessException {
        System.out.println("MybatisUserDao - updatePassword - id: " + id + ", newPassword: " + newPassword);
        userMapper.updateUserPassword(id, newPassword);
    }

    @Override
    public User findUser(String hint,String nickname) throws DataAccessException{
        System.out.println("MybatisUserDao - findUser - hint: " + hint + ", nickname: " + nickname);
        return userMapper.findUser(hint, nickname);
    }

    @Override
    public void updateNickname(Long id, String newNickname) throws DataAccessException{
        System.out.println("MybatisUserDao - updateNickname - id: " + id + ", nickname: " + newNickname);
        userMapper.updateUserNickname(id, newNickname);
    }

    @Override
    public User existingNickname(String nickname) throws DataAccessException{
        System.out.println("MybatisUserDao - existingNickname - nickname: " + nickname);
        return userMapper.existingUserNickname(nickname);
    }

    @Override
    public void deleteUser(Long id) throws DataAccessException{
        System.out.println("MybatisUserDao - deleteUser - id: " + id);
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> findAllUser() throws DataAccessException{
        System.out.println("MybatisUserDao - findAllUser");
        return userMapper.findAllUser();
    }

//    @Override
//    public void addInterest(Interest interest) throws DataAccessException{
//        System.out.println("MybatisUserDao - addInterest");
//        userMapper.addInterest(interest);
//    }
//
//    @Override
//    public Interest findInterest(Long id) throws DataAccessException{
//        System.out.println("MybatisUserDao - findInterest");
//        return userMapper.findInterest(id);
//    }

    @Override
    public void updateInterest(Long id, String genre) throws DataAccessException{
        System.out.println("MybatisUserDao - updateInterest");
        userMapper.updateInterest(id, genre);
    }

    @Override
    public void addUser(User user) throws DataAccessException{
        System.out.println("MybatisUserDao - addUser" + user.getId() + user.getUserId() + user.getPassword() + user.getHint() + user.getNickname());
        userMapper.addUser(user);
    }

    @Override
    public User findAllUserInterest(Long id) throws DataAccessException{
        System.out.println("MybatisUserDao - findAllUserInterest");
        return userMapper.findAllUserInterest(id);
    }

    //로그인 할 때 해당 유저가 있는지 없는지 확인용
    @Override
    public User findByUserId(String userId) throws DataAccessException {
        return userMapper.findByUserId(userId);
    }

}