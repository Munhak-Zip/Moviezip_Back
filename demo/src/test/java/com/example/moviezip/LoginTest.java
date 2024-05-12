//package com.example.moviezip;
//
//
//import com.example.moviezip.dao.mybatis.MybatisLoginDao;
//import com.example.moviezip.domain.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static junit.framework.TestCase.assertNotNull;
//
////사용할 DB 선언
//@RunWith(SpringJUnit4ClassRunner.class) //Unit 프레임워크에게 사용할 테스트 러너(runner)를 지정하는 데 사용
//@ContextConfiguration(locations = "file:src/main/resources/dataAccessContext-mybatis.xml")  //테스트 실행에 필요한 애플리케이션 컨텍스트를 구성하는 방법을 지정
//
//public class LoginTest {
//    @Autowired
//    private MybatisLoginDao mybatisLoginDao;
//
//    @Test
//    public void testLogin() throws Exception{
//        // 사용자 조회
//        User user1 = mybatisLoginDao.userLogin("dayun", "newPass");
//        User user2 = mybatisLoginDao.userLogin("dayun", "newPasss");
//        // 사용자가 null인지 확인
//        assertNotNull(user1);
//        //assertNotNull(user2);
//
//
//        System.out.println("userId: " + user1.getUser_id() + " nickName: " + user1.getNickname());
//        //System.out.println("userId: " + user2.getUser_id() + " nickName: " + user2.getNickname());
//    }
//
//}
