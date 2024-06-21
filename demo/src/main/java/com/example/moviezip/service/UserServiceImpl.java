package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.dao.mybatis.mapper.UserMapper;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.moviezip.domain.Interest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MybatisUserDao mybatisUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
//    //회원가입
//    public ResponseEntity<String> joinProcess(User user){
//
//        //db에 이미 있다면
//
//       User newuser = new User();
//        newuser.setUser_id(user.getUser_id());
//        newuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //암호화
//        newuser.setNickname(user.getNickname());
//        newuser.setHint(user.getHint());
//        mybatisUserDao.addUser(newuser);
//        return null;
//    }
// 회원가입
    public ResponseEntity<String> joinProcess(User user) {
        // Validate user input
        if (user.getUserId() == null || user.getPassword() == null || user.getNickname() == null || user.getHint() == null) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // Encrypt the password
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        // Create a new User object with the provided details
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        newUser.setPassword(encryptedPassword); // 암호화
        newUser.setNickname(user.getNickname());
        newUser.setHint(user.getHint());

        // Insert the new user into the database
        mybatisUserDao.addUser(newUser);

        return ResponseEntity.ok("User registered successfully.");
    }

//비밀번호 변경
    @Override
    public void updateUserPassword(String userId, String newPassword) {
        mybatisUserDao.updateUserPassword(userId, newPassword);
    }

    @Override
    public void updateNickname(Long id, String newNickname) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<Movie> searchMoviesByTitle(String keyword) {
        return null;
    }

    @Override
    public void signup(User user) {

    }

    @Override
    public User getUserById(String id) {
        return mybatisUserDao.getUserById(id);
    }

    @Override
    public User findByUserId(String userId) {
        return mybatisUserDao.findByUserId(userId);
    }


    @Override
    public Long getIdByUsername(String username) {
        return mybatisUserDao.getIdByUsername(username);
    }

    @Override
    public void addInterest(Long id, String genre) {
        mybatisUserDao.addInterest(id, genre);

    }

    @Override
    public String findUserIdByInfo(String nickname, String hint) {
       return mybatisUserDao.findUserIdByInfo(nickname, hint);
    }

    @Override
    public Boolean checkUserExistsById(String userId) {
        return mybatisUserDao.checkUserExistsById(userId);
    }

    @Override
    public Boolean findInterest(Long id) {
        return mybatisUserDao.findInterest(id);
    }

    @Override
    public User getUserById2(Long id) {
        return mybatisUserDao.getUserById2(id);
    }

    @Override
    public Interest findInterest2(Long id) {
        return mybatisUserDao.findInterest2(id);
    }

}