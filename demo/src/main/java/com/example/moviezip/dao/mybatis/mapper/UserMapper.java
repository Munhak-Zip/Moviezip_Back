package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.User;
import com.example.moviezip.domain.Interest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.moviezip.domain.Interest;
import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(String id);

    //비밀번호 변경하기
    void updateUserPassword(@Param("userId") String userId, @Param("newPassword") String newPassword);

    //힌트 통해 사용자 찾기
    User findUser(@Param("hint") String hint, @Param("nickname") String nickname);

    //사용자 닉네임 수정
    void updateUserNickname(@Param("id") Long id, @Param("newNickname") String newPassword);

    //사용자 중복 닉네임 체크
    User existingUserNickname(@Param("nickname") String nickname);

    //사용자 삭제
    void deleteUser(@Param("id") Long id);

    //사용자 전체 리스트 출력
    List<User> findAllUser();

   void addInterest(@Param("id")Long id, @Param("genre")String genre);

   //해당 아이디를 가진 사용자의 취향이 있는지
    Boolean findInterest(@Param("id") Long id);

    void updateInterest(@Param("id")Long id, @Param("genre")String genre);

    //회원가입
    void addUser(User user);

    User findAllUserInterest(@Param("id") Long id);


    User findByUserId(@Param("userId") String userId);
    
    //입력된 유저 아이디로 사용자 고유번호 가져오기
    Long getIdByUsername(@Param("username") String username);

    String findUserIdByInfo(@Param("nickname") String nickname,@Param("hint") String hint);

    Boolean checkUserExistsById(@Param("userId") String userId);


    User getUserById2(Long id);

    Interest findInterest2(Long id);
}