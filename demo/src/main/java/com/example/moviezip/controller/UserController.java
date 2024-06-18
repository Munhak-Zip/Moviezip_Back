package com.example.moviezip.controller;


import com.example.moviezip.domain.User;
import com.example.moviezip.service.CustomUserDetailsService;
import com.example.moviezip.service.UserService;
import com.example.moviezip.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


//    @GetMapping("/")
//    public String home() {
//        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userVo = userService.getUserById(id);
//        userVo.setPassword(null);
//        model.addAttribute("user", userVo);
//        return "home";
//    }


//    @GetMapping("/login")
//    public String loginPage() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "loginPage";
//        return "redirect:/";
//    }

    //회원가입
//    @GetMapping("/signup")
//    public String signupPage() {  // 회원 가입 페이지
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "signup";
//        return "redirect:/";
//    }

//    @PostMapping("/signup")
//    public User signup(@RequestBody User user) { // 회원 가입
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return userService.signup(user);
//
//        return userService.signup(user);
//    }

//    @GetMapping("/update")
//    public String editPage(Model model) { // 회원 정보 수정 페이지
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserVo userVo = userService.getUserById(id);
//        model.addAttribute("user", userVo);
//        return "editPage";
//    }

//    @PostMapping("/update")
//    public String edit(UserVo userVo) { // 회원 정보 수정
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userVo.setId(id);
//        userService.edit(userVo);
//        return "redirect:/";
//    }

//    @PostMapping("/delete")
//    public String withdraw(HttpSession session) { // 회원 탈퇴
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (id != null) {
//            userService.withdraw(id);
//        }
//        SecurityContextHolder.clearContext();
//        return "redirect:/";
//    }

//    @Autowired
//    private UserServiceImpl userService;  //나중에는 생성자방식 주입으로 바꾸기

//    @GetMapping("/login")
//    public String loginP() {
//        return "login.html";
//    }
//
//    @GetMapping("/signup")
//    public String joinP() {
//        return "join";
//    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession httpSession; // HttpSession 주입

    @PostMapping("/joinProc")
    public ResponseEntity<String> joinProcess(@RequestBody User joinDTO) {
        System.out.println(joinDTO.getUserId());
        return userService.joinProcess(joinDTO);
    }
    //로그인한 사용자의 아이디 반환
    @GetMapping("/user-id")
    public String getCurrentUserId(@AuthenticationPrincipal UserDetails userDetails) {
       if(userDetails!=null){
        return userDetails.getUsername();
       }
       return null;
    }
    @PostMapping("/getUserIdByUsername")
    public User getUserIdByUsername(@RequestBody String userId) {
        System.out.println("사용자 아이디:"+userId);
        return userService.getUserById(userId);
    }

}